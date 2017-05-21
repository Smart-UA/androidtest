package home.smart.cn.ua.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private Button btnRegister;
    private EditText registerLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerLogin = (EditText) findViewById(R.id.registerEmail);
        registerLogin.setText(getIntent().getExtras().getString("Login"));

        btnRegister = (Button) findViewById(R.id.registerButtonRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent loginIntent = new Intent();
        loginIntent.putExtra("Login", registerLogin.getText().toString());
        setResult(RESULT_OK, loginIntent);
        finish();
    }
}
