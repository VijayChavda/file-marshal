package me.vijaychavda.filemarshal.ui;

import java.io.File;
import javax.swing.JOptionPane;
import me.vijaychavda.filemarshal.settings.DeclutterSettings;

public class DeclutterSettingsPanel extends javax.swing.JPanel {

    private static final String Ungrouped = "Ungrouped";
    private static final String NoExtension = "NE";
    private static final String SmallGroup = "Others";

    public DeclutterSettingsPanel() {
        initComponents();
        initOtherThings();
    }

    public final DeclutterSettings getSettings() {
        DeclutterSettings settings = new DeclutterSettings();

        settings.setModeMove(RB_ModeMove.isSelected());
        settings.setModeCopy(RB_ModeCopy.isSelected());
        settings.setModeSimulation(RB_ModeSimulation.isSelected());

        settings.setOutputPath(TB_OutputPath.getText());
        settings.setGroupFormatString(TA_GroupingInfo.getText());

        settings.setUngroupedName(TB_More1.getText());
        settings.setNoExtensionName(TB_More2.getText());
        settings.setMinimumGroupCardinality((int) SP_DontGroupCount.getValue());
        settings.setSmallGroupGroup(TB_More3.getText());

        settings.setGroupByType(RB_Type.isSelected());
        settings.setGroupByExtension(RB_Extension.isSelected());
        settings.setGroupByBoth(RB_Both.isSelected());
        settings.setGroupByCustom(RB_Custom.isSelected());

        return settings;
    }

    //<editor-fold defaultstate="collapsed" desc="GUI stuff">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RBG_Mode = new javax.swing.ButtonGroup();
        RBG_OrganizeBy = new javax.swing.ButtonGroup();
        FilePicker = new javax.swing.JFileChooser();
        L_Info1 = new javax.swing.JLabel();
        TB_OutputPath = new javax.swing.JTextField();
        B_Browse = new javax.swing.JButton();
        L_Info0 = new javax.swing.JLabel();
        RB_ModeMove = new javax.swing.JRadioButton();
        RB_ModeCopy = new javax.swing.JRadioButton();
        RB_ModeSimulation = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        P_Settings = new javax.swing.JPanel();
        L_Info2 = new javax.swing.JLabel();
        RB_Type = new javax.swing.JRadioButton();
        RB_Extension = new javax.swing.JRadioButton();
        RB_Both = new javax.swing.JRadioButton();
        RB_Custom = new javax.swing.JRadioButton();
        Seperator = new javax.swing.JSeparator();
        L_Info3 = new javax.swing.JLabel();
        SP_GroupingInfo = new javax.swing.JScrollPane();
        TA_GroupingInfo = new javax.swing.JTextArea();
        P_MoreSettings = new javax.swing.JPanel();
        L_Info4 = new javax.swing.JLabel();
        CB_More1 = new javax.swing.JCheckBox();
        TB_More1 = new javax.swing.JTextField();
        CB_More2 = new javax.swing.JCheckBox();
        TB_More2 = new javax.swing.JTextField();
        CB_More3 = new javax.swing.JCheckBox();
        SP_DontGroupCount = new javax.swing.JSpinner();
        L_Info5 = new javax.swing.JLabel();
        TB_More3 = new javax.swing.JTextField();

        FilePicker.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        L_Info1.setText("Where should all the files organized in folders go?");

        TB_OutputPath.setToolTipText("A folder named Declutter will be created, inside which your files will be organized.");
        TB_OutputPath.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TB_OutputPathFocusLost(evt);
            }
        });

        B_Browse.setText("Browse");
        B_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_BrowseActionPerformed(evt);
            }
        });

        L_Info0.setText("Mode");

        RBG_Mode.add(RB_ModeMove);
        RB_ModeMove.setSelected(true);
        RB_ModeMove.setText("Move");

        RBG_Mode.add(RB_ModeCopy);
        RB_ModeCopy.setText("Copy");

        RBG_Mode.add(RB_ModeSimulation);
        RB_ModeSimulation.setText("Simulation");

        jLabel2.setText("Moves files to a new (organized) location");

        jLabel3.setText("Keeps your original (cluttered) files");

        jLabel4.setText("Not actual files, but shortcuts to files are organized");

        P_Settings.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        L_Info2.setText("Organize my files by:");

        RBG_OrganizeBy.add(RB_Type);
        RB_Type.setText("Type");
        RB_Type.setToolTipText("Files will be organized in folders like Documents, Images, Music, etc.");

        RBG_OrganizeBy.add(RB_Extension);
        RB_Extension.setText("Extension");
        RB_Extension.setToolTipText("Files will be organized in folders like JPG, DOC, MP3, etc.");

        RBG_OrganizeBy.add(RB_Both);
        RB_Both.setSelected(true);
        RB_Both.setText("Both");
        RB_Both.setToolTipText("Files will be organized in folders by their types like Documents, Images, Music, and inside them again organized by their extensions like JPG, DOC, MP3, etc.");

        RBG_OrganizeBy.add(RB_Custom);
        RB_Custom.setText("I'll decide");
        RB_Custom.setToolTipText("<html>\n<b>Format example:</b><br>\n'Pics' ai bmp gif ico jpeg jpg png ps psd svg tif tiff<br>\n'Docs' doc docx odt pdf rtf txt wks wps wpd ods xlr xls xlsx key odp pps ppt pptx<br>\n'Videos' 3g2 3gp avi flv m4v mkv mov mp4 mpg mpeg rm swf vob wmv<br>\n'My Songs' aif cda mid midi mp3 mpa ogg wav wma wpl<br>\n</html>");
        RB_Custom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                RB_CustomStateChanged(evt);
            }
        });

        Seperator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        L_Info3.setText("<html> In each line, enter in quotes the name of folder, followed by list of space seperated extensions of files that go in that folder. </html>");

        TA_GroupingInfo.setColumns(20);
        TA_GroupingInfo.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        TA_GroupingInfo.setRows(5);
        TA_GroupingInfo.setText("Pics: ai bmp gif ico jpeg jpg png ps psd svg tif tiff\nDocs: doc docx odt pdf rtf txt ods xlr xls xlsx odp pps ppt pptx\nVideos: 3g2 3gp avi flv m4v mkv mov mp4 mpg mpeg swf vob wmv\nMy Songs: aif cda mid midi mp3 mpa ogg wav wma wpl");
        SP_GroupingInfo.setViewportView(TA_GroupingInfo);

        javax.swing.GroupLayout P_SettingsLayout = new javax.swing.GroupLayout(P_Settings);
        P_Settings.setLayout(P_SettingsLayout);
        P_SettingsLayout.setHorizontalGroup(
            P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_SettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(L_Info2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(RB_Type, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(RB_Extension, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(RB_Both, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(RB_Custom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Seperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_Info3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(SP_GroupingInfo))
                .addContainerGap())
        );
        P_SettingsLayout.setVerticalGroup(
            P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_SettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P_SettingsLayout.createSequentialGroup()
                        .addComponent(L_Info3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SP_GroupingInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Seperator, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(P_SettingsLayout.createSequentialGroup()
                        .addComponent(L_Info2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RB_Type)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RB_Extension)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RB_Both)
                        .addGap(10, 10, 10)
                        .addComponent(RB_Custom)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P_MoreSettings.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        L_Info4.setText("More settings:");

        CB_More1.setSelected(true);
        CB_More1.setText("Files with unidentified type/extension, group them as:");
        CB_More1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_More1ActionPerformed(evt);
            }
        });

        TB_More1.setText("Ungrouped");
        TB_More1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TB_More1FocusLost(evt);
            }
        });

        CB_More2.setSelected(true);
        CB_More2.setText("Files without extensions, group them as:");
        CB_More2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_More2ActionPerformed(evt);
            }
        });

        TB_More2.setText("NE");
        TB_More2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TB_More2FocusLost(evt);
            }
        });

        CB_More3.setSelected(true);
        CB_More3.setText("When number of files in a group are less than:");
        CB_More3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_More3ActionPerformed(evt);
            }
        });

        SP_DontGroupCount.setModel(new javax.swing.SpinnerNumberModel(4, 2, 100, 1));

        L_Info5.setText("Place them in the following group instead:");

        TB_More3.setText("Others");
        TB_More3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TB_More3FocusLost(evt);
            }
        });

        javax.swing.GroupLayout P_MoreSettingsLayout = new javax.swing.GroupLayout(P_MoreSettings);
        P_MoreSettings.setLayout(P_MoreSettingsLayout);
        P_MoreSettingsLayout.setHorizontalGroup(
            P_MoreSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_MoreSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_MoreSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P_MoreSettingsLayout.createSequentialGroup()
                        .addComponent(L_Info4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(P_MoreSettingsLayout.createSequentialGroup()
                        .addGroup(P_MoreSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CB_More2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_More1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_More3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(P_MoreSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TB_More1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SP_DontGroupCount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TB_More2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(P_MoreSettingsLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(L_Info5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TB_More3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        P_MoreSettingsLayout.setVerticalGroup(
            P_MoreSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_MoreSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Info4)
                .addGap(18, 18, 18)
                .addGroup(P_MoreSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CB_More1)
                    .addComponent(TB_More1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_MoreSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CB_More2)
                    .addComponent(TB_More2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_MoreSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CB_More3)
                    .addComponent(SP_DontGroupCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_MoreSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(L_Info5)
                    .addComponent(TB_More3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P_MoreSettingsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_More2, TB_More2});

        P_MoreSettingsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_More1, TB_More1});

        P_MoreSettingsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_More3, SP_DontGroupCount});

        P_MoreSettingsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {L_Info5, TB_More3});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(P_MoreSettings, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(P_Settings, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(TB_OutputPath)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B_Browse)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L_Info1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(L_Info0)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(RB_ModeMove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(RB_ModeCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(RB_ModeSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(L_Info1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TB_OutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Browse))
                .addGap(18, 18, 18)
                .addComponent(L_Info0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_ModeMove)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_ModeCopy)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_ModeSimulation)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(P_Settings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(P_MoreSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {B_Browse, TB_OutputPath});

    }// </editor-fold>//GEN-END:initComponents

    private void RB_CustomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_RB_CustomStateChanged
        boolean value = RB_Custom.isSelected();
        L_Info3.setVisible(value);
        SP_GroupingInfo.setVisible(value);
    }//GEN-LAST:event_RB_CustomStateChanged

    private void TB_OutputPathFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TB_OutputPathFocusLost
        String path = TB_OutputPath.getText();

        if (path.equals(""))
            return;

        File file = new File(path);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "This path does not exist. Please check.");
            TB_OutputPath.requestFocus();
        } else if (!file.isDirectory()) {
            JOptionPane.showMessageDialog(this, "You need to enter a path to a directory.");
            TB_OutputPath.requestFocus();
        }
    }//GEN-LAST:event_TB_OutputPathFocusLost

    private void B_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_BrowseActionPerformed
        FilePicker.showOpenDialog(this);

        TB_OutputPath.setText(FilePicker.getSelectedFile().getAbsolutePath());
    }//GEN-LAST:event_B_BrowseActionPerformed

    private void CB_More1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_More1ActionPerformed
        if (!CB_More1.isSelected())
            TB_More1.setText(Ungrouped);
    }//GEN-LAST:event_CB_More1ActionPerformed

    private void CB_More2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_More2ActionPerformed
        if (!CB_More2.isSelected())
            TB_More2.setText(NoExtension);
    }//GEN-LAST:event_CB_More2ActionPerformed

    private void CB_More3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_More3ActionPerformed
        if (!CB_More3.isSelected()) {
            SP_DontGroupCount.setValue(4);
            TB_More3.setText(SmallGroup);
        }
    }//GEN-LAST:event_CB_More3ActionPerformed

    private void TB_More1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TB_More1FocusLost
        if (TB_More1.getText().trim().isEmpty()) {
            TB_More1.setText(Ungrouped);
        }
    }//GEN-LAST:event_TB_More1FocusLost

    private void TB_More2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TB_More2FocusLost
        if (TB_More2.getText().trim().isEmpty()) {
            TB_More2.setText(NoExtension);
        }
    }//GEN-LAST:event_TB_More2FocusLost

    private void TB_More3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TB_More3FocusLost
        if (TB_More3.getText().trim().isEmpty()) {
            SP_DontGroupCount.setValue(4);
            TB_More3.setText(SmallGroup);
        }
    }//GEN-LAST:event_TB_More3FocusLost

    private void initOtherThings() {
        L_Info3.setVisible(false);
        SP_GroupingInfo.setVisible(false);

        String desktopPath = System.getProperty("user.home") + "/Desktop";
        TB_OutputPath.setText(desktopPath);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Browse;
    private javax.swing.JCheckBox CB_More1;
    private javax.swing.JCheckBox CB_More2;
    private javax.swing.JCheckBox CB_More3;
    private javax.swing.JFileChooser FilePicker;
    private javax.swing.JLabel L_Info0;
    private javax.swing.JLabel L_Info1;
    private javax.swing.JLabel L_Info2;
    private javax.swing.JLabel L_Info3;
    private javax.swing.JLabel L_Info4;
    private javax.swing.JLabel L_Info5;
    private javax.swing.JPanel P_MoreSettings;
    private javax.swing.JPanel P_Settings;
    private javax.swing.ButtonGroup RBG_Mode;
    private javax.swing.ButtonGroup RBG_OrganizeBy;
    private javax.swing.JRadioButton RB_Both;
    private javax.swing.JRadioButton RB_Custom;
    private javax.swing.JRadioButton RB_Extension;
    private javax.swing.JRadioButton RB_ModeCopy;
    private javax.swing.JRadioButton RB_ModeMove;
    private javax.swing.JRadioButton RB_ModeSimulation;
    private javax.swing.JRadioButton RB_Type;
    private javax.swing.JSpinner SP_DontGroupCount;
    private javax.swing.JScrollPane SP_GroupingInfo;
    private javax.swing.JSeparator Seperator;
    private javax.swing.JTextArea TA_GroupingInfo;
    private javax.swing.JTextField TB_More1;
    private javax.swing.JTextField TB_More2;
    private javax.swing.JTextField TB_More3;
    private javax.swing.JTextField TB_OutputPath;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

    //</editor-fold>
}
