package me.vijaychavda.filemarshal.ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import me.vijaychavda.filemarshal.AppContext;
import me.vijaychavda.filemarshal.workers.BigBullyHunterWorker;
import me.vijaychavda.filemarshal.workers.DeclutterWorker;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="GUI Code">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        P_Content = new javax.swing.JPanel();
        TabbedPane = new javax.swing.JTabbedPane();
        P_Sources = new javax.swing.JPanel();
        selectSourcesPanel = new me.vijaychavda.filemarshal.ui.SelectSourcesPanel();
        P_FileOptions = new javax.swing.JPanel();
        selectionSettingsPanel = new me.vijaychavda.filemarshal.ui.SelectionSettingsPanel();
        P_Declutter = new javax.swing.JPanel();
        declutterSettingsPanel = new me.vijaychavda.filemarshal.ui.DeclutterSettingsPanel();
        P_Declone = new javax.swing.JPanel();
        decloneSettingsPanel = new me.vijaychavda.filemarshal.ui.DecloneSettingsPanel();
        P_Commands = new javax.swing.JPanel();
        B_Declone = new javax.swing.JButton();
        B_Declutter = new javax.swing.JButton();
        B_FindLarge = new javax.swing.JButton();
        Menubar = new javax.swing.JMenuBar();
        M_Help = new javax.swing.JMenu();
        MI_About = new javax.swing.JMenuItem();
        MI_Contact = new javax.swing.JMenuItem();
        MI_OpenSource = new javax.swing.JMenuItem();
        MI_License = new javax.swing.JMenuItem();
        MI_Donate = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Declone - Find and remove duplicate files");
        setResizable(false);

        P_Sources.setOpaque(false);
        P_Sources.setLayout(new java.awt.BorderLayout());

        selectSourcesPanel.setOpaque(false);
        P_Sources.add(selectSourcesPanel, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("Sources", P_Sources);

        P_FileOptions.setOpaque(false);
        P_FileOptions.setLayout(new java.awt.BorderLayout());

        selectionSettingsPanel.setOpaque(false);
        P_FileOptions.add(selectionSettingsPanel, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("File select", P_FileOptions);

        P_Declutter.setOpaque(false);
        P_Declutter.setLayout(new javax.swing.BoxLayout(P_Declutter, javax.swing.BoxLayout.Y_AXIS));

        declutterSettingsPanel.setOpaque(false);
        P_Declutter.add(declutterSettingsPanel);

        TabbedPane.addTab("Declutter", P_Declutter);

        P_Declone.setOpaque(false);
        P_Declone.setLayout(new java.awt.BorderLayout());

        decloneSettingsPanel.setOpaque(false);
        P_Declone.add(decloneSettingsPanel, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("Declone", P_Declone);

        javax.swing.GroupLayout P_ContentLayout = new javax.swing.GroupLayout(P_Content);
        P_Content.setLayout(P_ContentLayout);
        P_ContentLayout.setHorizontalGroup(
            P_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_ContentLayout.createSequentialGroup()
                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        P_ContentLayout.setVerticalGroup(
            P_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_ContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(P_Content, java.awt.BorderLayout.CENTER);

        B_Declone.setText("Declone");
        B_Declone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_DecloneActionPerformed(evt);
            }
        });

        B_Declutter.setText("Declutter");
        B_Declutter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_DeclutterActionPerformed(evt);
            }
        });

        B_FindLarge.setText("Big Bully Hunter");
        B_FindLarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_FindLargeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout P_CommandsLayout = new javax.swing.GroupLayout(P_Commands);
        P_Commands.setLayout(P_CommandsLayout);
        P_CommandsLayout.setHorizontalGroup(
            P_CommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_CommandsLayout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addComponent(B_FindLarge)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_Declutter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_Declone)
                .addContainerGap())
        );
        P_CommandsLayout.setVerticalGroup(
            P_CommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_CommandsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(P_CommandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B_Declone, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(B_Declutter, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(B_FindLarge, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        getContentPane().add(P_Commands, java.awt.BorderLayout.SOUTH);

        M_Help.setText("Help");

        MI_About.setText("About");
        M_Help.add(MI_About);

        MI_Contact.setText("Contact developer");
        M_Help.add(MI_Contact);

        MI_OpenSource.setText("Open Source");
        M_Help.add(MI_OpenSource);

        MI_License.setText("License");
        M_Help.add(MI_License);

        MI_Donate.setText("Donate");
        M_Help.add(MI_Donate);

        Menubar.add(M_Help);

        setJMenuBar(Menubar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void B_DecloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_DecloneActionPerformed
        AppContext.Current.setSources(selectSourcesPanel.getSources());
        AppContext.Current.setSelectionSettings(selectionSettingsPanel.getSettings());
        AppContext.Current.setDecloneSettings(decloneSettingsPanel.getSettings());

        JDialog decloneDialog = new JDialog(this, "File Marshal - Declone");
        DecloneWorkerPanel decloneWorkerPanel = new DecloneWorkerPanel();
        decloneDialog.add(decloneWorkerPanel);
        decloneDialog.pack();
        decloneDialog.setLocationRelativeTo(this);
        decloneDialog.setModal(true);
        decloneDialog.setVisible(true);
        decloneWorkerPanel.start();
    }//GEN-LAST:event_B_DecloneActionPerformed

    private void B_DeclutterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_DeclutterActionPerformed
        AppContext.Current.setSources(selectSourcesPanel.getSources());
        AppContext.Current.setSelectionSettings(selectionSettingsPanel.getSettings());
        AppContext.Current.setDeclutterSettings(declutterSettingsPanel.getSettings());

        String error = DeclutterWorker.GroupFormatString.check(declutterSettingsPanel.getSettings().getGroupFormatString());
        if (error != null) {
            JOptionPane.showMessageDialog(null, error, "Declutter - File marshal", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog decloneDialog = new JDialog(this, "File Marshal - Declutter");
        DeclutterWorkerPanel declutterWorkerPanel = new DeclutterWorkerPanel() {
            @Override
            public void completed() {
                JOptionPane.showMessageDialog(this, "Done!", "Declutter", JOptionPane.INFORMATION_MESSAGE);
//                decloneDialog.dispose();
            }
        };
        decloneDialog.add(declutterWorkerPanel);
        decloneDialog.pack();
        decloneDialog.setLocationRelativeTo(this);
        decloneDialog.setModal(true);
        decloneDialog.setVisible(true);
        declutterWorkerPanel.start();
    }//GEN-LAST:event_B_DeclutterActionPerformed

    private void B_FindLargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_FindLargeActionPerformed
        AppContext.Current.setSources(selectSourcesPanel.getSources());
        AppContext.Current.setSelectionSettings(selectionSettingsPanel.getSettings());
        AppContext.Current.setDeclutterSettings(declutterSettingsPanel.getSettings());

        BigBullyHunterWorker worker = new BigBullyHunterWorker();
        worker.execute();
    }//GEN-LAST:event_B_FindLargeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Declone;
    private javax.swing.JButton B_Declutter;
    private javax.swing.JButton B_FindLarge;
    private javax.swing.JMenuItem MI_About;
    private javax.swing.JMenuItem MI_Contact;
    private javax.swing.JMenuItem MI_Donate;
    private javax.swing.JMenuItem MI_License;
    private javax.swing.JMenuItem MI_OpenSource;
    private javax.swing.JMenu M_Help;
    private javax.swing.JMenuBar Menubar;
    private javax.swing.JPanel P_Commands;
    private javax.swing.JPanel P_Content;
    private javax.swing.JPanel P_Declone;
    private javax.swing.JPanel P_Declutter;
    private javax.swing.JPanel P_FileOptions;
    private javax.swing.JPanel P_Sources;
    private javax.swing.JTabbedPane TabbedPane;
    private me.vijaychavda.filemarshal.ui.DecloneSettingsPanel decloneSettingsPanel;
    private me.vijaychavda.filemarshal.ui.DeclutterSettingsPanel declutterSettingsPanel;
    private me.vijaychavda.filemarshal.ui.SelectSourcesPanel selectSourcesPanel;
    private me.vijaychavda.filemarshal.ui.SelectionSettingsPanel selectionSettingsPanel;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

}
