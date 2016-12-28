package jagerfield.library.NetworkUtil;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jagerfield.library.C;
import jagerfield.library.PermissionsUtil.PermissionsUtil;

public class NetworkUtil
{
    public NetworkUtil()
    {

    }

    public static NetworkUtil getInstance()
    {
        return new NetworkUtil();
    }

    /* Gets internet connectivity status and also pings to make sure it is available.
    *
    */
    public String getInternetConnectionStatus(Activity activity)
    {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null)
        {
            return "TYPE_NOT_FOUND";
        }

        int conn = activeNetwork.getType();
        int type = C.TYPE_NOT_FOUND;

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

        boolean result = pingGoogle(activity);

       if(!result)
        {
            return "TYPE_NOT_CONNECTED";
        }

        switch(type)
        {
            case C.TYPE_WIFI:
                return "TYPE_WIFI";
            case C.TYPE_WIMAX:
                return "TYPE_WIMAX";
            case C.TYPE_MOBILE:
                return "TYPE_MOBILE";
            case C.TYPE_NOT_CONNECTED:
                return "TYPE_NOT_CONNECTED";
            case C.TYPE_NOT_FOUND:
                return "TYPE_NOT_FOUND";
            default:
                return "TYPE_NOT_FOUND";
        }

    }

    private boolean pingGoogle(Activity activity)
    {
        int counter = 2;
        boolean result = false;

        if(isNetworkConnected(activity))
        {
            return true;
        }
        else
        {
            for (int i = 0; i <counter ; i++)
            {
                result = isNetworkConnected(activity);

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

    public boolean isNetworkConnected(Activity activity)
    {
        boolean connected = false;
        final ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE);
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

    public boolean isNfcPresent(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1)
        {
            NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
            return nfcAdapter != null;
        }

        return false;
    }

    public boolean isNfcEnabled(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
            NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
            return nfcAdapter != null && nfcAdapter.isEnabled();
        }
        return false;
    }

    public Boolean isWifiEnabled(Activity activity)
    {
        if(!PermissionsUtil.getInstance(activity).isPermissionGranted(Manifest.permission.ACCESS_WIFI_STATE))
        {
            Toast.makeText(activity, "Missing permission ACCESS_WIFI_STATE", Toast.LENGTH_SHORT).show();
            return false;
        }

        WifiManager wifiManager = (WifiManager) activity.getSystemService(Activity.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    public String getNetworkClass(Activity activity) {
        TelephonyManager mTelephonyManager = (TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return C.NETWORK_TYPE_2G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return C.NETWORK_TYPE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return C.NETWORK_TYPE_4G;
            default:
                return C.NOT_FOUND_VAL;
        }
    }

    public String getIMEI(Activity activity)
    {
        if(!PermissionsUtil.getInstance(activity).isPermissionGranted(Manifest.permission.READ_PHONE_STATE))
        {
            Toast.makeText(activity, "Missing permission READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
            return "Missing permission READ_PHONE_STATE";
        }

        TelephonyManager telephonyMgr = (TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE);
        return telephonyMgr.getDeviceId();
    }

    public String getIMSI(Activity activity)
    {
        if(!PermissionsUtil.getInstance(activity).isPermissionGranted(Manifest.permission.READ_PHONE_STATE))
        {
            Toast.makeText(activity, "Missing permission  READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
            return "Missing permission READ_PHONE_STATE";
        }

        TelephonyManager telephonyMgr = (TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE);
        return telephonyMgr.getSubscriberId();
    }

    public final String getPhoneType(Activity activity)
    {
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE);
        switch (tm.getPhoneType()) {
            case TelephonyManager.PHONE_TYPE_GSM:
                return C.PHONE_TYPE_GSM;
            case TelephonyManager.PHONE_TYPE_CDMA:
                return C.PHONE_TYPE_CDMA;
            case TelephonyManager.PHONE_TYPE_NONE:
            default:
                return C.PHONE_TYPE_NONE;
        }
    }

    public String getPhoneNumber(Activity activity)
    {
        if(!PermissionsUtil.getInstance(activity).isPermissionGranted(Manifest.permission.READ_PHONE_STATE))
        {
            Toast.makeText(activity, "Missing permission READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
            return "Missing permission READ_PHONE_STATE";
        }

        String serviceName = Activity.TELEPHONY_SERVICE;
        TelephonyManager m_telephonyManager = (TelephonyManager) activity.getSystemService(serviceName);
        return m_telephonyManager.getLine1Number();
    }

    public String getOperator(Activity activity)
    {
        String operatorName;
        TelephonyManager telephonyManager =((TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE));
        operatorName = telephonyManager.getNetworkOperatorName();
        if(operatorName == null)
        {
            operatorName = telephonyManager.getSimOperatorName();
        }
        return operatorName;
    }

    public final String getSimSerial(Activity activity)
    {
        if(!PermissionsUtil.getInstance(activity).isPermissionGranted(Manifest.permission.READ_PHONE_STATE))
        {
            Toast.makeText(activity, "Missing permission READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
            return "Missing permission READ_PHONE_STATE";
        }

        TelephonyManager telephonyManager =((TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE));
        return telephonyManager.getSimSerialNumber();
    }

    public final boolean isSimNetworkLocked(Activity activity) {
        TelephonyManager telephonyManager =((TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE));
        return telephonyManager.getSimState() == TelephonyManager.SIM_STATE_NETWORK_LOCKED;
    }

    @SuppressWarnings("MissingPermission")
    public String getBluetoothMAC(Activity activity)
    {
        if(!PermissionsUtil.getInstance(activity).isPermissionGranted(Manifest.permission.BLUETOOTH))
        {
            Toast.makeText(activity, "Missing permission BLUETOOTH", Toast.LENGTH_SHORT).show();
            return "Missing permission BLUETOOTH";
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            String mac = Settings.Secure.getString(activity.getContentResolver(), "bluetooth_address");
            return mac;
        }
        else
        {
            BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
            String result = bta != null ? bta.getAddress() : "00";
            return result;
        }
    }

    @SuppressWarnings("MissingPermission")
    public String getWifiMacAddress(Activity activity)
    {
        if(!PermissionsUtil.getInstance(activity).isPermissionGranted(Manifest.permission.ACCESS_WIFI_STATE))
        {
            Toast.makeText(activity, "Missing permission ACCESS_WIFI_STATE", Toast.LENGTH_SHORT).show();
            return "Missing permission ACCESS_WIFI_STATE";
        }

        WifiManager manager = (WifiManager) activity.getSystemService(Activity.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String address = info.getMacAddress();
        return address;
    }
}
