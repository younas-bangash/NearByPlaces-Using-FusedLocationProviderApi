package example.nearmeplaces;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Bangash on 3/3/2016.
 */
@SuppressWarnings("PointlessArithmeticExpression")
public class Configuration {


    /************************* Below is the variable used for nearme places *******************************/

    /**
     * Note : For better performance it is good to use either time or distance but for ur project its good to not used
     * the time use the distance parameter for location update.
     *
     * The desired interval for location updates (milli sec). Inexact. Updates may be more or less frequent.
     */
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;


    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    public static final boolean USED_TIME_PARAMETER_FOR_LOCATION_UPDATE = true; //if true time will be used for update

    /**
     * Searching radius for google places
     */
    public static final int RADIUS_FOR_PLACES_SEARCH = 500;


    /**
     * Tracks the status of the location updates request. Value changes when the user presses the
     * Start Updates and Stop Updates buttons.
     */
    public static Boolean mRequestingLocationUpdates = true;

    /**
     * Type of places to show in result
     */
    public static final String TYPE_OF_PLACES = "health|hospital|pharmacy|doctor|dentist";


    /**
     * Google API key used in google places api
     */

    public static final String API_KEY = "AIzaSyAJbaKH0QdGZrHmAvKRORpP5j2uvQXek8U";


    /**
    * Function to check the internet connectivity
    * */
    public static boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }


}