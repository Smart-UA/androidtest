package home.smart.cn.ua.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AboutActivity extends Activity implements View.OnClickListener {

    private Button btnLogout;
    private TextView accountLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        accountLogin = (TextView) findViewById(R.id.aboutAccount);
        accountLogin.setText(getIntent().getExtras().getString("Login"));

        btnLogout = (Button) findViewById(R.id.aboutButtonLogout);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
