package me.vijaychavda;

import info.debatty.java.stringsimilarity.JaroWinkler;

public class FileInfoComparer {

    public static boolean areSame(FileInfo f1, FileInfo f2) {
        CompareSettings settings = AppContext.getCompareSettings();

        if (settings.isUsingContent()) {
            return contentSimilar(f1.getHash(), f2.getHash());
        }

        boolean same = true;
        if (settings.isUsingNames())
            same = nameSimilar(f1.getName(), f2.getName(), settings.getNameDelta());

        if (settings.isUsingSize() && same)
            same = sizeSimilar(f1.getSize(), f2.getSize(), settings.getSizeDelta());

        return same;
    }

    private static boolean nameSimilar(String name1, String name2, float delta) {
        return 1 - new JaroWinkler().similarity(name1, name2) <= delta;
    }

    private static boolean sizeSimilar(long size1, long size2, float delta) {
        double difference = Math.abs(size1 - size2);
        double average = (size1 + size2) / 2.0D;

        return difference / average <= delta;
    }

    private static boolean contentSimilar(long hash1, long hash2) {
        return hash1 == hash2;
    }
}
