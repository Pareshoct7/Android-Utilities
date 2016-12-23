package jagerfield.library.PermissionsUtil;

import jagerfield.library.PermissionsUtil.Results.ICheckPermissionResult;

public interface IPermissionUtil
{
    int getPermissionsReqFlag();

    void makePermissionRequest(String permissionsItem);

    void makePermissionsRequests(String[] permissionsArray);

    boolean isPermissionGranted(String permission);

    ICheckPermissionResult checkPermissionsResults(String[] permissionsArray);
}
