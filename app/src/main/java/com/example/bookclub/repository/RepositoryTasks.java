package com.example.bookclub.repository;


import androidx.lifecycle.LiveData;

import com.example.bookclub.repository.room.models.Book;

import java.util.List;

public interface RepositoryTasks {
    LiveData<List<Book>> getAllBooks();
    void addBook(Book book);
    void deleteBook(Book book);
    void deleteAllBooks();
}
