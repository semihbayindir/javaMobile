package org.kasapbasi.guesstheclebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    String[] btngrp;

    ImageView imCelep;
    String[] imgs= new String[100];
    String[] names= new String[100];
    String[] imgsArry;
    String[] namesArray;

public class ContentDownloader extends AsyncTask<String,Void,String>{



//https://www.imdb.com/list/ls059786955/?sort=list_order,asc&mode=detail&page=1

    @Override
    protected String doInBackground(String... strings) {
        Document doc = null;
        try {
            doc = Jsoup.connect(strings[0]).get();
        } catch (IOException e){
            e.printStackTrace();
        }
        Log.d("Title",doc.title());
        Elements divs = doc.select(".lister-item-image");
        Elements imgs = divs.select("img");
        int count = 0;
        for (Element img : imgs) {
            Log.d("ALT",img.attr("alt"));
            Log.d("src",img.attr("src"));
            imgsArry[count]= img.attr("src");
            namesArray[count]=img.attr("alt");
        }
    }
}

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        RandomFill();
    }

    public class myImageDownloader extends AsyncTask<String,Void, Bitmap>{
    @Override
        protected Bitmap doinBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            InputStream is = con.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(is);
            return bmp;
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    }

    @Override
    int CorrectIndex;
    public void RandomFill(){
        Random rnd = new Random();
        int index = rnd.nextInt(100);
        CorrectIndex=index;
        String url=imgsArry[index];
        String name=namesArray[index];
        myImageDownloader idl = new myImageDownloader();
        try {
            Bitmap bmp=idl.execute(url).get();
            imCelep.setImageBitmap();
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        int pos= rnd.nextInt(4);
        btngrp[pos].setText(name);
        for (int i=0;i<4;i++){
            if(i==pos)
                continue;
            else {
                int rndpos=rnd.nextInt(100);
                while (rndpos==index){
                    rndpos=rnd.nextInt(100);
                }
                btngrp[i].setText(namesArray[rndpos]);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 =(Button)findViewById(R.id.btnOpt1);
        btn2 =(Button)findViewById(R.id.btnOpt2);
        btn3 =(Button)findViewById(R.id.btnOpt3);
        btn4 =(Button)findViewById(R.id.btnOpt4);

        btngrp[0] = btn1;
        btngrp[1] = btn2;
        btngrp[2] = btn3;
        btngrp[3] = btn4;




        imCelep=(ImageView) findViewById(R.id.ivCeleb);

        ContentDownloader cdl = new ContentDownloader();
        cdl.execute("https://www.imdb.com/list/ls059786955/?sort=list_order,asc&mode=detail&page=1");

    }
}
