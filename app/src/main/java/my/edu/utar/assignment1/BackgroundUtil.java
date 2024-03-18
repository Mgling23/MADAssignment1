package my.edu.utar.assignment1;

//import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.getResources;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.LinearLayout;

public class BackgroundUtil {
    public void setBackground_ll(Context context, LinearLayout ll){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bg_pic = BitmapFactory.decodeResource(context.getResources(),R.drawable.all_bg,options);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(),bg_pic);
        ll.setBackground(bitmapDrawable);
    }
}
