package ru.mirea.lukyanchuk.mireaproject.ui.kittens;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.lukyanchuk.mireaproject.ui.App;
import ru.mirea.lukyanchuk.mireaproject.ui.stories.AppDatabase;

public class KittensViewModel extends ViewModel {
    private final LiveData<List<Kittens>> kittens;
    private final AppDatabase appDatabase = App.instance.getDatabase();
    private final KittensDao kittensDao = appDatabase.kittensDao();

    public KittensViewModel() {
        kittens = kittensDao.getAllKittens();
    }

    public void addKitten(Kittens kit) {
        kittensDao.insert(kit);
    }

    public LiveData<List<Kittens>> getKittensLiveData() {
        return kittens;
    }

    public List<Kittens> getKittens() {
        return kittens.getValue();
    }
}
