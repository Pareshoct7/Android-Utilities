package jagerfield.permissions;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import jagerfield.library.AppUtilities;
import jagerfield.library.PermissionsUtil.PermissionsUtil;
import jagerfield.library.PermissionsUtil.Results.ICheckPermissionResult;
import jagerfield.utilities.R;

public class MainActivity extends AppCompatActivity
{
    private CoordinatorLayout activity_main;

    UserInterfaceManager userInterfaceManager = new UserInterfaceManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (CoordinatorLayout) findViewById(R.id.activity_main);
        userInterfaceManager.initiateAppViews(activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        PermissionsUtil permissionsUtil = AppUtilities.getPermissionUtil(this);

        if (requestCode == permissionsUtil.getPermissionsReqFlag())
        {
            ICheckPermissionResult result = null;
            result = permissionsUtil.checkPermissionsResults(UserInterfaceManager.PERMISSIONS_ARRAY);
            if (result == null)
            {
                return;
            }

            userInterfaceManager.setUserInterface(result);

            String str = "";
        }
    }
}




















