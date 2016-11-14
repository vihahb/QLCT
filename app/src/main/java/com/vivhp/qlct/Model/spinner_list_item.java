package com.vivhp.qlct.Model;

/**
 * Created by vivhp on 10/24/2016.
 */

public class spinner_list_item {
    int id;
    String name;

    public spinner_list_item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
