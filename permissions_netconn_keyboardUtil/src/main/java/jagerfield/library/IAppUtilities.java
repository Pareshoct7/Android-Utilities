package jagerfield.library;


import android.app.Activity;
import android.content.Context;

public interface IAppUtilities
{
    int getNetworkStatus(Context context);

    void setSoftboardKey(Activity activity, boolean status);

    void getPermissionUtil(Activity activity);
}
