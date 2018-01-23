package com.alper.pola.andoid.onlinemarket.Activity.LoginActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.alper.pola.andoid.onlinemarket.Activity.MainActivity.MainActivity;
import com.alper.pola.andoid.onlinemarket.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_button)
    Button loginButton;

    private String facebookEmail;
    private String facebookId;
    private String facebookName;
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private GraphRequest request;
    private AuthCredential credential;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private Intent intent;
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        setProgressDialog();
        loginButton.setOnClickListener(view -> {
            progressDialog.show();
            setFacebook();
        });


    }

    private void setFacebook() {
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("email"));
        List<String> permissions = new ArrayList<>();
        permissions.add("email");

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                    Log.e("object", object.toString());
                    try {
                        facebookEmail = object.getString("email");
                        facebookId = object.getString("id");
                        facebookName = object.getString("name");


                        Log.d("emailper", facebookEmail + " " + facebookName + " " + facebookId);

                    } catch (Exception e) {
                        facebookEmail = "";
                        e.printStackTrace();
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email, name");
                request.setParameters(parameters);
                request.executeAsync();

                handleFacebookAccessToken(loginResult.getAccessToken());


            }

            @Override
            public void onCancel() {
                Log.e("dd", "facebook login canceled");
                progressDialog.dismiss();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("dd", "facebook login error");
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (callbackManager != null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("", "handleFacebookAccessToken:" + token);

        credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("", "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
//
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("", "signInWithCredential:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                    // ...
                });
    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {
            intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("facebookid", facebookId);
            intent.putExtra("facebookname", facebookName);
            intent.putExtra("facebookemail", facebookEmail);

            startActivity(intent);
            progressDialog.dismiss();

            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();

        }
    }

    public void setProgressDialog() {
        progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging in");

    }
}