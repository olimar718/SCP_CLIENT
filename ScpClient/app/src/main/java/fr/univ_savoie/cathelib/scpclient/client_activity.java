package fr.univ_savoie.cathelib.scpclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.jcraft.jsch.*;
public class client_activity extends AppCompatActivity {
    private final static String TAG = client_activity.class.getName();
    private String ip;
private String password;
private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clien_activity);
        Intent intent = super.getIntent();

     /*   this.ip = intent.getStringExtra("ip");
        this.password = intent.getStringExtra("password");
        this.username = intent.getStringExtra("user");*/
        this.ip = "10.102.251.5";
        this.password = "TGJIa1wvA8dQtT0sgkcY";
        this.username = "cathelib";

        Toast.makeText(this, "Connecting to : "+ip+"password : "+password+"username : "+username, Toast.LENGTH_LONG).show();
                new JschThread(this,ip, username, password);
        }
    }


