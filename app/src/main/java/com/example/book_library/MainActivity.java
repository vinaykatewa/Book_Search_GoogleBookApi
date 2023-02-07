package com.example.book_library;

import static com.example.book_library.List_Adapter.counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<ListDT> List_Show = new ArrayList<ListDT>();
    String bookName = "flower";
    public static List_Adapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView recyclerView = findViewById(R.id.recycleView);
        EditText userInput = findViewById(R.id.searchId);
        madapter = new List_Adapter(MainActivity.this, R.layout.list_layout, List_Show);
        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Perform the task here
                    madapter.clear();
                    bookName = userInput.getText().toString();
                    String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + bookName + "+inauthor:keyes&key=AIzaSyCp3N9i88sAMDaai8U4PnY5zCMEHuB6D7A";
                    ExampleAsyncTask task = new ExampleAsyncTask();
                    task.execute(url);
                    userInput.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    // Hide the keyboard
                    imm.hideSoftInputFromWindow(userInput.getWindowToken(), 0);
                    counter = 1;
                    return true;
                }
                return false;
            }
        });


        recyclerView.setAdapter(madapter);
    }

}
