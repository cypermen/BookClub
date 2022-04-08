package com.example.bookclub.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookclub.R;
import com.example.bookclub.repository.room.models.Book;
import com.example.bookclub.viewmodel.ListBookViewModel;
import com.example.bookclub.databinding.BooksListFragmentBinding;
import java.util.List;

public class BookList extends Fragment {
     private ListBookViewModel bookViewModel;
     private BooksListFragmentBinding booksListFragmentBinding;
     @Override
     public void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         //booksListFragmentBinding.
         bookViewModel = new ViewModelProvider(this).get(ListBookViewModel.class);
         bookViewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
             @Override
             public void onChanged(List<Book> books) {
                 Toast.makeText(getContext(), "onChange", Toast.LENGTH_LONG).show();
             }
         });
     }

}
