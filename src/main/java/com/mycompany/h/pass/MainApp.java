package com.mycompany.h.pass;

import com.mycompany.h.pass.objects.Penghuni;
import com.mycompany.h.pass.objects.GenericDAO;

public class MainApp {
    public static void main(String[] args) {
        // 1. Inisialisasi GenericDAO khusus untuk objek Penghuni
        GenericDAO<Penghuni> penghuniDAO = new GenericDAO<>("penghuni", Penghuni.class);

        // 2. Membuat data penghuni (Contoh data kamu)
        Penghuni p = new Penghuni();
        p.setUidRfid("87654321");
        p.setNim("24090028");
        p.setNama("Dwi Riski Ariyanto");
        p.setKamar("A-01");
        p.setFakultas("Teknik");

        // 3. Simpan data
        penghuniDAO.save(p);

        // 4. Tampilkan data yang tersimpan
        System.out.println("\n--- Daftar Penghuni di Memori ---");
        for (Penghuni data : penghuniDAO.findAll()) {
            System.out.println("NIM: " + data.getNim() + " | Nama: " + data.getNama());
        }
    }
}