package org.tinlone.demo.rxjavasample.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


/**
 * @author Administrator on 2018/2/26 0026.
 */

public class Book extends BaseObservable {

    private int id;
    private String name;
    private String author;

    public Book() {
    }

    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   @Bindable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
