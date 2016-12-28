package jagerfield.permissions.DeviceData.Properties;

import android.app.Activity;
import java.util.ArrayList;
import jagerfield.library.AppUtilities;
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
        Utilities utilities = Utilities.getInstance();

        utilities.addProperty(properties, "Internet Connection Status", AppUtilities.getNetworkUtil().getInternetConnectionStatus(activity));

        utilities.addProperty(properties, "Is Network Connected", AppUtilities.getNetworkUtil().isNetworkConnected(activity));

        utilities.addProperty(properties, "Is Nfc Present", AppUtilities.getNetworkUtil().isNfcPresent(activity));

        utilities.addProperty(properties, "Is Nfc Enabled", AppUtilities.getNetworkUtil().isNfcEnabled(activity));

        utilities.addProperty(properties, "Is Wifi Enabled", AppUtilities.getNetworkUtil().isWifiEnabled(activity));

        utilities.addProperty(properties, "Network Class", AppUtilities.getNetworkUtil().getNetworkClass(activity));

        utilities.addProperty(properties, "IMEI", AppUtilities.getNetworkUtil().getIMEI(activity));

        utilities.addProperty(properties, "IMSI", AppUtilities.getNetworkUtil().getIMSI(activity));

        utilities.addProperty(properties, "Phone Type", AppUtilities.getNetworkUtil().getPhoneType(activity));

        utilities.addProperty(properties, "Phone Number", AppUtilities.getNetworkUtil().getPhoneNumber(activity));

        utilities.addProperty(properties, "Operator", AppUtilities.getNetworkUtil().getOperator(activity));

        utilities.addProperty(properties, "Sim Serial", AppUtilities.getNetworkUtil().getSimSerial(activity));

        utilities.addProperty(properties, "Is Sim Network Locked", AppUtilities.getNetworkUtil().isSimNetworkLocked(activity));

        utilities.addProperty(properties, "Bluetooth MAC", AppUtilities.getNetworkUtil().getBluetoothMAC(activity));

        utilities.addProperty(properties, "Wifi Mac Address", AppUtilities.getNetworkUtil().getWifiMacAddress(activity));

        return properties;
    }

}













