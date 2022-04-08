package ru.mirea.bookclub.repository;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.mirea.bookclub.repository.room.models.Book;

import java.util.List;

public interface RepositoryTasks {
    LiveData<List<Book>> getAllBooks();
    void addBook(Book book);
    void deleteBook(Book book);
    <T extends Book> MutableLiveData<Book> findBook(String id, LifecycleOwner owner);
    void deleteAllBooks();
}
