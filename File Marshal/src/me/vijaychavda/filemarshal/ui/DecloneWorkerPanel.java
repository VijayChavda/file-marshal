package me.vijaychavda.filemarshal.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import me.vijaychavda.filemarshal.FileInfo;
import me.vijaychavda.filemarshal.workers.DecloneWorker;
import me.vijaychavda.filemarshal.workers.GatherFileInfoWorker;
import me.vijaychavda.filemarshal.workers.ProcessFileInfosWorker;
import me.vijaychavda.filemarshal.workers.ScanSourcesWorker;

public class DecloneWorkerPanel extends javax.swing.JPanel {

    private ArrayList<File> allFiles;
    private ArrayList<FileInfo> fileInfos;
    private HashSet<ArrayList<FileInfo>> duplicates;

    public DecloneWorkerPanel() {
        initComponents();
    }

    public void start() {
        step1();
    }

    private void step1() {
        ScanSourcesWorker scanSourcesWorker = new ScanSourcesWorker() {
            @Override
            protected void process(List<String> chunks) {
                chunks.forEach((chunk) -> TA_Status.append(chunk + "\n"));

                ProgressBar.setValue(getProgress());
            }

            @Override
            protected void done() {
                try {
                    allFiles = get();
                    CB_Step1.setSelected(true);
                    step2();
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(DecloneWorkerPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        scanSourcesWorker.execute();
    }

    private void step2() {
        GatherFileInfoWorker gatherFileInfoWorker = new GatherFileInfoWorker(allFiles) {
            @Override
            protected void process(List<String> chunks) {
                chunks.forEach((chunk) -> TA_Status.append(chunk + "\n"));

                ProgressBar.setValue(getProgress());
            }

            @Override
            protected void done() {
                try {
                    fileInfos = get();
                    CB_Step2.setSelected(true);
                    step3();
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(DecloneWorkerPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        gatherFileInfoWorker.execute();
    }

    private void step3() {
        ProcessFileInfosWorker processFileInfosWorker = new ProcessFileInfosWorker(fileInfos) {
            @Override
            protected void process(List<String> chunks) {
                chunks.forEach((chunk) -> TA_Status.append(chunk + "\n"));

                ProgressBar.setValue(getProgress());
            }

            @Override
            protected void done() {
                CB_Step3.setSelected(true);
                step4();
            }
        };

        processFileInfosWorker.execute();
    }

    private void step4() {
        DecloneWorker decloneWorker = new DecloneWorker(fileInfos) {
            @Override
            protected void process(List<String> chunks) {
                chunks.forEach((chunk) -> TA_Status.append(chunk + "\n"));

                ProgressBar.setValue(getProgress());
            }

            @Override
            protected void done() {
                try {
                    duplicates = get();
                    CB_Step4.setSelected(true);

                    completed();
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(DecloneWorkerPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        decloneWorker.execute();
    }

    private void completed() {
        StringBuilder builder = new StringBuilder();

        for (ArrayList<FileInfo> set : duplicates) {
            if (set.size() > 1) {
                for (FileInfo fileInfo : set) {
                    builder.append(MessageFormat.format("{0} - {1}\n", fileInfo.getSize(), fileInfo.getPath()));
                }
                builder.append("\n\n");
            }
        }

        File desktop = new File(System.getProperty("user.home") + "/Desktop");
        File output = new File(desktop, "Declone output - " + System.nanoTime() + ".txt");

        try (FileWriter writer = new FileWriter(output)) {
            writer.write(builder.toString());
            JOptionPane.showMessageDialog(null, "Results can be found in '" + output.getAbsolutePath() + "'."
                + "\nOutput will be shown in a better way in the UI in coming update :)",
                "Duplicates", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(DecloneWorkerPanel.class.getName()).log(Level.SEVERE, null, ex);
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
