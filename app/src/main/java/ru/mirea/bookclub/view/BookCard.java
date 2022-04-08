package ru.mirea.bookclub.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

import ru.mirea.bookclub.databinding.BookCardBinding;
import ru.mirea.bookclub.repository.Repository;
import ru.mirea.bookclub.viewmodel.BookViewModel;

public class BookCard extends Fragment {


        private BookViewModel mViewModel;
        private BookCardBinding mBinding;

        public static BookCard newInstance() {
            return new BookCard();
        }

        @Override
        public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            mViewModel = new ViewModelProvider(this).get(BookViewModel.class);

            if (getArguments() != null) {

                mViewModel.setBook(
                        Repository.getRepository().findBook(getArguments().getString("Book").toString(),this).getValue()
                );

                Log.e("lol",mViewModel.getBook().getAuthor());
            }
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            mBinding = BookCardBinding.inflate(inflater, container, false);


            mBinding.backButton.setOnClickListener((View v) -> {
                Navigation.findNavController(v).popBackStack();
            });

            if (mViewModel.getBook() != null) {
                    mBinding.floatingActionButtonShare.setOnClickListener((View v) -> {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "http://book_review/" + mViewModel.getBook().getBookId());
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                });

                if(mViewModel.getBook().getCover() != null) {
                    try {
                        mBinding.cover.setImageBitmap( BitmapFactory.decodeFileDescriptor(
                                getActivity().getApplicationContext().getContentResolver().openFileDescriptor(
                                        Uri.parse(mViewModel.getBook().getCover()), "r").getFileDescriptor()
                        ));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                mBinding.titleID.setText(mViewModel.getBook().getTitle());
                mBinding.authorID.setText("Автор " + mViewModel.getBook().getAuthor());
                mBinding.typeID.setText("Жанр " + mViewModel.getBook().getType());
                mBinding.descriptionID.setText("Описание " +mViewModel.getBook().getDescription());


            }

            return mBinding.getRoot();
        }

    }
