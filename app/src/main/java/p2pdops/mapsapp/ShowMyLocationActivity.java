package p2pdops.mapsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMyLocationActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback {

    LocationManager locationManager;
    GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_location);
        getSupportActionBar().setTitle("Show my location");

        locationManager = getSystemService(LocationManager.class);
        SupportMapFragment mapView = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapView);
        assert mapView != null;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "No permission", Toast.LENGTH_LONG).show();
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                3600,
                1000,
                this
        );
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        // add marker to new location
        if (mMap != null) {
            LatLng newLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(newLatLng).title("New Marker"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 14f));
            Toast.makeText(this, "Location changed", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Toast.makeText(this, provider + " is enabled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Toast.makeText(this, provider + " is disabled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "No permission", Toast.LENGTH_LONG).show();
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap = googleMap;
    }
}