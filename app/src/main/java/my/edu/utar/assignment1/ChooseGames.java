package my.edu.utar.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseGames extends AppCompatActivity {

    View.OnClickListener buttonListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosegame);


        buttonListener = v -> {
            Intent intent;

            Button btn = (Button) v;
            if(btn.getId() == R.id.choCompareBtn) {
                intent = new Intent(ChooseGames.this,CompareActivity.class);
                startActivity(intent);
            }
            if(btn.getId() == R.id.choOrderBtn) {
                intent = new Intent(ChooseGames.this,CompareActivity.class);
                startActivity(intent);
            }
            if(btn.getId() == R.id.choCompareBtn) {
                intent = new Intent(ChooseGames.this,CompareActivity.class);
                startActivity(intent);
            }
            if(btn.getId() == R.id.choBackBtn) {
                intent = new Intent(ChooseGames.this,MainActivity.class);
                startActivity(intent);
            }
            if(btn.getId() == R.id.homeQuitBt)
                finish();
        };

        Button choCompareBtn = findViewById(R.id.choCompareBtn);
        Button choOrderBtn = findViewById(R.id.choOrderBtn);
        Button choComposeBtn = findViewById(R.id.choComposeBtn);



        choComposeBtn.setOnClickListener(buttonListener);
        choOrderBtn.setOnClickListener(buttonListener);
        choCompareBtn.setOnClickListener(buttonListener);

    }
}
