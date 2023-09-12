package com.example.mysneakersrack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

import com.example.mysneakersrack.R;
import com.example.mysneakersrack.models.PermissionManager;
import com.example.mysneakersrack.models.FirebaseWrapper;



@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getCanonicalName();

    private static final int PERMISSION_REQUEST_CODE = (new Random()).nextInt() & Integer.MAX_VALUE;

    private void goToActivity(Class<?> activity){
        Intent intent = new Intent(this, activity);
        this.startActivity(intent);
        this.finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //check user
        //se non è loggato la splash activity manda alla pagina di login
        //una volta loggato va nella main activity
        FirebaseWrapper.Auth auth = new FirebaseWrapper.Auth();
        if (!auth.isAuthenticated()) {
            // Go to Activity for LogIn or SignUp
            this.goToActivity(EnterActivity.class);
        }

        // Check permissions -- Do not request at the login!
        PermissionManager pm = new PermissionManager(this);
        if (!pm.askNeededPermission(PERMISSION_REQUEST_CODE, false)) {
            // Go to MainActivity
            this.goToActivity(MainActivity.class);
        }


        // Add the listener to the 'Grant Now' permission
        this.findViewById(R.id.grantPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!pm.askNeededPermission(PERMISSION_REQUEST_CODE, true)) {
                    // Go to MainActivity
                    SplashActivity.this.goToActivity(MainActivity.class);
                }
            }
        });

    }


    //il metodo ononRequestPermissionsResult risponde all'evento di quando l'utente dice accept permission o denied permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode != PERMISSION_REQUEST_CODE) {
            return;
        }

        for (int res : grantResults) {
            if (res == PackageManager.PERMISSION_DENIED) {
                Log.w(TAG, "A needed permission is not granted!");
                return;
            }
        }
        // All permissions are granted
        Log.d(TAG, "All the needed permissions are granted!");
        this.goToActivity(MainActivity.class);
        //possiamo andare alla MainActivity
    }
}