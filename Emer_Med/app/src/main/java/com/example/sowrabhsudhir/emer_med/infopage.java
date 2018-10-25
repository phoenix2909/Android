package com.example.sowrabhsudhir.emer_med;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class infopage extends AppCompatActivity {
    private TextView txtLat;
    private final int PERMISSION_ALL = 1;
    private GPSTracker gps;
    private TinyDB db;
    private TextView name, email, emergency, age, bloodgroup, bloodpressure, disease;
    private String[] PERMISSIONS = {
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infopage);

        db = new TinyDB(getApplicationContext());
        gps = new GPSTracker(getApplicationContext());
        txtLat = findViewById(R.id.txtLat);

        name = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        emergency = findViewById(R.id.emergency);
        age = findViewById(R.id.age);
        bloodgroup = findViewById(R.id.bloodgroup);
        bloodpressure = findViewById(R.id.bloodpressure);
        disease = findViewById(R.id.diseases);

        name.setText(db.getString("name"));
        email.setText(db.getString("email"));
        emergency.setText(db.getString("emergency"));
        age.setText(db.getString("age"));
        bloodpressure.setText(db.getString("bloodpressure"));
        bloodgroup.setText(db.getString("bloodgroup"));
        disease.setText(db.getString("diseases"));

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        } else {
            final String slatitude = gps.getStringLatitude();
            final String slongitude = gps.getStringLongtitude();
            txtLat.setText("My live location is: "+slatitude + ", " + slongitude);
            Button helpButton = (Button) findViewById(R.id.helpButton);
            Button logout = findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    db.clear();
                    Intent go = new Intent(infopage.this, MainActivity.class);
                    startActivity(go);
                    finishAffinity();
                }
            });
            helpButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sendSMS(db.getString("emergency"), "Please HELP me! I'm in an EMERGENCY! Here are my coordinates: https://www.google.com/maps/?q=" + slatitude + "," + slongitude);
                    Toast.makeText(infopage.this, "SMS Sent!", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ALL: {
                if (!hasPermissions(this, PERMISSIONS)) {
                    Toast.makeText(infopage.this, "All the permissions are mandatory.", Toast.LENGTH_SHORT).show();
                } else {
                    final String slatitude = gps.getStringLatitude();
                    final String slongitude = gps.getStringLongtitude();
                    txtLat.setText(slatitude + ", " + slongitude);
                    Button helpButton = (Button) findViewById(R.id.helpButton);
                    helpButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            sendSMS(db.getString("emergency"), "Please HELP me! I'm in an EMERGENCY! Here are my coordinates: https://www.google.com/maps/?q=" + slatitude + "," + slongitude);
                            Toast.makeText(infopage.this, "SMS Sent!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            default:
                return;
        }
    }


}
