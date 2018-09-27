package s2017s16.kr.hs.mirim.petness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteTodoActivity extends AppCompatActivity {
    TextView dateNow;
    EditText first, second, third, diary;
    CheckBox check1, check2, check3;
    ProgressBar progress;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdfNow = new SimpleDateFormat("MM월 dd일 E요일");
    String formatDate = sdfNow.format(date);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_todo);
        dateNow = findViewById(R.id.date);
        first=findViewById(R.id.first_todo);
        second=findViewById(R.id.second_todo);
        third=findViewById(R.id.third_todo);
        diary=findViewById(R.id.diary);
        check1=findViewById(R.id.first_check);
        check2=findViewById(R.id.second_check);
        check3=findViewById(R.id.third_check);
        progress=findViewById(R.id.progress);

        check1.setOnClickListener(checkhandler);
        check2.setOnClickListener(checkhandler);
        check3.setOnClickListener(checkhandler);

        dateNow.setText(formatDate);

        progress.setMax(100);


    }//onCreate

    View.OnClickListener checkhandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!(check1.isChecked() && check2.isChecked() && check3.isChecked())) progress.setProgress(0);
            if((check1.isChecked() && check2.isChecked()) || (check1.isChecked() && check3.isChecked()) || (check2.isChecked() && check3.isChecked())) progress.setProgress(70);
            if(check1.isChecked() && check2.isChecked() && check3.isChecked()) progress.setProgress(100);
            if(check1.isChecked()&&!(check2.isChecked())&&!(check3.isChecked())) progress.setProgress(35);
            if(check2.isChecked()&&!(check1.isChecked())&&!(check3.isChecked())) progress.setProgress(35);
            if(check3.isChecked()&&!(check2.isChecked())&&!(check1.isChecked())) progress.setProgress(35);
        }
    };
}//WriteTodoActivity
