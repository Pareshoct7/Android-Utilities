package jagerfield.permissions_netconn_keyboardutil.PermissionsUtil;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import jagerfield.permissions_netconn_keyboardutil.C;
import jagerfield.permissions_netconn_keyboardutil.PermissionsUtil.Results.IPermissionResult;
import jagerfield.permissions_netconn_keyboardutil.PermissionsUtil.Results.PermissionsResults;

public class PermissionUtil
{
    private Activity activity;
    private String[] permissionsArray;
    private final int PERMISSIONS_REQ = 1009989;

    public PermissionUtil(Activity activity, String[] permissionsArray)
    {
        this.activity = activity;
        this.permissionsArray = permissionsArray;
    }

    public PermissionUtil(Activity activity, String permissionsItem)
        {
            this.activity = activity;
            permissionsArray = new String[]{permissionsItem};
        }

    public int getPermissionsReqFlag() {
        return PERMISSIONS_REQ;
    }

    public void getPermissions()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return;
        }

        ArrayList<String> firstTimeAndNeverShowAgainPermissionsList = new ArrayList<>();
        ArrayList<String> previouslyDeniedPermissionsList = new ArrayList<>();
        ArrayList<String> missingPermissionsList = new ArrayList<>();

        for (int i = 0; i < permissionsArray.length; i++)
        {
            if (activity.checkSelfPermission(permissionsArray[i]) == PackageManager.PERMISSION_DENIED)
            {
                // if permission has been denied before
                if (activity.shouldShowRequestPermissionRationale(permissionsArray[i]))
                {
                    previouslyDeniedPermissionsList.add(permissionsArray[i]);
                }
                else //First time permissionsArray + permission has been denied before and box checked for not showing them again.
                {
                    firstTimeAndNeverShowAgainPermissionsList.add(permissionsArray[i]);
                }
            }
        }

        missingPermissionsList.addAll(firstTimeAndNeverShowAgainPermissionsList);
        missingPermissionsList.addAll(previouslyDeniedPermissionsList);

        if (!missingPermissionsList.isEmpty())
        {
            String[] missingPermissionsArray = missingPermissionsList.toArray(new String[missingPermissionsList.size()]);
            if (missingPermissionsArray.length > 0)
            {
                activity.requestPermissions(missingPermissionsArray, PERMISSIONS_REQ);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public IPermissionResult getPermissionReqResult()
    {
        PermissionsResults permissionsResults = new PermissionsResults();

        permissionsResults.setGetPermissionStatus(true);

        for (int i = 0; i < permissionsArray.length; i++)
        {
            /**
             *  Running the checkSelfPermission aGain will filter the persmissions denyed by the user from the Never Show Again permissionsArray
             *
             */

            if (activity.checkSelfPermission(permissionsArray[i]) == PackageManager.PERMISSION_DENIED)
            {
                if (activity.shouldShowRequestPermissionRationale(permissionsArray[i]))
                {
                   /*
                    * If permission has been denied by the user
                    */
                    permissionsResults.addItemUserDeniedPermissionsList(permissionsArray[i]);
                    permissionsResults.addItemAllPermissionsMap(permissionsArray[i], C.USER_DENIED);
                }
                else
                {
                   /*
                    * Found a "Never ask again" case"
                    */
                    permissionsResults.addItemNeverAskAgainList(permissionsArray[i]);
                    permissionsResults.addItemAllPermissionsMap(permissionsArray[i], C.NEVER_SHOW_AGAIN);
                }

                permissionsResults.setGetPermissionStatus(false);
            }
            else
            {
                permissionsResults.addItemGrantedPermissionsList(permissionsArray[i]);
                permissionsResults.addItemAllPermissionsMap(permissionsArray[i], C.GRANTED);
            }
        }

        return permissionsResults;
    }
}
