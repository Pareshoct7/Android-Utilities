package jagerfield.library.DeviceUtil;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import jagerfield.library.C;
import jagerfield.library.PermissionsUtil.PermissionsUtil;

public class DeviceUtil extends C
{
    private final String NOT_FOUND_VAL = "unknown";

    private Activity activity;
    private PermissionsUtil permissionsUtil;

    public DeviceUtil(Activity activity) {
        this.activity = activity;
        this.permissionsUtil = new PermissionsUtil(activity);
    }

    /* Device Info: */
    public final String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }

    public final String getReleaseBuildVersion() {
        return Build.VERSION.RELEASE;
    }

    public final String getBuildVersionCodeName() {
        return Build.VERSION.CODENAME;
    }

    public final String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public final String getModel() {
        return Build.MODEL;
    }


    public String getProduct() {
        return Build.PRODUCT;
    }

    public final String getFingerprint() {
        return Build.FINGERPRINT;
    }

    public final String getHardware() {
        return Build.HARDWARE;
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public final String getRadioVer() {
        return  Build.getRadioVersion();
    }

    public final String getDevice() {
        return Build.DEVICE;
    }

    public final String getBoard() {
        return Build.BOARD;
    }

    public final String getDisplayVersion() {
        return Build.DISPLAY;
    }

    public final String getBuildBrand() {
        return Build.BRAND;
    }

    public final String getBuildHost() {
        return Build.HOST;
    }

    public final long getBuildTime() {
        return Build.TIME;
    }

    public final String getBuildUser() {
        return Build.USER;
    }

    public final String getSerial() {
        return Build.SERIAL;
    }

    public final String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public final String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public final int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    public String getScreenDensity() {
        int density = activity.getResources().getDisplayMetrics().densityDpi;
        String scrType = "";
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                scrType = "ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                scrType = "mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                scrType = "hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                scrType = "xhdpi";
                break;
            default:
                scrType = "other";
                break;
        }
        return scrType;
    }

    public int getScreenHeight() {
        int height = 0;
        WindowManager wm = (WindowManager) activity.getSystemService(Activity.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        } else {
            height = display.getHeight();  // deprecated
        }
        return height;
    }

    public int getScreenWidth() {
        int width = 0;
        WindowManager wm = (WindowManager) activity.getSystemService(Activity.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        } else {
            width = display.getWidth();  // deprecated
        }
        return width;
    }

    /* App Info: */
    public String getVersionName() {
        PackageInfo pInfo;
        try {
            pInfo = activity.getPackageManager().getPackageInfo(
                    activity.getPackageName(), 0);
            return pInfo.versionName;
        } catch (Exception e1) {
            return null;
        }
    }

    public Integer getVersionCode() {
        PackageInfo pInfo;
        try {
            pInfo = activity.getPackageManager().getPackageInfo(
                    activity.getPackageName(), 0);
            return pInfo.versionCode;
        } catch (Exception e1) {
            return null;
        }
    }

    public String getPackageName() {
        return activity.getPackageName();
    }

    public String getActivityName() {
        return activity.getClass().getSimpleName();
    }

    public String getAppName() {
        PackageManager packageManager = activity.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(activity.getApplicationInfo().packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : NOT_FOUND_VAL);
    }

    public final boolean isAppInstalled(String packageName) {
        return activity.getPackageManager().getLaunchIntentForPackage(packageName) != null;
    }

    /* Battery Info:
     * battery percentage
     * is phone charging at the moment
     * Battery Health
     * Battery Technology
     * Battery Temperature
     * Battery Voltage
     * Charging Source
     * Check if battery is present */
    private Intent getBatteryStatusIntent() {
        IntentFilter batFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        return activity.registerReceiver(null, batFilter);
    }



    /* Id Info: */
    @SuppressWarnings("MissingPermission")
    public final String getBluetoothMAC()
    {
        if(!permissionsUtil.isPermissionGranted(Manifest.permission.BLUETOOTH))
              throw new RuntimeException("Access Bluetooth permission not granted!");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.Secure.getString(activity.getContentResolver(),
                    "bluetooth_address");
        } else {
            BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
            String result = bta != null ? bta.getAddress() : "00";
            return result;
        }
    }

    @SuppressWarnings("MissingPermission")
    public final String getWifiMacAddress() {
        if(!permissionsUtil.isPermissionGranted(Manifest.permission.ACCESS_WIFI_STATE))
                throw new RuntimeException("Access Wifi state permission not granted!");

        WifiManager manager = (WifiManager) activity.getSystemService(Activity.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String address = info.getMacAddress();
        return address;
    }

    public boolean isRunningOnEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT)
                || Build.PRODUCT.contains("vbox86p")
                || Build.DEVICE.contains("vbox86p")
                || Build.HARDWARE.contains("vbox86");
    }

    public String getDeviceRingerMode() {
        AudioManager audioManager = (AudioManager) activity.getSystemService(Activity.AUDIO_SERVICE);
        switch (audioManager.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                return RINGER_MODE_SILENT;
            case AudioManager.RINGER_MODE_VIBRATE:
                return RINGER_MODE_VIBRATE;
            default:
                return RINGER_MODE_NORMAL;
        }
    }

    public final boolean isDeviceRooted() {
        String[] paths = { "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }

    @SuppressWarnings("MissingPermission")
    public final List<String> getEmailAccounts() {
        if(!permissionsUtil.isPermissionGranted(Manifest.permission.GET_ACCOUNTS))
                throw new RuntimeException("Get Accounts permission not granted!");

        Set<String> emails = new HashSet<String>();
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(activity).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                emails.add(account.name);
            }
        }
        List list = new ArrayList<String>(new LinkedHashSet<String>(emails));
        return list;
    }

    public final String getAndroidId() {
        String androidId = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return androidId;
    }

    public String getInstallSource(){
        PackageManager pm = activity.getPackageManager();
        String installationSource = pm.getInstallerPackageName(activity.getPackageName());
        return installationSource;
    }

    public final String getUserAgent() {
        final String systemUa = System.getProperty("http.agent");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return WebSettings.getDefaultUserAgent(activity) + "__" + systemUa;
        }
         return new WebView(activity).getSettings().getUserAgentString() + "__" + systemUa;
    }

    public final String getGSFId() {
        Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        String ID_KEY = "android_id";
        String params[] = {ID_KEY};
        Cursor c = activity.getContentResolver().query(URI, null, null, params, null);

        if (!c.moveToFirst() || c.getColumnCount() < 2) {
            c.close();
            return NOT_FOUND_VAL;
        }
        try {
            String gsfId =  Long.toHexString(Long.parseLong(c.getString(1)));
            c.close();
            return gsfId;
        }
        catch (NumberFormatException e) {
            c.close();
            return NOT_FOUND_VAL;
        }
    }


//

}
