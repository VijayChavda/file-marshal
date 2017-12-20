package me.vijaychavda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Vijay
 */
public class FileInfo {

    private final String path;
    private long size;
    private int hash;

    public FileInfo(String path) {
        this.path = path;
        this.size = -1;
        this.hash = -1;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public int getHash() {
        return hash;
    }

    public static FileInfo init(String path) throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = reader.read()) != -1) {
            sb.append(ch);
        }

        FileInfo info = new FileInfo(path);
        info.hash = sb.toString().hashCode();
        info.size = file.length();

        return info;
    }
}
