package com.mycompany.h.pass.objects;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class TesKoneksi {
    public static void main(String[] args) {
        try {
            System.out.println("Memulai koneksi sistem H-Pass ke database...");
            
            MongoDatabase database = MongoManager.getDatabase();
            
            Document ping = new Document("ping", 1);
            database.runCommand(ping);
            
            System.out.println("=========================================");
            System.out.println("STATUS: KONEKSI H-PASS BERHASIL!");
            System.out.println("Terhubung ke Database: " + database.getName());
            System.out.println("=========================================");
            
            System.out.println("Daftar Koleksi Tersedia:");
            for (String name : database.listCollectionNames()) {
                System.out.println("- " + name);
            }

        } catch (Exception e) {
            System.err.println("=========================================");
            System.err.println("STATUS: KONEKSI H-PASS GAGAL!");
            System.err.println("Pesan Error: " + e.getMessage());
            System.err.println("Pastikan MongoDB Compass/Service sudah Aktif.");
            System.err.println("=========================================");
        }
    }
}