/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.h.pass.view;

import javax.swing.JOptionPane;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import com.mycompany.h.pass.objects.Admin;
import com.mycompany.h.pass.objects.GenericDAO;
import com.mycompany.h.pass.objects.MongoManager;
public class LoginView extends javax.swing.JFrame {

    public LoginView() {
        initComponents();
        // Memaksa Absolute Layout aktif
        getContentPane().setLayout(null); 
        
        // Langsung layar penuh saat dijalankan
        setExtendedState(MAXIMIZED_BOTH);
        
        testKoneksiDB();
    }

    private void testKoneksiDB() {
        try {
            if (MongoManager.getDatabase() != null) {
                System.out.println("MongoDB Connected!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Offline! Nyalakan MongoDB dulu.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelLogo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(243, 251, 255));
        setMaximumSize(new java.awt.Dimension(1440, 1024));
        setMinimumSize(new java.awt.Dimension(1440, 1240));
        setPreferredSize(new java.awt.Dimension(1440, 1024));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(172, 201, 241));
        jPanel1.setMaximumSize(new java.awt.Dimension(420, 1240));
        jPanel1.setMinimumSize(new java.awt.Dimension(420, 1024));
        jPanel1.setPreferredSize(new java.awt.Dimension(420, 1024));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/h/pass/image/logo nama.png"))); // NOI18N
        jLabelLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 310, -1));

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel4.setText("Selamat Datang Di");

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel5.setText("Harkat-Pass");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Nama Lengkap");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Password");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNama)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(403, 403, 403)
                        .addComponent(btnLogin)))
                .addGap(558, 558, 558))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addComponent(btnLogin))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String inputUser = txtNama.getText().trim();
        String inputPass = new String(txtPassword.getPassword());

        // 1. Validasi Input Kosong
        if (inputUser.isEmpty() || inputPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan Sandi harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // 2. Inisiasi DAO untuk koleksi 'admin'
            GenericDAO<Admin> adminDAO = new GenericDAO<>("admin", Admin.class);
            
            // 3. Buat Filter (Cari yang Nama DAN Sandi-nya cocok)
            Bson filter = Filters.and(
                Filters.eq("nama", inputUser), 
                Filters.eq("sandi", inputPass)
            );
            
            Admin hasil = adminDAO.findOne(filter);

            // 4. Cek Hasil Query
            if (hasil != null) {
                JOptionPane.showMessageDialog(this, 
                    "Login Berhasil!\nSelamat Datang, " + hasil.getNama(), 
                    "H-Pass Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // 5. Pindah ke DashboardView
                DashboardView dashboard = new DashboardView(hasil.getNama());
                dashboard.setVisible(true);
                
                // 6. Tutup form Login
                this.dispose(); 
                
            } else {
                JOptionPane.showMessageDialog(this, "Nama atau Sandi salah!", "Gagal Login", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi Kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLoginActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginView().setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNama;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
