package me.vijaychavda;

import info.debatty.java.stringsimilarity.JaroWinkler;
import java.util.Arrays;
import java.util.HashSet;

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
        //return 1 - new JaroWinkler().similarity(name1, name2) <= delta;

        int similarWords = 0;
        String wordsIn1[] = name1.split(" ");
        HashSet<String> wordsIn2 = new HashSet<>(Arrays.asList(name2.split(" ")));
        for (String word1 : wordsIn1) {
            for (String word2 : wordsIn2) {
                double j = new JaroWinkler().similarity(word1, word2);
                if (1 - j <= delta) {
                    similarWords++;
                    break;
                }
            }
        }

        int largerLength = wordsIn1.length > wordsIn2.size() ? wordsIn1.length : wordsIn2.size();

        if (largerLength == 1) {
            return similarWords == largerLength;
        }
        if (largerLength <= 5) {
            return similarWords > largerLength - 2;
        }
        if (largerLength <= 8) {
            return similarWords > largerLength - 3;
        }

        return similarWords >= 8;
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
