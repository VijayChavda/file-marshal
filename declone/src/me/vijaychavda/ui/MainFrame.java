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
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import me.vijaychavda.AppContext;
import me.vijaychavda.CompareSettings;
import me.vijaychavda.FileInfo;
import me.vijaychavda.FileInfoComparer;
import me.vijaychavda.SelectionSettings;

public class MainFrame extends javax.swing.JFrame {

    private static final long _1GB = 1073741824L;
    private static final long _1MB = 1048576L;
    private static final long _1KB = 1024L;

    private final ArrayList<File> sources;

    public MainFrame() {
        initComponents();

        sources = new ArrayList<>();
        B_RemoveSource.setVisible(false);
        L_Sources.addListSelectionListener(
            e -> B_RemoveSource.setVisible(L_Sources.getSelectedIndices().length > 0)
        );
    }

    private void setSelectionSettings() {
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

        SelectionSettings settings = AppContext.getSelectionSettings();
        settings.setExtensionCS(TB_Extensions.getText());
        settings.setSizeLowerLimit(sizeLowerLimit);
        settings.setSizeUpperLimit(sizeUpperLimit);
    }

    private void setCompareSettings() {
        CompareSettings settings = AppContext.getCompareSettings();
        settings.setUsingNames(CB_Name.isSelected());
        settings.setUsingSize(CB_Size.isSelected());
        settings.setUsingContent(CB_Content.isSelected());

        settings.setNameSimilar(RB_Name1.isSelected());
        settings.setNameCommonWords(RB_Name2.isSelected());
        settings.setNameSimilarCommonWords(RB_Name3.isSelected());
        settings.setNameExactlySame(RB_Name4.isSelected());

        settings.setSizeNoHugeDifference(RB_Size1.isSelected());
        settings.setSizeAlmostSame(RB_Size2.isSelected());
        settings.setSizeExactlySame(RB_Size3.isSelected());

        settings.setContent2p(RB_Content1.isSelected());
        settings.setContent10p(RB_Content2.isSelected());
        settings.setContent20p(RB_Content3.isSelected());
        settings.setContent50p(RB_Content4.isSelected());
        settings.setContent100p(RB_Content5.isSelected());
    }

    private void setSources() {
        sources.clear();

        DefaultListModel model = (DefaultListModel) L_Sources.getModel();
        for (Object sourcePath : model.toArray()) {
            File file = new File(sourcePath.toString());
            if (file.isDirectory())
                sources.add(file);
        }
    }

    public class DecloneWorker extends SwingWorker<Void, String> {

        @Override
        protected Void doInBackground() {
            try {
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
                        FileInfo info = FileInfo.init(path);
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
                CompareSettings settings = AppContext.getCompareSettings();
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
                        if (FileInfoComparer.areSame(fileInfo, set.get(0))) {
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

    // <editor-fold defaultstate="collapsed" desc="GUI Code">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FilePicker = new javax.swing.JFileChooser();
        ProgressDialog = new javax.swing.JDialog();
        P_ProgressContent = new javax.swing.JPanel();
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
        BG_FileSize = new javax.swing.ButtonGroup();
        RBG_Name = new javax.swing.ButtonGroup();
        RBG_Size = new javax.swing.ButtonGroup();
        RBG_Content = new javax.swing.ButtonGroup();
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
        TB_Advance = new javax.swing.JToggleButton();
        P_AdvanceSettings = new javax.swing.JPanel();
        P_CustomNameSize = new javax.swing.JPanel();
        L_Name = new javax.swing.JLabel();
        L_Size = new javax.swing.JLabel();
        RB_Name1 = new javax.swing.JRadioButton();
        RB_Name2 = new javax.swing.JRadioButton();
        RB_Name3 = new javax.swing.JRadioButton();
        RB_Name4 = new javax.swing.JRadioButton();
        RB_Size1 = new javax.swing.JRadioButton();
        RB_Size2 = new javax.swing.JRadioButton();
        RB_Size3 = new javax.swing.JRadioButton();
        P_CustomContent = new javax.swing.JPanel();
        L_Content = new javax.swing.JLabel();
        RB_Content1 = new javax.swing.JRadioButton();
        RB_Content2 = new javax.swing.JRadioButton();
        RB_Content3 = new javax.swing.JRadioButton();
        RB_Content4 = new javax.swing.JRadioButton();
        RB_Content5 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CB_Name = new javax.swing.JCheckBox();
        CB_Size = new javax.swing.JCheckBox();
        CB_Content = new javax.swing.JCheckBox();
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
                            .addComponent(CB_Step4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Step3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(L_Step1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(L_Step2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(L_Step4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(L_Step3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CB_Step3)
                    .addComponent(L_Step3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_ProgressContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Step4)
                    .addComponent(L_Step4))
                .addGap(18, 18, 18)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SP_Status, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addContainerGap())
        );

        P_ProgressContentLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_Step1, L_Step1});

        P_ProgressContentLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_Step2, L_Step2});

        P_ProgressContentLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CB_Step4, L_Step4});

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

        T_SourcePath.setText("/media/v/2TB_Vijay_9409472874/.Collection/Movies");
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
            .addGroup(P_SourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_SourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P_SourcesLayout.createSequentialGroup()
                        .addComponent(S_Sources)
                        .addContainerGap())
                    .addGroup(P_SourcesLayout.createSequentialGroup()
                        .addComponent(L_WhereDoWeLook, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_SourcesLayout.createSequentialGroup()
                        .addComponent(B_RemoveSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(B_Declone)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_SourcesLayout.createSequentialGroup()
                        .addComponent(T_SourcePath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_AddSource)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_Browse)
                        .addContainerGap())))
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
                .addComponent(S_Sources, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        CB_AnySize.setSelected(true);
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
                .addGap(18, 18, 18)
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
                .addGap(18, 18, 18)
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
            .addGroup(P_FileOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_FileOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(L_Info2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(P_FileOptionsLayout.createSequentialGroup()
                        .addComponent(P_FilterType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Seperator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P_FilterSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        L_Info31.setText("Which parameters should be considered to consider files as duplicates?");

        TB_Advance.setText("Enable custom settings");
        TB_Advance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TB_AdvanceActionPerformed(evt);
            }
        });

        P_AdvanceSettings.setOpaque(false);

        L_Name.setText("Name");

        L_Size.setText("Size");

        RBG_Name.add(RB_Name1);
        RB_Name1.setText("Similar");
        RB_Name1.setEnabled(false);

        RBG_Name.add(RB_Name2);
        RB_Name2.setSelected(true);
        RB_Name2.setText("Common words");
        RB_Name2.setEnabled(false);

        RBG_Name.add(RB_Name3);
        RB_Name3.setText("Similar common words");
        RB_Name3.setEnabled(false);

        RBG_Name.add(RB_Name4);
        RB_Name4.setText("Exactly same");
        RB_Name4.setEnabled(false);

        RBG_Size.add(RB_Size1);
        RB_Size1.setSelected(true);
        RB_Size1.setText("No huge difference");
        RB_Size1.setEnabled(false);

        RBG_Size.add(RB_Size2);
        RB_Size2.setText("Almost same");
        RB_Size2.setEnabled(false);

        RBG_Size.add(RB_Size3);
        RB_Size3.setText("Exactly same");
        RB_Size3.setEnabled(false);

        javax.swing.GroupLayout P_CustomNameSizeLayout = new javax.swing.GroupLayout(P_CustomNameSize);
        P_CustomNameSize.setLayout(P_CustomNameSizeLayout);
        P_CustomNameSizeLayout.setHorizontalGroup(
            P_CustomNameSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_CustomNameSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_CustomNameSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_Name)
                    .addComponent(L_Size)
                    .addGroup(P_CustomNameSizeLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(P_CustomNameSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RB_Size1)
                            .addComponent(RB_Name2)
                            .addComponent(RB_Name4)
                            .addComponent(RB_Size2)
                            .addComponent(RB_Size3)
                            .addComponent(RB_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RB_Name3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P_CustomNameSizeLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {L_Name, L_Size});

        P_CustomNameSizeLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {RB_Name1, RB_Name2, RB_Name4, RB_Size1, RB_Size2, RB_Size3});

        P_CustomNameSizeLayout.setVerticalGroup(
            P_CustomNameSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_CustomNameSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Name1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Name2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Name3)
                .addGap(3, 3, 3)
                .addComponent(RB_Name4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(L_Size)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RB_Size1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Size2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RB_Size3)
                .addContainerGap())
        );

        L_Content.setText("Content");

        RBG_Content.add(RB_Content1);
        RB_Content1.setSelected(true);
        RB_Content1.setText("2 %");
        RB_Content1.setEnabled(false);

        RBG_Content.add(RB_Content2);
        RB_Content2.setText("10 %");
        RB_Content2.setEnabled(false);

        RBG_Content.add(RB_Content3);
        RB_Content3.setText("20 %");
        RB_Content3.setEnabled(false);

        RBG_Content.add(RB_Content4);
        RB_Content4.setText("50 %");
        RB_Content4.setEnabled(false);

        RBG_Content.add(RB_Content5);
        RB_Content5.setText("100 %");
        RB_Content5.setEnabled(false);

        jLabel4.setText("Least");

        jLabel5.setText("Very less");

        jLabel6.setText("Less");

        jLabel7.setText("Half");

        jLabel8.setText("Complete");

        javax.swing.GroupLayout P_CustomContentLayout = new javax.swing.GroupLayout(P_CustomContent);
        P_CustomContent.setLayout(P_CustomContentLayout);
        P_CustomContentLayout.setHorizontalGroup(
            P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_CustomContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_Content)
                    .addGroup(P_CustomContentLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7))
                            .addGroup(P_CustomContentLayout.createSequentialGroup()
                                .addComponent(RB_Content5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)))))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        P_CustomContentLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        P_CustomContentLayout.setVerticalGroup(
            P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_CustomContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(L_Content)
                .addGap(24, 24, 24)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(P_CustomContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Content5)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout P_AdvanceSettingsLayout = new javax.swing.GroupLayout(P_AdvanceSettings);
        P_AdvanceSettings.setLayout(P_AdvanceSettingsLayout);
        P_AdvanceSettingsLayout.setHorizontalGroup(
            P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_AdvanceSettingsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(P_CustomNameSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_CustomContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        P_AdvanceSettingsLayout.setVerticalGroup(
            P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_AdvanceSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_AdvanceSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(P_CustomContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(P_CustomNameSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CB_Name.setSelected(true);
        CB_Name.setText("Name");
        CB_Name.setOpaque(false);
        CB_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_AdvanceActionPerformed(evt);
            }
        });

        CB_Size.setText("Size");
        CB_Size.setOpaque(false);
        CB_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_AdvanceActionPerformed(evt);
            }
        });

        CB_Content.setText("Content (Slow for large files)");
        CB_Content.setOpaque(false);
        CB_Content.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_AdvanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout P_SettingsLayout = new javax.swing.GroupLayout(P_Settings);
        P_Settings.setLayout(P_SettingsLayout);
        P_SettingsLayout.setHorizontalGroup(
            P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_SettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P_AdvanceSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_SettingsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TB_Advance))
                    .addGroup(P_SettingsLayout.createSequentialGroup()
                        .addGroup(P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CB_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Size, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_Content, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(L_Info31))
                        .addGap(0, 77, Short.MAX_VALUE)))
                .addContainerGap())
        );

        P_SettingsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {CB_Content, CB_Name, CB_Size});

        P_SettingsLayout.setVerticalGroup(
            P_SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_SettingsLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(L_Info31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Size)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Content)
                .addGap(12, 12, 12)
                .addComponent(TB_Advance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(P_AdvanceSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Options", P_Settings);

        javax.swing.GroupLayout P_ContentLayout = new javax.swing.GroupLayout(P_Content);
        P_Content.setLayout(P_ContentLayout);
        P_ContentLayout.setHorizontalGroup(
            P_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_ContentLayout.createSequentialGroup()
                .addComponent(TabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        RB_Content1.setEnabled(TB_Advance.isSelected());
        RB_Content2.setEnabled(TB_Advance.isSelected());
        RB_Content3.setEnabled(TB_Advance.isSelected());
        RB_Content4.setEnabled(TB_Advance.isSelected());
        RB_Content5.setEnabled(TB_Advance.isSelected());
        RB_Name1.setEnabled(TB_Advance.isSelected());
        RB_Name2.setEnabled(TB_Advance.isSelected());
        RB_Name4.setEnabled(TB_Advance.isSelected());
        RB_Name3.setEnabled(TB_Advance.isSelected());
        RB_Size1.setEnabled(TB_Advance.isSelected());
        RB_Size2.setEnabled(TB_Advance.isSelected());
        RB_Size3.setEnabled(TB_Advance.isSelected());

        TB_Advance.setText(TB_Advance.isSelected() ? "Disable Advance settings" : "Enable Advance settings");
    }//GEN-LAST:event_TB_AdvanceActionPerformed

    private void CB_AdvanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_AdvanceActionPerformed
        if (!CB_Name.isSelected() && !CB_Size.isSelected() && !CB_Content.isSelected())
            CB_Name.setSelected(true);
    }//GEN-LAST:event_CB_AdvanceActionPerformed

    private void B_DecloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_DecloneActionPerformed
        setSelectionSettings();

        setCompareSettings();

        setSources();

        TA_Status.setText("");
        CB_Step1.setSelected(false);
        CB_Step2.setSelected(false);
        CB_Step3.setSelected(false);
        CB_Step4.setSelected(false);

        DecloneWorker worker = new DecloneWorker();
        worker.execute();

        ProgressDialog.pack();
        ProgressDialog.setLocationRelativeTo(null);
        ProgressDialog.setVisible(true);
    }//GEN-LAST:event_B_DecloneActionPerformed

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
    private javax.swing.JCheckBox CB_Step4;
    private javax.swing.JCheckBox CB_TypeAll;
    private javax.swing.JCheckBox CB_Videos;
    private javax.swing.JFileChooser FilePicker;
    private javax.swing.JLabel L_Content;
    private javax.swing.JLabel L_Info;
    private javax.swing.JLabel L_Info2;
    private javax.swing.JLabel L_Info21;
    private javax.swing.JLabel L_Info22;
    private javax.swing.JLabel L_Info31;
    private javax.swing.JLabel L_Name;
    private javax.swing.JLabel L_Size;
    private javax.swing.JLabel L_SizeSign;
    private javax.swing.JList<String> L_Sources;
    private javax.swing.JLabel L_Step1;
    private javax.swing.JLabel L_Step2;
    private javax.swing.JLabel L_Step3;
    private javax.swing.JLabel L_Step4;
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
    private javax.swing.JPanel P_CustomContent;
    private javax.swing.JPanel P_CustomNameSize;
    private javax.swing.JPanel P_FileOptions;
    private javax.swing.JPanel P_FilterSize;
    private javax.swing.JPanel P_FilterType;
    private javax.swing.JPanel P_ProgressContent;
    private javax.swing.JPanel P_Settings;
    private javax.swing.JPanel P_Sources;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JDialog ProgressDialog;
    private javax.swing.ButtonGroup RBG_Content;
    private javax.swing.ButtonGroup RBG_Name;
    private javax.swing.ButtonGroup RBG_Size;
    private javax.swing.JRadioButton RB_Content1;
    private javax.swing.JRadioButton RB_Content2;
    private javax.swing.JRadioButton RB_Content3;
    private javax.swing.JRadioButton RB_Content4;
    private javax.swing.JRadioButton RB_Content5;
    private javax.swing.JRadioButton RB_Name1;
    private javax.swing.JRadioButton RB_Name2;
    private javax.swing.JRadioButton RB_Name3;
    private javax.swing.JRadioButton RB_Name4;
    private javax.swing.JRadioButton RB_Size1;
    private javax.swing.JRadioButton RB_Size2;
    private javax.swing.JRadioButton RB_Size3;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

}
