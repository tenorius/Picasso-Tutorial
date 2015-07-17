#Tutorial sobre a biblioteca Picasso para Android



###1. Crie o Projeto
    No Android Studio crie um projeto android chamado TutorialPicasso com uma Activity.
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
    Essas são as urls das imagens que iremos utilizar no aplicativo. Voce pode troca-las por links 
    de sua escolha, imagens nos recursos do aplicativo ou no cartao de memoria.

###4. AndroidManifest.xml
    Adicione as seguinte permissão: 
```xml
    <uses-permission android:name="android.permission.INTERNET"/>
```
    Obrigatorio quando se deseja que Picasso baixe imagens da internet.
    
###5. Crie uma classe java chamada MyImageAdapter
    
    Essa é a classe responsavel por popular o GridView de nossa tela principal. 
    O grosso do trabalho dessa classe é feito no seguinte trecho de codigo:
    
```java
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
```

####Destaques:
    
* Parametros:
    * position - a posição da imagem no adapter(tambem usada como index no array de imagens)
    * convertView - uma view antiga que pode ser reciclada
    * parent - o grid view no qual colocaremos a imagem
* ImageView - o image view que retornamos para a tela em uma das celulas do grid. Checamos se podemos reusar o convertView. Caso não, nos criamos a nova image view.
* Picasso - a biblioteca sobre estudo
* with - o contexto da instancia do objeto Picasso
* load - a imagem que queremos carregar na celula do grid
* placeholder - imagem mostrada enquanto é feito o loading da requisitada
* error - uma imagem mostrada quando a requisitada falha em carregar
* noFade - por default, a imagem sofre um 'fade in' se carregada do cache do disco ou da internet. No codigo nos disabilitamos o fade-in
* resize - definimos uma nova escala para imagem requisitada
       
     

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
###7. O codigo da main activity

```java
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

```

###8. Rode o App

    


