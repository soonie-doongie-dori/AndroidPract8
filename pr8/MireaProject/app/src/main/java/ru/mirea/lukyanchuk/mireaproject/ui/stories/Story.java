package ru.mirea.lukyanchuk.mireaproject.ui.stories;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Story {
    @PrimaryKey(autoGenerate = true)

    public long id;
    public String number;
    public String personName;
    public String petName;
}