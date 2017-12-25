package me.vijaychavda;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.zip.Adler32;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author Vijay
 */
public class FileInfo {

    private String path;
    private String name;
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

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public long getHash() {
        return hash;
    }

    public static FileInfo init(String path) throws IOException {
        File file = new File(path);
        Adler32 adler = new Adler32();

        int partScanLength = 1048576;
        long totalLength = file.length();

        if (totalLength < partScanLength) {
            byte data[] = new byte[(int) totalLength];

            try (FileInputStream stream = new FileInputStream(file)) {
                while (stream.read(data) != -1);
            }

            adler.update(data);

            FileInfo info = new FileInfo();
            info.path = path;
            info.hash = adler.getValue();
            info.size = file.length();

            return info;
        }

        double scanPercent = AppContext.getSettings().getContentVolumePercent();
        long scanLength = Math.round(totalLength * scanPercent);
        long partitions = scanLength / partScanLength;
        long partLength = totalLength / partitions;
        long skipLength = partLength - partScanLength;
        if (scanPercent != 1d)
            System.out.println("\t" + (MessageFormat.format(
                "Skipping {0} and scanning {1} bytes. In total, scanning {2} out of {3} bytes ({4}%).",
                skipLength, partScanLength, scanLength, totalLength, scanPercent * 100))
            );

        HashSet<Long> partialHashes = new HashSet<>();
        try (FileInputStream stream = new FileInputStream(file)) {
            byte data[] = new byte[partScanLength];

            long scanned = 0;
            while (scanned < scanLength) {
                if (stream.read(data) == -1)
                    break;

                adler.update(data);
                partialHashes.add(adler.getValue());

                if (scanPercent != 1d)
                    stream.skip(skipLength);

                scanned += partScanLength;
            }
        }

        byte[] hashBytes = SerializationUtils.serialize(partialHashes);
        adler.update(hashBytes);

        FileInfo info = new FileInfo();
        info.name = file.getName();
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

        return new FileInfoComparer().areSame(this, (FileInfo) obj);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(hash);
    }

}
