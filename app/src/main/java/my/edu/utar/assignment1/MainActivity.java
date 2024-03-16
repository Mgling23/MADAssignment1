package my.edu.utar.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    View.OnClickListener buttonListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout orderLinearLayout = findViewById(R.id.main_ll);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bg_pic = BitmapFactory.decodeResource(getResources(), R.drawable.all_bg2,options);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bg_pic);
        orderLinearLayout.setBackground(bitmapDrawable);

        buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                if(btn.getId() == R.id.homePlayBtn) {
                    Intent intent = new Intent(MainActivity.this,ChooseGames.class);
                    startActivity(intent);
                }
                if(btn.getId() == R.id.homeQuitBt)
                    finish();
            }
        };

        Button homePlayBtn = findViewById(R.id.homePlayBtn);
        Button homeQuitBtn = findViewById(R.id.homeQuitBt);


        homePlayBtn.setOnClickListener(buttonListener);
        homeQuitBtn.setOnClickListener(buttonListener);
    }

}