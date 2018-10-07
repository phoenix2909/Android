package com.example.sowrabhsudhir.emer_med;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class infopage extends AppCompatActivity implements LocationListener {

    protected LocationManager locationManager;
    protected Context context;
    TextView txtLat;
    protected LocationListener locationListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infopage);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        txtLat = (TextView) findViewById(R.id.txtLat);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location",location.toString());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        }


    }


}
