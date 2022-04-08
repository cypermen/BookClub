package ru.mirea.bookclub.view.adapter;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.List;

import ru.mirea.bookclub.R;
import ru.mirea.bookclub.databinding.BookListItemBinding;
import ru.mirea.bookclub.repository.Repository;
import ru.mirea.bookclub.repository.room.models.Book;
import ru.mirea.bookclub.view.MainActivity;


public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListHolder> {

    private List<Book> books;
    private MainActivity mActivity;
    public BookListAdapter(List<Book> books, MainActivity mActivity) {
        this.mActivity = mActivity;
        this.books = books;
    }

    @NonNull
    @NotNull
    @Override
    public BookListHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        BookListItemBinding binding = BookListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookListHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BookListHolder holder, int position) {
        holder.bookListItemBinding.bookTitleID.setText(books.get(position).getTitle());
        holder.bookListItemBinding.CardViewID.setOnClickListener((View v) -> {
            Bundle bundle = new Bundle();

            bundle.putString("Book", Integer.toString(books.get(position).getBookId()));

            Navigation.findNavController(v)
                    .navigate(R.id.action_bookList_to_bookCard, bundle);
        });
        if (books.get(position).getAuthor() != null) {
            holder.bookListItemBinding.bookAuthor.setText(books.get(position).getAuthor());
        }
        if(books.get(position).getCover() != null) {
              try {
                  holder.bookListItemBinding.bookImageID.setImageBitmap( BitmapFactory.decodeFileDescriptor(
                          mActivity.getApplicationContext().getContentResolver().openFileDescriptor(
                                  Uri.parse(books.get(position).getCover()), "r").getFileDescriptor()
                  ));
              } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public List<Book> getBooks() {
        return books;
    }

    class BookListHolder extends RecyclerView.ViewHolder{
        BookListItemBinding bookListItemBinding;
        public BookListHolder(BookListItemBinding bookListItemBinding) {
            super(bookListItemBinding.getRoot());
            this.bookListItemBinding = bookListItemBinding;
        }
    }
}
