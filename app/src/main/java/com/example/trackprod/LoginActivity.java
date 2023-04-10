package com.example.trackprod;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;

public class LoginActivity extends AppCompatActivity {

//    String[] user_role_items = new String[]{"PPC Manager", "Fabric Store Manager", "Trim Store Manager", "Cutting Store Manager", "Sewing Floor Manager", "Finishing/Packaging Manager"};
    Spinner user_role_dropdown;
    ImageView login_btn;
    EditText d_email, d_pass;
    TextView forg_pass;
    String email = "";
    String pass = "";
    String role = "";
    RelativeLayout d_login_header;
    App app;



    Credentials credentials;
    AtomicReference<User> user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_role_dropdown = findViewById(R.id.user_role_spinner);
        d_email = findViewById(R.id.email_LOGN);
        d_pass = findViewById(R.id.pass_LOGIN);
        forg_pass = findViewById(R.id.forgetpsd_LOGIN);
        login_btn = (ImageView) findViewById(R.id.button_LOGIN);
        d_login_header = findViewById(R.id.login_screen);

        ArrayAdapter<CharSequence> dropdownAdapter = ArrayAdapter.createFromResource(this, R.array.userrole_array, android.R.layout.simple_spinner_dropdown_item);
        dropdownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        UserroleAdapter dropdownAdapter = new UserroleAdapter(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(user_role_items));
        user_role_dropdown.setAdapter(dropdownAdapter);
        user_role_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                role = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String appID = "trackprod-pvupc";
        app = new App(new AppConfiguration.Builder(appID)
                .appName("TrackProd").requestTimeout(1, TimeUnit.MINUTES).build());

        user = new AtomicReference<User>();

        login_btn.setOnClickListener(
                view -> {
                    email = d_email.getText().toString();
                    pass = d_pass.getText().toString();

                    if (TextUtils.isEmpty(email)) {

                        Snackbar snackbar = Snackbar.make(d_login_header, "Please Fill E-Mail", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else if (TextUtils.isEmpty(pass)) {

                        Snackbar snackbar = Snackbar.make(d_login_header, "Please Fill Password", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else if (TextUtils.isEmpty(role)) {

                        Snackbar snackbar = Snackbar.make(d_login_header, "Please Select Role", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        credentials = Credentials.emailPassword(email, pass);
                        userLogin(credentials);
                    }
                }


        );

    }


    private void userLogin(Credentials credentials) {

        app.loginAsync(credentials, callback -> {
            Log.e("My log", String.valueOf(callback.get()));
            Log.e("My log", String.valueOf(callback.getError()));
            if(callback.isSuccess()){
                user.set(app.currentUser());
                Log.e("my log", String.valueOf(user));
                Snackbar snackbar = Snackbar.make(d_login_header, "Authentication Successful", Snackbar.LENGTH_LONG);
                snackbar.show();
                Prefs.INSTANCE.setLoginStatus(true);
            } else{
                Snackbar snackbar = Snackbar.make(d_login_header, "Authentication Failed", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

    }
//
////        loaderdialog.show();
//        try {
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
////            String URL = AppNetworkConstants.LOGIN;
//            String URL = "";
//
//            Log.e("mylog","Url is : "+URL);
//            Log.e("mylog","Email is : "+email);
//            Log.e("mylog","password is : "+pass);
//
//            JsonObjectRequest dataRequest = new
//                    JsonObjectRequest(Request.Method.POST,URL,
//                    new JSONObject().put("userrole", role).put("username",email).put("userpass",pass),
//                    response ->
//                    {
////                        loaderdialog.dismiss();
//                        try {
//                            Log.e("mylog","login response is : "+response);
//
//                            String id= response.getString("id");
//                            String email= response.getString("email");
//                            String firstName= response.getString("firstName");
//                            String lastName= response.getString("lastName");
//                            String profileId= response.getString("profileId");
//
//                            Log.e("my log"," id is :" +id);
//                            Log.e("my log"," email is :" +email);
//                            Log.e("my log"," first Name is :" +firstName);
//                            Log.e("my log"," last Name is :" +lastName);
//                            Log.e("my log"," profile id :" +profileId);
//
//                            Prefs.INSTANCE.setFirstName(firstName);
//                            Prefs.INSTANCE.setLastName(lastName);
//                            Prefs.INSTANCE.setID(id);
//
//                            Intent i = new Intent(LoginActivity.this,HomeActivity.class);
//                            startActivity(i);
//                            finish();
//
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    },
//                    error -> {
//                        loaderdialog.dismiss();
//                        // Log.e("mylog", error.toString());
//                        Log.e("my log"," Invalid Credentials :");
//                        Toast.makeText(LoginActivity.this, "Invalid Credentials ",Toast.LENGTH_SHORT).show();
//                        //  progressHud.setVisibility(View.GONE);
//                        //   showSnackBar("Failed to load Data !!", R.color.red);
//                    });
//            dataRequest.setRetryPolicy(new DefaultRetryPolicy(
//                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
//                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            requestQueue.add(dataRequest);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            //    showSnackBar("Failed to load Data !!", android.R.color.holo_red_dark);
//        }


   
}