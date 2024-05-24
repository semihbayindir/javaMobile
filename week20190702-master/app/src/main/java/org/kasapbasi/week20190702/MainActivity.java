package org.kasapbasi.week20190702;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv;
    Button b0;
    Button b1;
    Button b2;
    Button b3;

    ArrayList<String> CelebNames= new ArrayList<>();
    ArrayList<String> CelebImgUrl= new ArrayList<>();

    public class DownloadImage extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url= new URL(strings[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.connect();
                InputStream in =con.getInputStream();
                final Bitmap bmp= BitmapFactory.decodeStream(in);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageBitmap(bmp);
                    }
                });

                return bmp;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

    public class DownloadContent extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();

               final Elements imgs =doc.select("img");
                Log.i("Size",imgs.size()+"");

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Pattern name= Pattern.compile("<img alt=\"(.*?)\"");
                        Pattern url= Pattern.compile("src=\"(.*?)\"");

                        Matcher m;

                        for(Element e:imgs){
                            if(e.toString().contains("height=\"209\"")){
                                m=name.matcher(e.toString());
                                boolean git=false;
                                while(m.find())
                                {git=true;
                                    CelebNames.add(m.group(1));
                                }
                                if(git){
                                    m=url.matcher(e.toString());
                                    while(m.find())
                                        CelebImgUrl.add(m.group(1));
                                }
                                git=false;
                            }
                        }
                        Log.i("Sonuc",imgs.html());
                        update();
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }


 public void update(){


        Random rnd= new Random();
     int randomitem = rnd.nextInt(CelebNames.size());
     int randomitem2 = rnd.nextInt(CelebNames.size());
     int randomitem3 = rnd.nextInt(CelebNames.size());


     int Celeb= rnd.nextInt(CelebNames.size());
        String randomElement = CelebNames.get(Celeb);

     b0.setText(randomElement);
     b1.setText(CelebNames.get(randomitem));
     b2.setText(CelebNames.get(randomitem2));
     b3.setText(CelebNames.get(randomitem3));


     DownloadImage di= new DownloadImage();
        di.execute(CelebImgUrl.get(Celeb));


       // DownloadContent dc= new DownloadContent();
       // dc.execute("https://www.imdb.com/list/ls052283250/");

 }

    public void Selection(View v){
update();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView) findViewById(R.id.imageView);
        tv=(TextView) findViewById(R.id.textView);


        b0=(Button)findViewById(R.id.button0);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        DownloadContent dc= new DownloadContent();
        dc.execute("https://www.imdb.com/list/ls052283250/");



    }
}
