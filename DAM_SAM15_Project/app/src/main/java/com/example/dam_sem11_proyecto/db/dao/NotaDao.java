package com.example.dam_sem11_proyecto.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.lifecycle.LiveData;

import com.example.dam_sem11_proyecto.db.entity.NotaEntity;

import java.util.List;

@Dao
public interface NotaDao {

    @Insert
    void insert(NotaEntity nota);

    @Update
    void update(NotaEntity nota);

    @Query("DELETE FROM notas")
    void deleteAll();

    //@Query("DELETE FROM notas WHERE id = : idNota")
    //void deleteById(int idNota);

    @Query("SELECT * FROM notas ORDER BY titulo ASC")
    LiveData<List<NotaEntity>> getAll();

    @Query("SELECT * FROM notas WHERE favorita LIKE 'true'")
    LiveData<List<NotaEntity>> getAllFavoritas();



    /*
    @Query("SELECT * FROM notas ORDER BY titulo ASC")
    List<NotaEntity> getAll();

    @Query("SELECT * FROM notas WHERE favorita LIKE 'true'")
    List<NotaEntity> getAllFavoritas();
*/





}
