package jagerfield.permissions.DeviceData;

import java.util.ArrayList;
import jagerfield.permissions.Fragments.DevicePropertiesModel;

public class Utilities
{
    public Utilities()
    {

    }

    public static Utilities getInstance()
    {
        Utilities utilities = new Utilities();
        return utilities;
    }

    public void addProperty(ArrayList<DevicePropertiesModel> properties, String type, String value)
    {
        if (type == null || type.isEmpty())
        {
            return;
        }

        DevicePropertiesModel property = new DevicePropertiesModel();
        property.setPropertyType(type);
        try
        {
            property.setValue(value);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(ArrayList<DevicePropertiesModel> properties, String type, String value, String suffex)
    {
        if (type == null || type.isEmpty())
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

        DevicePropertiesModel property = new DevicePropertiesModel();
        property.setPropertyType(type);
        try
        {
            property.setValue(value + suffex);
            properties.add(property);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProperty(ArrayList<DevicePropertiesModel> properties, String type, long value, String suffex)
    {
        if (type == null || type.isEmpty())
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

        DevicePropertiesModel property = new DevicePropertiesModel();
        property.setPropertyType(type);

        try
        {
            property.setValue(String.valueOf(value/1000000) + suffex);
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

    public void addProperty(ArrayList<DevicePropertiesModel> properties, String type, Boolean value)
    {
        if (type == null || type.isEmpty())
        {
            return;
        }

        DevicePropertiesModel property = new DevicePropertiesModel();
        property.setPropertyType(type);

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
