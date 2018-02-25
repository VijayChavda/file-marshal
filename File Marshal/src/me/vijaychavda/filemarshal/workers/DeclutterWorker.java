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
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import me.vijaychavda.filemarshal.AppContext;
import me.vijaychavda.filemarshal.settings.DeclutterSettings;

public class DeclutterWorker extends SwingWorker<Void, String> {

    private static final String Ungrouped = "Ungrouped";
    private static final String NoExtension = "No extension";
    private static final String SmallGroup = "Others";

    private final ArrayList<File> allFiles;

    public DeclutterWorker(ArrayList<File> allFiles) {
        this.allFiles = allFiles;
    }

    @Override
    protected Void doInBackground() {
        try {
            DeclutterSettings settings = AppContext.Current.getDeclutterSettings();

            int progress = 0;
            setProgress(0);
            publish("Running task: Classify files");

            GroupFormatString format = new GroupFormatString(
                settings.isGroupByCustom()
                ? settings.getGroupFormatString()
                : GroupFormatString.ValueOfGroupByType
            );
            if (!format.valid) {
                JOptionPane.showMessageDialog(null, format.errorMsg, "File marshal", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            int filesInMap = 0;
            HashMap<String, HashSet<File>> groupMap = new HashMap<>();
            String group;
            for (File file : allFiles) {
                group = format.getGroupOf(extension(file));

                if (groupMap.containsKey(group) == false) {
                    publish("\tCreating group: [" + group + "]");
                    groupMap.put(group, new HashSet<>());
                }

                if (file.getCanonicalPath().equals(file.getAbsolutePath())) {
                    groupMap.get(group).add(file);
                    filesInMap++;
                } else
                    publish("\t\tIgnoring '" + file.getName() + "' as it is a shortcut.");

                setProgress((int) Math.round(100 * (double) progress / allFiles.size()));
                progress++;
            }
            publish("Done!\n");

            Thread.sleep(200);
            setProgress(progress = 0);
            publish("Running task: Move files to better place");

            File outputDir = new File(settings.getOutputPath(), "Decluttered");
            outputDir.mkdir();
            if (!outputDir.exists()) {
                publish("\tError! Failed to create directory: " + outputDir);
                setProgress(0);
                return null;
            }
            for (String extension : groupMap.keySet()) {
                boolean bigEnoughGroup = groupMap.get(extension).size() >= settings.getMinimumGroupCardinality();
                File groupDir = new File(outputDir, bigEnoughGroup ? extension : SmallGroup);
                groupDir.mkdirs();
                if (!groupDir.exists()) {
                    publish("\tError! Failed to create directory: " + outputDir);
                    setProgress(0);
                    return null;
                }

                for (File sourceFile : groupMap.get(extension)) {
                    File destFile = new File(groupDir, sourceFile.toPath().getFileName().toString());
                    publish(MessageFormat.format("\tMoving: {0} to {1}.", sourceFile.getAbsolutePath(), destFile.getAbsolutePath()));
                    copyFileUsingStream(sourceFile, destFile);

                    setProgress((int) Math.round(100 * (double) progress / filesInMap));
                    progress++;
                }
            }

            setProgress(100);
            publish("Done.\n");
        } catch (IOException | InterruptedException e) {
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

    public static class GroupFormatString {

        private final boolean valid;

        private final String value;
        private String errorMsg;

        private final HashMap<String, HashSet<String>> map;

        private static final String ValueOfGroupByType
            = "Pictures: ai bmp gif ico jpeg jpg png ps psd svg tif tiff\n"
            + "Documents: doc docx odt pdf rtf txt wks wps wpd ods xlr xls xlsx key odp pps ppt pptx\n"
            + "Videos: 3g2 3gp avi flv m4v mkv mov mp4 mpg mpeg rm swf vob wmv\n"
            + "Music: aif cda mid midi mp3 mpa ogg wav wma wpl";

        private GroupFormatString(String value) {
            this.map = new HashMap<>();
            this.value = value;
            this.valid = check(value) == null;
            init();
        }

        public static String check(String value) {
            if (value.length() == 0) {
                return "The format string is empty.";
            }

            int line = 0;
            String[] groupDefinations = value.split("\n");
            for (String gd : groupDefinations) {
                line++;

                int colonIndex = gd.indexOf(':');
                if (colonIndex == -1) {
                    return "Invalid format. Format for each line is:\n[Name of group]: [list of space seperated extensions that go in this group.]";
                }
                if (gd.charAt(colonIndex) + 1 == ' ') {
                    return "Invalid format. You need to put a space after : at line " + line;
                }

                String groupName = gd.substring(0, colonIndex);
                String[] extensions = gd.substring(colonIndex + 1, gd.length()).split(" ");

                if (groupName == null || groupName.isEmpty()) {
                    return "Group name cannot be empty at line " + line;
                }
                if (extensions.length <= 1) {
                    return "Could not find list of space seperated extensions after group name at line " + line;
                }
            }

            return null;
        }

        private void init() {
            String[] groupDefinations = value.split("\n");
            for (String gd : groupDefinations) {
                int colonIndex = gd.indexOf(':');
                String groupName = gd.substring(0, colonIndex);
                String[] extensions = gd.substring(colonIndex + 1, gd.length()).split(" ");

                for (int i = 1; i < extensions.length; i++) {
                    if (map.containsKey(groupName) == false)
                        map.put(groupName, new HashSet<>());
                    map.get(groupName).add(extensions[i]);
                }
            }
        }

        private String getGroupOf(String extension) {
            DeclutterSettings settings = AppContext.Current.getDeclutterSettings();

            if (settings.isGroupByExtension())
                return extension;

            if (settings.isGroupByType())
                return getGroupOfHelper(extension);

            if (settings.isGroupByBoth())
                return getGroupOfHelper(extension).concat(File.separator).concat(extension);

            return getGroupOfHelper(extension);
        }

        private String getGroupOfHelper(String extension) {
            for (String group : map.keySet()) {
                if (map.get(group).contains(extension))
                    return group;
            }
            return DeclutterWorker.Ungrouped;
        }
    }
}
