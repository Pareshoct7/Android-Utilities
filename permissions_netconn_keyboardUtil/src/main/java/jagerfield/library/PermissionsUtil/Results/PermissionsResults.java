package jagerfield.library.PermissionsUtil.Results;

import java.util.ArrayList;
import java.util.HashMap;

public class PermissionsResults implements ICheckPermissionResult
{
    public static final String APPROVED = "APPROVED";
    private boolean permissionStatus = false;
    private ArrayList<String> neverAskAgainPermissionsList = new ArrayList<>();
    private ArrayList<String> userDeniedPermissionsList = new ArrayList<>();
    private ArrayList<String> grantedPermissionsList = new ArrayList<>();
    private ArrayList<String> missingInManifest_SdkLessThanM = new ArrayList<>();
    private HashMap<String, String> allPermissionsMap = new HashMap<>();

    public void addItemNeverAskAgainList(String item)
    {
        if (item != null && !item.isEmpty()) {
            neverAskAgainPermissionsList.add(item);
        }
    }

    @Override
    public ArrayList<String> getUserDeniedPermissionsList() {
        return userDeniedPermissionsList;
    }

    public void addItemUserDeniedPermissionsList(String item) {
        if (item != null && !item.isEmpty()) {
            userDeniedPermissionsList.add(item);
        }
    }

    public boolean arePermissionsGranted() {
        return permissionStatus;
    }

    public void setGetPermissionStatus(boolean status) {
        permissionStatus = status;
    }

    @Override
    public ArrayList<String> getGrantedPermissionsList() {
        return grantedPermissionsList;
    }

    @Override
    public ArrayList<String> getNeverAskAgainPermissionsList() {
        return neverAskAgainPermissionsList;
    }

    @Override
    public String getAPermissionReqStatus(String key)
    {
        String result = allPermissionsMap.get(key);
        return result;
    }

    public void addItemGrantedPermissionsList(String item)
    {
        if (item != null && !item.isEmpty()) {
            grantedPermissionsList.add(item);
        }
    }

    @Override
    public HashMap<String, String> getAllPermissionsWithStatusMap() {
        return allPermissionsMap;
    }

    public void addItemAllPermissionsMap(String name, String type)
    {
        allPermissionsMap.put(name, type);
    }

    public ArrayList<String> getMissingInManifest_SdkLessThanM()
    {
        return missingInManifest_SdkLessThanM;
    }

    public void addItemMissingInManifest_SdkLessThanM(String item)
    {
        if (item != null && !item.isEmpty()) {
            missingInManifest_SdkLessThanM.add(item);
        }
    }
}