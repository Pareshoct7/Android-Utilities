package jagerfield.library;


import android.app.Activity;
import jagerfield.library.NetworkUtil.NetworkUtil;
import jagerfield.library.PermissionsUtil.PermissionsUtil;
import jagerfield.library.SoftKeyboradUtil.SoftKeyboardUtil;

public class AppUtilities
{
    public static NetworkUtil getNetworkUtil(Activity activity)
    {
        return NetworkUtil.getInstance();
    }

    public static void setSoftboardKey(Activity activity, boolean status)
    {
        SoftKeyboardUtil.getInstance().setSkb(activity, status);
    }

    public static PermissionsUtil getPermissionUtil(Activity activity)
    {
        return PermissionsUtil.getInstance(activity);
    }

}
