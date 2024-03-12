package my.edu.utar.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    View.OnClickListener buttonListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.homePlayBtn);
        Button button1 = findViewById(R.id.homeQuitBt);

        // Create a GradientDrawable
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, // Gradient orientation

                new int[]{getResources().getColor(R.color.startColor), getResources().getColor(R.color.endColor)} // Gradient colors
        );

        gradientDrawable.setCornerRadius(getResources().getDimension(R.dimen.button_corner_radius));

        // Set the gradient drawable as the background tint
        button.setBackgroundTintList(null); // To remove any existing tint
        button.setBackground(gradientDrawable);
        button1.setBackgroundTintList(null); // To remove any existing tint
        button1.setBackground(gradientDrawable);

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