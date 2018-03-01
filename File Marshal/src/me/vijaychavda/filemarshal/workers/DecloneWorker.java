package me.vijaychavda.filemarshal.workers;

import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.SwingWorker;
import me.vijaychavda.filemarshal.FileInfo;

public class DecloneWorker extends SwingWorker<HashSet<ArrayList<FileInfo>>, String> {

    private final ArrayList<FileInfo> fileInfos;

    public DecloneWorker(ArrayList<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    @Override
    protected HashSet<ArrayList<FileInfo>> doInBackground() {
        int progress = 0;
        setProgress(0);
        publish("Running task: Declone!");

        HashSet<ArrayList<FileInfo>> duplicates = new HashSet<>();
        outer:
        for (FileInfo fileInfo : fileInfos) {
            publish("\tChecking: " + fileInfo.getPath());
            for (ArrayList<FileInfo> set : duplicates) {
                if (fileInfo.equals(set.get(0))) {
                    set.add(fileInfo);
                    continue outer;
                }
            }

            ArrayList<FileInfo> newSet = new ArrayList<>();
            newSet.add(fileInfo);
            duplicates.add(newSet);

            setProgress((int) Math.round(100 * (double) progress / fileInfos.size()));
            progress++;
        }

        publish("Done.\n");
        setProgress(100);

        return duplicates;
    }

}
