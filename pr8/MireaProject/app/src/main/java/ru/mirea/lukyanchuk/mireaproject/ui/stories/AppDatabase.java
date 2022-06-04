package ru.mirea.lukyanchuk.mireaproject.ui.stories;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.lukyanchuk.mireaproject.ui.kittens.Kittens;
import ru.mirea.lukyanchuk.mireaproject.ui.kittens.KittensDao;

@Database(entities = {Story.class, Kittens.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase{
    public abstract StoriesDAO storiesDAO();
    public abstract KittensDao kittensDao();
}
