package com.example.book_library;

import static com.example.book_library.MainActivity.madapter;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class ExampleAsyncTask extends AsyncTask<String, Void, ArrayList<ListDT>> {
    @Override
    protected ArrayList<ListDT> doInBackground(String... url) {
        // This method runs on a background thread and performs the background
        // computation. It should not access the UI or perform any UI tasks.
        // In this example, we're just returning a hard-coded string.
        return Api.FindingFromNet(url[0]);
    }


    @Override
    protected void onPostExecute(ArrayList<ListDT> result) {
        // This method runs on the UI thread and is used to display the result
        // of the background computation, such as updating the UI with the result.
        madapter.addAll(result);
    }
}
