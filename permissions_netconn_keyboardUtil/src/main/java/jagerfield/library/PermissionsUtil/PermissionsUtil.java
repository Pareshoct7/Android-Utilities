package jagerfield.library.PermissionsUtil;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import java.util.ArrayList;
import jagerfield.library.C;
import jagerfield.library.PermissionsUtil.Results.ICheckPermissionResult;
import jagerfield.library.PermissionsUtil.Results.PermissionsResults;

public class PermissionsUtil
{
    private Activity activity;
    private final int PERMISSIONS_REQ = 1009989;

    public PermissionsUtil(Activity activity)
    {
        this.activity = activity;
    }

    public static PermissionsUtil getInstance(Activity activity)
    {
        PermissionsUtil obj = new PermissionsUtil(activity);
        return obj;
    }

    public int getPermissionsReqFlag() {
        return PERMISSIONS_REQ;
    }

    public synchronized void makePermissionRequest(String permissionsItem)
    {
        if (permissionsItem==null || permissionsItem.isEmpty() )
        {
            Log.i(C.TAG, "The given permission item is incorrect");
            return;
        }

        makePermissionsRequests(new String[]{permissionsItem});
    }

    public synchronized void makePermissionsRequests(String[] permissionsArray)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return;
        }

        if (permissionsArray==null || permissionsArray.length==0)
        {
            Log.i(C.TAG, "The given permission request is incorrect");
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
        String msg = "";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            result = activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED ? true:false;
            msg = " is not available";
        }
        else
        {
            result = activity.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED ? true:false;
            msg = " is missing in Manifest";
        }

        if (!result)
        {
            Log.e(C.TAG, permission + " is not available");
        }

        return result;
    }

    public synchronized ICheckPermissionResult checkPermissionsResults(String[] permissionsArray)
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
                int value = activity.checkSelfPermission(permissionsArray[i]);
                if (value == PackageManager.PERMISSION_DENIED)
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
                boolean result = activity.checkCallingOrSelfPermission(permissionsArray[i]) == PackageManager.PERMISSION_GRANTED;

                if (result)
                {
                    permissionsResults.addItemGrantedPermissionsList(permissionsArray[i]);
                    permissionsResults.addItemAllPermissionsMap(permissionsArray[i], C.GRANTED);
                }
                else
                {
                    permissionsResults.setGetPermissionStatus(false);
                    permissionsResults.addItemMissingInManifest_SdkLessThanM(permissionsArray[i]);
                    permissionsResults.addItemAllPermissionsMap(permissionsArray[i], C.MISSING_IN_MAIFEST);
                }

            }

        }

        return permissionsResults;
    }
}
