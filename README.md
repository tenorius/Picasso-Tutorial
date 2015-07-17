#Tutorial sobre a biblioteca Picasso para Android



###1. Adicionar a biblioteca Picasso
    Crie um projeto android chamado TutorialPicasso com uma Activity.
###2. Adicione a biblioteca Picasso
    Adicione a seguinte linha seguinte linha as dependendias do build.gradle:
  
    `compile 'com.squareup.picasso:picasso:2.3.3'`

###3. Strings.xml
    Adicione o seguinte array:
```xml
<string-array name="images">
        <item>http://i.imgur.com/Yfa4JDLh.jpg</item>
        <item>http://i.imgur.com/hoqnvq5.jpg</item>
        <item>http://i.imgur.com/CvIpxcw.jpg</item>
        
        <item>http://i.imgur.com/IDgHz9Y.jpg</item>
        <item>http://i.imgur.com/fSwxpcp.jpg</item>
        <item>http://i.imgur.com/9hOdzsx.jpg</item>

        <item>http://i.imgur.com/BZFHk5l.jpg</item>
        <item>http://i.imgur.com/ZfqEtUw.jpg</item>
        <item>http://i.imgur.com/bG3G8Mg.jpg</item>

        <item>http://i.imgur.com/4maX4Jf.jpg</item>
        <item>http://i.imgur.com/MzikJ6H.jpg</item>
        <item>http://i.imgur.com/IA3PVYn.jpg</item>

        <item>http://i.imgur.com/uE9vStH.jpg</item>
        <item>http://i.imgur.com/OrT3GsX.jpg</item>
        <item>http://i.imgur.com/ZMrsTmZ.jpg</item>

        <item>http://i.imgur.com/bh918lC.jpg</item>
        <item>http://i.imgur.com/AfXb9nl.png</item>
        <item>http://i.imgur.com/GDcH5uA.jpg</item>

    </string-array>
```
###4. AndroidManifest.xml
    Adicione a seguinte permissão: 
    
```xml
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
###5. Crie uma classe java chamada MyImageAdapter
    adicionde o seguinte codigo a classe:
    
```java
    package com.example.tenorio.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by tenorio on 7/7/2015.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private String[] mImages;
    public ImageAdapter(Context c, String[] i) {
        mContext = c;
        mImages = i;
    }

    public int getCount() {
        return mImages.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {

            imageView = new ImageView(mContext);


        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).load(mImages[position]).placeholder(R.drawable.placeholder).error(R.drawable.sorry).resize(200,200).noFade().centerCrop().into(imageView);

        return imageView;
    }

}
```
###6. Adicione um GridView ao activity_main.xml
    
```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent" android:paddingLeft="@dimen/activity_horizontal_margin"
    android:background="#000000"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin" tools:context=".MainActivity">


    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/imageView"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true" />
    <GridView xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/gridview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:columnWidth="90dp"
        android:numColumns="auto_fit"
        android:verticalSpacing="10dp"
        android:horizontalSpacing="10dp"
        android:stretchMode="columnWidth"
        android:gravity="center"
        />

</RelativeLayout>
```
    


