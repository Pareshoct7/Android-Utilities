package jagerfield.permissions_and_utilities_library.PermissionsUtil;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import jagerfield.permissions_and_utilities_library.C;
import jagerfield.permissions_and_utilities_library.PermissionsUtil.Results.IPermissionResult;
import jagerfield.permissions_and_utilities_library.PermissionsUtil.Results.PermissionsResults;

public class PermissionsUtil
{
    private Activity activity;
    private String[] permissionsArray;
    private final int PERMISSIONS_REQ = 1009989;

    public PermissionsUtil(Activity activity, String[] permissionsArray)
    {
        this.activity = activity;
        this.permissionsArray = permissionsArray;
    }

    public PermissionsUtil(Activity activity, String permissionsItem)
        {
            this.activity = activity;
            permissionsArray = new String[]{permissionsItem};
        }


    public PermissionsUtil(Activity activity)
    {
        this.activity = activity;
    }

    public void getPermissions(String permissionsItem)
    {
        if (permissionsItem==null || permissionsItem.isEmpty() )
        {
            Toast.makeText(activity, "The given permission request is incorrect", Toast.LENGTH_SHORT).show();
            return;
        }

        permissionsArray = new String[]{permissionsItem};
        getPermissions();
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

    public synchronized final boolean isPermissionGranted(String permission)
    {
        boolean result = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            result = activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }

        result =  ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;

        if (!result)
        {
            Toast.makeText(activity, "The " + permission +" is not given", Toast.LENGTH_SHORT).show();
            Log.e(C.TAG, permission + " is not provided");
        }

        return result;
    }

    public synchronized IPermissionResult checkPermissionsResults()
    {
        PermissionsResults permissionsResults = new PermissionsResults();

        permissionsResults.setGetPermissionStatus(true);

        for (int i = 0; i < permissionsArray.length; i++)
        {
            /**
             *  Running the checkSelfPermission aGain will filter the persmissions denyed by the user from the Never Show Again permissionsArray
             *
             */

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
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
            else
            {
                permissionsResults.addItemGrantedPermissionsList(permissionsArray[i]);
                permissionsResults.addItemAllPermissionsMap(permissionsArray[i], C.GRANTED);
            }

        }

        return permissionsResults;
    }
}
