package me.vijaychavda.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import me.vijaychavda.AppContext;
import me.vijaychavda.CompareSettings;
import me.vijaychavda.FileInfo;
import me.vijaychavda.SelectionSettings;

public class MainFrame extends javax.swing.JFrame {

    private static final long _1GB = 1073741824L;
    private static final long _1MB = 1048576L;
    private static final long _1KB = 1024L;

    public MainFrame() {
        initComponents();

        B_RemoveSource.setVisible(false);
        L_Sources.addListSelectionListener(
            e -> B_RemoveSource.setVisible(L_Sources.getSelectedIndices().length > 0)
        );
    }

    public class DecloneWorker extends SwingWorker<Void, String> {

        private final ArrayList<File> sources;

        public DecloneWorker() {
            sources = new ArrayList<>();
        }

        public DecloneWorker(ArrayList<File> sources) {
            this.sources = sources;
        }

        @Override
        protected Void doInBackground() throws Exception {
            ArrayList<File> inputFiles = new ArrayList<>();

            publish("\n\nStart declone.\n");
            publish("Step 1. Scanning sources.");
            setProgress(0);

            inputFiles.clear();
            for (File directory : sources) {
                publish("Scanning: " + directory.getPath());
                getAllFiles(inputFiles, directory);
            }
            publish("Done.\n");
            setProgress(100);
            Thread.sleep(1000);

            publish("Step 2. Analyzing files.");
            setProgress(0);
            ArrayList<FileInfo> fileInfos = new ArrayList<>();
            for (int i = 0; i < inputFiles.size(); i++) {
                File inputFile = inputFiles.get(i);

                String path = inputFile.getPath();
                try {
                    publish("\tAnalyzing: " + path);
                    FileInfo info = FileInfo.init(path);
                    fileInfos.add(info);
//                publish("\tHash = " + info.getHash());
                } catch (IOException ex) {
                    publish("\tFailed! Error was logged.");
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                setProgress((int) Math.round(100 * (double) i / inputFiles.size()));
            }
            publish("Done.\n");
            setProgress(100);
            Thread.sleep(1000);

            return null;
        }

        @Override
        protected void process(List<String> chunks) {
            chunks.forEach((chunk) -> TA_Status.append(chunk + "\n"));
//            System.out.println(getProgress());
            ProgressBar.setValue(getProgress());

            if (getProgress() == 100) {
                if (!CB_Step1.isSelected())
                    CB_Step1.setSelected(true);
                else if (!CB_Step2.isSelected())
                    CB_Step2.setSelected(true);
                else if (!CB_Step3.isSelected())
                    CB_Step3.setSelected(true);
            }
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
            SelectionSettings settings = AppContext.getSelectionSettings();

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FilePicker = new javax.swing.JFileChooser();
        BG_FileSize = new javax.swing.ButtonGroup();
        ProgressDialog = new javax.swing.JDialog();
        P_ProgressContent = new javax.swing.JPanel();
        L_Info = new javax.swing.JLabel();
        CB_Step1 = new javax.swing.JCheckBox();
        CB_Step2 = new javax.swing.JCheckBox();
        CB_Step3 = new javax.swing.JCheckBox();
        ProgressBar = new javax.swing.JProgressBar();
        SP_Status = new javax.swing.JScrollPane();
        TA_Status = new javax.swing.JTextArea();
        Seperator1 = new javax.swing.JSeparator();
        L_Step1 = new javax.swing.JLabel();
        L_Step2 = new javax.swing.JLabel();
        L_Step3 = new javax.swing.JLabel();
        P_Content = new javax.swing.JPanel();
        TabbedPane = new javax.swing.JTabbedPane();
        P_Sources = new javax.swing.JPanel();
        S_Sources = new javax.swing.JScrollPane();
        L_Sources = new javax.swing.JList<>();
        L_WhereDoWeLook = new javax.swing.JLabel();
        T_SourcePath = new javax.swing.JTextField();
        B_AddSource = new javax.swing.JButton();
        B_RemoveSource = new javax.swing.JButton();
        B_Browse = new javax.swing.JButton();
        B_Declone = new javax.swing.JButton();
        P_FileOptions = new javax.swing.JPanel();
        L_Info2 = new javax.swing.JLabel();
        P_FilterType = new javax.swing.JPanel();
        L_Info21 = new javax.swing.JLabel();
        CB_CustomType = new javax.swing.JCheckBox();
        CB_Pictures = new javax.swing.JCheckBox();
        CB_Others = new javax.swing.JCheckBox();
        CB_Videos = new javax.swing.JCheckBox();
        CB_Audios = new javax.swing.JCheckBox();
        CB_TypeAll = new javax.swing.JCheckBox();
        SP_Extensions = new javax.swing.JScrollPane();
        TB_Extensions = new javax.swing.JTextArea();
        Seperator21 = new javax.swing.JSeparator();
        Seperator22 = new javax.swing.JSeparator();
        Seperator2 = new javax.swing.JSeparator();
        P_FilterSize = new javax.swing.JPanel();
        CB_CustomSize = new javax.swing.JCheckBox();
        CB_Large = new javax.swing.JCheckBox();
        CB_Medium = new javax.swing.JCheckBox();
        CB_Small = new javax.swing.JCheckBox();
        CB_AnySize = new javax.swing.JCheckBox();
        SP_GreaterThan = new javax.swing.JSpinner();
        L_SizeSign = new javax.swing.JLabel();
        SP_LessThan = new javax.swing.JSpinner();
        CB_GreaterThan = new javax.swing.JComboBox<>();
        CB_LessThan = new javax.swing.JComboBox<>();
        L_Info22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        P_Settings = new javax.swing.JPanel();
        L_Info31 = new javax.swing.JLabel();
        Info32 = new javax.swing.JLabel();
        CB_Name = new javax.swing.JCheckBox();
        CB_Size = new javax.swing.JCheckBox();
        CB_Content = new javax.swing.JCheckBox();
        L_Info33 = new javax.swing.JLabel();
        TB_Advance = new javax.swing.JToggleButton();
        P_AdvanceSettings = new javax.swing.JPanel();
        L_AdName = new javax.swing.JLabel();
        SL_Name = new javax.swing.JSlider();
        L_AdSize = new javax.swing.JLabel();
        SL_Size = new javax.swing.JSlider();
        L_Content = new javax.swing.JLabel();
        SL_Content = new javax.swing.JSlider();
        Menubar = new javax.swing.JMenuBar();
        M_Help = new javax.swing.JMenu();
        MI_About = new javax.swing.JMenuItem();
        MI_Contact = new javax.swing.JMenuItem();
        MI_OpenSource = new javax.swing.JMenuItem();
        MI_License = new javax.swing.JMenuItem();
        MI_Donate = new javax.swing.JMenuItem();

        FilePicker.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        FilePicker.setMultiSelectionEnabled(true);

        ProgressDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ProgressDialog.setTitle("Working on it...");
        ProgressDialog.setAlwaysOnTop(true);
        ProgressDialog.setModal(true);
        ProgressDialog.setResizable(false);
        ProgressDialog.setType(java.awt.Window.Type.UTILITY);

        P_ProgressContent.setOpaque(false);

        L_Info.setText("<html> The magical elves are doing their job.<br> Why don't you make sure Dobby gets his wages? Hermionie will be pleased :) </html>");

        CB_Step1.setEnabled(false);

        CB_Step2.setEnabled(false);

        CB_Step3.setEnabled(false);

        ProgressBar.setToolTipText("Estimated progress.");

        TA_Status.setEditable(false);
        TA_Status.setColumns(20);
        TA_Status.setRows(5);
        SP_Status.setViewportView(TA_Status);

        L_Step1.setText("Scanning sources");

        L_Step2.setText("Analizing files");

        L_Step3.setText("Finding duplicates");

        javax.swing.GroupLayout P_ProgressContentLayout = new javax.swing.GroupLayout(P_ProgressContent);
        P_ProgressContent.setLayout(P_ProgressContentLayout);
        P_ProgressContentLayout.setHorizontalGroup(
            P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_ProgressContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SP_Status)
                    .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Seperator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(L_Info, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                    .addGroup(P_ProgressContentLayout.createSequentialGroup()
                        .addGroup(P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CB_Step3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(L_Step1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(L_Step2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(L_Step3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        P_ProgressContentLayout.setVerticalGroup(
            P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_ProgressContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Step1)
                    .addComponent(L_Step1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Step2)
                    .addComponent(L_Step2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Step3)
                    .addComponent(L_Step3))
                .addGap(18, 18, 18)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SP_Status, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );

        P_ProgressContentLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_Step1, L_Step1});

        P_ProgressContentLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_Step2, L_Step2});

        P_ProgressContentLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_Step3, L_Step3});

        javax.swing.GroupLayout ProgressDialogLayout = new javax.swing.GroupLayout(ProgressDialog.getContentPane());
        ProgressDialog.getContentPane().setLayout(ProgressDialogLayout);
        ProgressDialogLayout.setHorizontalGroup(
            ProgressDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(P_ProgressContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ProgressDialogLayout.setVerticalGroup(
            ProgressDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(P_ProgressContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Declone - Find and remove duplicate files");
        setResizable(false);

        P_Sources.setOpaque(false);

        L_Sources.setModel(new DefaultListModel());
        S_Sources.setViewportView(L_Sources);

        L_WhereDoWeLook.setText("Where do we look for duplicate files?");

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

        B_Declone.setText("Declone");
        B_Declone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_DecloneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout P_SourcesLayout = new javax.swing.GroupLayout(P_Sources);
        P_Sources.setLayout(P_SourcesLayout);
        P_SourcesLayout.setHorizontalGroup(
            P_SourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_SourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_SourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, P_SourcesLayout.createSequentialGroup()
                        .addComponent(B_RemoveSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(B_Declone))
                    .addComponent(S_Sources)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, P_SourcesLayout.createSequentialGroup()
                        .addComponent(T_SourcePath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_AddSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_Browse))
                    .addComponent(L_WhereDoWeLook, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addContainerGap())
        );
        P_SourcesLayout.setVerticalGroup(
            P_SourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_SourcesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(L_WhereDoWeLook)
                .addGap(30, 30, 30)
                .addGroup(P_SourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(T_SourcePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_AddSource)
                    .addComponent(B_Browse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(S_Sources, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_SourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_RemoveSource)
                    .addComponent(B_Declone))
                .addContainerGap())
        );

        TabbedPane.addTab("Sources", P_Sources);

        P_FileOptions.setOpaque(false);

        L_Info2.setText("Which files from the sources should be checked for duplicates?");

        P_FilterType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        L_Info21.setText("File type:");

        CB_CustomType.setText("Custom");
        CB_CustomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_TypeActionPerformed(evt);
            }
        });

        CB_Pictures.setText("Pictures");
        CB_Pictures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_TypeActionPerformed(evt);
            }
        });

        CB_Others.setText("Others");
        CB_Others.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_TypeActionPerformed(evt);
            }
        });

        CB_Videos.setText("Video");
        CB_Videos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_TypeActionPerformed(evt);
            }
        });

        CB_Audios.setText("Audio");
        CB_Audios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_TypeActionPerformed(evt);
            }
        });

        CB_TypeAll.setSelected(true);
        CB_TypeAll.setText("All");
        CB_TypeAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_TypeActionPerformed(evt);
            }
        });

        TB_Extensions.setEditable(false);
        TB_Extensions.setColumns(20);
        TB_Extensions.setLineWrap(true);
        TB_Extensions.setRows(3);
        TB_Extensions.setTabSize(1);
        TB_Extensions.setText(".*");
        TB_Extensions.setWrapStyleWord(true);
        SP_Extensions.setViewportView(TB_Extensions);

        javax.swing.GroupLayout P_FilterTypeLayout = new javax.swing.GroupLayout(P_FilterType);
        P_FilterType.setLayout(P_FilterTypeLayout);
        P_FilterTypeLayout.setHorizontalGroup(
            P_FilterTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_FilterTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_FilterTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SP_Extensions, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(CB_CustomType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Seperator22)
                    .addComponent(CB_Others, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_Pictures, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_Videos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_Audios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Seperator21)
                    .addComponent(CB_TypeAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_Info21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        P_FilterTypeLayout.setVerticalGroup(
            P_FilterTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_FilterTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Info21)
                .addGap(18, 18, 18)
                .addComponent(CB_TypeAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Seperator21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Audios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Videos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Pictures)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Others)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Seperator22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_CustomType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SP_Extensions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Seperator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        P_FilterSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BG_FileSize.add(CB_CustomSize);
        CB_CustomSize.setSelected(true);
        CB_CustomSize.setText("Custom");
        CB_CustomSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_SizeActionPerformed(evt);
            }
        });

        BG_FileSize.add(CB_Large);
        CB_Large.setText("Large");
        CB_Large.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_SizeActionPerformed(evt);
            }
        });

        BG_FileSize.add(CB_Medium);
        CB_Medium.setText("Medium");
        CB_Medium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_SizeActionPerformed(evt);
            }
        });

        BG_FileSize.add(CB_Small);
        CB_Small.setText("Small");
        CB_Small.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_SizeActionPerformed(evt);
            }
        });

        BG_FileSize.add(CB_AnySize);
        CB_AnySize.setText("Any");
        CB_AnySize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_SizeActionPerformed(evt);
            }
        });

        SP_GreaterThan.setModel(new javax.swing.SpinnerNumberModel(500L, 1L, null, 1L));

        L_SizeSign.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_SizeSign.setText("<   size   < ");
        L_SizeSign.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        SP_LessThan.setModel(new javax.swing.SpinnerNumberModel(1L, 1L, null, 1L));

        CB_GreaterThan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GB", "MB", "KB", "B" }));
        CB_GreaterThan.setSelectedIndex(1);

        CB_LessThan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GB", "MB", "KB", "B" }));

        L_Info22.setText("File size:");

        jLabel1.setText("< 10 MB");

        jLabel2.setText("[ 10, 100 ] MB");

        jLabel3.setText("> 100 MB");

        javax.swing.GroupLayout P_FilterSizeLayout = new javax.swing.GroupLayout(P_FilterSize);
        P_FilterSize.setLayout(P_FilterSizeLayout);
        P_FilterSizeLayout.setHorizontalGroup(
            P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_FilterSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_Info22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CB_CustomSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(P_FilterSizeLayout.createSequentialGroup()
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CB_GreaterThan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SP_GreaterThan, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(L_SizeSign, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CB_LessThan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SP_LessThan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(P_FilterSizeLayout.createSequentialGroup()
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CB_Large, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Medium, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Small, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(CB_AnySize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        P_FilterSizeLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {CB_GreaterThan, SP_GreaterThan});

        P_FilterSizeLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {CB_LessThan, SP_LessThan});

        P_FilterSizeLayout.setVerticalGroup(
            P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_FilterSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Info22)
                .addGap(18, 18, 18)
                .addComponent(CB_AnySize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Small)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Medium)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Large)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_CustomSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(P_FilterSizeLayout.createSequentialGroup()
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(SP_GreaterThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SP_LessThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(P_FilterSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CB_GreaterThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CB_LessThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(L_SizeSign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        P_FilterSizeLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_GreaterThan, CB_LessThan, SP_GreaterThan, SP_LessThan});

        P_FilterSizeLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_Small, jLabel1});

        P_FilterSizeLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_Medium, jLabel2});

        P_FilterSizeLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_Large, jLabel3});

        javax.swing.GroupLayout P_FileOptionsLayout = new javax.swing.GroupLayout(P_FileOptions);
        P_FileOptions.setLayout(P_FileOptionsLayout);
        P_FileOptionsLayout.setHorizontalGroup(
            P_FileOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_FileOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_FileOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(L_Info2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(P_FileOptionsLayout.createSequentialGroup()
                        .addComponent(P_FilterType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Seperator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P_FilterSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        P_FileOptionsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {P_FilterSize, P_FilterType});

        P_FileOptionsLayout.setVerticalGroup(
            P_FileOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_FileOptionsLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(L_Info2)
                .addGap(30, 30, 30)
                .addGroup(P_FileOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Seperator2)
                    .addComponent(P_FilterType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(P_FilterSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P_FileOptionsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {P_FilterSize, P_FilterType});

        TabbedPane.addTab("File select", P_FileOptions);

        P_Settings.setOpaque(false);

        L_Info31.setText("How should files be compared?");

        Info32.setText("Parameters:");

        CB_Name.setSelected(true);
        CB_Name.setText("Name");
        CB_Name.setOpaque(false);
        CB_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_AdvanceActionPerformed(evt);
            }
        });

        CB_Size.setSelected(true);
        CB_Size.setText("Size");
        CB_Size.setOpaque(false);
        CB_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_AdvanceActionPerformed(evt);
            }
        });

        CB_Content.setSelected(true);
        CB_Content.setText("Content");
        CB_Content.setOpaque(false);
        CB_Content.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_AdvanceActionPerformed(evt);
            }
        });

        L_Info33.setText("(Slow for large files)");

        TB_Advance.setText("Enable Advance settings");
        TB_Advance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TB_AdvanceActionPerformed(evt);
            }
        });

        L_AdName.setText("Name similarity:");

        SL_Name.setMajorTickSpacing(20);
        SL_Name.setMinimum(20);
        SL_Name.setMinorTickSpacing(1);
        SL_Name.setPaintLabels(true);
        SL_Name.setPaintTicks(true);
        SL_Name.setSnapToTicks(true);
        SL_Name.setToolTipText("100% => Names should be exactly same.");
        SL_Name.setValue(70);
        SL_Name.setEnabled(false);

        L_AdSize.setText("Size similarity:");

        SL_Size.setMajorTickSpacing(20);
        SL_Size.setMinimum(20);
        SL_Size.setMinorTickSpacing(1);
        SL_Size.setPaintLabels(true);
        SL_Size.setPaintTicks(true);
        SL_Size.setSnapToTicks(true);
        SL_Size.setToolTipText("100% => Sizes should be exactly same.");
        SL_Size.setValue(90);
        SL_Size.setEnabled(false);

        L_Content.setText("Content volume:");

        SL_Content.setMajorTickSpacing(20);
        SL_Content.setMinimum(20);
        SL_Content.setMinorTickSpacing(1);
        SL_Content.setPaintLabels(true);
        SL_Content.setPaintTicks(true);
        SL_Content.setSnapToTicks(true);
        SL_Content.setToolTipText("100% => Entire file will be scanned for content.");
        SL_Content.setValue(30);
        SL_Content.setEnabled(false);

        javax.swing.GroupLayout P_AdvanceSettingsLayout = new javax.swing.GroupLayout(P_AdvanceSettings);
        P_AdvanceSettings.setLayout(P_AdvanceSettingsLayout);
        P_AdvanceSettingsLayout.setHorizontalGroup(
            P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_AdvanceSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P_AdvanceSettingsLayout.createSequentialGroup()
                        .addComponent(L_Content, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SL_Content, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(P_AdvanceSettingsLayout.createSequentialGroup()
                        .addGroup(P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(L_AdSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(L_AdName, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SL_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SL_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        P_AdvanceSettingsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L_AdName, L_AdSize, L_Content});

        P_AdvanceSettingsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {SL_Content, SL_Name, SL_Size});

        P_AdvanceSettingsLayout.setVerticalGroup(
            P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_AdvanceSettingsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(SL_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L_AdName))
                .addGap(18, 18, 18)
                .addGroup(P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(L_AdSize)
                    .addComponent(SL_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(L_Content)
                    .addComponent(SL_Content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout P_SettingsLayout = new javax.swing.GroupLayout(P_Settings);
        P_Settings.setLayout(P_SettingsLayout);
        P_SettingsLayout.setHorizontalGroup(
            P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_SettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P_AdvanceSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_Info31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(P_SettingsLayout.createSequentialGroup()
                        .addComponent(Info32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TB_Advance))
                    .addGroup(P_SettingsLayout.createSequentialGroup()
                        .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CB_Name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Size, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Content, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(L_Info33)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        P_SettingsLayout.setVerticalGroup(
            P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_SettingsLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(L_Info31)
                .addGap(30, 30, 30)
                .addComponent(Info32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Size)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_Content)
                    .addComponent(L_Info33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TB_Advance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_AdvanceSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        TabbedPane.addTab("Options", P_Settings);

        javax.swing.GroupLayout P_ContentLayout = new javax.swing.GroupLayout(P_Content);
        P_Content.setLayout(P_ContentLayout);
        P_ContentLayout.setHorizontalGroup(
            P_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_ContentLayout.createSequentialGroup()
                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        P_ContentLayout.setVerticalGroup(
            P_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_ContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(P_Content, java.awt.BorderLayout.CENTER);

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

    private void B_AddSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_AddSourceActionPerformed
        String path = T_SourcePath.getText();

        File file = new File(path);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "This path does not exist. Please check.");
            T_SourcePath.setText("");
        } else if (!file.isDirectory()) {
            JOptionPane.showMessageDialog(this, "The path is not a directory.");
        } else {
            DefaultListModel model = (DefaultListModel) L_Sources.getModel();
            if (model.contains(file.getAbsolutePath()))
                JOptionPane.showMessageDialog(this, "This source is already added.");
            else
                model.addElement(file.getAbsolutePath());
        }
    }//GEN-LAST:event_B_AddSourceActionPerformed

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

    private void T_SourcePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T_SourcePathActionPerformed
        B_AddSourceActionPerformed(evt);
    }//GEN-LAST:event_T_SourcePathActionPerformed

    private void B_RemoveSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_RemoveSourceActionPerformed
        DefaultListModel model = (DefaultListModel) L_Sources.getModel();

        for (int i : L_Sources.getSelectedIndices()) {
            model.removeElementAt(i);
        }
    }//GEN-LAST:event_B_RemoveSourceActionPerformed

    private void CB_TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_TypeActionPerformed
        TB_Extensions.setText("");
        TB_Extensions.setEditable(false);

        JCheckBox cb = (JCheckBox) evt.getSource();

        if (cb == CB_Audios || cb == CB_Videos || cb == CB_Pictures || cb == CB_Others) {
            CB_TypeAll.setSelected(false);
            CB_CustomType.setSelected(false);
        }

        if (cb == CB_TypeAll) {
            CB_Audios.setSelected(false);
            CB_Videos.setSelected(false);
            CB_Pictures.setSelected(false);
            CB_Others.setSelected(false);
            CB_CustomType.setSelected(false);
        }

        if (cb == CB_CustomType) {
            CB_Audios.setSelected(false);
            CB_Videos.setSelected(false);
            CB_Pictures.setSelected(false);
            CB_Others.setSelected(false);
            CB_TypeAll.setSelected(false);
        }

        if (!CB_TypeAll.isSelected()
            && !CB_Audios.isSelected()
            && !CB_Videos.isSelected()
            && !CB_Pictures.isSelected()
            && !CB_Others.isSelected()
            && !CB_CustomType.isSelected()) {
            CB_TypeAll.setSelected(true);
        }

        if (CB_Audios.isSelected())
            TB_Extensions.append(".aif .cda .mid .midi .mp3 .mpa .ogg .wav .wma .wpl ");

        if (CB_Videos.isSelected())
            TB_Extensions.append(".3g2 .3gp .avi .flv .m4v .mkv .mov .mp4 .mpg .mpeg .rm .swf .vob .wmv ");

        if (CB_Pictures.isSelected())
            TB_Extensions.append(".ai .bmp .gif .ico .jpeg .jpg .png .ps .psd .svg .tif .tiff ");

        if (CB_Others.isSelected())
            TB_Extensions.append(".doc .docx .odt .pdf .rtf .txt .wks .wps .wpd .ods .xlr .xls .xlsx .key .odp .pps .ppt .pptx .zip .tar.gz .rpm .7z .rar ");

        if (CB_TypeAll.isSelected())
            TB_Extensions.setText(".*");

        if (CB_CustomType.isSelected()) {
            TB_Extensions.setText("");
            TB_Extensions.setEditable(true);
            TB_Extensions.requestFocus();
        }
    }//GEN-LAST:event_CB_TypeActionPerformed

    private void CB_SizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_SizeActionPerformed
        SP_GreaterThan.setEnabled(CB_CustomSize.isSelected());
        SP_LessThan.setEnabled(CB_CustomSize.isSelected());
        CB_GreaterThan.setEnabled(CB_CustomSize.isSelected());
        CB_LessThan.setEnabled(CB_CustomSize.isSelected());
    }//GEN-LAST:event_CB_SizeActionPerformed

    private void TB_AdvanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TB_AdvanceActionPerformed
        SL_Name.setEnabled(TB_Advance.isSelected());
        SL_Size.setEnabled(TB_Advance.isSelected());
        SL_Content.setEnabled(TB_Advance.isSelected());
        TB_Advance.setText(TB_Advance.isSelected() ? "Disable Advance settings" : "Enable Advance settings");
    }//GEN-LAST:event_TB_AdvanceActionPerformed

    private void CB_AdvanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_AdvanceActionPerformed
        if (!CB_Name.isSelected() && !CB_Size.isSelected() && !CB_Content.isSelected())
            CB_Size.setSelected(true);
    }//GEN-LAST:event_CB_AdvanceActionPerformed

    private void B_DecloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_DecloneActionPerformed
        long customLowerSize, customUpperSize, sizeLowerLimit, sizeUpperLimit;

        customLowerSize = (long) SP_GreaterThan.getValue()
            * (CB_GreaterThan.getSelectedIndex() == 0 ? _1GB
            : CB_GreaterThan.getSelectedIndex() == 1 ? _1MB
            : CB_GreaterThan.getSelectedIndex() == 2 ? _1KB : 1L);

        customUpperSize = (long) SP_LessThan.getValue()
            * (CB_LessThan.getSelectedIndex() == 0 ? _1GB
            : CB_LessThan.getSelectedIndex() == 1 ? _1MB
            : CB_LessThan.getSelectedIndex() == 2 ? _1KB : 1L);

        sizeLowerLimit
            = CB_Small.isSelected() || CB_AnySize.isSelected() ? 0L
            : CB_Medium.isSelected() ? 10L * _1MB
            : CB_Large.isSelected() ? 100L * _1MB
            : customLowerSize;

        sizeUpperLimit
            = CB_Small.isSelected() ? 10L * _1MB
            : CB_Medium.isSelected() ? 100L * _1MB
            : CB_Large.isSelected() || CB_AnySize.isSelected() ? Long.MAX_VALUE
            : customUpperSize;

        CompareSettings compareSettings = AppContext.getCompareSettings();
        compareSettings.setUsingNames(CB_Name.isSelected());
        compareSettings.setUsingSize(CB_Size.isSelected());
        compareSettings.setUsingContent(CB_Content.isSelected());
        compareSettings.setNameDelta(1 - (SL_Name.getValue() / 100F));
        compareSettings.setSizeDelta(1 - (SL_Size.getValue() / 100F));
        compareSettings.setContentVolumePercent(SL_Content.getValue() / 100F);

        SelectionSettings selectionSettings = AppContext.getSelectionSettings();
        selectionSettings.setExtensionCS(TB_Extensions.getText());
        selectionSettings.setSizeLowerLimit(sizeLowerLimit);
        selectionSettings.setSizeUpperLimit(sizeUpperLimit);

        ArrayList<File> sources = new ArrayList<>();
        DefaultListModel model = (DefaultListModel) L_Sources.getModel();
        for (Object sourcePath : model.toArray()) {
            File file = new File(sourcePath.toString());
            if (file.isDirectory())
                sources.add(file);
        }

        TA_Status.setText("");
        CB_Step1.setSelected(false);
        CB_Step2.setSelected(false);
        CB_Step3.setSelected(false);

        ProgressDialog.pack();
        ProgressDialog.setLocationRelativeTo(null);

        DecloneWorker worker = new DecloneWorker(sources);
        worker.execute();

        ProgressDialog.setVisible(true);
    }//GEN-LAST:event_B_DecloneActionPerformed

    // <editor-fold defaultstate="collapsed" desc="GUI Variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BG_FileSize;
    private javax.swing.JButton B_AddSource;
    private javax.swing.JButton B_Browse;
    private javax.swing.JButton B_Declone;
    private javax.swing.JButton B_RemoveSource;
    private javax.swing.JCheckBox CB_AnySize;
    private javax.swing.JCheckBox CB_Audios;
    private javax.swing.JCheckBox CB_Content;
    private javax.swing.JCheckBox CB_CustomSize;
    private javax.swing.JCheckBox CB_CustomType;
    private javax.swing.JComboBox<String> CB_GreaterThan;
    private javax.swing.JCheckBox CB_Large;
    private javax.swing.JComboBox<String> CB_LessThan;
    private javax.swing.JCheckBox CB_Medium;
    private javax.swing.JCheckBox CB_Name;
    private javax.swing.JCheckBox CB_Others;
    private javax.swing.JCheckBox CB_Pictures;
    private javax.swing.JCheckBox CB_Size;
    private javax.swing.JCheckBox CB_Small;
    private javax.swing.JCheckBox CB_Step1;
    private javax.swing.JCheckBox CB_Step2;
    private javax.swing.JCheckBox CB_Step3;
    private javax.swing.JCheckBox CB_TypeAll;
    private javax.swing.JCheckBox CB_Videos;
    private javax.swing.JFileChooser FilePicker;
    private javax.swing.JLabel Info32;
    private javax.swing.JLabel L_AdName;
    private javax.swing.JLabel L_AdSize;
    private javax.swing.JLabel L_Content;
    private javax.swing.JLabel L_Info;
    private javax.swing.JLabel L_Info2;
    private javax.swing.JLabel L_Info21;
    private javax.swing.JLabel L_Info22;
    private javax.swing.JLabel L_Info31;
    private javax.swing.JLabel L_Info33;
    private javax.swing.JLabel L_SizeSign;
    private javax.swing.JList<String> L_Sources;
    private javax.swing.JLabel L_Step1;
    private javax.swing.JLabel L_Step2;
    private javax.swing.JLabel L_Step3;
    private javax.swing.JLabel L_WhereDoWeLook;
    private javax.swing.JMenuItem MI_About;
    private javax.swing.JMenuItem MI_Contact;
    private javax.swing.JMenuItem MI_Donate;
    private javax.swing.JMenuItem MI_License;
    private javax.swing.JMenuItem MI_OpenSource;
    private javax.swing.JMenu M_Help;
    private javax.swing.JMenuBar Menubar;
    private javax.swing.JPanel P_AdvanceSettings;
    private javax.swing.JPanel P_Content;
    private javax.swing.JPanel P_FileOptions;
    private javax.swing.JPanel P_FilterSize;
    private javax.swing.JPanel P_FilterType;
    private javax.swing.JPanel P_ProgressContent;
    private javax.swing.JPanel P_Settings;
    private javax.swing.JPanel P_Sources;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JDialog ProgressDialog;
    private javax.swing.JSlider SL_Content;
    private javax.swing.JSlider SL_Name;
    private javax.swing.JSlider SL_Size;
    private javax.swing.JScrollPane SP_Extensions;
    private javax.swing.JSpinner SP_GreaterThan;
    private javax.swing.JSpinner SP_LessThan;
    private javax.swing.JScrollPane SP_Status;
    private javax.swing.JScrollPane S_Sources;
    private javax.swing.JSeparator Seperator1;
    private javax.swing.JSeparator Seperator2;
    private javax.swing.JSeparator Seperator21;
    private javax.swing.JSeparator Seperator22;
    private javax.swing.JTextArea TA_Status;
    private javax.swing.JToggleButton TB_Advance;
    private javax.swing.JTextArea TB_Extensions;
    private javax.swing.JTextField T_SourcePath;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

}
