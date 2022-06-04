package ru.mirea.lukyanchuk.mireaproject.ui.kittens;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KittensDao {
    @Query("SELECT * FROM Kittens")
    LiveData<List<Kittens>> getAllKittens();
    @Insert
    void insert(Kittens kittens);
    @Update
    void update(Kittens kittens);
    @Delete
    void delete(Kittens kittens);

}
