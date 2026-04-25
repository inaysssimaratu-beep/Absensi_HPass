package com.mycompany.h.pass.objects;

import java.util.ArrayList;
import java.util.List;

public class GenericDAO<T> implements BaseDAO<T> {
    private final String collectionName;
    private final Class<T> clazz; 
    private List<T> dataList = new ArrayList<>();

    public GenericDAO(String collectionName, Class<T> clazz) {
        this.collectionName = collectionName;
        this.clazz = clazz;
    }

    @Override
    public void save(T entity) {
        dataList.add(entity);
        // Menampilkan pesan log sesuai tipe objek (misal: Penghuni)
        System.out.println("Menyimpan data " + clazz.getSimpleName() + 
                           " ke koleksi: " + collectionName);
    }

    @Override
    public void update(int index, T entity) {
        if (index >= 0 && index < dataList.size()) {
            dataList.set(index, entity);
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < dataList.size()) {
            dataList.remove(index);
        }
    }

    @Override
    public List<T> findAll() {
        return dataList;
    }

    @Override
    public T findByIndex(int index) {
        return (index >= 0 && index < dataList.size()) ? dataList.get(index) : null;
    }
}