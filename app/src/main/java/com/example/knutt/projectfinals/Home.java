package com.example.knutt.projectfinals;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;


public class Home extends Fragment {

    private Button btn;

    private CallbackManager callbackManager;
    private Button buttonLoginfb;


    public Home() {


        // Required empty public constructor
    }


    public static FragmentA newInstance(String param1, String param2) {
        FragmentA fragment = new FragmentA();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home, container, false);
        FacebookSdk.sdkInitialize(getContext());
        callbackManager = CallbackManager.Factory.create();
        // Inflate the layout for this fragment
        buttonLoginfb = (Button) v.findViewById(R.id.btnloginfb);
        buttonLoginfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(Home.this, Arrays.asList("public_profile", "email","user_posts","user_friends"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        getUserDetail(loginResult);

                        buttonLoginfb.setEnabled(false);

                    }

                    @Override
                    public void onCancel() {


                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
            }

            private void getUserDetail(final LoginResult loginResult) {
                GraphRequest data_request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject json_object, GraphResponse response) {

                        Intent intent = new Intent(getActivity().getApplicationContext(),
                                FragmentA.class);
                        intent.putExtra("userProfile", json_object.toString());
                        intent.putExtra("Logout",loginResult.getAccessToken());
                        intent.putExtra("id",loginResult.getAccessToken().getUserId());
                        startActivity(intent);
                    }
                });
                Bundle permission_param = new Bundle();
                permission_param.putString("fields", "id,first_name,last_name,email,gender,picture.width(120).height(120)");
                data_request.setParameters(permission_param);
                data_request.executeAsync();
            }
        });





        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
