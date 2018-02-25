package me.vijaychavda.filemarshal.workers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.SwingWorker;
import me.vijaychavda.filemarshal.AppContext;
import me.vijaychavda.filemarshal.FileInfo;
import me.vijaychavda.filemarshal.settings.DecloneSettings;

public class ProcessFileInfosWorker extends SwingWorker<Void, String> {

    private final ArrayList<FileInfo> fileInfos;

    public ProcessFileInfosWorker(ArrayList<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    @Override
    protected Void doInBackground() throws Exception {
        int progress = 0;
        setProgress(0);
        publish("Running task: Processing gathered information.");

        DecloneSettings settings = AppContext.Current.getDecloneSettings();
        if (settings.isUsingNames() == false || settings.isNameExactlySame() || settings.isNameSimilar()) {
            publish("\tNo needed to process information based on current settings.");
            return null;
        }

        publish("\tAggregating information.");

        HashMap<String, Integer> wordFrequencyMap = new HashMap<>();
        for (FileInfo fileInfo : fileInfos) {
            String newName = fileInfo.getName().trim().toLowerCase().replaceAll("[^A-Za-z0-9]", " ");
            fileInfo.setName(newName);
            List<String> words = Arrays.asList(newName.split(" "));
            for (String word : words) {
                if (wordFrequencyMap.containsKey(word) == false) {
                    wordFrequencyMap.put(word, 1);
                }
                wordFrequencyMap.put(word, wordFrequencyMap.get(word) + 1);
            }

            setProgress(((int) Math.round(100 * (double) (progress / fileInfos.size()))) / 2);
            progress++;
        }

        HashSet<String> commonWords = new HashSet<>();
        for (String word : wordFrequencyMap.keySet()) {
            if (wordFrequencyMap.get(word) > 8) {
                commonWords.add(word);
            }
        }

        setProgress(50);
        progress = 0;

        publish("\tProcessing files");
        for (FileInfo fileInfo : fileInfos) {
            publish("\tProcessing: " + fileInfo.getPath());
            StringBuilder nameBuilder = new StringBuilder();
            String words[] = fileInfo.getName().split(" ");
            for (String word : words) {
                if (commonWords.contains(word) == false) {
                    nameBuilder.append(word).append(" ");
                }
            }
            String newName = nameBuilder.toString();
            fileInfo.setName(newName);

            setProgress(50 + ((int) Math.round(100 * (double) (progress / fileInfos.size()))) / 2);
            progress++;
        }

        publish("Done.\n");
        setProgress(100);

        return null;
    }
}
