package com.uttarakhand.kisanseva2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.uttarakhand.kisanseva2.R;
import com.uttarakhand.kisanseva2.fragments.PhoneLoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new PhoneLoginFragment()).commit();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();
//        finish();
//        moveTaskToBack(true);
    }
}