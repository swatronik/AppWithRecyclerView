package com.example.swatronik.appwithreciclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    List<String> mass = new ArrayList();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButton(v);
            }
        });

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<TextRealm> stores = realm.where(TextRealm.class).findAll();
        for (TextRealm tr: stores) {
            mass.add(tr.getText());
        }
        RecyclerView rv = (RecyclerView)findViewById(R.id.recicledview);

        MyAdapter ma = new MyAdapter(mass);
        rv.setAdapter(ma);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

    }
    public void ClickButton(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String text = data.getStringExtra("text");

        TextRealm tr = new TextRealm();

        tr.setText(text);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(tr);
        realm.commitTransaction();

        mass.add(text);

        RecyclerView rv = (RecyclerView)findViewById(R.id.recicledview);

        MyAdapter ma = new MyAdapter(mass);
        rv.setAdapter(ma);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
    }

}