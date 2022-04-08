package ru.mirea.bookclub.repository;

import android.app.Application;
import android.widget.Toast;

import ru.mirea.bookclub.repository.room.BookRepository;


public class Repository {

    static RepositoryTasks repository;
    static Application app;
    static public void init(Application application) {
        app = application;
        if (repository == null) {
            repository = new BookRepository(application);
        }
    }

    static public RepositoryTasks getRepository() {
        Toast.makeText(app.getBaseContext(),app.toString(),Toast.LENGTH_SHORT).show();
        return repository;
    }

}
