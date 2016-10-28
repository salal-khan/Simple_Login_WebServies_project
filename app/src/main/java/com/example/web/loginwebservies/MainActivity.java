package com.example.web.loginwebservies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (TextView)findViewById(R.id.et_username);
        pass = (TextView)findViewById(R.id.et_password);
    }

    public void onlogin(View view){
        String username =user.getText().toString();
        String password =pass.getText().toString();
        BackgroundWorker bgw = new BackgroundWorker(this);
        String type ="login";
        bgw.execute(type ,username,password);
        Intent intent = new Intent();

    }
}
