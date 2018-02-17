package me.vijaychavda;

import me.vijaychavda.settings.CompareSettings;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.zip.Adler32;
import org.apache.commons.lang3.SerializationUtils;

public class FileInfo {

    private final String path;
    private String name;
    private String type;
    private final long size;
    private final long hash;

    private FileInfo() {
        path = "<Not initialized>";
        name = "<Not initialized>";
        type = "<Not initialized>";
        size = -1;
        hash = -1;
    }

    private FileInfo(String path, String name, String type, long size, long hash) {
        this.path = path;
        this.name = name;
        this.size = size;
        this.hash = hash;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public static FileInfo get(String path) throws IOException {
        //<editor-fold defaultstate="collapsed" desc="variables">
        File file = new File(path);
        long fileSize = file.length();
        String fileName = file.getName();

        int i = fileName.lastIndexOf('.');
        String fileType = i > 0 ? fileName.substring(i + 1) : "";
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Case 1: No need to check content">
        if (AppContext.Current.getCompareSettings().isUsingContent() == false)
            return new FileInfo(path, fileName, fileType, fileSize, -1);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="more variables">
        Adler32 adler = new Adler32();
        CompareSettings settings = AppContext.Current.getCompareSettings();

        int partScanLength = 1048576;
        long totalLength = fileSize;
        double scanPercent
            = settings.isContent2p() ? 2
            : settings.isContent10p() ? 10
            : settings.isContent20p() ? 20
            : settings.isContent50p() ? 50 : 100;
        long scanLength = Math.round(totalLength * scanPercent / 100);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Case 2: File is very small">
        if (scanLength < partScanLength) {
            byte data[] = new byte[(int) totalLength];

            try (FileInputStream stream = new FileInputStream(file)) {
                while (stream.read(data) != -1);
            }

            adler.update(data);

            return new FileInfo(path, fileName, fileType, fileSize, adler.getValue());
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="even more variables">
        long partitions = scanLength / partScanLength;
        long partLength = totalLength / partitions;
        long skipLength = partLength - partScanLength;
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Case 3: File is large">
        HashSet<Long> partialHashes = new HashSet<>();
        try (FileInputStream stream = new FileInputStream(file)) {
            byte data[] = new byte[partScanLength];

            long scanned = 0;
            while (scanned < scanLength) {
                if (stream.read(data) == -1)
                    break;

                adler.update(data);
                partialHashes.add(adler.getValue());

                if (scanPercent != 100)
                    stream.skip(skipLength);

                scanned += partScanLength;
            }
        }

        byte[] hashBytes = SerializationUtils.serialize(partialHashes);
        adler.update(hashBytes);

        return new FileInfo(path, fileName, fileType, fileSize, adler.getValue());
        //</editor-fold>
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
        return path.hashCode() ^ Long.hashCode(hash);
    }
}
