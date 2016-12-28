package jagerfield.permissions.Utilities;

import android.Manifest;
import android.app.Activity;
import java.util.ArrayList;
import jagerfield.permissions.DeviceData.Properties.BatteryUtilData;
import jagerfield.permissions.DeviceData.Properties.DeviceUtilData;
import jagerfield.permissions.DeviceData.Properties.MemoryUtilData;
import jagerfield.permissions.DeviceData.Properties.NetworkUtilData;
import jagerfield.permissions.DeviceData.Properties.PropertyModel;
import jagerfield.utilities.R;

public class Utilities
{
    public static final String PERMISSIONS_TITLE = "Permissions";
    public static final String MEMORY_INFO_TAB = "Memory Info";
    public static final String NETWORK_INFO_TITLE = "Network Info";
    public static final String DEVIS_INFO_TITLE = "Device Info";
    public static final String BATTERY_INFO_TITLE = "Battery Info";

    public static final String FRAGMENT_TITLE = "FRAGMENT_TITLE";

    public static final String[] PERMISSIONS_ARRAY = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.ACCESS_WIFI_STATE
    };


    public Utilities()
    {

    }

    public static Utilities getInstance()
    {
        Utilities obj = new Utilities();
        return obj;
    }

    public void addProperty(ArrayList<PropertyModel> properties, String name, String value)
    {
        if (name == null || name.isEmpty())
        {
            return;
        }

        PropertyModel property = new PropertyModel();
        property.setPropertyType(name);

        try
        {
            property.setValue(value);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FragmentData getFragmentData(String title, Activity activity)
    {
        FragmentData fragmentData = new FragmentData(activity);

        switch(title)
        {
            case Utilities.MEMORY_INFO_TAB:
                fragmentData.propertiesList = MemoryUtilData.getInstance().getDeviceMemoryProperties(activity);
                fragmentData.valueTextColor = activity.getResources().getColor(R.color.blue_grey_800_50p);

                break;

            case Utilities.NETWORK_INFO_TITLE:
                fragmentData.propertiesList = NetworkUtilData.getInstance().getDeviceNetworkProperties(activity);
                fragmentData.valueTextColor = activity.getResources().getColor(R.color.smokey_vblue);
                break;

            case Utilities.DEVIS_INFO_TITLE:
                fragmentData.propertiesList = DeviceUtilData.getInstance().getDeviceProperties(activity);
                fragmentData.valueTextColor = activity.getResources().getColor(R.color.smokey_green);
                break;

            case Utilities.BATTERY_INFO_TITLE:
                fragmentData.propertiesList = BatteryUtilData.getInstance().getDeviceBatteryProperties(activity);
                fragmentData.valueTextColor = activity.getResources().getColor(R.color.smokey_reddish_orange);
                break;
            default:

                break;
        }

        return fragmentData;
    }

    public void addProperty(ArrayList<PropertyModel> properties, String name, String value, String suffex)
    {
        if (name == null || name.isEmpty())
        {
            return;
        }
        if (suffex==null || suffex.trim().isEmpty())
        {
            suffex="";
        }
        else
        {
            suffex = " " + suffex;
        }

        PropertyModel property = new PropertyModel();
        property.setPropertyType(name);
        try
        {
            property.setValue(value + suffex);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(ArrayList<PropertyModel> properties, String name, long value, int factor, String suffex)
    {
        if (name == null || name.isEmpty())
        {
            return;
        }
        if (suffex==null || suffex.trim().isEmpty())
        {
            suffex="";
        }
        else
        {
            suffex = " " + suffex;
        }

        PropertyModel property = new PropertyModel();
        property.setPropertyType(name);

        try
        {
            property.setValue(String.valueOf(value/factor) + suffex);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(ArrayList<PropertyModel> properties, String name, boolean value)
    {
        if (name == null || name.isEmpty())
        {
            return;
        }

        PropertyModel property = new PropertyModel();
        property.setPropertyType(name);

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

    public void addProperty(ArrayList<PropertyModel> properties, String name, float value)
    {
        if (name == null || name.isEmpty())
        {
            return;
        }

        PropertyModel property = new PropertyModel();
        property.setPropertyType(name);

        try
        {
            String str = String.valueOf(value);

            property.setValue(str);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(ArrayList<PropertyModel> properties, String name, int value)
    {
        if (name == null || name.isEmpty())
        {
            return;
        }

        PropertyModel property = new PropertyModel();
        property.setPropertyType(name);

        try
        {
            String str = String.valueOf(value);

            property.setValue(str);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(ArrayList<PropertyModel> properties, String name, Integer value)
    {
        if (name == null || name.isEmpty())
        {
            return;
        }

        PropertyModel property = new PropertyModel();
        property.setPropertyType(name);

        try
        {
            String str= "";

            if (value == null)
            {
                str = "Can't get value";
            }

            str = String.valueOf(value);

            property.setValue(str);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(ArrayList<PropertyModel> properties, String name, Boolean value)
    {
        if (name == null || name.isEmpty())
        {
            return;
        }

        PropertyModel property = new PropertyModel();
        property.setPropertyType(name);

        try
        {
            String str = "";
            if (value==null)
            {
                str = "Can't read property";
            }
            else
            {
                str = String.valueOf(value);
                char c = Character.toUpperCase(str.charAt(0));
                str = str.replaceFirst(Character.toString(str.charAt(0)), Character.toString(c));
            }

            property.setValue(str);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class FragmentData
    {
        private int valueTextColor = -1;
        private ArrayList<PropertyModel> propertiesList = new ArrayList<>();

        public FragmentData(Activity activity)
        {
            valueTextColor = activity.getResources().getColor(R.color.smokey_sand);
        }

        public ArrayList<PropertyModel> getPropertiesList() {
            return propertiesList;
        }

        public void setPropertiesList(ArrayList<PropertyModel> propertiesList) {
            this.propertiesList = propertiesList;
        }

        public int getValueTextColor() {
            return valueTextColor;
        }

        public void setValueTextColor(int valueTextColor)
        {
            this.valueTextColor = valueTextColor;
        }

    }
}
