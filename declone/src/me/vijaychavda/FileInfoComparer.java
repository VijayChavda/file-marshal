package me.vijaychavda;

import info.debatty.java.stringsimilarity.JaroWinkler;

/**
 *
 * @author Vijay
 */
public class FileInfoComparer {

    private final SearchSettings searchSettings;

    public FileInfoComparer(SearchSettings searchSettings) {
        this.searchSettings = searchSettings;
    }

    public boolean areSame(FileInfo f1, FileInfo f2) {
        return false;
    }

    private boolean nameSimilar(String name1, String name2, float delta) {
        return 1 - new JaroWinkler().similarity(name1, name2) <= delta;
    }

    private boolean sizeSimilar(long size1, long size2, float delta) {
        double difference = Math.abs(size1 - size2);
        double average = (size1 + size2) / 2.0D;

        return difference / average <= delta;
    }

    private boolean contentSimilar(long hash1, long hash2) {
        return hash1 == hash2;
    }
}
