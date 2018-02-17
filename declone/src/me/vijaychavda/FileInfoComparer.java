package me.vijaychavda;

import me.vijaychavda.settings.DecloneSettings;
import info.debatty.java.stringsimilarity.JaroWinkler;
import java.util.Arrays;
import java.util.HashSet;

public class FileInfoComparer {

    public static boolean areSame(FileInfo f1, FileInfo f2) {
        DecloneSettings settings = AppContext.Current.getDecloneSettings();

        if (settings.isUsingSize())
            if (!sizeSimilar(f1.getSize(), f2.getSize()))
                return false;

        if (settings.isUsingNames())
            if (!nameSimilar(f1.getName(), f2.getName()))
                return false;

        if (settings.isUsingContent())
            if (!contentSimilar(f1.getHash(), f2.getHash()))
                return false;

        return true;
    }

    private static boolean nameSimilar(String name1, String name2) {
        DecloneSettings settings = AppContext.Current.getDecloneSettings();
        final float delta = 0.90f;

        if (settings.isNameSimilar())
            return new JaroWinkler().similarity(name1, name2) > delta;

        if (settings.isNameExactlySame())
            return name1.equals(name2);

        String wordsIn1[] = name1.split(" ");
        HashSet<String> wordsIn2 = new HashSet<>(Arrays.asList(name2.split(" ")));
        int commonWords = 0;
        for (String word1 : wordsIn1) {
            if (settings.isNameCommonWords()) {
                if (wordsIn2.contains(word1))
                    commonWords++;
            } else {
                for (String word2 : wordsIn2) {
                    double x = new JaroWinkler().similarity(word1, word2);
                    if (x > delta) {
                        commonWords++;
                        break;
                    }
                }
            }
        }

        int longerStringWords = wordsIn1.length > wordsIn2.size() ? wordsIn1.length : wordsIn2.size();

        return commonWords >= longerStringWords - Math.ceil((longerStringWords - 1) / 2.0);
    }

    private static boolean sizeSimilar(long size1, long size2) {
        DecloneSettings settings = AppContext.Current.getDecloneSettings();

        if (settings.isSizeExactlySame())
            return size1 == size2;

        final float delta = settings.isSizeNoHugeDifference() ? 0.40f : 0.05f;

        double difference = Math.abs(size1 - size2);
        double average = (size1 + size2) / 2.0D;

        return difference / average <= delta;
    }

    private static boolean contentSimilar(long hash1, long hash2) {
        return hash1 == hash2;
    }
}
