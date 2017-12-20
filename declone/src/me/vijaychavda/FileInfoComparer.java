package me.vijaychavda;

import info.debatty.java.stringsimilarity.JaroWinkler;

/**
 *
 * @author Vijay
 */
public class FileInfoComparer {

    public boolean areSame(FileInfo f1, FileInfo f2) {
        boolean same = true;

        SearchSettings settings = AppContext.getSettings();

        if (settings.isName())
            same = nameSimilar(f1.getName(), f2.getName(), settings.getNameDelta());

        if (same && settings.isSize())
            same = sizeSimilar(f1.getSize(), f2.getSize(), settings.getSizeDelta());

        if (same && settings.isContent())
            same = contentSimilar(f1.getHash(), f2.getHash());

        return same;
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
