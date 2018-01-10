package com.geekghost.gp.activity_smzq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.e(TAG, "onCreate2: " );
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e(TAG, "onStart2: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume2: " );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart2: " );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause2: " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop2: " );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy2: " );
    }
}
