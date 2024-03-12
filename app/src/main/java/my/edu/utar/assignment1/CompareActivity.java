package my.edu.utar.assignment1;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CompareActivity extends AppCompatActivity {

    private TextView compareText;
    private Button compNumOne, compNumTwo, compSubmitBtn;
    private int number1 , number2, count;
    private boolean num1 = false, num2 = false;

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
                num1 = true;
                num2 = false;
                compNumOne.setBackgroundColor(getResources().getColor(R.color.btn_selected));
                compNumTwo.setBackgroundColor(getResources().getColor(R.color.btn_origin));                //checkAnswer(number1, number2);
            }
        });

        compNumTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2 = true;
                num1 = false;
                compNumTwo.setBackgroundColor(getResources().getColor(R.color.btn_selected));
                compNumOne.setBackgroundColor(getResources().getColor(R.color.btn_origin));
                //checkAnswer(number2, number1);
            }
        });

        compSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1){
                    checkAnswer(number1,number2);
                }
                else
                    checkAnswer(number2,number1);

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
        compNumOne.setBackgroundColor(getResources().getColor(R.color.btn_origin));
        compNumTwo.setBackgroundColor(getResources().getColor(R.color.btn_origin));
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