package jagerfield.permissions.DeviceData.Properties;

import android.app.Activity;
import java.util.ArrayList;
import jagerfield.library.NetworkUtil.NetworkUtil;
import jagerfield.permissions.Fragments.PropertyModel;
import jagerfield.permissions.Utilities.Utilities;

public class NetworkUtilData
{
    public NetworkUtilData()
    {

    }

    public static NetworkUtilData getInstance()
    {
        NetworkUtilData networkUtilData = new NetworkUtilData();
        return networkUtilData;
    }

    public ArrayList<PropertyModel> getDeviceNetworkProperties(Activity activity)
    {
        ArrayList<PropertyModel> properties = new ArrayList<>();
        NetworkUtil networkUtil = NetworkUtil.getInstance();
        Utilities utilities = Utilities.getInstance();

        utilities.addProperty(properties, "Internet Connection Status", networkUtil.getInternetConnectionStatus(activity));

        utilities.addProperty(properties, "Is Network Connected", networkUtil.isNetworkConnected(activity));

        utilities.addProperty(properties, "Is Nfc Present", networkUtil.isNfcPresent(activity));

        utilities.addProperty(properties, "Is Nfc Enabled", networkUtil.isNfcEnabled(activity));

        utilities.addProperty(properties, "Is Wifi Enabled", networkUtil.isWifiEnabled(activity));

        utilities.addProperty(properties, "Network Class", networkUtil.getNetworkClass(activity));

        utilities.addProperty(properties, "IMEI", networkUtil.getIMEI(activity));

        utilities.addProperty(properties, "IMSI", networkUtil.getIMSI(activity));

        utilities.addProperty(properties, "Phone Type", networkUtil.getPhoneType(activity));

        utilities.addProperty(properties, "Phone Number", networkUtil.getPhoneNumber(activity));

        utilities.addProperty(properties, "Operator", networkUtil.getOperator(activity));

        utilities.addProperty(properties, "Sim Serial", networkUtil.getSimSerial(activity));

        utilities.addProperty(properties, "Is Sim Network Locked", networkUtil.isSimNetworkLocked(activity));

        utilities.addProperty(properties, "Bluetooth MAC", networkUtil.getBluetoothMAC(activity));

        utilities.addProperty(properties, "Wifi Mac Address", networkUtil.getWifiMacAddress(activity));

        return properties;
    }

}













