package jagerfield.permissions_netconn_keyboardutil.PermissionsUtil.Results;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPermissionResult
{
    boolean arePermissionsGranted();
    ArrayList<String> getGrantedPermissionsList();
    ArrayList<String> getUserDeniedPermissionsList();
    ArrayList<String> getNeverAskAgainPermissionsList();
    HashMap<String, String> getAllPermissionsWithStatusMap();

}
