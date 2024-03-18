package my.edu.utar.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class CompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        LinearLayout compare_ll = findViewById(R.id.complete_ll);
        //CompareActivity compareActivity = new CompareActivity();
        //compareActivity.setBackground_ll(compare_ll);



    }
}