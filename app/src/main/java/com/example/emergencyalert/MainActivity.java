package com.example.emergencyalert;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnHelp, btnVoice;
    EditText editText;

    FusedLocationProviderClient locationClient;

    String phoneNumber = "9835268065"; // 🔴 PUT YOUR NUMBER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHelp = findViewById(R.id.btnHelp);
        btnVoice = findViewById(R.id.btnVoice);
        editText = findViewById(R.id.editText);

        locationClient = LocationServices.getFusedLocationProviderClient(this);

        // 🔴 HELP BUTTON
        btnHelp.setOnClickListener(v ->{v.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(100)
                .withEndAction(() -> v.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100));
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (vibrator != null) {
                vibrator.vibrate(200); // vibrate for 200ms
            }
                triggerAlert();});

        // 🎤 VOICE BUTTON
        btnVoice.setOnClickListener(v -> startVoiceInput());
    }

    // 🎤 VOICE INPUT
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        try {
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            Toast.makeText(this, "Voice not supported", Toast.LENGTH_SHORT).show();
        }
    }

    // 🎤 HANDLE VOICE RESULT
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String spokenText = result.get(0).toLowerCase();

            Toast.makeText(this, "You said: " + spokenText, Toast.LENGTH_SHORT).show();

            if (spokenText.contains("code red") || spokenText.contains("help")) {
                triggerAlert();
            }
        }
    }

    // 🚨 MAIN ALERT FUNCTION
    private void triggerAlert() {
        Toast.makeText(this, "Trigger started", Toast.LENGTH_SHORT).show();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            return;
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);

        locationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Toast.makeText(MainActivity.this, "Location received", Toast.LENGTH_SHORT).show();

                if (locationResult == null || locationResult.getLastLocation() == null) {
                    Toast.makeText(MainActivity.this, "Location is NULL!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Location location = locationResult.getLastLocation();

                double lat = location.getLatitude();
                double lon = location.getLongitude();

                Toast.makeText(MainActivity.this,
                        "Lat: " + lat + ", Lon: " + lon,
                        Toast.LENGTH_LONG).show();

                Toast.makeText(MainActivity.this, "About to send SMS", Toast.LENGTH_SHORT).show();

                String message = "🚨 EMERGENCY!\nI need help.\n\n📍 Tap to view location:\nhttps://maps.google.com/?q=" + lat + "," + lon;

                sendSMS(message);

                locationClient.removeLocationUpdates(this);
            }
        }, Looper.getMainLooper());
    }

    // 📩 SEND SMS (AUTO + FALLBACK)
    private void sendSMS(String message) {
        try {
            SmsManager smsManager;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                smsManager = getSystemService(SmsManager.class);
            } else {
                smsManager = SmsManager.getDefault();
            }

            // 🔥 HANDLE LONG MESSAGE
            ArrayList<String> parts = smsManager.divideMessage(message);

            smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);

            Toast.makeText(this, "🚨 SMS Sent!", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "SMS failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // 🔐 HANDLE PERMISSIONS
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                triggerAlert();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}