package ru.mirea.bookclub.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ru.mirea.bookclub.R;
import ru.mirea.bookclub.databinding.BooksListFragmentBinding;
import ru.mirea.bookclub.repository.room.models.Book;
import ru.mirea.bookclub.view.adapter.BookListAdapter;
import ru.mirea.bookclub.viewmodel.ListBookViewModel;

import java.util.List;

public class BookList extends Fragment {
     private ListBookViewModel bookViewModel;
     private BooksListFragmentBinding booksListFragmentBinding;
    public static BookList newInstance() {
        return new BookList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        booksListFragmentBinding = BooksListFragmentBinding.inflate(getLayoutInflater(), container, false);

        booksListFragmentBinding.booksList.setLayoutManager(new LinearLayoutManager(getContext()));

        booksListFragmentBinding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_partyList_to_addParty);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                bookViewModel.deleteBook(((BookListAdapter) booksListFragmentBinding.booksList.getAdapter()).getBooks().get(position));
            }
        }).attachToRecyclerView(booksListFragmentBinding.booksList);
        return booksListFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookViewModel = new ViewModelProvider(this).get(ListBookViewModel.class);

        bookViewModel.getAllBooks().observe(getViewLifecycleOwner(), (List<Book> books) -> {
            booksListFragmentBinding.booksList.setAdapter(new BookListAdapter(books,(MainActivity) requireActivity()));
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        booksListFragmentBinding = null;
        bookViewModel = null;
    }
}
