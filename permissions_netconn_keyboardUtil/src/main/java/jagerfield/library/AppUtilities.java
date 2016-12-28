package jagerfield.library;


import android.app.Activity;

import jagerfield.library.BatteryUtil.BatteryUtil;
import jagerfield.library.DeviceUtil.DeviceUtil;
import jagerfield.library.MemoryUtil.MemoryUtil;
import jagerfield.library.NetworkUtil.NetworkUtil;
import jagerfield.library.PermissionsUtil.PermissionsUtil;
import jagerfield.library.SoftKeyboradUtil.SoftKeyboardUtil;

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
