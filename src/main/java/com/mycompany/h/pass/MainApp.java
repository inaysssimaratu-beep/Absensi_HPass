package com.mycompany.h.pass;

import com.mycompany.h.pass.objects.Penghuni;

public class MainApp {
    public static void main(String[] args) {
        Penghuni p = new Penghuni();
        p.setUidRfid("87654321");
        p.setNim("24090028");
        p.setNama("Dwi Riski Ariyanto");
        p.setKamar("Asrama-01");
        p.setFakultas("Teknik");

        // Print hasil test
        System.out.println("Data Berhasil Dibuat:");
        System.out.println(p.toString());
    }
}