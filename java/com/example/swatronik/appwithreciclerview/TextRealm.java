package com.example.swatronik.appwithreciclerview;

import io.realm.RealmObject;

/**
 * Created by Swatronik on 16.05.2018.
 */

public class TextRealm extends RealmObject {
    private String text;

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

}
