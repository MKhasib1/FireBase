package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity {
    protected EditText email;
    protected EditText password;
    protected Button Login;
    protected Button SignUp;
    protected FirebaseAuth mAuth;

    public  static final String EXTRA_EMAIL="com.example.login.EXTRA_EMAIL";
    public  static final String EXTRA_NAME="com.example.login.EXTRA_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        Login=findViewById(R.id.Login);
        SignUp=findViewById(R.id.SignUp);
        mAuth = FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email!=null&&password!=null&&TextUtils.isEmpty(email.getText().toString())==false&&TextUtils.isEmpty(password.getText().toString())==false){
                    mSginIN(email.getText().toString(), password.getText().toString());

                }
                else
                { Toast.makeText(MainActivity.this, "Enter the email with password",
                        Toast.LENGTH_SHORT).show();

                }
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email!=null&&password!=null&&TextUtils.isEmpty(email.getText().toString())==false&&TextUtils.isEmpty(password.getText().toString())==false)
                mSginUp(email.getText().toString(),password.getText().toString());
                else
                { Toast.makeText(MainActivity.this, "Enter the email with password",
                        Toast.LENGTH_SHORT).show();

                }

            }
        });



    }



    private void mSginIN(String toString, String toString1){
        mAuth.signInWithEmailAndPassword(toString, toString1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("sucess", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void mSginUp(String toString, String toString1) {
        mAuth.createUserWithEmailAndPassword(toString,toString1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("success", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("Fail", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


  /*  @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        updateUI(currentUser);
    }
*/
    private void updateUI(FirebaseUser currentUser) {
        if(currentUser!=null)
        {
            openNewActivity(currentUser.getEmail());
        }
    }

    private void openNewActivity(String email) {
        Intent intent= new Intent(this,Sucess.class);
        intent.putExtra(EXTRA_EMAIL,email);

        startActivity(intent);
    }

}
