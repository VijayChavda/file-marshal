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
            //TODO: Generate hash according to settings
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

    @Override
    public String toString() {
        return "[" + hash + "] " + size + " " + path;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!obj.getClass().equals(FileInfo.class))
            return false;

        FileInfo other = (FileInfo) obj;

        //TODO: Compare FileInfos according to settings
        return other.size == size;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(hash);
    }

}
