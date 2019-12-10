package fr.univ_savoie.cathelib.scpclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    private final static String TAG = login.class.getName();
    private EditText ip;
    private EditText password;
    private EditText username;
    private Button validate;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: ");
        this.ip = super.findViewById(R.id.ip);
        this.password = super.findViewById(R.id.password);
        this.username = super.findViewById(R.id.user);
        this.sharedPreferences = super.getPreferences(MODE_PRIVATE);
        this.validate = super.findViewById(R.id.validate);
        this.validate.setEnabled(false);
        this.ip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after)
            {
                login.this.ip.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count)
            {
                login.this.validate.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable text)
            {
                if(text.length() == 0)
                {
                    login.this.validate.setEnabled(false);
                }
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

    }

    public void validate(View view) {
        Log.d(TAG, "validate: ");
        if(Patterns.IP_ADDRESS.matcher(this.ip.getText()).matches())
        {
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putString("ip", this.ip.getText().toString());
            editor.commit();

            this.nextActivity(this.ip.getText().toString(), this.password.getText().toString(), this.username.getText().toString());
        }
        else
        {
            this.ip.setError(super.getString(R.string.ip_not_valid));
        }
    }
    public void nextActivity(String ip, String password, String user)
    {
        Intent intent = new Intent(this, client_activity.class);
        intent.putExtra("ip", ip);
        intent.putExtra("password", password);
        intent.putExtra("user", user);
        super.startActivity(intent);
        super.finish();
    }
}
