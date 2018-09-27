package s2017s16.kr.hs.mirim.petness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button signin = findViewById(R.id.btn_signin);
        Button login=findViewById(R.id.btn_login);

        login.setOnClickListener(handler);
        signin.setOnClickListener(handler);
    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_signin:
                    Intent intent = new Intent(LoginActivity.this,SigninMainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_login:

                    Toast.makeText(LoginActivity.this, "로그인되었습니다:)", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    };
}
