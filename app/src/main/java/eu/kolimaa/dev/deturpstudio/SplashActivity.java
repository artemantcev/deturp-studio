package eu.kolimaa.dev.deturpstudio;

import eu.kolimaa.dev.deturpstudio.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SplashActivity extends Activity {

    Intent init;
    private static final int SPLASH_TIME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                init = new Intent(SplashActivity.this, StartActivity.class);
                startActivity(init);
                finish();
            }
        }, SPLASH_TIME);

    }

}