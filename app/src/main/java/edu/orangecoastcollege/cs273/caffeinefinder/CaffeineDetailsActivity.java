package edu.orangecoastcollege.cs273.caffeinefinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CaffeineDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    CaffeineLocation mLocation;
    GoogleMap mGoogleMap;

    private TextView detailsName;
    private TextView detailsAdd;
    private TextView detailsNum;
    private TextView detailsCoord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_details);

        mLocation = getIntent().getParcelableExtra("Location");
        detailsName = (TextView) findViewById(R.id.detailsName);
        detailsAdd = (TextView) findViewById(R.id.detailsAdd);
        detailsNum = (TextView) findViewById(R.id.detailsNum);
        detailsCoord = (TextView) findViewById(R.id.detailsCoords);

        detailsName.setText(mLocation.getName());
        detailsAdd.setText(mLocation.getAddress());
        detailsNum.setText(mLocation.getPhone());


        String latDirect = (mLocation.getLatitude() >= 0.0) ? "N" : "S";
        String lonDirect = (mLocation.getLongitude() >= 0.0) ? "E" : "W";

        detailsCoord.setText(Math.abs(mLocation.getLatitude()) + " " +
                latDirect + " " + (Math.abs(mLocation.getLongitude()) + " " + lonDirect));

        SupportMapFragment caffeineMapFrag = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.caffeineDetailsMapFragment);
        caffeineMapFrag.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng coords = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
        mGoogleMap.addMarker(new MarkerOptions().position(coords).title(mLocation.getName()));
        CameraPosition camPos = CameraPosition.builder().target(coords).zoom(14.0f).build();
        CameraUpdate camUp = CameraUpdateFactory.newCameraPosition(camPos);
        mGoogleMap.moveCamera(camUp);
    }


}
