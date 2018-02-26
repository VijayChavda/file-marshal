package me.vijaychavda.filemarshal;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import me.vijaychavda.filemarshal.ui.MainFrame;
import me.vijaychavda.filemarshal.workers.DeclutterWorker;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
//            deleteDir(new File("/home/v/Desktop/Decluttered/"));
            new MainFrame().setVisible(true);
        });
    }
    
    private static void deleteDir(File file) {
        try {
            File[] contents = file.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    deleteDir(f);
                }
            }
            Files.delete(file.toPath());
        } catch (IOException e) {
            Logger.getLogger(DeclutterWorker.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
