package com.example.tenorio.myapplication3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class MainActivity extends ActionBarActivity {

    static String[] images;
    static boolean rotation=false;
    static boolean resizing=false;
    static boolean placeholder=false;
    static boolean transform=false;
    static boolean debug=false;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images = getResources().getStringArray(R.array.images);
        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, images));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(MainActivity.this,ImageActivity.class);

                i.putExtra("url", position);
                startActivity(i);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_rotation:
                rotation = !rotation;
                item.setChecked(rotation);
                return false;
            case R.id.action_resize:
                resizing = !resizing;
                item.setChecked(resizing);
                return false;
            case R.id.action_placeholder:
                placeholder = !placeholder;
                item.setChecked(placeholder);
                return false;
            case R.id.action_debug:
                debug = !debug;
                item.setChecked(debug);
                return false;
            case R.id.action_transform:
                transform = !transform;
                item.setChecked(transform);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
