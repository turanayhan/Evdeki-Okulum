package com.hrdijital.evdekiokulum.register;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.hrdijital.evdekiokulum.R;
import com.hrdijital.evdekiokulum.config.Config;
import com.hrdijital.evdekiokulum.login.Login;
import com.hrdijital.evdekiokulum.services.DataCallback;
import com.hrdijital.evdekiokulum.services.DefaultRequest;
import com.hrdijital.evdekiokulum.services.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    Activity activity;
    DefaultRequest defaultRequest;
    SessionManager manager;
    SharedPreferences preferences;
    LinearLayout registerView;
    TextInputEditText emailText,passwordText,passwordText2,nameText,dateText;
    TextView btn_login;
    Button register_btn;
    CheckBox checkBox;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        nameText=findViewById(R.id.name_surname);
        emailText=findViewById(R.id.email);
        dateText=findViewById(R.id.date);
        passwordText=findViewById(R.id.password1);
        passwordText2=findViewById(R.id.password2);
        register_btn=findViewById(R.id.register);
        checkBox=findViewById(R.id.checkbox_meat);
        btn_login=findViewById(R.id.btn_login);
        transition();
        initalized();
    }

    private  void transition(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(Register.this);
                progressDialog.setMessage("Lütfen bekleyiniz..");
                progressDialog.show();
                startActivity(new Intent(Register.this,Login.class));
                finish();

            }
        });




    }




    private void initalized() {
        activity = Register.this;
        registerView = findViewById(R.id.registerView);
        defaultRequest = new DefaultRequest(activity);
        manager = new SessionManager(activity);
        preferences = activity.getSharedPreferences(SessionManager.PREF_NAME, SessionManager.PRIVATE_MODE);

        validate();
    }
    private  void validate(){

        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (emailText != null){
            String email = emailText.getText().toString().trim();
            String pass = passwordText.getText().toString().trim();
            if (emailText.getText().toString().isEmpty()) {
                setSnachbar("Eposta giriniz");
                emailText.requestFocus();
            } else if (!email.matches(emailPattern)) {
                setSnachbar("Epostanızı kontrol ediniz");
            }else if (passwordText.getText().toString().isEmpty()) {
                setSnachbar("Şifrenizi giriniz");
                passwordText.requestFocus();
            } else if (pass.matches("")) {
                setSnachbar("Şifreniz boş bırakamazsınız");
                passwordText.requestFocus();
            } else if (pass.length() <= 7) {
                setSnachbar("Şifrenizin en az 8 karakter olmalıdır ");
                passwordText.requestFocus();
            }else{
                executeLogin();
            }
        }


        executeLogin();
    }
    private void executeLogin() {

        try {
            final JSONObject object = new JSONObject();
            object.put("IdentifierName", "Deneme123");
            object.put("IdentifierUsername", "jegopil650@tdcryo.com");
            object.put("BirthDate","01.01.2020");
            object.put("IdentifierPassword","123456");
            object.put("OriginNationality_ISO","true");
            object.put("DeviceID","aa");
            object.put("DeviceManufacturer","aa");
            object.put("DeviceModel","aa");
            object.put("OsLanguage","aa");
            object.put("OsVersion","aa");

             defaultRequest.postRequest("https://api.evdekiokulum.com/Api/SetAuthentication/RegistrationMobile?IdentifierName=Deneme%2026112&IdentifierUsername=jegopil650@tdcryo.com&BirthDate=01.01.2000&IdentifierPassword=123456&OriginNationality_ISO=true&DeviceID=aa&DeviceManufacturer=aa&DeviceModel=aa&OsLanguage=aa&OsVersion=aa", activity, object, new DataCallback() {
                @Override
                public void onSuccess(Object result) {
                    if (result instanceof JSONObject) {
                        try {

                            JSONObject object = new JSONObject(result.toString());
                            Log.d(  "onSuccess: ",object.toString());
                            if (object.getString("CustomResponseState").equalsIgnoreCase("true")) {
                                String Message = object.getString("CustomResponseMessage");
                                Log.d(  "CustomResponseMessage: ",Message);
                                setSnachbar(Message);
                            } else if (object.getString("CustomResponseState").equalsIgnoreCase("false")) {

                                String Message = object.getString("CustomResponseMessage");
                                Log.d(  "CustomResponseMessage: ",Message );
                                setSnachbar(Message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onError(VolleyError error) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void setSnachbar(String text) {
        Snackbar.make(registerView, text, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(registerView.getResources().getColor(R.color.white))
                .setTextColor(registerView.getContext().getColor(R.color.black))
                .show();
    }
    public static void hideSoftKeyboard(FragmentActivity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
    }
    @SuppressLint("ClickableViewAccessibility")
    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                hideSoftKeyboard(null);
                return false;
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
}