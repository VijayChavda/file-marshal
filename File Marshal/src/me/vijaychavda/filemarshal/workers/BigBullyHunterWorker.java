package me.vijaychavda.filemarshal.workers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import me.vijaychavda.filemarshal.AppContext;
import me.vijaychavda.filemarshal.ui.DecloneWorkerPanel;

public class BigBullyHunterWorker extends SwingWorker<Void, String> {

    private final PriorityQueue<Path> queue;

    public BigBullyHunterWorker() {
        queue = new PriorityQueue<>(10, (Path o1, Path o2) -> {
            try {
                return (Files.size(o1) >= Files.size(o2)) ? 1 : -1;
            } catch (IOException ex) {
                Logger.getLogger(BigBullyHunterWorker.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        });
    }

    @Override
    protected Void doInBackground() {
        ArrayList<File> sources = AppContext.Current.getSources();
        for (File source : sources) {
            scanDir(source.toPath());
        }

        StringBuilder builder = new StringBuilder();

        for (Path path : queue) {
            try {
                builder.append(MessageFormat.format("{0} - {1}\n", Files.size(path), path));
            } catch (IOException ex) {
                Logger.getLogger(BigBullyHunterWorker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        File desktop = new File(System.getProperty("user.home") + "/Desktop");
        File output = new File(desktop, "Large files output - " + System.nanoTime() + ".txt");

        try (FileWriter writer = new FileWriter(output)) {
            writer.write(builder.toString());
            JOptionPane.showMessageDialog(null, "Results can be found in '" + output.getAbsolutePath() + "'."
                + "\nOutput will be shown in a better way in the UI in coming update :)",
                "Large files", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(DecloneWorkerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void scanDir(Path folder) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry))
                    scanDir(entry);
                else
                    addToQueue(entry);
            }
        } catch (IOException ex) {
            Logger.getLogger(BigBullyHunterWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addToQueue(Path entry) {
        queue.add(entry);

        if (queue.size() > 10)
            queue.poll();
    }
}
