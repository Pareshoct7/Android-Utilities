package jagerfield.permissions.DeviceData.Properties;

import android.app.Activity;
import java.util.ArrayList;
import jagerfield.library.AppUtilities;
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
        Utilities utilities = Utilities.getInstance();
        int factor = 1000000;

        utilities.addProperty(properties, "Has External SD Card", AppUtilities.getMemoryUtil().hasExternalSDCard());

        utilities.addProperty(properties, "Total RAM", AppUtilities.getMemoryUtil().getTotalRAM(activity), factor, "MB");

        utilities.addProperty(properties, "Available Internal Memory Size", AppUtilities.getMemoryUtil().getAvailableInternalMemorySize(activity), factor, "MB");

        utilities.addProperty(properties, "Total Internal Memory Size", AppUtilities.getMemoryUtil().getTotalInternalMemorySize(), factor, "MB");

        utilities.addProperty(properties, "Available External Memory Size", AppUtilities.getMemoryUtil().getAvailableExternalMemorySize(), factor, "MB");

        utilities.addProperty(properties, "Total External Memory Size", AppUtilities.getMemoryUtil().getTotalExternalMemorySize(), factor, "MB");

        return properties;
    }

}
