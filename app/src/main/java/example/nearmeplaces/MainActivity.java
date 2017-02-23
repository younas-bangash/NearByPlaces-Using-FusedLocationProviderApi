package example.nearmeplaces;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static example.nearmeplaces.Configuration.TYPE_OF_PLACES;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private boolean mPermissionGranted = false;
    private boolean mGooglePlacesOldVersion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        try {
            Integer device_play_Services = getPackageManager().
                    getPackageInfo("com.google.android.gms", 0 ).versionCode;
            Integer project_play_services_version = 6587000;

            Log.d(TAG, "project_play_services_version : " + R.integer.google_play_services_version );
            Log.d(TAG, "device_play_Services : " + device_play_Services );

            if( project_play_services_version > device_play_Services){
                mGooglePlacesOldVersion = false;
                Toast.makeText(this, "Update the Google Play Services Version", Toast.LENGTH_SHORT).show();
            }else{
                mGooglePlacesOldVersion = true;
                int MyVersion = Build.VERSION.SDK_INT;
                if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (!checkPermission(this)) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                        android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }else{
                        mPermissionGranted = true;
                    }

                } else {
                    mPermissionGranted = true;
                }

                if(!Configuration.isGPSEnabled(getApplicationContext())){
                    Toast.makeText(this, "Enable GPS to get current location", Toast.LENGTH_SHORT).show();
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkPermission(final Context context) {
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    //Implement click event handler methods
    public void onHospitalClick(View v) {
        TYPE_OF_PLACES = "health|hospital|pharmacy|doctor|dentist";
        if(mGooglePlacesOldVersion){
            if(mPermissionGranted){
                startActivity(new Intent(this,PlacesListActicity.class));
            }else{
                showPermissionToast();
            }
        }else{
            showGoogleServiceUpdateMessage();
        }


    }

    private void showGoogleServiceUpdateMessage() {
        Toast.makeText(getApplicationContext(),"This App Require New Version of google play services" +
                ". Update Google Play Service from Play Store", Toast.LENGTH_LONG).show();
    }

    private void showPermissionToast() {
        Toast.makeText(getApplicationContext(),"Permission Required, " +
                "You cannot access location data. Enable Permission from sitting", Toast.LENGTH_LONG).show();
    }

    public void onSchoolClick(View v) {
        TYPE_OF_PLACES = "school|university|library|physiotherapist";
        if(mGooglePlacesOldVersion){
            if(mPermissionGranted){
                startActivity(new Intent(this,PlacesListActicity.class));
            }else{
                showPermissionToast();
            }
        }else{
            showGoogleServiceUpdateMessage();
        }

    }

    public void onSportsClick(View v) {
        TYPE_OF_PLACES = "gym|stadium";
        if(mGooglePlacesOldVersion){
            if(mPermissionGranted){
                startActivity(new Intent(this,PlacesListActicity.class));
            }else{
                showPermissionToast();
            }
        }else{
            showGoogleServiceUpdateMessage();
        }
    }

    public void onShoppingClick(View v) {
        TYPE_OF_PLACES = "bicycle_store|book_store|clothing_store|convenience_store|department_store|" +
                "electronics_store|furniture_store|hardware_store|home_goods_store|jewelry_store|liquor_store|pet_store|" +
                "shoe_store|shopping_mall";
        if(mGooglePlacesOldVersion){
            if(mPermissionGranted){
                startActivity(new Intent(this,PlacesListActicity.class));
            }else{
                showPermissionToast();
            }
        }else{
            showGoogleServiceUpdateMessage();
        }
    }

    public void onTravelClick(View v) {
        TYPE_OF_PLACES = "train_station|travel_agency|subway_station|airport|embassy";
        if(mGooglePlacesOldVersion){
            if(mPermissionGranted){
                startActivity(new Intent(this,PlacesListActicity.class));
            }else{
                showPermissionToast();
            }
        }else{
            showGoogleServiceUpdateMessage();
        }
    }

    public void onRestaurantClick(View v) {
        TYPE_OF_PLACES = "restaurant|meal_delivery";
        if(mGooglePlacesOldVersion){
            if(mPermissionGranted){
                startActivity(new Intent(this,PlacesListActicity.class));
            }else{
                showPermissionToast();
            }
        }else{
            showGoogleServiceUpdateMessage();
        }
    }

    public void onFinanceClick(View v) {
        TYPE_OF_PLACES = "accounting|bank|atm|finance";
        if(mGooglePlacesOldVersion){
            if(mPermissionGranted){
                startActivity(new Intent(this,PlacesListActicity.class));
            }else{
                showPermissionToast();
            }
        }else{
            showGoogleServiceUpdateMessage();
        }
    }

    public void onReligionClick(View v) {
        TYPE_OF_PLACES = "church|hindu_temple|mosque";
        if(mGooglePlacesOldVersion){
            if(mPermissionGranted){
                startActivity(new Intent(this,PlacesListActicity.class));
            }else{
                showPermissionToast();
            }
        }else{
            showGoogleServiceUpdateMessage();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPermissionGranted = true;
                } else {
                    Toast.makeText(getApplicationContext(),"Permission Denied, " +
                            "You cannot access location data.", Toast.LENGTH_LONG).show();
                    mPermissionGranted = false;
                }
                break;
        }
    }
}
