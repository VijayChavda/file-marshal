package workers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.SwingWorker;

public class DeclutterWorker extends SwingWorker<Void, String> {

    private ArrayList<File> allFiles;

    public DeclutterWorker(ArrayList<File> allFiles) {
        this.allFiles = allFiles;
    }

    @Override
    protected Void doInBackground() throws Exception {
        int progress = 0;
        setProgress(0);
        publish("Running task: Declutter!");

        HashMap<String, HashSet<File>> map = new HashMap<>();
        String ex;
        for (File file : allFiles) {
            ex = extension(file);
            if (map.containsKey(ex) == false) {
                publish("\tIdentified type: [" + ex + "]");
                map.put(ex, new HashSet<>());
            }
            map.get(ex).add(file);

            setProgress((int) Math.round(100 * (double) progress / allFiles.size()));
            progress++;
        }
        Thread.sleep(5000);
        publish("\tDone.");

        System.out.println("\n\n\n RESULTS:");
        for (String e : map.keySet()) {
            System.out.println(e + ": ");
            for (File file : map.get(e)) {
                System.out.println("\t" + file.getName());
            }
            System.out.println();
        }

        return null;
    }

    private String extension(File file) {
        String name = file.getName();
        int i = name.lastIndexOf('.');
        return i > 0 ? name.substring(i + 1) : "";
    }
}
