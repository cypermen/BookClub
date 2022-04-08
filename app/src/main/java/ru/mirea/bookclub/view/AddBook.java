package ru.mirea.bookclub.view;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import ru.mirea.bookclub.databinding.BookAddFragmentBinding;
import ru.mirea.bookclub.repository.room.models.Book;
import ru.mirea.bookclub.viewmodel.AddBookViewModel;


public class AddBook extends Fragment {

    private AddBookViewModel addBookViewModel;
    private BookAddFragmentBinding bookAddFragmentBinding;
    private String cover;
    private MainActivity mActivity;
    public static AddBook newInstance() {
        return new AddBook();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        bookAddFragmentBinding = BookAddFragmentBinding.inflate(getLayoutInflater(), container, false);

        bookAddFragmentBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        bookAddFragmentBinding.addCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requireActivity() != null) {
                    requireActivity().getActivityResultRegistry().register("key", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                        @Override
                        public void onActivityResult(Uri result) {
                            requireActivity().getApplicationContext().getContentResolver().takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            cover = result.toString();
                        }
                    }).launch(new String[]{"image/*"});
                }
            }

        });

        bookAddFragmentBinding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bookAddFragmentBinding.bookTitle.getText().toString().isEmpty()) {
                    addBookViewModel.addBook(new Book(bookAddFragmentBinding.bookTitle.getText().toString()
                            ,bookAddFragmentBinding.bookDescription.getText().toString()
                            ,bookAddFragmentBinding.bookAuthor.getText().toString()
                            ,bookAddFragmentBinding.bookType.getText().toString()
                            , cover)
                    );
                    Navigation.findNavController(v).popBackStack();
                } else {
                    Toast.makeText(getContext(), "Не все обязательные поля заполнены", Toast.LENGTH_LONG).show();
                }
            }
        });





        return bookAddFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addBookViewModel = new ViewModelProvider(this).get(AddBookViewModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bookAddFragmentBinding = null;
        addBookViewModel = null;
    }
}
