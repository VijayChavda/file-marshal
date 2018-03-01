package me.vijaychavda.filemarshal.workers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import me.vijaychavda.filemarshal.FileInfo;

public class GatherFileInfoWorker extends SwingWorker<ArrayList<FileInfo>, String> {

    private final ArrayList<File> inputFiles;

    public GatherFileInfoWorker(ArrayList<File> inputFiles) {
        this.inputFiles = inputFiles;
    }

    @Override
    protected ArrayList<FileInfo> doInBackground() {
        try {
            ArrayList<FileInfo> fileInfos = new ArrayList<>();

            int progress = 0;
            setProgress(0);
            publish("Running task: Gathering files' information.");

            for (File inputFile : inputFiles) {
                String path = inputFile.getPath();

                publish("\tAnalyzing: " + path);
                fileInfos.add(FileInfo.get(path));
                //publish("\tHash = " + info.getHash());

                setProgress((int) Math.round(100 * (double) progress / inputFiles.size()));
                progress++;
            }

            publish("Done.\n");
            setProgress(100);

            return fileInfos;
        } catch (IOException ex) {
            publish("\tFailed! Error was logged.");
            Logger.getLogger(GatherFileInfoWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
