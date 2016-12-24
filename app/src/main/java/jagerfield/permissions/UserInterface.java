package jagerfield.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import jagerfield.library.AppUtilities;
import jagerfield.library.NetworkUtil.NetworkUtil;
import jagerfield.library.PermissionsUtil.IPermissionUtil;
import jagerfield.library.PermissionsUtil.Results.ICheckPermissionResult;
import jagerfield.utilities.R;


public class UserInterface
{
    private TextView tv_getting_permission;
    private TextView tv_requestPermissions;
    private TextView tv_requestNeverAskAgainPermissions;
    private TextView tv_neverAskAgainPermissionsList;
    private TextView tv_deniedPermissionsList;
    private Button bt_getDeniedPermissions;
    private Button bt_openSettings;
    private Button bt_checkPermissions;
    public String PERMISSIONS_MISSING = "Permissions are missing";
    public String PERMISSIONS_GRANTED = "All Permission Available";

    public static final String[] PERMISSIONS_ARRAY = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };

    private MainActivity mainActivity;

    public UserInterface(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public void initiateAppViews(View view)
    {
        tv_getting_permission = (TextView) view.findViewById(R.id.tv_getting_permission);
        tv_requestPermissions = (TextView) view.findViewById(R.id.tv_requestPermissions);
        tv_requestNeverAskAgainPermissions = (TextView) view.findViewById(R.id.tv_requestNeverAskAgainPermissions);
        tv_neverAskAgainPermissionsList = (TextView) view.findViewById(R.id.tv_neverAskAgainPermissionsList);
        tv_deniedPermissionsList = (TextView) view.findViewById(R.id.tv_deniedPermissionsList);
        bt_getDeniedPermissions = (Button) view.findViewById(R.id.bt_getDeniedPermissions);
        bt_openSettings = (Button) view.findViewById(R.id.bt_openSettings);
        bt_checkPermissions = (Button) view.findViewById(R.id.bt_checkPermissions);

        bt_getDeniedPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getPermissions();

            }
        });

        bt_openSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                openSettings(mainActivity);
            }
        });

        bt_checkPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getPermissions();
            }
        });
    }

    private void getPermissions()
    {
        IPermissionUtil permissionsUtil = AppUtilities.getPermissionUtil(mainActivity);

        ICheckPermissionResult result = permissionsUtil.checkPermissionsResults(PERMISSIONS_ARRAY);

        if (result.arePermissionsGranted())
        {
            setUserInterface(result);
        }
        else
        {
            permissionsUtil.makePermissionsRequests(PERMISSIONS_ARRAY);
        }

        NetworkUtil network = new NetworkUtil();

        int i = network.getInternetStatus(mainActivity);

        String str = "";
    }

    public void setUserInterface(ICheckPermissionResult permissionsResults)
    {
        if (permissionsResults ==null)
        {
            return;
        }

        /**
         *  All permissions granted
         */
        managePermissionStatusTitle(permissionsResults);

        /**
         * If not all permissions granted, then check the permissions user had denied
         */
        manageDeniedPermissionsViews(permissionsResults);

        /**
         * If not all permissions granted,check never show again permissions
         */
        manageNeverAskPermissionsViews(permissionsResults);
    }

    private void managePermissionStatusTitle(ICheckPermissionResult permissionsResults)
    {
        String msg = "";
        boolean gotPermissions = permissionsResults.arePermissionsGranted();

        if (gotPermissions && permissionsResults.getNeverAskAgainPermissionsList().isEmpty() && permissionsResults.getUserDeniedPermissionsList().isEmpty())
        {
            msg = PERMISSIONS_GRANTED;
            tv_getting_permission.setText(msg);
            tv_getting_permission.setTextColor(getThisColor(R.color.green));
            bt_checkPermissions.setVisibility(View.VISIBLE);
        }
        else if (!gotPermissions && !permissionsResults.getNeverAskAgainPermissionsList().isEmpty() && permissionsResults.getUserDeniedPermissionsList().isEmpty())
        {
            msg = PERMISSIONS_MISSING;
            tv_getting_permission.setText(msg);
            tv_getting_permission.setTextColor(getThisColor(R.color.red));
            bt_checkPermissions.setVisibility(View.VISIBLE);
        }
        else if (!gotPermissions && !permissionsResults.getUserDeniedPermissionsList().isEmpty())
        {
            msg = PERMISSIONS_MISSING;
            tv_getting_permission.setText(msg);
            tv_getting_permission.setTextColor(getThisColor(R.color.red));
            bt_checkPermissions.setVisibility(View.GONE);
        }
    }

    private int getThisColor(int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            return mainActivity.getColor(color);
        }
        else
        {
            return mainActivity.getResources().getColor(color);
        }
    }

    private void manageDeniedPermissionsViews(ICheckPermissionResult results)
    {
        int status = View.GONE;
        boolean gotPermissions = results.arePermissionsGranted();
        ArrayList<String> list = results.getUserDeniedPermissionsList();

        if (!results.arePermissionsGranted() && results.getUserDeniedPermissionsList().isEmpty())
        {
            status = View.GONE;
        }
        else if (!gotPermissions && !results.getUserDeniedPermissionsList().isEmpty())
        {
            status = View.VISIBLE;
        }

        tv_requestPermissions.setVisibility(status);
        String text = getList(list, status);
        tv_deniedPermissionsList.setText(text);
        tv_deniedPermissionsList.setVisibility(status);
        bt_getDeniedPermissions.setVisibility(status);
    }

    private void manageNeverAskPermissionsViews(ICheckPermissionResult results)
    {
        int status = View.GONE;
        boolean gotPermissions = results.arePermissionsGranted();
        ArrayList<String> list = results.getNeverAskAgainPermissionsList();

        if (!gotPermissions && results.getNeverAskAgainPermissionsList().isEmpty())
        {
            status = View.GONE;
        }
        else if (!gotPermissions && !results.getNeverAskAgainPermissionsList().isEmpty())
        {
            status = View.VISIBLE;
        }

        tv_requestNeverAskAgainPermissions.setVisibility(status);
        String text = getList(list, status);
        tv_neverAskAgainPermissionsList.setText(text);
        tv_neverAskAgainPermissionsList.setVisibility(status);
        bt_openSettings.setVisibility(status);
    }

    @NonNull
    private String getList(ArrayList<String> list, int status)
    {
        StringBuilder items = new StringBuilder();

        if (status == View.VISIBLE)
        {
            for (int i = 0; i < list.size(); i++)
            {
                String permission = list.get(i);
                int v = permission.lastIndexOf(".");
                String name = permission.substring(v+1, permission.length()).toLowerCase();
                char c = Character.toUpperCase(name.charAt(0));
                name = name.replaceFirst(Character.toString(name.charAt(0)), Character.toString(c));
                items.append(i +1 + " - " + name + "\n");
            }
        }

        return items.toString().replace("_", " ").trim();
    }

    public static void openSettings(final Activity context) {
        if (context == null) {
            return;
        }

        Uri uri = Uri.parse("package:" + context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri);
        context.startActivity(intent);
    }

}
