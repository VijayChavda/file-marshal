package me.vijaychavda.filemarshal.workers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import me.vijaychavda.filemarshal.AppContext;
import me.vijaychavda.filemarshal.settings.DeclutterSettings;

public class DeclutterWorker extends SwingWorker<Void, String> {

    private ArrayList<File> allFiles;

    public DeclutterWorker(ArrayList<File> allFiles) {
        this.allFiles = allFiles;
    }

    @Override
    protected Void doInBackground() {
        try {
            int progress = 0;
            setProgress(0);
            publish("Running task: Declutter!");

            DeclutterSettings settings = AppContext.Current.getDeclutterSettings();
            HashMap<String, HashSet<File>> map = new HashMap<>();
            String ex;
            for (File file : allFiles) {
                ex = extension(file);
                if (map.containsKey(ex) == false) {
                    publish("\tIdentified type: [" + ex + "]");
                    map.put(ex, new HashSet<>());
                }

                if (file.getCanonicalPath().equals(file.getAbsolutePath()))
                    map.get(ex).add(file);
                else
                    publish("\tIgnoring '" + file.getName() + "' as it is a shortcut.");

                setProgress((int) Math.round(100 * (double) progress / allFiles.size()));
                progress++;
            }
            publish("\tDone.");

            File outputPath = new File(settings.getOutputPath());
            File outputDir = new File(outputPath, "Decluttered");

            outputDir.mkdir();
            if (!outputPath.exists()) {
                publish("\tError! Failed to create directory: " + outputDir);
                setProgress(0);
                return null;
            }
            for (String extension : map.keySet()) {
                boolean bigEnoughGroup = map.get(extension).size() >= settings.getMinimumGroupCardinality();
                File groupFolder = new File(outputDir, bigEnoughGroup ? extension : "Others");
                groupFolder.mkdirs();
                if (!groupFolder.exists()) {
                    publish("\tError! Failed to create directory: " + outputDir);
                    setProgress(0);
                    return null;
                }

                for (File sourceFile : map.get(extension)) {
                    File destFile = new File(groupFolder, sourceFile.toPath().getFileName().toString());
                    copyFileUsingStream(sourceFile, destFile);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(DeclutterWorker.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private String extension(File file) {
        String name = file.getName();
        int i = name.lastIndexOf('.');
        return i > 0 ? name.substring(i + 1) : "No Extension";
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source);
            OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}
