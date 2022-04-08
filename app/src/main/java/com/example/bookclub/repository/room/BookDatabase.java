package com.example.bookclub.repository.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bookclub.repository.room.dao.BookDAO;
import com.example.bookclub.repository.room.models.Book;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    private static BookDatabase instance;

    public abstract BookDAO bookDAO();

    public static synchronized BookDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BookDatabase.class, "book_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new FillDbAsyncTask(instance).execute();
        }
    };


    private static class FillDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private BookDAO bookDAO;

        private  FillDbAsyncTask(BookDatabase bookDatabase){
            this.bookDAO = bookDatabase.bookDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bookDAO.insert(new Book("Book1", "description1","King", "type1"));
            bookDAO.insert(new Book("Book2", "description2","King", "type2"));
            bookDAO.insert(new Book("Book3", "description3","King", "type3"));
            bookDAO.insert(new Book("Book4", "description4","King", "type4"));
            return null;
        }
    }
}
