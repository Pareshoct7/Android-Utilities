package jagerfield.permissions.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jagerfield.library.PermissionsUtil.Results.ICheckPermissionResult;
import jagerfield.permissions.MainActivity;
import jagerfield.permissions.UserInterfaceManager.UserInterfaceManager;
import jagerfield.utilities.R;


public class PermissionsFragment extends Fragment {


    private UserInterfaceManager userInterfaceManager;

    public PermissionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_permissions, container, false);

//        EventBus.getDefault().register(this);

        userInterfaceManager = new UserInterfaceManager((MainActivity) getActivity());
        userInterfaceManager.initiateAppViews(view);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGreenRobotEvent(ICheckPermissionResult result)
    {
        /*
         * Posted from : MainActivity - onRequestPermissionsResult
         */

        if (userInterfaceManager != null)
        {
            userInterfaceManager.setUserInterface(result);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
