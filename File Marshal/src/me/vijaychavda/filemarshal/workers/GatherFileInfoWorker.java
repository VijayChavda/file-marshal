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
    protected ArrayList<FileInfo> doInBackground() throws Exception {
        ArrayList<FileInfo> fileInfos = new ArrayList<>();

        int progress = 0;
        setProgress(0);
        publish("Running task: Gathering files' information.");

        for (File inputFile : inputFiles) {
            String path = inputFile.getPath();
            try {
                publish("\tAnalyzing: " + path);
                FileInfo info = FileInfo.get(path);
                fileInfos.add(info);
                //publish("\tHash = " + info.getHash());
            } catch (IOException ex) {
                publish("\tFailed! Error was logged.");
                Logger.getLogger(GatherFileInfoWorker.class.getName()).log(Level.SEVERE, null, ex);
            }

            setProgress((int) Math.round(100 * (double) progress / inputFiles.size()));
            progress++;
        }

        publish("Done.\n");
        setProgress(100);

        return fileInfos;
    }
}
