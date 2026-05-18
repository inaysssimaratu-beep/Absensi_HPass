/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.h.pass.view;
 
import com.mycompany.h.pass.objects.GenericDAO; 
import com.mycompany.h.pass.objects.Penghuni; 
import com.mongodb.client.model.Filters; 
import org.bson.conversions.Bson; 
import java.awt.BorderLayout; 
import javax.swing.*; 
import java.awt.*; 
import java.util.List; 
 
/**
 *
 * @author LENOVO
 */
public class DataPenghuni extends javax.swing.JFrame {

    private String adminName; 
    private JPanel panelList; 
    private JPanel panelDetail;

    public DataPenghuni(String nama) {
        this.adminName = nama; 
        initComponents(); 
        initManualLayout(); 
        loadDataPenghuni(); 

        jLabel5.setText(nama); // Menampilkan nama admin di label
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    // ===================== INIT LAYOUT =====================
    private void initManualLayout() {
        // 1. Ubah panel utama jadi CardLayout dan hapus kunci ukuran dari NetBeans
        panelDataGrid.setLayout(new CardLayout());
        panelDataGrid.setPreferredSize(null); 

        // 2. Setup Panel List (Grid Kartu)
        panelList = new JPanel(new GridLayout(0, 3, 20, 20));
        panelList.setBackground(new Color(245, 247, 251));
        panelList.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 3. Wrapper (Agar kartu selalu rapat di atas)
        JPanel listWrapper = new JPanel(new BorderLayout());
        listWrapper.add(panelList, BorderLayout.NORTH);
        listWrapper.setBackground(new Color(245, 247, 251));

        // 4. Setup Panel Detail
        panelDetail = new JPanel(null);
        panelDetail.setBackground(Color.WHITE);

        // 5. Masukkan keduanya ke CardLayout
        panelDataGrid.add(listWrapper, "LIST");
        panelDataGrid.add(panelDetail, "DETAIL");

        // 6. Masukkan CardLayout ini ke dalam ScrollPane
        scrollPaneGrid1.setViewportView(panelDataGrid);
        scrollPaneGrid1.getVerticalScrollBar().setUnitIncrement(20);
        scrollPaneGrid1.setBorder(null);
    }

    // ===================== LOAD DATA =====================
    public void loadDataPenghuni() {
        panelList.removeAll();

        try {
            GenericDAO<Penghuni> dao = new GenericDAO<>("penghuni", Penghuni.class);
            String keyword = txtSearch.getText().trim();
            String fakultas = jComboBox1.getSelectedItem().toString();

            Bson filter = Filters.or(
                    Filters.regex("nama", keyword, "i"),
                    Filters.regex("nim", keyword, "i")
            );

            if (!fakultas.equals("Semua Fakultas")) {
                String target = fakultas.contains("DKV") ? "DKV" : fakultas;
                filter = Filters.and(filter, Filters.eq("fakultas", target));
            }

            List<Penghuni> data = dao.findMany(filter, com.mongodb.client.model.Sorts.ascending("kamar"), 100);

            for (Penghuni p : data) {
                panelList.add(createCard(p));
            }

            // HITUNG TINGGI AGAR BISA SCROLL
            int rows = (int) Math.ceil(data.size() / 3.0);
            panelList.setPreferredSize(new Dimension(900, (rows * 170) + 100));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Paksa CardLayout kembali menampilkan "LIST"
        ((CardLayout) panelDataGrid.getLayout()).show(panelDataGrid, "LIST");

        panelDataGrid.revalidate();
        panelDataGrid.repaint();
    }

    // ===================== CARD =====================
    private JPanel createCard(Penghuni p) {
        JPanel card = new JPanel(null);
        card.setPreferredSize(new Dimension(280, 150));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel name = new JLabel(p.getNama() == null ? "Tanpa Nama" : p.getNama());
        name.setFont(new Font("Arial Black", Font.BOLD, 14));
        name.setBounds(20, 20, 250, 20);

        JLabel nim = new JLabel("NIM: " + (p.getNim() == null ? "-" : p.getNim()));
        nim.setBounds(20, 50, 250, 20);

        JLabel kamar = new JLabel("Kamar: " + (p.getKamar() == null ? "-" : p.getKamar()));
        kamar.setBounds(20, 80, 250, 20);

        card.add(name);
        card.add(nim);
        card.add(kamar);

        // Hover Effect Tipis
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) { tampilkanDetailPenghuni(p); }
            public void mouseEntered(java.awt.event.MouseEvent e) { card.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2)); }
            public void mouseExited(java.awt.event.MouseEvent e) { card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); }
        });

        return card;
    }

    // ===================== DETAIL VIEW =====================
    private void tampilkanDetailPenghuni(Penghuni p) {
        panelDetail.removeAll();

        // Tombol Kembali
        JButton back = new JButton("← Kembali");
        back.setBounds(20, 20, 120, 35);
        back.addActionListener(e -> loadDataPenghuni());
        panelDetail.add(back);

        // Judul
        JLabel title = new JLabel("Detail Penghuni");
        title.setFont(new Font("Arial Black", Font.BOLD, 28));
        title.setBounds(20, 80, 500, 40);
        panelDetail.add(title);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 130, 900, 2);
        panelDetail.add(separator);

        // Isi Data
        String[][] info = {
                {"UID RFID", p.getUidRFID()}, {"Nama", p.getNama()}, {"NIM", p.getNim()},
                {"Kamar", p.getKamar()}, {"Fakultas", p.getFakultas()}, {"Gender", p.getGender()}
        };

        int y = 160;
        for (String[] f : info) {
            JLabel lblKey = new JLabel(f[0]);
            lblKey.setFont(new Font("Arial", Font.BOLD, 16));
            lblKey.setBounds(40, y, 150, 30);
            
            JLabel lblVal = new JLabel(": " + (f[1] == null ? "-" : f[1]));
            lblVal.setFont(new Font("Arial", Font.PLAIN, 16));
            lblVal.setBounds(200, y, 600, 30);
            
            panelDetail.add(lblKey);
            panelDetail.add(lblVal);
            y += 45;
        }

        // Tombol Edit & Hapus
        JButton edit = new JButton("Edit");
        edit.setBounds(40, y + 20, 120, 40);
        edit.setBackground(new Color(52, 152, 219));
        edit.setForeground(Color.WHITE);
        edit.addActionListener(e -> { new FormPenghuniDialog(this, true, p).setVisible(true); loadDataPenghuni(); });
        panelDetail.add(edit);

        JButton hapus = new JButton("Hapus");
        hapus.setBounds(180, y + 20, 120, 40);
        hapus.setBackground(new Color(231, 76, 60));
        hapus.setForeground(Color.WHITE);
        hapus.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Hapus " + p.getNama() + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION) == 0) {
                try {
                    new GenericDAO<>("penghuni", Penghuni.class).delete(Filters.eq("nim", p.getNim()));
                    loadDataPenghuni();
                } catch (Exception ex) { ex.printStackTrace(); }
            }
        });
        panelDetail.add(hapus);

        // Ubah tampilan jadi DETAIL
        ((CardLayout) panelDataGrid.getLayout()).show(panelDataGrid, "DETAIL");

        // Paksa scrollbar kembali ke paling atas
        SwingUtilities.invokeLater(() -> scrollPaneGrid1.getVerticalScrollBar().setValue(0));
    }

    // ===================== FORM CRUD =====================
    public class FormPenghuniDialog extends JDialog {
        public FormPenghuniDialog(Frame parent, boolean modal, Penghuni p) {
            super(parent, modal);
            setTitle(p == null ? "Tambah Data" : "Edit Data");
            setSize(400, 450);
            setLocationRelativeTo(parent);
            JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
            form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JTextField tU = new JTextField(p != null ? p.getUidRFID() : "");
            JTextField tN = new JTextField(p != null ? p.getNama() : "");
            JTextField tI = new JTextField(p != null ? p.getNim() : "");
            JTextField tK = new JTextField(p != null ? p.getKamar() : "");
            JTextField tF = new JTextField(p != null ? p.getFakultas() : "");
            JComboBox<String> cbG = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});

            if (p != null) {
                cbG.setSelectedItem(p.getGender());
                tI.setEditable(false); tU.setEditable(false);
            }

            form.add(new JLabel("UID RFID")); form.add(tU);
            form.add(new JLabel("Nama")); form.add(tN);
            form.add(new JLabel("NIM")); form.add(tI);
            form.add(new JLabel("Kamar")); form.add(tK);
            form.add(new JLabel("Fakultas")); form.add(tF);
            form.add(new JLabel("Gender")); form.add(cbG);

            JButton save = new JButton("Simpan");
            save.addActionListener(e -> {
                try {
                    GenericDAO<Penghuni> dao = new GenericDAO<>("penghuni", Penghuni.class);
                    Penghuni d = new Penghuni(tU.getText(), tI.getText(), tN.getText(), tK.getText(), tF.getText(), cbG.getSelectedItem().toString());
                    if (p == null) dao.insert(d); else dao.update(Filters.eq("nim", p.getNim()), d);
                    dispose();
                } catch (Exception ex) { ex.printStackTrace(); }
            });

            add(form, BorderLayout.CENTER);
            add(save, BorderLayout.SOUTH);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDataGrid = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ButtonDashboard = new javax.swing.JButton();
        ButtonPelanggaran = new javax.swing.JButton();
        ButtonLaporan = new javax.swing.JButton();
        ButtonDataPenghuni = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        scrollPaneGrid1 = new javax.swing.JScrollPane();
        containerKartu1 = new javax.swing.JPanel();

        panelDataGrid.setBackground(new java.awt.Color(255, 255, 255));
        panelDataGrid.setMaximumSize(new java.awt.Dimension(940, 565));
        panelDataGrid.setMinimumSize(new java.awt.Dimension(940, 565));
        panelDataGrid.setPreferredSize(new java.awt.Dimension(940, 565));

        javax.swing.GroupLayout panelDataGridLayout = new javax.swing.GroupLayout(panelDataGrid);
        panelDataGrid.setLayout(panelDataGridLayout);
        panelDataGridLayout.setHorizontalGroup(
            panelDataGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 948, Short.MAX_VALUE)
        );
        panelDataGridLayout.setVerticalGroup(
            panelDataGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 650));

        jPanel1.setBackground(new java.awt.Color(172, 201, 241));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/h/pass/image/logo menu.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        ButtonDashboard.setBackground(new java.awt.Color(81, 131, 199));
        ButtonDashboard.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonDashboard.setText("Dashboard");
        ButtonDashboard.setPreferredSize(new java.awt.Dimension(256, 96));
        ButtonDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 70));

        ButtonPelanggaran.setBackground(new java.awt.Color(81, 131, 199));
        ButtonPelanggaran.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonPelanggaran.setText("Pelanggaran");
        ButtonPelanggaran.setPreferredSize(new java.awt.Dimension(256, 96));
        ButtonPelanggaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPelanggaranActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonPelanggaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, 70));

        ButtonLaporan.setBackground(new java.awt.Color(81, 131, 199));
        ButtonLaporan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonLaporan.setText("Laporan");
        ButtonLaporan.setPreferredSize(new java.awt.Dimension(256, 96));
        ButtonLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLaporanActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, 70));

        ButtonDataPenghuni.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ButtonDataPenghuni.setText("DataPenghuni");
        ButtonDataPenghuni.setPreferredSize(new java.awt.Dimension(256, 96));
        ButtonDataPenghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDataPenghuniActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonDataPenghuni, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, 70));

        jButton1.setBackground(new java.awt.Color(51, 204, 0));
        jButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton1.setText("+Tambah Penghuni");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/h/pass/image/profile.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Username");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setText("Data Penghuni");

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Fakultas", "Teknik Informatika", "Farmasi", "Teknik Mesin", "Hukum", "DKV (Desain Komunikasi Visual)", "Perhotelan", "Akuntansi", "Komputer", "Perawat" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        scrollPaneGrid1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        containerKartu1.setBackground(new java.awt.Color(255, 255, 255));
        containerKartu1.setMaximumSize(new java.awt.Dimension(940, 565));
        containerKartu1.setMinimumSize(new java.awt.Dimension(940, 565));
        containerKartu1.setPreferredSize(new java.awt.Dimension(940, 565));

        javax.swing.GroupLayout containerKartu1Layout = new javax.swing.GroupLayout(containerKartu1);
        containerKartu1.setLayout(containerKartu1Layout);
        containerKartu1Layout.setHorizontalGroup(
            containerKartu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        containerKartu1Layout.setVerticalGroup(
            containerKartu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );

        scrollPaneGrid1.setViewportView(containerKartu1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(780, 780, 780)
                        .addComponent(jLabel5)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(760, 760, 760)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPaneGrid1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addComponent(jLabel6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGap(556, 556, 556))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPaneGrid1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDashboardActionPerformed
    new DashboardView(adminName).setVisible(true);
    this.dispose();
    }//GEN-LAST:event_ButtonDashboardActionPerformed

    private void ButtonPelanggaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPelanggaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonPelanggaranActionPerformed

    private void ButtonLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLaporanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonLaporanActionPerformed

    private void ButtonDataPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDataPenghuniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDataPenghuniActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new FormPenghuniDialog(this, true, null).setVisible(true);
        loadDataPenghuni();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        loadDataPenghuni();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        loadDataPenghuni();
    }//GEN-LAST:event_jComboBox1ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new DataPenghuni("Admin").setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonDashboard;
    private javax.swing.JButton ButtonDataPenghuni;
    private javax.swing.JButton ButtonLaporan;
    private javax.swing.JButton ButtonPelanggaran;
    private javax.swing.JPanel containerKartu1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelDataGrid;
    private javax.swing.JScrollPane scrollPaneGrid1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}