package my.edu.utar.assignment1;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class OrderActivity extends AppCompatActivity {

    TextView num1, num2, num3, ans1, ans2, ans3;
    private int number1,number2,number3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        num1 = (TextView) findViewById(R.id.o_num1);
        num2 = (TextView) findViewById(R.id.o_num2);
        num3 = (TextView) findViewById(R.id.o_num3);

        ans1 = findViewById(R.id.o_ans1);
        ans2 = findViewById(R.id.o_ans2);
        ans3 = findViewById(R.id.o_ans3);

        LinearLayout orderLinearLayout = findViewById(R.id.order_ll);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bg_pic = BitmapFactory.decodeResource(getResources(),R.drawable.all_bg2,options);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bg_pic);
        orderLinearLayout.setBackground(bitmapDrawable);



        num1.setOnTouchListener(touchListener);
        num2.setOnTouchListener(touchListener);
        num3.setOnTouchListener(touchListener);


//        num1.setOnLongClickListener(longClickListener);
//        num2.setOnLongClickListener(longClickListener);
//
//        num3.setOnLongClickListener(longClickListener);
        ans1.setOnDragListener(dragListener);
        ans2.setOnDragListener(dragListener);
        ans3.setOnDragListener(dragListener);

        generateNumbers();
    }



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
//    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View v) {
//            ClipData data = ClipData.newPlainText("", "");
//            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
//            v.startDrag(data, myShadowBuilder, v, 0);
//            return true;
//        }
//    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            TextView tv = findViewById( v.getId());
            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(getDrawable(R.drawable.stars));
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.o_num1) {
                        tv.setText("Num1 is being dragged");
                    }
                    else if (view.getId() == R.id.o_num2){
                        tv.setText("Num2 is being dragged");
                    } else if (view.getId() == R.id.o_num3){
                        tv.setText("Num3 is being dragged");
                    }

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(getDrawable(R.drawable.text_bg));

                    tv.setText("");
                    break;
                case DragEvent.ACTION_DROP:
                    final View view1 = (View) event.getLocalState();

                    if (view1.getId() == R.id.o_num1) {
                        tv.setText("Num1");

                    }
                    else if (view1.getId() == R.id.o_num2){
                        tv.setText("Num2");
                    } else if (view1.getId() == R.id.o_num3){
                        tv.setText("Num3");
                    }
                    break;
            }
            return true;
        }
    };
    private void generateNumbers() {
        Random random = new Random();
        number1 = random.nextInt(10) + 1;
        number2 = random.nextInt(10) + 1;
        number3 = random.nextInt(10) + 1;

        while (number2 == number1) {
            number2 = random.nextInt(10) + 1;
        }

        num1.setText(String.valueOf(number1));
        num2.setText(String.valueOf(number2));
        num3.setText(String.valueOf(number2));

    }

}
