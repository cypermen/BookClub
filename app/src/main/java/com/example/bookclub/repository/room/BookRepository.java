package com.example.bookclub.repository.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.bookclub.repository.RepositoryTasks;
import com.example.bookclub.repository.room.dao.BookDAO;
import com.example.bookclub.repository.room.models.Book;

import java.util.List;

public class BookRepository implements RepositoryTasks {
    private BookDAO bookDAO;
    private LiveData<List<Book>> books;

    public BookRepository(Application application){
        BookDatabase database = BookDatabase.getInstance(application);
        bookDAO = database.bookDAO();
        books = bookDAO.getAllBooks();

    }
    @Override
    public void addBook(Book book){
        new InsertBookAsyncTask(bookDAO).execute(book);
    }
    @Override
    public void deleteBook(Book book){
        new DeleteBookAsyncTask(bookDAO).execute(book);
    }
    @Override
    public void deleteAllBooks(){
        new DeleteAllBookAsyncTask(bookDAO).execute();
    }
    @Override
    public LiveData<List<Book>> getAllBooks(){
        return books;
    }


    public static class InsertBookAsyncTask extends AsyncTask<Book,Void, Void>{
        private BookDAO bookDAO;
        private InsertBookAsyncTask(BookDAO bookDAO){
            this.bookDAO = bookDAO;
        }
        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.insert(books[0]);
            return null;
        }
    }

    public static class DeleteBookAsyncTask extends AsyncTask<Book,Void, Void>{
        private BookDAO bookDAO;
        private DeleteBookAsyncTask(BookDAO bookDAO){
            this.bookDAO = bookDAO;
        }
        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.delete(books[0]);
            return null;
        }
    }

    public static class DeleteAllBookAsyncTask extends AsyncTask<Void,Void, Void>{
        private BookDAO bookDAO;
        private DeleteAllBookAsyncTask(BookDAO bookDAO){
            this.bookDAO = bookDAO;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            bookDAO.deleteAllBooks();
            return null;
        }
    }
}
