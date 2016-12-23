package jagerfield.permissions_and_utilities_library.NetworkUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jagerfield.permissions_and_utilities_library.C;

public class NetworkUtil
{
    /* Gets internet connectivity status and also pings to make sure it is available.
    *
    * */

    public int checkInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) return C.NOT_FOUND_VAL;

        int conn = activeNetwork.getType();
        int type = C.NOT_FOUND_VAL;

        if (conn == C.TYPE_WIFI)
        {
            type = C.TYPE_WIFI;
            Log.i(C.TAG, "Internet TYPE_WIFI");
        }
        else if (conn == C.TYPE_WIMAX)
        {
            type = C.TYPE_WIMAX;
            Log.i(C.TAG, "Internet TYPE_WIMAX");
        }
        else if (conn == C.TYPE_MOBILE)
        {
            type = C.TYPE_MOBILE;
            Log.i(C.TAG, "Internet TYPE_MOBILE");
        }
        else if (conn == C.TYPE_NOT_CONNECTED)
        {
            type = C.TYPE_NOT_CONNECTED;
            Log.i(C.TAG, "Internet TYPE_NOT_CONNECTED");
        }

        boolean result = pingGoogle(context);

        if(result && (type==C.TYPE_WIFI || type==C.TYPE_MOBILE))
        {
            return type;
        }
        else if(!result)
        {
            return type = C.TYPE_NOT_CONNECTED;
        }

        return type;
    }


    private boolean pingGoogle(Context context)
    {
        int counter = 2;
        boolean result = false;

        if(isNetAvailable(context))
        {
            return true;
        }
        else
        {
            for (int i = 0; i <counter ; i++)
            {
                result = isNetAvailable(context);

                if (result)
                {
                    return true;
                }
                else
                {
                    Log.i(C.TAG, "Failed to ping Google " + i + " times");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //Wait

                        }
                    }, 5000);
                }
            }
        }

        return result;
    }

    private static boolean isNetAvailable(Context context) {
        boolean connected = false;
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo newtWorkInfo = connectivityManager.getActiveNetworkInfo();

        if (newtWorkInfo != null && newtWorkInfo.isConnectedOrConnecting())
        {
            connected = true;
        }
        else if (newtWorkInfo != null && newtWorkInfo.isConnected() && connectivityManager.getActiveNetworkInfo().isAvailable())
        {
            connected = true;
        }
        else if (newtWorkInfo != null && newtWorkInfo.isConnected())
        {
            try
            {
                URL url = new URL("http://www.google.com");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(3000);
                urlc.connect();
                if (urlc.getResponseCode() == 200)
                {
                    connected = true;
                }
            }
            catch (MalformedURLException e1)
            {
                e1.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        else if (connectivityManager != null)
        {
            final NetworkInfo[] netInfoAll = connectivityManager.getAllNetworkInfo();

            for (NetworkInfo network : netInfoAll)
            {
                System.out.println("get network type :::" + network.getTypeName());

                if ((network.getTypeName().equalsIgnoreCase("WIFI") || network.getTypeName().equalsIgnoreCase("MOBILE")) && network.isConnected() && network.isAvailable())
                {
                    connected = true;
                    if (connected)
                    {
                        break;
                    }
                }
            }
        }

        return connected;
    }

}
