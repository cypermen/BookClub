package ru.mirea.bookclub.repository.room;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.mirea.bookclub.repository.RepositoryTasks;
import ru.mirea.bookclub.repository.room.dao.BookDAO;
import ru.mirea.bookclub.repository.room.models.Book;

import java.util.List;

public class BookRepository implements RepositoryTasks {
    private BookDAO bookDAO;
    private LiveData<List<Book>> books;
    public Application app;
    public BookRepository(Application application){
        app = application;
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
    @Override
    public MutableLiveData<Book> findBook(String id,LifecycleOwner owner) {
        MutableLiveData<Book> specificBook = new MutableLiveData<>();

        for (Book b:books.getValue()) {
            Toast.makeText(app.getBaseContext(),b.toString(),Toast.LENGTH_SHORT).show();
            if (id.equalsIgnoreCase(String.valueOf(b.getBookId()))){
                Toast.makeText(app.getBaseContext(),"findbook2",Toast.LENGTH_SHORT).show();
                specificBook.setValue(b);
            }
        }
        return specificBook;
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
