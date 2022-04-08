package ru.mirea.bookclub.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import ru.mirea.bookclub.repository.Repository;
import ru.mirea.bookclub.repository.RepositoryTasks;
import ru.mirea.bookclub.repository.room.models.Book;

import java.util.List;

public class ListBookViewModel extends AndroidViewModel {
    private RepositoryTasks repository;
    public ListBookViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository();
    }
    public void deleteBook(Book book){repository.deleteBook(book);}
    public LiveData<List<Book>> getAllBooks(){
        return repository.getAllBooks();
    }
}
