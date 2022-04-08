package ru.mirea.bookclub.viewmodel;

import androidx.lifecycle.ViewModel;

import ru.mirea.bookclub.repository.room.models.Book;

public class BookViewModel extends ViewModel {
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
