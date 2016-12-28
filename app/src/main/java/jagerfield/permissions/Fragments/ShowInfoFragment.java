package jagerfield.permissions.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;

import jagerfield.permissions.DeviceData.Properties.MemoryUtilData;
import jagerfield.permissions.DeviceData.Properties.NetworkUtilData;
import jagerfield.permissions.MainActivity;
import jagerfield.permissions.Utilities.Utilities;
import jagerfield.utilities.R;

public class ShowInfoFragment extends Fragment
{
    private String title;
    private RecyclerView recyclerView;
    private DeviceInfoListViewAdapter adapter;

    public ShowInfoFragment()
    {
        // Required empty public constructor
    }

    public static ShowInfoFragment newInstance(String title)
    {
        ShowInfoFragment fragment = new ShowInfoFragment();
        Bundle args = new Bundle();
        args.putString(Utilities.FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deviceinfo_list, container, false);
        Context context = view.getContext();

        String title = "";
        ArrayList<PropertyModel> propertiesList = null;

        if (getArguments() != null)
        {
            title = getArguments().getString(Utilities.FRAGMENT_TITLE);
            propertiesList = Utilities.getInstance().getFragmentPropertiesList(title, getActivity());
        }

        if (propertiesList==null)
        {
            propertiesList = new ArrayList<>();
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.deviceInfolist);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        adapter = new DeviceInfoListViewAdapter(propertiesList);

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        try
        {
            EventBus.getDefault().register(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fragment List Adapter
     */
    private class DeviceInfoListViewAdapter extends RecyclerView.Adapter<DeviceInfoListViewAdapter.ViewHolder>
    {
        private ArrayList<PropertyModel> itemsList = new ArrayList<>();

        public DeviceInfoListViewAdapter(ArrayList<PropertyModel> itemsList)
        {
            this.itemsList = itemsList;
        }

        @Override
        public DeviceInfoListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_deviceinfo_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final DeviceInfoListViewAdapter.ViewHolder holder, final int position)
        {
            holder.vhObject = itemsList.get(position);
            holder.propertyType.setText(holder.vhObject.getPropertyType());
            holder.value.setText(holder.vhObject.getValue());
        }

        @Override
        public int getItemCount() {
            return itemsList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View hView;
            public final TextView propertyType;
            public final TextView value;
            public PropertyModel vhObject;

            public ViewHolder(View view)
            {
                super(view);
                hView = view;
                propertyType = (TextView) view.findViewById(R.id.propertyType);
                value = (TextView) view.findViewById(R.id.propertyValue);
            }
        }

        public void updateItemList(ArrayList<PropertyModel> list)
        {
            itemsList = list;
            notifyDataSetChanged();
        }
    }
}


