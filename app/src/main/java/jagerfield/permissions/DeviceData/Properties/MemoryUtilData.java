package jagerfield.permissions.DeviceData.Properties;

import android.app.Activity;
import java.util.ArrayList;
import jagerfield.library.MemoryUtil.MemoryUtil;
import jagerfield.permissions.DeviceData.Utilities;
import jagerfield.permissions.Fragments.DevicePropertiesModel;

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

    public ArrayList<DevicePropertiesModel> getDeviceMemoryProperties(Activity activity)
    {
        ArrayList<DevicePropertiesModel> properties = new ArrayList<>();
        MemoryUtil memoryUtil = MemoryUtil.getInstance();
        Utilities utilities = Utilities.getInstance();

        utilities.addProperty(properties, "Has External SD Card", memoryUtil.hasExternalSDCard());

        utilities.addProperty(properties, "Total RAM", memoryUtil.getTotalRAM(activity), "MB");

        utilities.addProperty(properties, "Available Internal Memory Size", memoryUtil.getAvailableInternalMemorySize(activity), "MB");

        utilities.addProperty(properties, "Total Internal Memory Size", memoryUtil.getTotalInternalMemorySize(), "MB");

        utilities.addProperty(properties, "Available External Memory Size", memoryUtil.getAvailableExternalMemorySize(), "MB");

        utilities.addProperty(properties, "Total External Memory Size", memoryUtil.getTotalExternalMemorySize(), "MB");

        return properties;
    }

}
