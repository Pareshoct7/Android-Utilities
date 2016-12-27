package jagerfield.permissions.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jagerfield.library.MemoryUtil.MemoryUtil;
import jagerfield.utilities.R;


public class MemoryInfoFragment extends Fragment {


    public MemoryInfoFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deviceinfo_list, container, false);
        Context context = view.getContext();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.deviceInfolist);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        ArrayList<DevicePropertiesModel> propertiesList = getMemoryProperties(getActivity());

        recyclerView.setAdapter(new DeviceInfoListViewAdapter(this, propertiesList));

        return view;
    }

    /**
     * Fragment List Adapter
     */
    private class DeviceInfoListViewAdapter extends RecyclerView.Adapter<DeviceInfoListViewAdapter.ViewHolder>
    {
        private MemoryInfoFragment fragment;
        private Context context;
        private ArrayList<DevicePropertiesModel> itemsList = new ArrayList<>();

        public DeviceInfoListViewAdapter(MemoryInfoFragment fragment, ArrayList<DevicePropertiesModel> itemsList)
        {
            this.fragment = fragment;
            context = fragment.getActivity();
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
            public DevicePropertiesModel vhObject;

            public ViewHolder(View view)
            {
                super(view);
                hView = view;
                propertyType = (TextView) view.findViewById(R.id.propertyType);
                value = (TextView) view.findViewById(R.id.propertyValue);
            }
        }
    }

    public ArrayList<DevicePropertiesModel> getMemoryProperties(Activity activity)
    {
        ArrayList<DevicePropertiesModel> properties = new ArrayList<>();
        MemoryUtil memoryUtil = MemoryUtil.getInstance();

        addProperty(properties, "Has External SD Card", memoryUtil.hasExternalSDCard());

        addProperty(properties, "Total RAM", memoryUtil.getTotalRAM(activity), "MB");

        addProperty(properties, "Available Internal Memory Size", memoryUtil.getAvailableInternalMemorySize(activity), "MB");

        addProperty(properties, "Total Internal Memory Size", memoryUtil.getTotalInternalMemorySize(), "MB");

        addProperty(properties, "Available External Memory Size", memoryUtil.getAvailableExternalMemorySize(), "MB");

        addProperty(properties, "Total External Memory Size", memoryUtil.getTotalExternalMemorySize(), "MB");

        return properties;
    }

    public void addProperty(ArrayList<DevicePropertiesModel> properties, String type, long value, String suffex)
    {
        DevicePropertiesModel property = new DevicePropertiesModel();
        property.setPropertyType(type);
        try
        {
            property.setValue(String.valueOf(value/1000000) + " " + suffex);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(ArrayList<DevicePropertiesModel> properties, String type, boolean value)
    {
        if (type == null || type.isEmpty())
        {
            return;
        }

        DevicePropertiesModel property = new DevicePropertiesModel();
        property.setPropertyType(type);

        try
        {
            String str = String.valueOf(value);
            char c = Character.toUpperCase(str.charAt(0));
            str = str.replaceFirst(Character.toString(str.charAt(0)), Character.toString(c));

            property.setValue(str);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}


