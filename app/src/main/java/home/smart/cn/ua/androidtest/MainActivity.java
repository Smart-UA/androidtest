package home.smart.cn.ua.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    static final private int login = 0;
    private Button btnLogin;
    private Button btnRegister;
    EmailValidator chechTheMail;
    String hello = getResources().getString(R.string.app_name);
    String wrongPassword = getResources().getString(R.string.errorPasswordNotCorrect);
    String passwordsNotMatch = getResources().getString(R.string.errorPasswordsNotMatch);

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        chechTheMail = new EmailValidator();
        btnLogin = (Button) findViewById(R.id.loginButtonLogin);
        btnRegister = (Button) findViewById(R.id.loginButtonRegister);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        EditText loginLogin = (EditText) findViewById(R.id.loginEmail);
        EditText loginPassword = (EditText) findViewById(R.id.loginPassword);
        switch (v.getId()) {
            case R.id.loginButtonLogin:
                if (chechTheMail.isValid(loginLogin.getText().toString()) && DatabaseHandler.getInstance(this).isAccountExist(new Account(loginLogin.getText().toString(),loginPassword.getText().toString()) )) {
                    intent = new Intent(this, AboutActivity.class);
                    intent.putExtra("Login", loginLogin.getText().toString());
                    startActivity(intent);
                } else {
                    showToast(wrongPassword);
                }
                break;
            case R.id.loginButtonRegister:
                intent = new Intent(this, RegisterActivity.class);
                intent.putExtra("Login", loginLogin.getText().toString());
                startActivityForResult(intent, login);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EditText loginEmail = (EditText) findViewById(R.id.loginEmail);

        if (requestCode == login) {
            if (resultCode == RESULT_OK) {
                String login = data.getStringExtra("Login");
                loginEmail.setText(login);
            } else {
                loginEmail.setText(""); // стираем текст
            }
        }
    }

    public void showToast(String textToShow) {
        Toast toast = Toast.makeText(getApplicationContext(),
                textToShow,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}