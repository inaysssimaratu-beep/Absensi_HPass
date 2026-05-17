package com.mycompany.h.pass.objects;

public class Penghuni {
    private String uidRFID;
    private String nim;
    private String nama;
    private String kamar;
    private String fakultas;
    private String gender;

    public Penghuni() {}

    public Penghuni(String uidRFID, String nim, String nama, String kamar, String fakultas, String gender) {
        this.uidRFID = uidRFID;
        this.nim = nim;
        this.nama = nama;
        this.kamar = kamar;
        this.fakultas = fakultas;
        this.gender = gender;
    }

    public String getUidRFID() { return uidRFID; }
    public void setUidRFID(String uidRfid) { this.uidRFID = uidRfid; }

    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getKamar() { return kamar; }
    public void setKamar(String kamar) { this.kamar = kamar; }

    public String getFakultas() { return fakultas; }
    public void setFakultas(String fakultas) { this.fakultas = fakultas; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    @Override
    public String toString() {
        return "Penghuni{" + "uidRFID=" + uidRFID + ", nim=" + nim + 
               ", nama=" + nama + ", kamar=" + kamar + ", fakultas=" + fakultas + '}';
    }
}
