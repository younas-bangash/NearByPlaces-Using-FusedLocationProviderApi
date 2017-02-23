package example.nearmeplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPane extends AppCompatActivity {
    GoogleMap mMap;
    PlacesList nearPlaces;
    double latitude,longitude;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_places);        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        // Getting intent data
        Intent i = getIntent();
        // Users current geo location
        String user_latitude = i.getStringExtra("user_latitude");
        String user_longitude = i.getStringExtra("user_longitude");
        final LatLng user_location = new LatLng(Double.parseDouble(user_latitude), Double.parseDouble(user_longitude));
        // Nearplaces list
        nearPlaces = (PlacesList) i.getSerializableExtra("near_places");

        mMap.addMarker(new MarkerOptions()
                .position(user_location)
                .title("This is You")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.on)));

        // check for null in case it is null
        if (nearPlaces.results != null) {
            // loop through all the places
            for (Place place : nearPlaces.results) {
                latitude = place.geometry.location.lat; // latitude
                longitude = place.geometry.location.lng; // longitude
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(place.name)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_accent)));
            }
        }

        // Move the camera instantly to Sydney with a zoom of 100.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_location, 20));

        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


    }

}