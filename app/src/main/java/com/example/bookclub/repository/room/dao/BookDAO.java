package com.example.bookclub.repository.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookclub.repository.room.models.Book;

import java.util.List;

@Dao
public interface BookDAO {
    @Insert
    void insert(Book book);

    @Delete
    void delete(Book book);

    @Query("SELECT * FROM book_table")
    LiveData<List<Book>> getAllBooks();

    @Query("DELETE FROM book_table")
    void deleteAllBooks();
}
