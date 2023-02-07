package com.example.book_library;

public class ListDT {
    private String title_listDT;
    private String author_listDT;
    private String year_listDT;


    public ListDT(String title_listDT, String author_listDT, String year_listDT) {
        this.title_listDT = title_listDT;
        this.author_listDT = author_listDT;
        this.year_listDT = year_listDT;
    }

    public String getTitle_listDT() {
        return title_listDT;
    }

    public String getAuthor_listDT() {
        return author_listDT;
    }

    public String getYear_listDT() {
        return year_listDT;
    }
}
