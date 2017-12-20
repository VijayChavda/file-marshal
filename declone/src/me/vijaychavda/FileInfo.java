package me.vijaychavda;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.Adler32;

/**
 *
 * @author Vijay
 */
public class FileInfo {

    private String path;
    private long size;
    private long hash;

    private FileInfo() {
        path = "";
        size = -1;
        hash = -1;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public long getHash() {
        return hash;
    }

    public static FileInfo init(String path) throws IOException {
        File file = new File(path);

        byte data[] = new byte[(int) file.length()];

        try (FileInputStream stream = new FileInputStream(file)) {
            //TODO:
            while (stream.read(data) != -1);
        }

        Adler32 adler = new Adler32();
        adler.update(data);

        FileInfo info = new FileInfo();
        info.path = path;
        info.hash = adler.getValue();
        info.size = file.length();

        return info;
    }
}
