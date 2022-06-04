package ru.mirea.lukyanchuk.mireaproject.ui.kittens;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kittens {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String type;
    public String age;
}
