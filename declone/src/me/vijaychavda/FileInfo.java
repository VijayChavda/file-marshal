package me.vijaychavda;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Adler32;
import org.apache.commons.lang3.SerializationUtils;

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

    public void setName(String name) {
        this.name = name;
    }

    public static FileInfo init(String path) throws IOException {
        File file = new File(path);

        if (!AppContext.getCompareSettings().isUsingContent()) {
            FileInfo info = new FileInfo();
            info.name = file.getName();
            info.size = file.length();
            info.path = path;
            return info;
        }

        Adler32 adler = new Adler32();

        int partScanLength = 1048576;
        long totalLength = file.length();
        double scanPercent = getContentVolumePercent();
        long scanLength = Math.round(totalLength * scanPercent / 100);

        if (scanLength < partScanLength) {
            byte data[] = new byte[(int) totalLength];

            try (FileInputStream stream = new FileInputStream(file)) {
                while (stream.read(data) != -1);
            }

            adler.update(data);

            FileInfo info = new FileInfo();
            info.name = file.getName();
            info.path = path;
            info.hash = adler.getValue();
            info.size = file.length();

            return info;
        }

        long partitions = scanLength / partScanLength;
        long partLength = totalLength / partitions;
        long skipLength = partLength - partScanLength;

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

        return FileInfoComparer.areSame(this, (FileInfo) obj);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(hash);
    }

    private static double getContentVolumePercent() {
        CompareSettings settings = AppContext.getCompareSettings();

        if (settings.isContent2p())
            return 2;

        if (settings.isContent10p())
            return 5;

        if (settings.isContent20p())
            return 20;

        if (settings.isContent50p())
            return 50;

        if (settings.isContent100p())
            return 100;

        Logger.getLogger(FileInfo.class.getName()).log(Level.SEVERE, "Invalid content volume percent state detected.");
        return 0;
    }
}
