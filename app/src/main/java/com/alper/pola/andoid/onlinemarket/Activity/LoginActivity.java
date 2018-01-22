package com.alper.pola.andoid.onlinemarket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.alper.pola.andoid.onlinemarket.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_button)
    Button loginButton;
    CallbackManager callbackManager;
    String facebookEmail;
    String facebookId;
    String facebookName;
    JSONArray facebookim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // loginButton.setReadPermissions("email");
        // If using in a fragment
        LoginButton login= findViewById(R.id.login_button);
login.setOnClickListener(view -> setFacebook());
        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//            Log.d("login","Success");    // App code
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });
    }
    private void setFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.logOut();
        loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("email"));
        List<String> permissions = new ArrayList<>();
        permissions.add("email");

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                final GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("object", object.toString());
                        try {
                            facebookEmail = object.getString("email");
                            facebookId = object.getString("id");
                            facebookName = object.getString("name");


                            Log.d("emailper",facebookEmail+" "+facebookName+" " + facebookId);

                        } catch (Exception e) {
                            facebookEmail= "";
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email, name");
                request.setParameters(parameters);
                request.executeAsync();




            }
            @Override
            public void onCancel() {
                Log.e("dd", "facebook login canceled");
            }
            @Override
            public void onError(FacebookException error) {
                Log.e("dd", "facebook login error");
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


}
