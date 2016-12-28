package jagerfield.permissions.Utilities;

import android.Manifest;
import android.app.Activity;
import java.util.ArrayList;
import jagerfield.permissions.DeviceData.Properties.BatteryUtilData;
import jagerfield.permissions.DeviceData.Properties.DeviceUtilData;
import jagerfield.permissions.DeviceData.Properties.MemoryUtilData;
import jagerfield.permissions.DeviceData.Properties.NetworkUtilData;
import jagerfield.permissions.DeviceData.Properties.PropertyModel;

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

    public ArrayList<PropertyModel> getFragmentPropertiesList (String title, Activity activity)
    {
        ArrayList<PropertyModel> propertiesList = new ArrayList<>();

        switch(title)
        {
            case Utilities.MEMORY_INFO_TAB:
                propertiesList = MemoryUtilData.getInstance().getDeviceMemoryProperties(activity);
                break;

            case Utilities.NETWORK_INFO_TITLE:
                propertiesList = NetworkUtilData.getInstance().getDeviceNetworkProperties(activity);
                break;

            case Utilities.DEVIS_INFO_TITLE:
                propertiesList = DeviceUtilData.getInstance().getDeviceProperties(activity);
                break;

            case Utilities.BATTERY_INFO_TITLE:
                propertiesList = BatteryUtilData.getInstance().getDeviceBatteryProperties(activity);
                break;
        }

        return propertiesList;
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
}
