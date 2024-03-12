package my.edu.utar.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CompareActivity extends AppCompatActivity {

    private TextView compareText;
    private Button compNumOne, compNumTwo, compSubmitBtn;
    private int number1 , number2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        compareText = findViewById(R.id.caompareText);
        compNumOne = findViewById(R.id.conpNumOne);
        compNumTwo = findViewById(R.id.compNumTwo);
        compSubmitBtn = findViewById(R.id.compSubmitBtn);

        generateNumbers();

        compNumOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(number1, number2);
            }
        });

        compNumTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(number2, number1);
            }
        });

        compSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNumbers();
            }
        });
    }

    private void generateNumbers() {
        Random random = new Random();

            number1 = random.nextInt(10) + 1;
            number2 = random.nextInt(10) + 1;

        while (number2 == number1) {
            number2 = random.nextInt(10) + 1;
        }

        compNumOne.setText(String.valueOf(number1));
        compNumTwo.setText(String.valueOf(number2));

        compareText.setText("Please choose the bigger number");
    }

    private void checkAnswer(int selectedNumber, int otherNumber) {
        if (selectedNumber > otherNumber) {
            compareText.setText("Correct! " + selectedNumber + " is bigger.");
        } else {
            compareText.setText("Wrong! " + selectedNumber + " is smaller.");
        }
    }
}