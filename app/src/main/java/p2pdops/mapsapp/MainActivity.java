package p2pdops.mapsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

// https://developers.google.com/maps/documentation/android-sdk/get-api-key
// keytool -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
public class MainActivity extends AppCompatActivity {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void action(View view) {
        switch (view.getId()) {
            case R.id.simple_maps:
                startActivity(new Intent(this, SimpleMapActivity.class));
                break;
            case R.id.get_location:
                startActivity(new Intent(this, GetLocationActivity.class));
                break;
            case R.id.show_my_location:
                startActivity(new Intent(this, ShowMyLocationActivity.class));
                break;
            case R.id.show_custom_location:
                startActivity(new Intent(this, ShowCustomLocationActivity.class));
                break;
        }
    }
}