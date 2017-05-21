package home.smart.cn.ua.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private Button btnRegister;
    private EditText registerLogin;
    private EditText registerPassword;
    private EditText registerPasswordCheck;
    private String wrongEmail;
    private String passwordsNotMatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerLogin = (EditText) findViewById(R.id.registerEmail);
        registerLogin.setText(getIntent().getExtras().getString("Login"));

        registerPassword = (EditText) findViewById(R.id.loginPassword);
        registerPasswordCheck = (EditText) findViewById(R.id.registerPasswordCheck);

        btnRegister = (Button) findViewById(R.id.registerButtonRegister);
        btnRegister.setOnClickListener(this);

        wrongEmail = getString(R.string.errorMailNotValid);
        passwordsNotMatch = getString(R.string.errorPasswordsNotMatch);
    }

    @Override
    public void onClick(View v) {
        Intent loginIntent = new Intent();
        loginIntent.putExtra("Login", registerLogin.getText().toString());
        Boolean isValid = EmailValidator.isValid(registerLogin.getText().toString());
        Boolean isMatch = registerPassword.equals(registerPasswordCheck);
        if (!isValid) {
            showToast(wrongEmail);
        } else {
            if (!isMatch) {
                showToast(passwordsNotMatch);
            } else {
                DatabaseHandler.getInstance(this).addAccount(new Account(registerLogin.getText().toString(), registerPassword.toString()));
            }

        }
        setResult(RESULT_OK, loginIntent);
        finish();
    }

    public void showToast(String textToShow) {
        Toast toast = Toast.makeText(getApplicationContext(),
                textToShow,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
