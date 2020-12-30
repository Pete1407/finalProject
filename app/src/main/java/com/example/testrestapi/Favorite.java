package com.example.testrestapi;

import java.util.ArrayList;

public class Favorite {
    public String username;
    public ArrayList<String> favoritelist;
    public int codeStyle;

    public Favorite(){

    }

    public Favorite(String username,int codeStyle,ArrayList<String> likelist) {
        this.username = username;
        this.favoritelist = likelist;
        this.codeStyle = codeStyle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getFavoritelist() {
        return favoritelist;
    }

    public void setFavoritelist(ArrayList<String> favoritelist) {
        this.favoritelist = favoritelist;
    }

    public int getCodeStyle() {
        return codeStyle;
    }

    public void setCodeStyle(int codeStyle) {
        this.codeStyle = codeStyle;
    }
}
