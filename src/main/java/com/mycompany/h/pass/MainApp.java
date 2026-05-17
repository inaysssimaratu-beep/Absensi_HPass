package com.mycompany.h.pass;

import com.mycompany.h.pass.objects.MongoManager;
import com.mycompany.h.pass.view.LoginView;
import javax.swing.JOptionPane;

public class MainApp {
    public static void main(String[] args) {
        try {
            // 1. Inisialisasi Koneksi ke MongoDB
            System.out.println("Memulai sistem H-Pass...");
            
            // Mencoba mengambil database (memicu koneksi di MongoManager)
            if (MongoManager.getDatabase() != null) {
                System.out.println("Koneksi Database Siap!");
            }

            // 2. Memunculkan Tampilan Login secara responsif
            java.awt.EventQueue.invokeLater(() -> {
                new LoginView().setVisible(true);
            });
            
        } catch (Exception e) {
            // Jika database mati atau ada error sistem lainnya
            JOptionPane.showMessageDialog(null, 
                "Terjadi masalah saat memulai aplikasi:\n" + e.getMessage(), 
                "Sistem Error", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}