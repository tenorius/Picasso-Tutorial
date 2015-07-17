#Tutorial sobre a biblioteca Picasso para Android

Esse tutorial pode ser resumido em 5 etapas:

1. Criar a aplicacao
2. 
3. Setar as permissoes
4. Criar o Adapter
5. Popular o GridView com o adapter

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
    


