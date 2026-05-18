/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.h.pass.view;

import com.mycompany.h.pass.objects.GenericDAO;
import com.mycompany.h.pass.objects.LogAbsensi;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;

public class DashboardView extends javax.swing.JFrame {
    
    private String adminName;

    public DashboardView(String nama) {
        this.adminName = nama;
        initComponents();
        jLabel5.setText(nama); // Set Nama Admin
        startClock();
        loadAttendanceData();
        
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    private void startClock() {
    // 1. Buat dulu formatnya
    SimpleDateFormat formatJam = new SimpleDateFormat("HH:mm:ss"); 
    Locale id = new Locale("id", "ID");
    SimpleDateFormat formatTgl = new SimpleDateFormat("EEEE, dd MMMM yyyy", id);
    
    // 2. KUNCI BIAR GAK DELAY: Panggil update sekali secara instan
    Date sekarangLangsung = new Date();
    jLabel4.setText(formatJam.format(sekarangLangsung));
    jLabel14.setText(formatTgl.format(sekarangLangsung));

    // 3. Baru jalankan Timer untuk detak selanjutnya setiap 1 detik
    new Timer(1000, e -> {
        Date sekarang = new Date();
        jLabel4.setText(formatJam.format(sekarang));
        jLabel14.setText(formatTgl.format(sekarang));
    }).start();
}

    public void loadAttendanceData() {
    // 1. Bersihkan panel, tapi jangan hapus judul (jLabel2)
    panelMonitoringGrid.removeAll();
    
    // 2. Tambahkan kembali judul ke panel sebelum kartu-kartu
    // Kita berikan margin sedikit agar tidak terlalu mepet ke atas
    jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 16, 10, 0));
    panelMonitoringGrid.setLayout(new java.awt.BorderLayout());
    panelMonitoringGrid.add(jLabel2, java.awt.BorderLayout.NORTH);

    // 3. Buat panel khusus untuk menampung kartu-kartu (Grid)
    JPanel containerKartu = new JPanel();
    containerKartu.setBackground(java.awt.Color.WHITE);
    containerKartu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

    try {
        GenericDAO<LogAbsensi> logDAO = new GenericDAO<>("log_absensi", LogAbsensi.class);
        java.util.List<LogAbsensi> listLog = logDAO.findWithSort(com.mongodb.client.model.Sorts.descending("waktu_asli"));

        int maxData = 9; 
        int counter = 0;

        for (LogAbsensi log : listLog) {
            if (counter >= maxData) break;

            // --- KARTU MONITORING ---
            JPanel card = new JPanel();
            card.setBackground(java.awt.Color.WHITE);
            card.setPreferredSize(new java.awt.Dimension(305, 95));
            card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
            card.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(235, 235, 235), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));

            JLabel lblNama = new JLabel(log.getNama());
            lblNama.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
            
            JLabel lblDetail = new JLabel(log.getKamar() + " | " + log.getWaktu());
            lblDetail.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 11));
            lblDetail.setForeground(java.awt.Color.GRAY);

            JLabel lblStatus = new JLabel(log.getStatus());
            lblStatus.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 11));
            
            if (log.getStatus().equalsIgnoreCase("Terlambat")) {
                lblStatus.setForeground(new java.awt.Color(204, 0, 0));
            } else {
                lblStatus.setForeground(new java.awt.Color(0, 153, 51));
            }

            card.add(lblNama);
            card.add(javax.swing.Box.createVerticalStrut(5));
            card.add(lblDetail);
            card.add(javax.swing.Box.createVerticalStrut(10));
            card.add(lblStatus);

            containerKartu.add(card);
            counter++;
        }
    } catch (Exception e) {
        System.err.println("Gagal load monitoring: " + e.getMessage());
    }

    // 4. Masukkan container kartu ke bagian tengah panel utama
    panelMonitoringGrid.add(containerKartu, java.awt.BorderLayout.CENTER);

    panelMonitoringGrid.revalidate();
    panelMonitoringGrid.repaint();
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ButtonDashboard = new javax.swing.JButton();
        ButtonPelanggaran = new javax.swing.JButton();
        ButtonLaporan = new javax.swing.JButton();
        ButtonDataPenghuni = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        panelMonitoringGrid = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1440, 1024));
        setMinimumSize(new java.awt.Dimension(1440, 1024));
        setPreferredSize(new java.awt.Dimension(1440, 1024));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(172, 201, 241));
        jPanel1.setMaximumSize(new java.awt.Dimension(360, 1024));
        jPanel1.setMinimumSize(new java.awt.Dimension(360, 1024));
        jPanel1.setPreferredSize(new java.awt.Dimension(360, 1024));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/h/pass/image/logo menu.png"))); // NOI18N

        ButtonDashboard.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonDashboard.setText("Dashboard");
        ButtonDashboard.setPreferredSize(new java.awt.Dimension(256, 96));
        ButtonDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDashboardActionPerformed(evt);
            }
        });

        ButtonPelanggaran.setBackground(new java.awt.Color(81, 131, 199));
        ButtonPelanggaran.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonPelanggaran.setText("Pelanggaran");
        ButtonPelanggaran.setPreferredSize(new java.awt.Dimension(256, 96));
        ButtonPelanggaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPelanggaranActionPerformed(evt);
            }
        });

        ButtonLaporan.setBackground(new java.awt.Color(81, 131, 199));
        ButtonLaporan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonLaporan.setText("Laporan");
        ButtonLaporan.setPreferredSize(new java.awt.Dimension(256, 96));
        ButtonLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLaporanActionPerformed(evt);
            }
        });

        ButtonDataPenghuni.setBackground(new java.awt.Color(81, 131, 199));
        ButtonDataPenghuni.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonDataPenghuni.setText("DataPenghuni");
        ButtonDataPenghuni.setPreferredSize(new java.awt.Dimension(256, 96));
        ButtonDataPenghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDataPenghuniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1))
                    .addComponent(ButtonLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPelanggaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonDataPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(ButtonDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonDataPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonPelanggaran, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(456, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/h/pass/image/profile.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 20, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel4.setText("23:40");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel14.setText("Senin,13 Mei 2026");

        jButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton1.setText("Absen");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Silahkan klik absen untuk absen");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel14))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(171, 171, 171))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel8))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 960, 180));

        panelMonitoringGrid.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setText("Monitoring Live");

        javax.swing.GroupLayout panelMonitoringGridLayout = new javax.swing.GroupLayout(panelMonitoringGrid);
        panelMonitoringGrid.setLayout(panelMonitoringGridLayout);
        panelMonitoringGridLayout.setHorizontalGroup(
            panelMonitoringGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMonitoringGridLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addContainerGap(731, Short.MAX_VALUE))
        );
        panelMonitoringGridLayout.setVerticalGroup(
            panelMonitoringGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMonitoringGridLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addContainerGap(720, Short.MAX_VALUE))
        );

        getContentPane().add(panelMonitoringGrid, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 960, 770));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Username");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(784, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(71, 71, 71))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 960, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDashboardActionPerformed
        loadAttendanceData();
    }//GEN-LAST:event_ButtonDashboardActionPerformed

    private void ButtonPelanggaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPelanggaranActionPerformed
        
    }//GEN-LAST:event_ButtonPelanggaranActionPerformed

    private void ButtonLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLaporanActionPerformed
        
    }//GEN-LAST:event_ButtonLaporanActionPerformed

    private void ButtonDataPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDataPenghuniActionPerformed
        new DataPenghuni(adminName).setVisible(true);
        this.dispose();       
    }//GEN-LAST:event_ButtonDataPenghuniActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            TapCard scan = new TapCard(this, true); 
            scan.setLocationRelativeTo(this);
            scan.setVisible(true);
            loadAttendanceData(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
                                       
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new DashboardView("Admin").setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonDashboard;
    private javax.swing.JButton ButtonDataPenghuni;
    private javax.swing.JButton ButtonLaporan;
    private javax.swing.JButton ButtonPelanggaran;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel panelMonitoringGrid;
    // End of variables declaration//GEN-END:variables
}
