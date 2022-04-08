package com.example.bookclub.repository;

import android.app.Application;

import com.example.bookclub.repository.room.BookRepository;


public class Repository {

    static RepositoryTasks repository;

    static public void init(Application application) {
        if (repository == null) {
            repository = new BookRepository(application);
        }
    }

    static public RepositoryTasks getRepository() {
        return repository;
    }

}
