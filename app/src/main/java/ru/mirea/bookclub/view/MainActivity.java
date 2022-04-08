package ru.mirea.bookclub.view;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import ru.mirea.bookclub.R;
import ru.mirea.bookclub.databinding.ActivityMainBinding;
import ru.mirea.bookclub.repository.Repository;
import ru.mirea.bookclub.repository.room.models.Book;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getBaseContext(),"start",Toast.LENGTH_SHORT).show();
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Repository.init(getApplication());
        Uri income = getIntent().getData();

        if (income != null) {

            Toast.makeText(getBaseContext(),"start2",Toast.LENGTH_SHORT).show();
            String[] parts = income.toString().split("/");
            String id = parts[parts.length - 1];

            Repository.getRepository().findBook(id,this).observe(this, (Book book) -> {
                Bundle bundle = new Bundle();
                bundle.putString("Book", id);
                Toast.makeText(getBaseContext(),income.toString(),Toast.LENGTH_SHORT).show();
                Navigation.findNavController(mBinding.navHostFragment).navigate(
                        R.id.action_bookList_to_bookCard,
                        bundle
                );
            });
        }
       // mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(mBinding.getRoot());

    }
}