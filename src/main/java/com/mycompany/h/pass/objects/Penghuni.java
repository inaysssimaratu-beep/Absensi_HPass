package com.mycompany.h.pass.objects;

public class Penghuni {
    private String uidRfid;
    private String nim;
    private String nama;
    private String kamar;
    private String fakultas;

    public Penghuni() {}

    public Penghuni(String uidRfid, String nim, String nama, String kamar, String fakultas) {
        this.uidRfid = uidRfid;
        this.nim = nim;
        this.nama = nama;
        this.kamar = kamar;
        this.fakultas = fakultas;
    }

    // Getter & Setter (Wajib untuk Enkapsulasi)
    public String getUidRfid() { return uidRfid; }
    public void setUidRfid(String uidRfid) { this.uidRfid = uidRfid; }

    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getKamar() { return kamar; }
    public void setKamar(String kamar) { this.kamar = kamar; }

    public String getFakultas() { return fakultas; }
    public void setFakultas(String fakultas) { this.fakultas = fakultas; }

    @Override
    public String toString() {
        return "Penghuni{" + "uidRfid=" + uidRfid + ", nim=" + nim + 
               ", nama=" + nama + ", kamar=" + kamar + ", fakultas=" + fakultas + '}';
    }
}