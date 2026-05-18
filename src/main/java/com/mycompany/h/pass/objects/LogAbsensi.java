package com.mycompany.h.pass.objects;

import java.util.Date;

public class LogAbsensi {
    private String nama;
    private String kamar;
    private String tipe;
    private String waktu;
    private Date waktu_asli;
    private String status;

    public LogAbsensi() {}

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getKamar() { return kamar; }
    public void setKamar(String kamar) { this.kamar = kamar; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    public String getWaktu() { return waktu; }
    public void setWaktu(String waktu) { this.waktu = waktu; }

    public Date getWaktu_asli() { return waktu_asli; }
    public void setWaktu_asli(Date waktu_asli) { this.waktu_asli = waktu_asli; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
