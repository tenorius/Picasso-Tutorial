#Tutorial Picasso para Android

A Biblioteca Picasso é uma ferramenta muito poderosa para evitar manipulação de bitmaps em sua aplicação. Ela se encarregar  de exibir a imagem de forma eficiente em um ImageView,seja essa imagem oriunda de sdcard local, rede ou recurso de aplicativo. Tudo isto pode ser feito de uma única linha de código.
Sem Picasso o desenvolvedor deve:
* Usar AsyncTask pra baixar imagens no background
* Alem da thread no background, tem que manter controle sobre os downloads para uma ImageView em ordem pra lidar com mudanças de orientaçao ou se o usuario usou o scroll da List/GridView
* Implementar LRUCache<String, Bitmap> pra o caching de imagens baixadas

Com Picasso você nao precisa se preocupar com nada disso. Basta uma linha de codigo!!! 
Então vamos ver na pratica como essa biblioteca funciona.   
###1. Crie o Projeto
    No Android Studio crie um projeto android chamado TutorialPicasso com uma Activity e um menu(extenda ActionBarActivity). 
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

    *   Parametros:
        *   position - a posição da imagem no adapter(tambem usada como index no array de imagens)
        *   convertView - uma view antiga que pode ser reciclada
        *   parent - o grid view no qual colocaremos a imagem
    *   Picasso - a biblioteca sobre estudo
    *   ImageView - o image view que retornamos para a tela em uma das celulas do grid.
        Checamos se podemos reusar o ConvertView. Caso não, nos criamos a nova image view.
    *   with - o contexto da instancia do objeto Picasso
    *   load - a imagem que queremos carregar na celula do grid
    *   placeholder - imagem mostrada enquanto é feito o loading da requisitada
    *   error - uma imagem mostrada quando a requisitada falha em carregar
    *   noFade - por default, a imagem sofre um 'fade in' se carregada do cache do disco ou da internet. 
        No codigo nosdisabilitamos o fade-in
    *   resize - definimos uma nova escala para imagem requisitada



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
    Setamos aqui como queremos a aparencia do nosso grid, tamanho das celulas, espacamento entre elas, etc...

<img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/telaInicial.png" alt="alt text" width="270" height="480"> <img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/telainicial2.png" alt="alt text" width="480" height="270">

###7. O codigo da main activity
    Nessa classe adicionamos um listener para vigiar clicks nas celucas do gridView:
```java
gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(MainActivity.this,ImageActivity.class);

                i.putExtra("url", position);
                startActivity(i);

            }
        });
```
    Selecionar uma imagem do grid dispara o metodo onItemClick(). Consequentemente uma activity é iniciada para mostrar a        imagem em uma versão maior e com certas funcionalidades da picasso aplicadas. veremos mais adiante que funcionalidades       sao essas.
    destaques:
*   Criamos um Intent que é usado para iniciar a segunda activity
*   putExtra - passamos a posicao do item selecionado no adapter. Usaremos esse parametro na segunda activity para pegar a       url da imagem
*   startActivity - inicia uma nova activity

    Crie as seguintes variaveis estaticas globais:
```java
    static boolean rotation=false;
    static boolean resizing=false;
    static boolean placeholder=false;
    static boolean transform=false;
    static boolean debug=false;
```

<img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/menu1.png" alt="alt text" width="270" height="480">

    Essas variavies serao fornecidas para o usuario ativar certas funcionalidades da biblioteca Picasso.
    Ao criar o projeto no Android Studio, por default ele ja inclui um menu na activity. Adicionamos o seguinte codigo ao        metodo `onOptionsItemSelected`:
    
```java
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
```
Aqui temos um listener para os clicks nos itens do nosso menu. Checamos que item foi clicado e ativamos/desativamos a funcionalidade relativa ao item.
As funcionalidades:
* Rotation - gira a imagem original em um certo sentido e angulo.
* Resize - Altera a escala da imagem
* debug - podemos ver de onde a imagem esta sendo carregada. O triangulo colorido indica a fonte: verde cache, amarelo disk.
* placeholder - imagem temporaria.
 
<img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/menu2.png" alt="alt text" width="270" height="480">

###8. A segunda activity: ImageActivity
    Nesta activity mostraremos a imagem com as funcionalidades ativadas pelo usuario. No metodo `onCreate` adicione:
    
    ```java
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
    ```
* Primeiramente, recuperamos o extra passado pela Main Activity
* Entao começamos a construir nossa requisicao Picasso passo a passo:
    *  Criamos o objeto Picasso com o contexto `Picasso pp = Picasso.with(this);`
    *  Ativamos/desativamos o modo de debug com `pp.setIndicatorsEnabled();`
    *  Criamos o objeto RequestCreator com a imagem requisitada `RequestCreator p = pp.load(MainActivity.images[selItem]);`
    *  Ativamos/desativamos as funcionalidades de rotacao, resizing e placeholder
    *  e para finalizar a requisicao que carregará a imagem usamos `p.error(R.drawable.sorry).into(imageView);`
    
<img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/dog_normal.png" alt="alt text" width="270" height="480"> <img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/dog_resize.png" alt="alt text" width="270" height="480"> <img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/planeta1.png" alt="alt text" width="270" height="480">  <img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/planeta2.png" alt="alt text" width="270" height="480"> <img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/debug1.png" alt="alt text" width="270" height="480"> <img src="https://github.com/tenorius/Picasso-Tutorial/blob/master/app/src/main/res/tuto_images/debug2.png" alt="alt text" width="270" height="480">   

###9. Rode o App

    


