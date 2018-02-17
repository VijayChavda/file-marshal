package me.vijaychavda.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import me.vijaychavda.AppContext;
import me.vijaychavda.FileInfo;
import me.vijaychavda.settings.CompareSettings;
import me.vijaychavda.settings.SelectionSettings;

public class DecloneWorkerPanel extends javax.swing.JPanel {

    public DecloneWorkerPanel() {
        initComponents();
    }

    public void start() {
        new DecloneWorker().execute();
    }

    private class DecloneWorker extends SwingWorker<Void, String> {

        @Override
        protected Void doInBackground() {
            try {
                ArrayList<File> sources = AppContext.Current.getSources();
                ArrayList<File> inputFiles = new ArrayList<>();
                ArrayList<FileInfo> fileInfos = new ArrayList<>();

                publish("\n\nStart declone.\n");
                int p;

                //<editor-fold defaultstate="collapsed" desc="Step 1 - Scanning sources">
                publish("Step 1. Scanning sources.");
                setProgress(0);
                p = 0;

                inputFiles.clear();
                for (File directory : sources) {
                    publish("Scanning: " + directory.getPath());
                    getAllFiles(inputFiles, directory);

                    setProgress((int) Math.round(100 * (double) p / sources.size()));
                    p++;
                }
                publish("Done.\n");
                setProgress(100);
                Thread.sleep(1000);
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Step 2 - Analyzing files">
                publish("Step 2. Analyzing files.");
                setProgress(0);
                p = 0;
                for (File inputFile : inputFiles) {
                    String path = inputFile.getPath();
                    try {
                        publish("\tAnalyzing: " + path);
                        FileInfo info = FileInfo.get(path);
                        fileInfos.add(info);
//                      publish("\tHash = " + info.getHash());
                    } catch (IOException ex) {
                        publish("\tFailed! Error was logged.");
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    setProgress((int) Math.round(100 * (double) p / inputFiles.size()));
                    p++;
                }
                publish("Done.\n");
                setProgress(100);
                Thread.sleep(1000);
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Step 3 - Processing gathered information">
                publish("Step 3. Processing files.");
                setProgress(0);
                p = 0;
                CompareSettings settings = AppContext.Current.getCompareSettings();
                if (settings.isUsingNames() && (settings.isNameCommonWords() || settings.isNameSimilarCommonWords())) {
                    HashMap<String, Integer> wordFrequencyMap = new HashMap<>();
                    publish("\tGathering aggregate information");
                    for (FileInfo fileInfo : fileInfos) {
                        String newName = fileInfo.getName().trim().toLowerCase().replaceAll("[^A-Za-z0-9]", " ");
                        fileInfo.setName(newName);
                        List<String> words = Arrays.asList(newName.split(" "));
                        for (String word : words) {
                            if (wordFrequencyMap.containsKey(word) == false) {
                                wordFrequencyMap.put(word, 1);
                            }
                            wordFrequencyMap.put(word, wordFrequencyMap.get(word) + 1);
                        }
                        setProgress((int) Math.round(100 * (double) (p / fileInfos.size() / 2)));
                        p++;
                    }

                    HashSet<String> commonWords = new HashSet<>();
                    for (String word : wordFrequencyMap.keySet()) {
                        if (wordFrequencyMap.get(word) > 8) {
                            commonWords.add(word);
                        }
                    }

                    setProgress(50);
                    p = 0;

                    publish("\tProcessing files");
                    for (FileInfo fileInfo : fileInfos) {
                        publish("\tProcessing: " + fileInfo.getPath());
                        StringBuilder nameBuilder = new StringBuilder();
                        String words[] = fileInfo.getName().split(" ");
                        for (String word : words) {
                            if (commonWords.contains(word) == false) {
                                nameBuilder.append(word).append(" ");
                            }
                        }
                        String newName = nameBuilder.toString();
                        fileInfo.setName(newName);

                        setProgress(50 + 100 * (int) Math.round((double) (p / fileInfos.size() / 2)));
                        p++;
                    }
                } else {
                    publish("No operation needed based on current settings.");
                }
                publish("Done.\n");
                setProgress(100);
                Thread.sleep(1000);
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Step 4 - Finding duplicates">
                publish("Step 4. Finding duplicates.");
                setProgress(0);
                p = 0;
                HashSet<ArrayList<FileInfo>> duplicates = new HashSet<>();
                outer:
                for (FileInfo fileInfo : fileInfos) {
                    publish("\tChecking: " + fileInfo.getPath());
                    for (ArrayList<FileInfo> set : duplicates) {
                        if (fileInfo.equals(set.get(0))) {
                            set.add(fileInfo);
                            continue outer;
                        }
                    }

                    ArrayList<FileInfo> newSet = new ArrayList<>();
                    newSet.add(fileInfo);
                    duplicates.add(newSet);

                    setProgress(100 * (int) Math.round((double) (p / fileInfos.size())));
                    p++;
                }
                publish("Done.\n");
                setProgress(100);
                Thread.sleep(1000);
                //</editor-fold>

                System.out.println("RESULT");
                for (ArrayList<FileInfo> set : duplicates) {
                    if (set.size() > 1) {
                        for (FileInfo fileInfo : set) {
                            System.out.println(fileInfo.getName());
                            System.out.println(fileInfo);
                            System.out.println();
                        }
                        System.out.println("\n");
                    }
                }

                return null;
            } catch (InterruptedException e) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "an exception was thrown", e);
                return null;
            }
        }

        @Override
        protected void process(List<String> chunks) {
            chunks.forEach((chunk) -> TA_Status.append(chunk + "\n"));

            ProgressBar.setValue(getProgress());

            boolean done = false;
            for (String chunk : chunks) {
                if (chunk.equals("Done.\n")) {
                    done = true;
                    break;
                }
            }

            if (done) {
                if (!CB_Step1.isSelected())
                    CB_Step1.setSelected(true);
                else if (!CB_Step2.isSelected())
                    CB_Step2.setSelected(true);
                else if (!CB_Step3.isSelected())
                    CB_Step3.setSelected(true);
                else if (!CB_Step4.isSelected())
                    CB_Step4.setSelected(true);
            }
        }

        @Override
        protected void done() {
            System.out.println(isCancelled() ? "You cancelled it" : "Completed, yaay!!");
        }

        private void getAllFiles(ArrayList<File> files, File directory) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    getAllFiles(files, file);
                    continue;
                }

                if (trySelectFile(file)) {
                    files.add(file);
                    publish("\tSelected: " + file.getPath());
                }
            }
        }

        private boolean trySelectFile(File file) {
            SelectionSettings settings = AppContext.Current.getSelectionSettings();

            String name = file.getName();

            if (file.length() < settings.getSizeLowerLimit() || file.length() > settings.getSizeUpperLimit())
                return false;

            if (settings.getExtensionCS().equals(".*"))
                return true;

            int doti = name.lastIndexOf('.');
            if (doti == -1)
                return true;

            String ext = name.substring(doti, name.length());

            String[] extensions = settings.getExtensionCS().split(" ");
            for (String extension : extensions) {
                if (extension.equals(ext))
                    return true;
            }
            return false;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="GUI stuff">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        L_Info = new javax.swing.JLabel();
        CB_Step1 = new javax.swing.JCheckBox();
        CB_Step2 = new javax.swing.JCheckBox();
        CB_Step3 = new javax.swing.JCheckBox();
        CB_Step4 = new javax.swing.JCheckBox();
        ProgressBar = new javax.swing.JProgressBar();
        SP_Status = new javax.swing.JScrollPane();
        TA_Status = new javax.swing.JTextArea();
        Seperator1 = new javax.swing.JSeparator();
        L_Step1 = new javax.swing.JLabel();
        L_Step2 = new javax.swing.JLabel();
        L_Step3 = new javax.swing.JLabel();
        L_Step4 = new javax.swing.JLabel();

        L_Info.setText("<html> The magical elves are doing their job. Please wait :) </html>");

        CB_Step1.setEnabled(false);

        CB_Step2.setEnabled(false);

        CB_Step3.setEnabled(false);

        CB_Step4.setEnabled(false);

        ProgressBar.setToolTipText("Estimated progress.");

        TA_Status.setEditable(false);
        TA_Status.setColumns(20);
        TA_Status.setRows(5);
        SP_Status.setViewportView(TA_Status);

        L_Step1.setText("Scanning sources");

        L_Step2.setText("Analizing files");

        L_Step3.setText("Processing files");

        L_Step4.setText("Finding duplicates");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SP_Status)
                    .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Seperator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(L_Info, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CB_Step4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(L_Step1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(L_Step2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(L_Step4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(L_Step3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(L_Step1)
                    .addComponent(CB_Step1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CB_Step2)
                    .addComponent(L_Step2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CB_Step3)
                    .addComponent(L_Step3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CB_Step4)
                    .addComponent(L_Step4))
                .addGap(18, 18, 18)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SP_Status, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CB_Step1;
    private javax.swing.JCheckBox CB_Step2;
    private javax.swing.JCheckBox CB_Step3;
    private javax.swing.JCheckBox CB_Step4;
    private javax.swing.JLabel L_Info;
    private javax.swing.JLabel L_Step1;
    private javax.swing.JLabel L_Step2;
    private javax.swing.JLabel L_Step3;
    private javax.swing.JLabel L_Step4;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JScrollPane SP_Status;
    private javax.swing.JSeparator Seperator1;
    private javax.swing.JTextArea TA_Status;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}
