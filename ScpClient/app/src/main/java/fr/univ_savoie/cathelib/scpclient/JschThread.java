package fr.univ_savoie.cathelib.scpclient;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedOutputStream;

public class JschThread extends Thread {
    private final static String TAG = JschThread.class.getName();
    private String ip;
    private String password;
    private String username;
    private Activity activity;
    public JschThread(Activity activity,String ip, String username, String password){
    this.activity = activity;
    this.ip = ip;
    this.username = username;
    this.password = password;
    this.start();
    }
    public void run() {
    try {
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();
        Session session=jsch.getSession(username, ip, 22);
        session.setConfig(config);
        session.setPassword(password);
        session.connect();
        Channel channel=session.openChannel("shell");
        final String input = new String();
        String output = new String();
        FileInputStream filin = new FileInputStream(input);
        FileOutputStream filout = new FileOutputStream(output);
        channel.setInputStream(filin);
        channel.setOutputStream(filout);
        channel.connect(1000);
        this.activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(activity, input, Toast.LENGTH_LONG).show();
            }
        });
    }catch (Exception e){
        Log.d(TAG, "jsch error: " + e.toString());
    }
    }
}
