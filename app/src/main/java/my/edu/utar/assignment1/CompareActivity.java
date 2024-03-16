package my.edu.utar.assignment1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CompareActivity extends AppCompatActivity {

    private TextView compareText;
    private Button compNumOne, compNumTwo, compSubmitBtn, nextBtn;
    private ImageView greatJobImage, wrongAnswerImage;
    private int number1, number2, ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        LinearLayout compareLinearLayout = findViewById(R.id.compare_ll);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bg_pic = BitmapFactory.decodeResource(getResources(),R.drawable.all_bg,options);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bg_pic);
        compareLinearLayout.setBackground(bitmapDrawable);

        compareText = findViewById(R.id.caompareText);
        compNumOne = findViewById(R.id.conpNumOne);
        compNumTwo = findViewById(R.id.compNumTwo);
        compSubmitBtn = findViewById(R.id.compSubmitBtn);
        greatJobImage = findViewById(R.id.greatJobImage);
        wrongAnswerImage = findViewById(R.id.wrongAnswerImage);
        nextBtn = findViewById(R.id.nextBtn);

        greatJobImage.setVisibility(View.GONE);
        wrongAnswerImage.setVisibility(View.GONE);
        nextBtn.setVisibility(View.GONE);
        //nextBtn.setBackground(getDrawable(R.drawable.button_border));

        generateNumbers();

        compNumOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compNumOne.setBackground(getResources().getDrawable(R.drawable.button_selected));
                compNumTwo.setBackground(getDrawable(R.drawable.button_border));
                //checkAnswer(number1, number2);
                ans = 1;
            }
        });

        compNumTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compNumTwo.setBackground(getResources().getDrawable(R.drawable.button_selected));
                compNumOne.setBackground(getDrawable(R.drawable.button_border));
                //checkAnswer(number2, number1);
                ans = 2;
            }
        });

        compSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide comparison elements
                compareText.setVisibility(View.GONE);
                compNumOne.setVisibility(View.GONE);
                compNumTwo.setVisibility(View.GONE);
                compSubmitBtn.setVisibility(View.GONE);

                if (ans == 1)
                    checkAnswer(number1, number2);
                else
                    checkAnswer(number2, number1);
                // Show result image
//                if (greatJobImage.getVisibility() == View.VISIBLE) {
//                    greatJobImage.setVisibility(View.VISIBLE);
//                } else if (wrongAnswerImage.getVisibility() == View.VISIBLE) {
//                    wrongAnswerImage.setVisibility(View.VISIBLE);
//                }

                compNumOne.setBackground(getResources().getDrawable(R.drawable.button_border));
                compNumTwo.setBackground(getResources().getDrawable(R.drawable.button_border));
                // Show next button
                nextBtn.setVisibility(View.VISIBLE);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide result image and next button
                greatJobImage.setVisibility(View.GONE);
                wrongAnswerImage.setVisibility(View.GONE);
                nextBtn.setVisibility(View.GONE);


                // Generate new numbers and show comparison elements
                generateNumbers();
                compareText.setVisibility(View.VISIBLE);
                compNumOne.setVisibility(View.VISIBLE);
                compNumTwo.setVisibility(View.VISIBLE);
                compSubmitBtn.setVisibility(View.VISIBLE);
            }
        });

        // Handle touch event to hide result image
        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                greatJobImage.setVisibility(View.GONE);
                wrongAnswerImage.setVisibility(View.GONE);
                return false;
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
        boolean correct = true;
        if (selectedNumber > otherNumber) {
            correct = true;
            animateGreatJobImage(greatJobImage,correct);
            //greatJobImage.setVisibility(View.VISIBLE);

        } else {
            correct = false;
            animateGreatJobImage(wrongAnswerImage,correct);
            //wrongAnswerImage.setVisibility(View.VISIBLE);

        }
    }

    private void animateGreatJobImage(ImageView iv,boolean correct) {
        iv.setVisibility(View.VISIBLE);

        Bitmap bitmap;
        // Load the bitmap image using BitmapFactory with options to downscale
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2; // Adjust the sample size as needed to reduce the image size
        if(correct){
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.greatjob, options);
        }else
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.wrong_answer,options);
        //Bitmap bitmap = BitmapFactory.decodeResource(iv);

        // Set the bitmap to the ImageView
        iv.setImageBitmap(bitmap);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000); // Animation duration in milliseconds
        //scaleAnimation.setFillAfter(true);

        iv.startAnimation(scaleAnimation);
        //iv.setVisibility(View.GONE);
    }
}
