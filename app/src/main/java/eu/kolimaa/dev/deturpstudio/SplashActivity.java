package eu.kolimaa.dev.deturpstudio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    private static final int SPLASH_TIME = 1500;

    private Intent getExplicitSplashIntent() {
        Intent init = new Intent(SplashActivity.this, StartActivity.class);
        return init;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(getExplicitSplashIntent());
                finish();
            }
        }, SPLASH_TIME);

    }

}