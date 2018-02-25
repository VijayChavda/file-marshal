package me.vijaychavda.filemarshal.ui;

import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class SelectSourcesPanel extends javax.swing.JPanel {

    public SelectSourcesPanel() {
        initComponents();
        initOtherThings();
    }

    public ArrayList<File> getSources() {
        ArrayList<File> sources = new ArrayList<>();

        DefaultListModel model = (DefaultListModel) L_Sources.getModel();
        for (Object sourcePath : model.toArray()) {
            File file = new File(sourcePath.toString());
            if (file.isDirectory())
                sources.add(file);
        }

        return sources;
    }

    //<editor-fold defaultstate="collapsed" desc="GUI stuff">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FilePicker = new javax.swing.JFileChooser();
        S_Sources = new javax.swing.JScrollPane();
        L_Sources = new javax.swing.JList<>();
        L_WhereDoWeLook = new javax.swing.JLabel();
        T_SourcePath = new javax.swing.JTextField();
        B_AddSource = new javax.swing.JButton();
        B_RemoveSource = new javax.swing.JButton();
        B_Browse = new javax.swing.JButton();

        FilePicker.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        FilePicker.setMultiSelectionEnabled(true);

        L_Sources.setModel(new DefaultListModel());
        S_Sources.setViewportView(L_Sources);

        L_WhereDoWeLook.setText("Where do we look for files?");

        T_SourcePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T_SourcePathActionPerformed(evt);
            }
        });

        B_AddSource.setText("Add");
        B_AddSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_AddSourceActionPerformed(evt);
            }
        });

        B_RemoveSource.setText("Remove selected");
        B_RemoveSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_RemoveSourceActionPerformed(evt);
            }
        });

        B_Browse.setText("Browse");
        B_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_BrowseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S_Sources)
                    .addComponent(L_WhereDoWeLook, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(B_RemoveSource)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(T_SourcePath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_AddSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_Browse)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(L_WhereDoWeLook)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(T_SourcePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_AddSource)
                    .addComponent(B_Browse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(S_Sources, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(B_RemoveSource)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initOtherThings() {
        B_RemoveSource.setVisible(false);
        L_Sources.addListSelectionListener(
            e -> B_RemoveSource.setVisible(L_Sources.getSelectedIndices().length > 0)
        );
        
        String desktopPath = System.getProperty("user.home") + "/Desktop";
        T_SourcePath.setText(desktopPath);
    }

    private void T_SourcePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T_SourcePathActionPerformed
        B_AddSourceActionPerformed(evt);
    }//GEN-LAST:event_T_SourcePathActionPerformed

    private void B_AddSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_AddSourceActionPerformed
        String path = T_SourcePath.getText();

        File file = new File(path);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "This path does not exist. Please check.");
            T_SourcePath.setText("");
            T_SourcePath.requestFocus();
        } else if (!file.isDirectory()) {
            JOptionPane.showMessageDialog(this, "The path is not a directory.");
            T_SourcePath.setText("");
            T_SourcePath.requestFocus();
        } else {
            DefaultListModel model = (DefaultListModel) L_Sources.getModel();
            if (model.contains(file.getAbsolutePath()))
                JOptionPane.showMessageDialog(this, "This source is already added.");
            else
                model.addElement(file.getAbsolutePath());
        }
    }//GEN-LAST:event_B_AddSourceActionPerformed

    private void B_RemoveSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_RemoveSourceActionPerformed
        DefaultListModel model = (DefaultListModel) L_Sources.getModel();

        for (int i : L_Sources.getSelectedIndices()) {
            model.removeElementAt(i);
        }
    }//GEN-LAST:event_B_RemoveSourceActionPerformed

    private void B_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_BrowseActionPerformed
        FilePicker.showOpenDialog(this);

        File[] files = FilePicker.getSelectedFiles();

        if (files == null || files.length == 0)
            return;

        for (File file : files) {
            DefaultListModel model = (DefaultListModel) L_Sources.getModel();
            if (model.contains(file.getAbsolutePath()))
                JOptionPane.showMessageDialog(this, "This source is already added.");
            else
                model.addElement(file.getAbsolutePath());
        }
    }//GEN-LAST:event_B_BrowseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_AddSource;
    private javax.swing.JButton B_Browse;
    private javax.swing.JButton B_RemoveSource;
    private javax.swing.JFileChooser FilePicker;
    private javax.swing.JList<String> L_Sources;
    private javax.swing.JLabel L_WhereDoWeLook;
    private javax.swing.JScrollPane S_Sources;
    private javax.swing.JTextField T_SourcePath;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}
