package ru.mirea.bookclub.repository.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import ru.mirea.bookclub.repository.room.dao.BookDAO;
import ru.mirea.bookclub.repository.room.models.Book;

@Database(entities = {Book.class}, version = 2, exportSchema = false)
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

    private static Callback roomCallback = new Callback(){
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

            return null;
        }
    }
}
