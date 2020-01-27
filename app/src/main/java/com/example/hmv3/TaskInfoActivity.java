package com.example.hmv3;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

public class TaskInfoActivity extends AppCompatActivity implements TaskInfoFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
