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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infopage);
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
        txtLat = (TextView) findViewById(R.id.txtLat);

    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat.setText("Current location: "+location.getLatitude()+","+location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

}
