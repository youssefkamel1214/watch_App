package Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;


import org.chromium.base.task.AsyncTask;

import java.io.InputStream;

public class NetImage extends AsyncTask<Bitmap> {
    private String url;
    private ImageView destimage;
    private Exception exception;

    public NetImage(String url, ImageView destimage ){
        this.url = url;
        this.destimage = destimage;
    }


    @Override
    protected Bitmap doInBackground() {
        Bitmap bitmap=null;
        try{
            System.out.println(Thread.currentThread().getName());
            InputStream in=new java.net.URL(url).openStream();
            bitmap= BitmapFactory.decodeStream(in);
        }
        catch (Exception e) {
            exception=e;
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
             if(exception!=null)
                 Toast.makeText(destimage.getContext(), "error = "+exception.getMessage(), Toast.LENGTH_LONG).show();
             else
                 destimage.setImageBitmap(bitmap);
    }
}
