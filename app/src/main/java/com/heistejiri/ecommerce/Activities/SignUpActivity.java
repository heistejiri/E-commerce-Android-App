package com.heistejiri.ecommerce.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.heistejiri.ecommerce.Common;
import com.heistejiri.ecommerce.Config;
import com.heistejiri.ecommerce.MVP.SignUpResponse;
import com.heistejiri.ecommerce.Retrofit.Api;
import com.heistejiri.ecommerce.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignUpActivity extends AppCompatActivity {

    @BindViews({R.id.username, R.id.email, R.id.password, R.id.confirmPassword})
    List<EditText> editTexts;

    @BindView(R.id.loginLinearLayout)
    LinearLayout loginLinearLayout;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        loginLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);

            }
        });
    }
    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @OnClick({R.id.txtSignIn, R.id.signUp, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtSignIn:
                Config.moveTo(SignUpActivity.this, LoginActivity.class);
                finishAffinity();
                break;
            case R.id.back:
                Config.moveTo(SignUpActivity.this, LoginActivity.class);
                finishAffinity();
                break;
            case R.id.signUp:
                if (validate(editTexts.get(0)) && Config.validateEmail(editTexts.get(1),SignUpActivity.this) && validatePassword(editTexts.get(2)) &&
                        validatePassword(editTexts.get(3))) {
                    if (editTexts.get(2).getText().toString().trim().equals(editTexts.get(3).getText().toString().trim())) {
                        signUp();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Password and Confirm Password should be same", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    private boolean validate(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return true;
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    private boolean validatePassword(EditText editText) {
        if (editText.getText().toString().trim().length() > 5) {
            return true;
        } else if (editText.getText().toString().trim().length() > 0) {
            editText.setError("Password must be of 6 characters");
            editText.requestFocus();
            return false;
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }


    private void signUp() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(SignUpActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        // sending gcm token to server
        Api.getClient().registration(editTexts.get(0).getText().toString().trim(),
                editTexts.get(1).getText().toString().trim(),
                editTexts.get(2).getText().toString().trim(),
                "email",
                new Callback<SignUpResponse>() {
                    @Override
                    public void success(SignUpResponse signUpResponse, Response response) {
                        pDialog.dismiss();
//                        Log.d("signUpResponse", signUpResponse.getMessage());
                        Toast.makeText(SignUpActivity.this, signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        if (signUpResponse.getSuccess().equalsIgnoreCase("true")) {

                            Common.saveUserData(SignUpActivity.this, "email", editTexts.get(1).getText().toString());
                            Common.saveUserData(SignUpActivity.this, "userId", signUpResponse.getUserid() + "");
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            intent.putExtra("from", "signUp");
                            startActivity(intent);
//                            Config.moveTo(SignUp.this, MainActivity.class);
                            finishAffinity();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        pDialog.dismiss();

                        Log.e("error", error.toString());
                    }
                });
    }


}
