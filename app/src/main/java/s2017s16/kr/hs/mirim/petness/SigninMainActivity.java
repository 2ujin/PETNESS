package s2017s16.kr.hs.mirim.petness;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SigninMainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private static final int RC_SIGN_IN=10;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_main);
        mAuth=FirebaseAuth.getInstance();

        Button google = findViewById(R.id.btn_google);
        Button email = findViewById(R.id.btn_email);

        google.setOnClickListener(handler);
        email.setOnClickListener(handler);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

    }//onCreate

    View.OnClickListener handler = new View.OnClickListener() {
        class RC_SIGN_IN {
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_email:
                    Intent intent = new Intent(SigninMainActivity.this,SigninActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_google:
                    Intent intent2 = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(intent2,RC_SIGN_IN);
                    break;
            }
        }
    };//handler

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }else{

            }
        }
    }//onActivityResult

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){

        AuthCredential credential= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){

                        if(!task.isSuccessful()){

                        }else{
                            Toast.makeText(SigninMainActivity.this, "회원가입이 완료되었습니다:)", Toast.LENGTH_SHORT).show();
                            Intent move = new Intent(SigninMainActivity.this,LoginActivity.class);
                            startActivity(move);
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}//SigninMainActivity
