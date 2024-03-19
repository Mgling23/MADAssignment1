package my.edu.utar.assignment1;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class ComposeActivity extends AppCompatActivity {


    int level = 0, correct = 0;
    TextView num_sum, level_text;
    TextView[] num = new TextView[6], ans = new TextView[2];
    LinearLayout q_ll, a_ll, c_ll;
    boolean q1f = false, q2f = false;
    Button submitBtn, nextBtn, finish_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        LinearLayout linearLayout_bg = findViewById(R.id.compose_ll);

        q_ll = findViewById(R.id.compose_question_ll);
        a_ll = findViewById(R.id.compose_answer_ll);
        c_ll = findViewById(R.id.compose_complete_ll);
        submitBtn = findViewById(R.id.compose_submit_btn);
        nextBtn = findViewById(R.id.compose_nextBtn);
        finish_btn = findViewById(R.id.compose_finish);
        level_text = findViewById(R.id.compose_level);
        num[0] = findViewById(R.id.compose_num1);
        num[1] = findViewById(R.id.compose_num2);
        num[2] = findViewById(R.id.compose_num3);
        num[3] = findViewById(R.id.compose_num4);
        //num[4] = findViewById(R.id.compose_num5);
        //num[5] = findViewById(R.id.compose_num6);

        ans[0] = findViewById(R.id.compose_ans1);
        ans[1] = findViewById(R.id.compose_ans2);

        num_sum = findViewById(R.id.compose_sum);

        generateNumber();
        for (int i = 0; i < 4; i++) {
            num[i].setOnTouchListener(touchListener);
        }
        for (int i = 0; i < 2; i++) {
            ans[i].setOnDragListener(onDragListener);
        }
        setBackground_ll(linearLayout_bg);

        level_text.setText("Level " + level);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level < 1) {
                    generateNumber();
                    q_ll.setVisibility(View.VISIBLE);
                    a_ll.setVisibility(View.GONE);
                } else {
                    c_ll.setVisibility(View.VISIBLE);
                    a_ll.setVisibility(View.GONE);
                }
            }
        });
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComposeActivity.this, ChooseGames.class);
                startActivity(intent);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();

            }
        });
    }

    View.OnDragListener onDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            TextView tv = findViewById(v.getId());
            int dragEvent = event.getAction();

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    final View view1 = (View) event.getLocalState();
                    int answer = checkNumText(view1);
                    if (v.getId() == ans[0].getId())
                        q1f = true;
                    else
                        q2f = true;
                    if (checkDuplicate(answer)) {
                        tv.setText(String.valueOf(num[answer].getText()));
                    }
                    //checkAnswer();
                    break;

            }
            return true;
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

    private void generateNumber() {
        int q1, q2;
        level++;

        int[] num_q = new int[4];
        Random random = new Random();
        q1 = random.nextInt(4);
        q2 = random.nextInt(4);

        ans[0].setText("");
        ans[1].setText("");
        q1f = false;
        q2f = false;

        while (q2 == q1) {
            q2 = random.nextInt(4);
        }
        //int ad = random.nextInt(10)+1;
        Set<Integer> numbers = new HashSet<>();

        while (numbers.size() < 4) {
            numbers.add(random.nextInt(20) + 1);
        }

        Iterator<Integer> iterator = numbers.iterator();
        for (int i = 0; i < 4; i++) {
            num_q[i] = iterator.next();
            num[i].setText(String.valueOf(num_q[i]));
        }

        num_sum.setText(String.valueOf(num_q[q1] + num_q[q2]));
        level_text.setText("Level " + level);

    }

    private int checkNumText(View v) {
        for (int i = 0; i < 4; i++) {
            if (v.getId() == num[i].getId())
                return i;
        }

        return 5;
    }

    private boolean checkDuplicate(int answer) {
        if (ans[0].getText() == num[answer].getText() || ans[1].getText() == num[answer].getText())
            return false;
        else
            return true;
    }

    public void setBackground_ll(LinearLayout ll) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        Bitmap bg_pic = BitmapFactory.decodeResource(getResources(), R.drawable.allbg4, options);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bg_pic);
        ll.setBackground(bitmapDrawable);
    }

    public void checkAnswer() {

        if (q1f && q2f) {

            int answer1 = Integer.parseInt(ans[0].getText().toString());
            int answer2 = Integer.parseInt(ans[1].getText().toString());
            if ((answer1 + answer2) == Integer.parseInt(num_sum.getText().toString())) {
                q_ll.setVisibility(View.GONE);
                a_ll.setVisibility(View.VISIBLE);
                correct++;
            } else {
            }
        }
    }
}
