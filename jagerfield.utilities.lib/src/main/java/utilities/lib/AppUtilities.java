package utilities.lib;


import android.app.Activity;

import utilities.lib.BatteryUtil.BatteryUtil;
import utilities.lib.DeviceUtil.DeviceUtil;
import utilities.lib.MemoryUtil.MemoryUtil;
import utilities.lib.NetworkUtil.NetworkUtil;
import utilities.lib.PermissionsUtil.PermissionsUtil;
import utilities.lib.SoftKeyboradUtil.SoftKeyboardUtil;

public class AppUtilities
{
    public static void setSoftKeyboard(Activity activity, boolean status)
    {
        SoftKeyboardUtil.getInstance().setSkb(activity, status);
    }

    public static PermissionsUtil getPermissionUtil(Activity activity)
    {
        return PermissionsUtil.getInstance(activity);
    }

    public static NetworkUtil getNetworkUtil()
    {
        return NetworkUtil.getInstance();
    }

    public static MemoryUtil getMemoryUtil()
    {
        return MemoryUtil.getInstance();
    }

    public static DeviceUtil getDeviceUtil()
    {
        return DeviceUtil.getInstance();
    }

    public static BatteryUtil getDBatteryUtil()
    {
        return BatteryUtil.getInstance();
    }


}
