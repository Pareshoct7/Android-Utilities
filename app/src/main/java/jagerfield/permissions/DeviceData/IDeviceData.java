package jagerfield.permissions.DeviceData;

import android.app.Activity;
import java.util.ArrayList;
import jagerfield.permissions.Fragments.DevicePropertiesModel;

public interface IDeviceData
{
    ArrayList<DevicePropertiesModel> propertiesList(Activity activity);

}
