package my.edu.utar.assignment1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseGames extends AppCompatActivity {

    View.OnClickListener buttonListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosegame);

        LinearLayout orderLinearLayout = findViewById(R.id.choose_ll);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bg_pic = BitmapFactory.decodeResource(getResources(), R.drawable.all_bg3, options);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bg_pic);
        orderLinearLayout.setBackground(bitmapDrawable);

        Button choCompareBtn = findViewById(R.id.choCompareBtn);
        Button choOrderBtn = findViewById(R.id.choOrderBtn);
        Button choComposeBtn = findViewById(R.id.choComposeBtn);
        Button choBackBtn = findViewById(R.id.choBackBtn);

        choBackBtn.setOnClickListener(buttonListener);
        choComposeBtn.setOnClickListener(buttonListener);
        choOrderBtn.setOnClickListener(buttonListener);
        choCompareBtn.setOnClickListener(buttonListener);

        buttonListener = v -> {
            Intent intent;

            Button btn = (Button) v;
            if (btn.getId() == R.id.choCompareBtn) {
                intent = new Intent(ChooseGames.this, CompareActivity.class);
                startActivity(intent);
            }
            if (btn.getId() == R.id.choOrderBtn) {
                intent = new Intent(ChooseGames.this, OrderActivity.class);
                startActivity(intent);
            }
            if (btn.getId() == R.id.choComposeBtn) {
                intent = new Intent(ChooseGames.this, ComposeActivity.class);
                startActivity(intent);
            }
            if (btn.getId() == R.id.choBackBtn) {
                intent = new Intent(ChooseGames.this, MainActivity.class);
                startActivity(intent);
            }
            if (btn.getId() == R.id.homeQuitBt)
                finish();
        };



    }
}
