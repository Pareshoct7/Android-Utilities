package jagerfield.permissions.DeviceData.Properties;

import android.app.Activity;
import java.util.ArrayList;
import jagerfield.library.MemoryUtil.MemoryUtil;
import jagerfield.permissions.Fragments.PropertyModel;
import jagerfield.permissions.Utilities.Utilities;

public class MemoryUtilData
{
    public MemoryUtilData()
    {

    }

    public static MemoryUtilData getInstance()
    {
        MemoryUtilData memoryUtilData = new MemoryUtilData();
        return memoryUtilData;
    }

    public ArrayList<PropertyModel> getDeviceMemoryProperties(Activity activity)
    {
        ArrayList<PropertyModel> properties = new ArrayList<>();
        MemoryUtil memoryUtil = MemoryUtil.getInstance();
        Utilities utilities = Utilities.getInstance();
        int factor = 1000000;

        utilities.addProperty(properties, "Has External SD Card", memoryUtil.hasExternalSDCard());

        utilities.addProperty(properties, "Total RAM", memoryUtil.getTotalRAM(activity), factor, "MB");

        utilities.addProperty(properties, "Available Internal Memory Size", memoryUtil.getAvailableInternalMemorySize(activity), factor, "MB");

        utilities.addProperty(properties, "Total Internal Memory Size", memoryUtil.getTotalInternalMemorySize(), factor, "MB");

        utilities.addProperty(properties, "Available External Memory Size", memoryUtil.getAvailableExternalMemorySize(), factor, "MB");

        utilities.addProperty(properties, "Total External Memory Size", memoryUtil.getTotalExternalMemorySize(), factor, "MB");

        return properties;
    }

}
