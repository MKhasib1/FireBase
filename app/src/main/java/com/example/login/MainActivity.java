package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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



public class MainActivity extends AppCompatActivity {
    protected EditText email;
    protected EditText password;
    protected Button Login;
    protected Button SignUp;
    protected FirebaseAuth mAuth;
    protected Button UpdatePassword;

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
        UpdatePassword=findViewById(R.id.UpdatePassword);
        mAuth = FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email!=null&&password!=null&&TextUtils.isEmpty(email.getText().toString())==false&&TextUtils.isEmpty(password.getText().toString())==false){
                    mSginIN(email.getText().toString(), password.getText().toString());

                }
                else
                { Toast.makeText(MainActivity.this, "Fill both email with password please!",
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
                { Toast.makeText(MainActivity.this, "Fill both email with password please!",
                        Toast.LENGTH_SHORT).show();

                }

            }
        });
        UpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email!=null&&password!=null&&TextUtils.isEmpty(email.getText().toString())==false&&TextUtils.isEmpty(password.getText().toString())==false)
                {
                    mUpdatePassword(email.getText().toString(),password.getText().toString());
                }
                else
                { Toast.makeText(MainActivity.this, "Fill both email with password please!",
                        Toast.LENGTH_SHORT).show();

                }
            }
        });



    }

    private void mUpdatePassword(String toString, String toString1){
        mAuth.signInWithEmailAndPassword(toString, toString1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("success", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            passwordDialoag(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            String type=task.getException().toString();

                            if(type.contains("The email address is badly formatted"))
                                Toast.makeText(MainActivity.this, "The Email isn't formatted correctly",
                                        Toast.LENGTH_SHORT).show();
                            if(type.contains("There is no user record corresponding to this identifier"))
                                Toast.makeText(MainActivity.this, "No Such Email exists",
                                        Toast.LENGTH_SHORT).show();
                            if(type.contains("The password is invalid"))
                                Toast.makeText(MainActivity.this, "InCorrect Password",
                                        Toast.LENGTH_SHORT).show();
                            Log.e("error", task.getException().toString());

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
                            String type=task.getException().toString();

                            if(type.contains("The email address is badly formatted"))
                                Toast.makeText(MainActivity.this, "The Email isn't formatted correctly",
                                        Toast.LENGTH_SHORT).show();
                            if(type.contains("There is no user record corresponding to this identifier"))
                                Toast.makeText(MainActivity.this, "No Such Email exists",
                                        Toast.LENGTH_SHORT).show();
                            if(type.contains("The password is invalid"))
                                Toast.makeText(MainActivity.this, "InCorrect Password",
                                        Toast.LENGTH_SHORT).show();
                            Log.e("error", task.getException().toString());

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
                            Log.e("error", "createUserWithEmail:failure", task.getException());
                            String type=task.getException().toString();

                            if(type.contains("The email address is badly formatted"))
                                Toast.makeText(MainActivity.this, "The Email isn't formatted correctly",
                                        Toast.LENGTH_SHORT).show();
                            if(type.contains("There is no user record corresponding to this identifier"))
                                Toast.makeText(MainActivity.this, "No Such Email exists",
                                        Toast.LENGTH_SHORT).show();
                            if(type.contains("The password is invalid"))
                                Toast.makeText(MainActivity.this, "InCorrect Password",
                                        Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this, "The Email is already exists",
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
    private void passwordDialoag(FirebaseUser user) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Confirmation");
        final EditText newPass = new EditText(MainActivity.this);
        final EditText confirmPass = new EditText(MainActivity.this);
        final FirebaseUser user1=user;

        newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        confirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        newPass.setHint("New Password");
        confirmPass.setHint("Confirm Password");
        LinearLayout ll = new LinearLayout(MainActivity.this);
        ll.setOrientation(LinearLayout.VERTICAL);


        ll.addView(newPass);
        ll.addView(confirmPass);
        alertDialog.setView(ll);
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        user1.updatePassword(newPass.getText().toString());
                        Toast.makeText(MainActivity.this, "Password Updated Successfully!",
                                Toast.LENGTH_SHORT).show();

                        email.setHint("Enter your Email");
                        password.setHint("Enter your Password");
                        dialog.cancel();
                    }
                });
        alertDialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = alertDialog.create();
        alert11.show();
    }

}
