package my.edu.utar.assignment1;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class OrderActivity extends AppCompatActivity {

    TextView num1, num2, num3, ans1, ans2, ans3, levelText, questionText;
    TextView[] ans = new TextView[3];
    private int number1, number2, number3;
    LinearLayout q_ll, a_ll, c_ll;
    int level = 0;
    Button nextBtn,finishBtn;
    Boolean isAscending;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        a_ll = findViewById(R.id.order_answer_ll);
        q_ll = findViewById(R.id.order_question_ll);
        c_ll = findViewById(R.id.order_complete_ll);

        questionText = findViewById(R.id.order_question);
        levelText = findViewById(R.id.order_level);
        nextBtn = findViewById(R.id.order_nextBtn);
        finishBtn= findViewById(R.id.order_finish);
        num1 = (TextView) findViewById(R.id.o_num1);
        num2 = (TextView) findViewById(R.id.o_num2);
        num3 = (TextView) findViewById(R.id.o_num3);


        ans[0] = findViewById(R.id.o_ans1);
        ans[1] = findViewById(R.id.o_ans2);
        ans[2] = findViewById(R.id.o_ans3);
        ans1 = findViewById(R.id.o_ans1);
        ans2 = findViewById(R.id.o_ans2);
        ans3 = findViewById(R.id.o_ans3);

        LinearLayout orderLinearLayout = findViewById(R.id.order_ll);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bg_pic = BitmapFactory.decodeResource(getResources(), R.drawable.allbg4, options);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bg_pic);
        orderLinearLayout.setBackground(bitmapDrawable);


        nextBtn.setOnClickListener(onClickListener_next);
        finishBtn.setOnClickListener(onClickListener_finish);


        num1.setOnTouchListener(touchListener);
        num2.setOnTouchListener(touchListener);
        num3.setOnTouchListener(touchListener);



        for(int i =0;i<3;i++){
            ans[i].setOnDragListener(dragListener);
        }
//        ans1.setOnDragListener(dragListener);
//        ans2.setOnDragListener(dragListener);
//        ans3.setOnDragListener(dragListener);

        levelText.setText("Level " + level);
        generateNumbers();
    }

    View.OnClickListener onClickListener_next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(level<10){
                q_ll.setVisibility(View.VISIBLE);
                a_ll.setVisibility(View.GONE);
                generateNumbers();
                clearAnswer();
            }
            else{
                c_ll.setVisibility(View.VISIBLE);
                a_ll.setVisibility(View.GONE);
            }
        }
    };

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, myShadowBuilder, v, 0);
                return true;
            }
            return false;
        }
    };

    View.OnClickListener onClickListener_finish = new View.OnClickListener(){
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(OrderActivity.this,ChooseGames.class);
        startActivity(intent);
    }
};

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            TextView tv = findViewById(v.getId());
            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(getDrawable(R.drawable.stars));
                    final View view = (View) event.getLocalState();

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(getDrawable(R.drawable.order_answer));
                    tv.setText("");
                    break;
                case DragEvent.ACTION_DROP:
                    final View view1 = (View) event.getLocalState();

                    if (view1.getId() == R.id.o_num1) {
                        tv.setText(num1.getText());
                    } else if (view1.getId() == R.id.o_num2) {
                        tv.setText(num2.getText());
                    } else if (view1.getId() == R.id.o_num3) {
                        tv.setText(num3.getText());
                    }
                    checkAnswer();
                    break;
            }
            return true;
        }
    };


    private void generateNumbers() {
        level++;
        Random random = new Random();
        int ad = random.nextInt(10)+1;
        Set<Integer> numbers = new HashSet<>();

        while (numbers.size() < 3) {
            numbers.add(random.nextInt(20) + 1);
        }

        Iterator<Integer> iterator = numbers.iterator();
        number1 = iterator.next();
        number2 = iterator.next();
        number3 = iterator.next();

        num1.setText(String.valueOf(number1));
        num2.setText(String.valueOf(number2));
        num3.setText(String.valueOf(number3));

        levelText.setText("Level " + level);
        if(ad%2==0){
            isAscending = true;
            questionText.setText("Arrange them in ascending order:");
        }
        else{
            isAscending = false;
            questionText.setText("Arrange them in descending order:");
        }
    }

    private void clearAnswer() {
        for (int i = 0; i < 3; i++) {
            ans[i].setBackground(getDrawable(R.drawable.order_answer));
            ans[i].setText("");
        }
        //ans1.setBackground(getDrawable(R.drawable.order_answer));

    }

    private void checkAnswer() {
        try {
            int answer1 = Integer.parseInt(ans1.getText().toString());
            int answer2 = Integer.parseInt(ans2.getText().toString());
            int answer3 = Integer.parseInt(ans3.getText().toString());

            if(isAscending){
                if (answer1 < answer2 && answer2 < answer3) {
                    q_ll.setVisibility(View.GONE);
                    a_ll.setVisibility(View.VISIBLE);
                }
            }
            else{
                if (answer1 > answer2 && answer2 > answer3) {
                    q_ll.setVisibility(View.GONE);
                    a_ll.setVisibility(View.VISIBLE);
                }
            }

        } catch (NumberFormatException e) {

        }
    }
}
