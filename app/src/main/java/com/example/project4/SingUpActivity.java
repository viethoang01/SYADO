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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingUpActivity extends AppCompatActivity {

    private EditText edt_email,edt_password,edt_re_password;
    private Button btn_signup;
    private ProgressDialog progressDialog;
    private LinearLayout layout_signin;
    private void onBindingView(){

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_signup = findViewById(R.id.btn_signup);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang xử lý");
        progressDialog.setMessage("Vui lòng chờ");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true); //chỉ sử dụng cho style spinner

        layout_signin = findViewById(R.id.layout_signin);
        edt_re_password = findViewById(R.id.edt_re_password);

        //share preference
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("email", "");
        String pass = preferences.getString("pass", "");
        String repass = preferences.getString("repass", "");
        edt_email.setText(email);


        // end
    }
    private void onBindingAction(){
        layout_signin.setOnClickListener(this:: onSignIn);
        btn_signup.setOnClickListener(this:: onSignUp);
    }

    private void onSignIn(View view) {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
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

    private void validSignUp(){
        String strEmail = edt_email.getText().toString().trim();
        String strPass = edt_password.getText().toString().trim();
        String strRePass = edt_re_password.getText().toString().trim();

        if(!isValidEmail(strEmail)){
            Toast.makeText(this, "Email không đúng format", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
            return;

        }
        if(!strPass.equals(strRePass)){
            Toast.makeText(this, "Xác nhận lại password", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
            return;
        }

    }
    @Override
    protected void onPause() {
        super.onPause();

        String email = edt_email.getText().toString();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("email", email).apply();


    }

    private void onSignUp(View view) {
        String strEmail = edt_email.getText().toString().trim();
        String strPass = edt_password.getText().toString().trim();

        validSignUp();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(strEmail, strPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SingUpActivity.this, " Sign up successfully.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SingUpActivity.this,MainActivity.class);
                            startActivity(intent);  // dong all activity truoc do
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SingUpActivity.this, " authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        onBindingView();
        onBindingAction();

    }
}