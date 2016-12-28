package utilities.lib.PermissionsUtil.Results;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICheckPermissionResult
{
    boolean arePermissionsGranted();
    ArrayList<String> getGrantedPermissionsList();
    ArrayList<String> getUserDeniedPermissionsList();
    ArrayList<String> getNeverAskAgainPermissionsList();
    String getAPermissionReqStatus(String key);
    HashMap<String, String> getAllPermissionsWithStatusMap();

}
