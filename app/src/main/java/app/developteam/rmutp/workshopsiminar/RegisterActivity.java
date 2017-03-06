package app.developteam.rmutp.workshopsiminar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUser;
    EditText edtPass;
    EditText edtConfirmPass;
    EditText edtEmail;
    Button btnSignup;

    int checkuser = -1;
    String sCheck ="-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUser = (EditText) findViewById(R.id.sign_user);
        edtPass = (EditText) findViewById(R.id.sign_pass);
        edtConfirmPass = (EditText) findViewById(R.id.sign_confirm);
        edtEmail = (EditText) findViewById(R.id.sign_email);
        btnSignup = (Button) findViewById(R.id.signup_btn);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                String confirm = edtConfirmPass.getText().toString();
                String email = edtEmail.getText().toString();

                if (!user.equals("") && !pass.equals("") && !confirm.equals("") && !email.equals("")) {
                    if (pass.equals(confirm)) {

                        OkHttpClient okHttpClient = new OkHttpClient();
                        RequestBody requestBody = new FormEncodingBuilder()
                                .add("user", user)
                                .add("pass", pass)
                                .add("email", email)
                                .build();

                        Request request = new Request.Builder()
                                .url("http://192.168.1.36/workshop/insertuser.php")
                                .post(requestBody)
                                .build();

                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {

                            }

                            @Override
                            public void onResponse(Response response) throws IOException {
                                sCheck = response.body().string();
                                checkuser=Integer.valueOf(sCheck);
                            }
                        });


                        if (checkuser == 1) {

                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "user นี้มีผู้ใช้งานแล้ว", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "พาสเวิร์ดผิด", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "ข้อมูลไม่ครบ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
