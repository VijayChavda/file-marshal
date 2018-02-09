package me.vijaychavda.util;

import java.util.*;

public class MapUtil {

    public static <K, V extends Comparable<? super V>> Map<K, V>
        sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (Map.Entry<K, V> o1, Map.Entry<K, V> o2) -> (o1.getValue()).compareTo(o2.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
