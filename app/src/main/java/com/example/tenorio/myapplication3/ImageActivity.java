package com.example.tenorio.myapplication3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestCreator;


public class ImageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Bundle b = this.getIntent().getExtras();

        int selItem = b.getInt("url");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Picasso pp = Picasso.with(this);

        if(MainActivity.debug) {
            pp.setIndicatorsEnabled(true);
        }else {
            pp.setIndicatorsEnabled(false);
        }

        RequestCreator p = pp.load(MainActivity.images[selItem]);

        if(MainActivity.rotation) {
            p.rotate(60);
        }
        if(MainActivity.resizing) {
            p.resize((int) (Math.random() * 200) + 50, (int) (Math.random() * 200) + 50);
        }
        if(MainActivity.placeholder) {
            p.placeholder(R.drawable.placeholder);
        }
        p.error(R.drawable.sorry).into(imageView);










        //Picasso.with(this).load(MainActivity.images[selItem]).placeholder(R.drawable.placeholder).into(imageView);
    }




}
