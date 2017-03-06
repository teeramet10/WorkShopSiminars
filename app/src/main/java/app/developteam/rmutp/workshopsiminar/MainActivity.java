package app.developteam.rmutp.workshopsiminar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static int COMPLETE = 1;
    EditText edtUser;
    EditText edtPass;
    Button btnLogin;
    TextView tvSignUp;

    int status = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUser = (EditText) findViewById(R.id.edituser);
        edtPass = (EditText) findViewById(R.id.editpass);
        btnLogin = (Button) findViewById(R.id.login);
        tvSignUp = (TextView) findViewById(R.id.signup);

        SharePreference sp = new SharePreference(MainActivity.this);
        boolean status_boolean = sp.getStatus();

        if (status_boolean) {
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
        }

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();

                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.1.36/workshop/checkuser.php?user=" + user + "&pass=" + pass)
                        .build();


                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        status = Integer.valueOf(response.body().string());
                    }
                });


                if (status == COMPLETE) {
                    SharePreference sp = new SharePreference(MainActivity.this);
                    sp.setStatus(true);
                    sp.setUser(user);

                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(intent);
                    finish();
                } else if(status !=COMPLETE){
                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}
