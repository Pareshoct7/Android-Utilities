package jagerfield.permissions;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import jagerfield.library.AppUtilities;
import jagerfield.library.PermissionsUtil.IPermissionUtil;
import jagerfield.library.PermissionsUtil.Results.ICheckPermissionResult;
import jagerfield.utilities.R;

public class MainActivity extends AppCompatActivity
{
    private CoordinatorLayout activity_main;

    UserInterface userInterface = new UserInterface(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (CoordinatorLayout) findViewById(R.id.activity_main);
        userInterface.initiateAppViews(activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        IPermissionUtil permissionsUtil = AppUtilities.getPermissionUtil(this);

        if (requestCode == permissionsUtil.getPermissionsReqFlag())
        {
            ICheckPermissionResult result = null;
            result = permissionsUtil.checkPermissionsResults(UserInterface.PERMISSIONS_ARRAY);
            if (result == null)
            {
                return;
            }

            userInterface.setUserInterface(result);

            String str = "";
        }
    }
}




















