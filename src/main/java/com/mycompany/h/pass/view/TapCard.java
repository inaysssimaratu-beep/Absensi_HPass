/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.h.pass.view;

import com.mycompany.h.pass.objects.GenericDAO;
import com.mycompany.h.pass.objects.LogAbsensi;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TapCard extends javax.swing.JDialog {

    // Deklarasikan logger agar tidak error di bagian main
    private static final Logger logger = Logger.getLogger(TapCard.class.getName());

    public TapCard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        // 1. Styling Background
        getContentPane().setBackground(new Color(243, 251, 255));
        jPanel2.setBackground(new Color(243, 251, 255));
        
        // 2. Membuat Tombol Jadi Bundar
        btnTapSimulasi.setText(""); 
        btnTapSimulasi.setBackground(new Color(211, 211, 211));
        btnTapSimulasi.setContentAreaFilled(false);
        btnTapSimulasi.setOpaque(true);
        btnTapSimulasi.setBorderPainted(false);
        btnTapSimulasi.setFocusPainted(false);
        btnTapSimulasi.setBorder(new javax.swing.border.LineBorder(new Color(211, 211, 211), 50, true));
        
        setLocationRelativeTo(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTapSimulasi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel5.setText("Username");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/h/pass/image/profile.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(333, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("Silakan Dekatkan Kartu Anda ");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/h/pass/image/Group 8 (1).png"))); // NOI18N

        btnTapSimulasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTapSimulasiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btnTapSimulasi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTapSimulasi, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTapSimulasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTapSimulasiActionPerformed
        try {
        GenericDAO<LogAbsensi> logDAO = new GenericDAO<>("log_absensi", LogAbsensi.class);
        
        // 1. Ambil waktu saat ini
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int jam = cal.get(java.util.Calendar.HOUR_OF_DAY); // Mengambil jam format 24 jam
        
        // 2. Tentukan Status berdasarkan jam (22:00 - 04:00)
        String status;
        if (jam >= 22 || jam < 4) {
            status = "Terlambat";
        } else {
            status = "Tepat Waktu";
        }

        // 3. Buat Object LogAbsensi
        LogAbsensi baru = new LogAbsensi();
        baru.setNama("Dwi Riski Ariyanto"); 
        baru.setKamar("2.28");
        baru.setTipe("Masuk");
        baru.setWaktu(new SimpleDateFormat("HH:mm").format(new Date()));
        baru.setWaktu_asli(new Date()); 
        baru.setStatus(status); // Menggunakan variabel status hasil pengecekan

        // 4. Simpan ke MongoDB
        logDAO.insert(baru);
        
        // 5. Tutup Jendela
        this.dispose(); 
        
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Gagal Absen: " + e.getMessage());
    }
    }//GEN-LAST:event_btnTapSimulasiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TapCard dialog = new TapCard(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTapSimulasi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
