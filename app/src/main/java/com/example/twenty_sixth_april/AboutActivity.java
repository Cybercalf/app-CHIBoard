package com.example.twenty_sixth_april;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        fragmentManager = getSupportFragmentManager();
    }

    /**
     * finish AboutActivity and go back to last Activity
     */
    public void finishAboutActivity(View view) {
        finishAndRemoveTask();
    }

    /**
     * 展示License信息。利用一个AboutLicenseFragment
     *
     * @param view
     */
    public void showLicence(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.about_licence_content,
                AboutLicenseFragment.getSingletonInstance());
        // 让创建的AboutLicenceFragment入栈，便于通过使它出栈来销毁它
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}