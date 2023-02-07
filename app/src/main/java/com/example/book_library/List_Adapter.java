package com.example.book_library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

public class List_Adapter extends ArrayAdapter<ListDT> {
    public static int counter = 1;

    public List_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<ListDT> values) {
        super(context, resource, values);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListView = convertView;
        ListDT listDT = getItem(position);
        if (ListView == null) {
            ListView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_layout, parent, false);
        }
        TextView series = ListView.findViewById(R.id.seriesid);
        series.setText(String.valueOf(counter)+": ");
        counter++;
        TextView title_adapter = ListView.findViewById(R.id.titleid);
        title_adapter.setText(listDT.getTitle_listDT());
        TextView author_adapter = ListView.findViewById(R.id.authorid);
        author_adapter.setText(listDT.getAuthor_listDT());
        TextView year_adapter = ListView.findViewById(R.id.yearid);
        year_adapter.setText(listDT.getYear_listDT());

        return ListView;
    }

}
