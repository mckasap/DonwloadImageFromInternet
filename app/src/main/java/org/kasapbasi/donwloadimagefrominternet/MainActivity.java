package org.kasapbasi.donwloadimagefrominternet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

//http://www.agaclar.net/forum/attachments/sukulent/7290d1171654782-pasa-008.jpg

 public class Imagegetir extends AsyncTask<String,Void, Bitmap>{

     @Override
     protected Bitmap doInBackground(String... strings) {
         try {
             URL url = new URL(strings[0]);
             HttpURLConnection con= (HttpURLConnection)url.openConnection();
             InputStream is =con.getInputStream();
             Bitmap bmp = BitmapFactory.decodeStream(is);
             return bmp;

         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }


         return null;
     }
 }

    public void click(View v){
        Imagegetir task= new Imagegetir();

        Bitmap bmp= null;
        try {
            bmp = task.execute("http://www.agaclar.net/forum/attachments/sukulent/7290d1171654782-pasa-008.jpg").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        iv.setImageBitmap(bmp);

    }

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            iv=(ImageView)findViewById(R.id.imageView);



    }
}
