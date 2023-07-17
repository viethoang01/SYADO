package com.example.project4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    private LinearLayout layout_signup;
    private TextView tv_pass_login,tv_email_login;
    private ProgressDialog progressDialog;
    private Button btn_signin;
    private void onBindingView(){
        tv_email_login = findViewById(R.id.tv_email_login);
        tv_pass_login = findViewById(R.id.tv_pass_login);

        layout_signup = findViewById(R.id.layout_signup);
        btn_signin = findViewById(R.id.btn_signin);

        //
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang xử lý");
        progressDialog.setMessage("Vui lòng chờ");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true); //chỉ sử dụng cho style spinner
        //

        /// SharedPreferences

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("email", "");


        tv_email_login.setText(email);

        //end SharedPreferences
    }

    private void onBindingAction(){
        layout_signup.setOnClickListener(this:: toSingUpActivity);
        btn_signin.setOnClickListener(this:: onSignIn);
    }
    // hàm check mail
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    // end check mail

    @Override
    protected void onPause() {
        super.onPause();

        String email = tv_email_login.getText().toString();



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("email", email).apply();


    }
    private void checkLogin(){
        String email = tv_email_login.getText().toString();


        if(!isValidEmail(email)){
            Toast.makeText(this, "Email không đúng format", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
            return;
        }
    }
    private void onSignIn(View view) {
        // check login

        checkLogin();

        String email = tv_email_login.getText().toString();
        String password = tv_pass_login.getText().toString();


        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void toSingUpActivity(View view) {
        Intent intent = new Intent(this,SingUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        onBindingView();
        onBindingAction();
    }
}