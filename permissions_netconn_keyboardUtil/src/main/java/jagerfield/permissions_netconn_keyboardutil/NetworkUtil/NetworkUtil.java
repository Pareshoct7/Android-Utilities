package jagerfield.permissions_netconn_keyboardutil.NetworkUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jagerfield.permissions_netconn_keyboardutil.C;

public class NetworkUtil
{
    private String tag = "Network";

    /* Gets internet connectivity status and also pings to make sure it is available.
    *
    * */
    public int checkInternet(Context context)
    {
        int conn = isNetConnected(context);
        int status = 0;

        if (conn == C.TYPE_WIFI)
        {
            status = C.TYPE_WIFI;
            Log.i(tag, "Internet TYPE_WIFI");
        }
        else if (conn == C.TYPE_MOBILE)
        {
            status = C.TYPE_MOBILE;
            Log.i(tag, "Internet TYPE_MOBILE");
        }
        else if (conn == C.TYPE_NOT_CONNECTED)
        {
            status = C.TYPE_NOT_CONNECTED;
            Log.i(tag, "Internet TYPE_NOT_CONNECTED");
        }

        boolean result = pingGoogle(context);

        if(result && (status==C.TYPE_WIFI || status==C.TYPE_MOBILE))
        {
            return status;
        }
        else if(!result)
        {
            return status = C.TYPE_NOT_CONNECTED;
        }

        return status;
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
                    Log.i(tag, "Failed to ping Google " + i + " times");
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

    private int isNetConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return C.TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return C.TYPE_MOBILE;
        }
        return C.TYPE_NOT_CONNECTED;
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
