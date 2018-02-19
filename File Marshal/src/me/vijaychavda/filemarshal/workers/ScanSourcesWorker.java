package me.vijaychavda.filemarshal.workers;

import java.io.File;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import me.vijaychavda.filemarshal.AppContext;
import me.vijaychavda.filemarshal.settings.SelectionSettings;

public class ScanSourcesWorker extends SwingWorker<ArrayList<File>, String> {

    public ScanSourcesWorker() {
    }

    @Override
    protected ArrayList<File> doInBackground() throws Exception {
        ArrayList<File> inputFiles = new ArrayList<>();
        ArrayList<File> sources = AppContext.Current.getSources();

        int progress = 0;
        publish("Running task: Scanning sources.");
        setProgress(0);

        for (File directory : sources) {
            publish("Scanning: " + directory.getPath());
            getFilesRecursively(inputFiles, directory);

            setProgress((int) Math.round(100 * (double) progress / sources.size()));
            progress++;
        }

        publish("Done.\n");
        setProgress(100);

        return inputFiles;
    }

    private void getFilesRecursively(ArrayList<File> files, File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                getFilesRecursively(files, file);
                continue;
            }

            if (trySelectFile(file)) {
                files.add(file);
                publish("\tSelected: " + file.getPath());
            }
        }
    }

    private boolean trySelectFile(File file) {
        SelectionSettings settings = AppContext.Current.getSelectionSettings();

        String name = file.getName();
        long size = file.length();

        if (size < settings.getSizeLowerLimit() || size > settings.getSizeUpperLimit())
            return false;

        if (settings.getExtensionCS().equals(".*"))
            return true;

        int doti = name.lastIndexOf('.');
        if (doti == -1)
            return true;

        String ext = name.substring(doti, name.length());

        String[] extensions = settings.getExtensionCS().split(" ");
        for (String extension : extensions) {
            if (extension.equals(ext))
                return true;
        }
        return false;
    }

}
