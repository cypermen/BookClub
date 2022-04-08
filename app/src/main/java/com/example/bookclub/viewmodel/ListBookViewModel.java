package com.example.bookclub.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bookclub.repository.Repository;
import com.example.bookclub.repository.RepositoryTasks;
import com.example.bookclub.repository.room.models.Book;

import java.util.List;

public class ListBookViewModel extends AndroidViewModel {
    private RepositoryTasks repository;
    public ListBookViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository();
    }

    public LiveData<List<Book>> getAllBooks(){
        return repository.getAllBooks();
    }
}
