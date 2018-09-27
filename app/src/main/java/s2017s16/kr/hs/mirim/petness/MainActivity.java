package s2017s16.kr.hs.mirim.petness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.ActivityGroup;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private TabHost tabhost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*toolbar=(Toolbar)findViewById(R.id.toolbar);
        ((Toolbar)findViewById(R.id.toolbar)).setLogo(R.drawable.toolbar_logo);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);*/

        tabhost=(TabHost)findViewById(R.id.tabhost);

        TabHost.TabSpec spec;
        tabhost.setup();

        spec=tabhost.newTabSpec("tab1").setContent(R.id.tab1).setIndicator("",getResources().getDrawable(R.drawable.tab1_o));
        tabhost.addTab(spec);

        spec = tabhost.newTabSpec("Tab2").setContent(R.id.tab2).setIndicator("",getResources().getDrawable(R.drawable.tab2_o));
        tabhost.addTab(spec);

        spec = tabhost.newTabSpec("Tab3").setContent(R.id.tab3).setIndicator("",getResources().getDrawable(R.drawable.tab3_o));
        tabhost.addTab(spec);

        tabhost.setCurrentTab(1);

        Button btn_status=findViewById(R.id.btn_status);
        Button btn_alarm=findViewById(R.id.btn_alarm);
        Button btn_plan=findViewById(R.id.btn_plan);
        Button btn_vet=findViewById(R.id.btn_vet);
        btn_status.setOnClickListener(handler);
        btn_plan.setOnClickListener(handler);
        btn_alarm.setOnClickListener(handler);
        btn_vet.setOnClickListener(handler);
    }//onCreate

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent;
            switch(v.getId()){
                case R.id.btn_status:
                    intent = new Intent().setClass(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_plan:
                    intent =new Intent(MainActivity.this, WriteTodoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_alarm:
                    intent =new Intent(MainActivity.this, InfoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_vet:
                   // intent=new Intent(MainActivity.this, VetActivity.class);
                   // startActivity(intent);
                    break;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected

}//MainActivity