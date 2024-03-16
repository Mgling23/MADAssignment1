package my.edu.utar.assignment1;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    TextView num1, num2, num3, ans1, ans2, ans3;

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



        num1.setOnLongClickListener(longClickListener);
        num2.setOnLongClickListener(longClickListener);

        num3.setOnLongClickListener(longClickListener);

    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder();
            v.startDrag(data, myShadowBuilder, v, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.o_ans1) {
                        ans1.setText("Num1 is being dragged");
                    }

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    };


}
