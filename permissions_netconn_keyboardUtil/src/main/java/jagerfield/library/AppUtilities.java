package jagerfield.library;


import android.app.Activity;
import android.content.Context;
import jagerfield.library.NetworkUtil.NetworkUtil;
import jagerfield.library.PermissionsUtil.IPermissionUtil;
import jagerfield.library.PermissionsUtil.PermissionsUtil;
import jagerfield.library.SoftKeyboradUtil.SoftKeyboardUtil;

public class AppUtilities //implements IAppUtilities
{
//    @Override
    public static int getNetworkStatus(Context context)
    {
        return NetworkUtil.getInstance().getInternetStatus(context);
    }

//    @Override
    public static void setSoftboardKey(Activity activity, boolean status)
    {
        SoftKeyboardUtil.getInstance().setSkb(activity, status);
    }

//    @Override
    public static IPermissionUtil getPermissionUtil(Activity activity)
    {
        return PermissionsUtil.getInstance(activity);
    }


}
