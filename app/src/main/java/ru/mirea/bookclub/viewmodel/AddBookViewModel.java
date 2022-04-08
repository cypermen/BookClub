package ru.mirea.bookclub.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import ru.mirea.bookclub.repository.Repository;
import ru.mirea.bookclub.repository.RepositoryTasks;
import ru.mirea.bookclub.repository.room.models.Book;

public class AddBookViewModel extends AndroidViewModel {

    private RepositoryTasks repository;
    public AddBookViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository();
    }
    public void addBook(Book book){
        repository.addBook(book);
    }
}
