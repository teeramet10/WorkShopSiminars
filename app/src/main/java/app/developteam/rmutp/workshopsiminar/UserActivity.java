package app.developteam.rmutp.workshopsiminar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {
    TextView tvUser;
    TextView tvHello;
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tvUser =(TextView) findViewById(R.id.user);
        btnSignOut =(Button) findViewById(R.id.logout);

        SharePreference sp=new SharePreference(UserActivity.this);
        String user =sp.getUser();
        tvUser.setText(user);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePreference sp=new SharePreference(UserActivity.this);
                sp.removeStatus();
                sp.removeUser();

                Intent intent=new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
