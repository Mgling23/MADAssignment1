package my.edu.utar.assignment1;

import android.content.Intent;
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
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CompareActivity extends AppCompatActivity {

    private TextView compareText, compareLevel, compareScore;
    private Button compNumOne, compNumTwo, compSubmitBtn, nextBtn, finishBtn, instructionBtn, instructionFBtn;
    private ImageView greatJobImage, wrongAnswerImage;
    private int number1, number2, ans = 0, maxGame = 1, score = 0;
    private boolean isBigger;
    private LinearLayout q_ll, c_ll, r_ll, i_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        LinearLayout compareLinearLayout = findViewById(R.id.compare_ll);
        setBackground_ll(compareLinearLayout);

        Toast toast = Toast.makeText(this, "Please choose an answer", Toast.LENGTH_SHORT);

        i_ll = findViewById(R.id.compare_instruction_ll);
        r_ll = findViewById(R.id.compare_answer_ll);
        c_ll = findViewById(R.id.complete_compare_ll);
        q_ll = findViewById(R.id.compare_question_ll);

        compareLevel = findViewById(R.id.cmpLevelTxt);
        compareText = findViewById(R.id.caompareText);
        compNumOne = findViewById(R.id.conpNumOne);
        compNumTwo = findViewById(R.id.compNumTwo);
        compSubmitBtn = findViewById(R.id.compSubmitBtn);
        greatJobImage = findViewById(R.id.greatJobImage);
        wrongAnswerImage = findViewById(R.id.wrongAnswerImage);

        nextBtn = findViewById(R.id.nextBtn);
        finishBtn = findViewById(R.id.cmp_finish);
        compareScore = findViewById(R.id.cmp_score);
        instructionBtn = findViewById(R.id.compare_instruction_btn);
        instructionFBtn = findViewById(R.id.compare_instruction_f_btn);

        greatJobImage.setVisibility(View.GONE);
        wrongAnswerImage.setVisibility(View.GONE);
        nextBtn.setVisibility(View.GONE);

        compareLevel.setText("Level 1");
        generateNumbers();


        // If the answer button is clicked, change the background if the button
        // and set the answer to 1

        compNumOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compNumOne.setBackground(getResources().getDrawable(R.drawable.button_selected));
                compNumTwo.setBackground(getDrawable(R.drawable.button_border));
                ans = 1;
            }
        });


        // If the answer button is clicked, change the background if the button
        // and set the answer to 2
        compNumTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compNumTwo.setBackground(getResources().getDrawable(R.drawable.button_selected));
                compNumOne.setBackground(getDrawable(R.drawable.button_border));
                ans = 2;
            }
        });

        // event handler when the submit button is clicked, it will check what answer is chosen
        // and check is the correctness of the answer
        compSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check what answer is chosen
                if (ans == 0) {
                    toast.show();
                } else {

                    q_ll.setVisibility(View.GONE);

                    if (ans == 1)
                        checkAnswer(number1, number2, isBigger);
                    else
                        checkAnswer(number2, number1, isBigger);


                    compNumOne.setBackground(getResources().getDrawable(R.drawable.button_border));
                    compNumTwo.setBackground(getResources().getDrawable(R.drawable.button_border));
                    // Show next button
                    r_ll.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        // If the "how to play" button clicked, show the tutorial
        instructionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i_ll.setVisibility(View.VISIBLE);
                q_ll.setVisibility(View.GONE);
            }
        });

        // If close button clicked, close the tutorial
        instructionFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q_ll.setVisibility(View.VISIBLE);
                i_ll.setVisibility(View.GONE);
            }
        });


        // If the next Button is clicked, call the function to
        // generate new question
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maxGame++;
                if (maxGame < 11) { // If the game round > 10 end the game


                    // Hide result image and next button
                    greatJobImage.setVisibility(View.GONE);
                    wrongAnswerImage.setVisibility(View.GONE);
                    r_ll.setVisibility(View.GONE);


                    // Generate new numbers and show comparison elements
                    generateNumbers();
                    q_ll.setVisibility(View.VISIBLE);

                    compareLevel.setText("Level " + maxGame);
                } else { // If game number > 10 , end game and show the score

                    compareScore.setText("Score: " + score + "/" + (maxGame - 1));
                    c_ll.setVisibility(View.VISIBLE);
                    r_ll.setVisibility(View.GONE);
                    q_ll.setVisibility(View.GONE);
                }
            }

        });

        // Button clicked, go back to choose game page
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompareActivity.this, ChooseGames.class);
                startActivity(intent);
            }
        });

    }

    // Function to resize and set the background image
    public void setBackground_ll(LinearLayout ll) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bg_pic = BitmapFactory.decodeResource(getResources(), R.drawable.allbg4, options);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bg_pic);
        ll.setBackground(bitmapDrawable);
    }


    // Function to generate the question for new round
    private void generateNumbers() {

        Random random = new Random();
        int quest = random.nextInt(10) + 1;
        number1 = random.nextInt(10) + 1;
        number2 = random.nextInt(10) + 1;


        while (number2 == number1) {
            number2 = random.nextInt(10) + 1;
        }

        compNumOne.setText(String.valueOf(number1));
        compNumTwo.setText(String.valueOf(number2));
        isBigger = random.nextBoolean();
        if (isBigger) {
            compareText.setText("Please choose the \"BIGGER\" number");
        } else {
            compareText.setText("Please choose the \"SMALLER\" number");
        }
    }


    // function to check the answer according to the question (bigger/smaller)
    private void checkAnswer(int selectedNumber, int otherNumber, boolean isBigger) {
        boolean correct = true;
        if (isBigger) {
            if (selectedNumber > otherNumber) {
                correct = true;
                animateGreatJobImage(greatJobImage, correct);
                // Call the function to play the animation of corresponding image
                score++;

            } else {
                correct = false;
                animateGreatJobImage(wrongAnswerImage, correct);
            }
        } else {
            if (selectedNumber < otherNumber) {
                correct = true;
                animateGreatJobImage(greatJobImage, correct);
                score++;

            } else {
                correct = false;
                animateGreatJobImage(wrongAnswerImage, correct);
            }
        }

    }


    // Function to play the animation of image when get result image
    private void animateGreatJobImage(ImageView iv, boolean correct) {
        iv.setVisibility(View.VISIBLE);

        Bitmap bitmap;
        // Load the bitmap image using BitmapFactory with options to downscale
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3; // Adjust the sample size as needed to reduce the image size
        if (correct) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.greatjob, options);
        } else
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wrong_answer, options);

        // Set the bitmap to the ImageView
        iv.setImageBitmap(bitmap);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000); // Animation duration in milliseconds

        iv.startAnimation(scaleAnimation);
    }

}
