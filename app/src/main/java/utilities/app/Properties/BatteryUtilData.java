package utilities.app.Properties;

import android.app.Activity;

import java.util.ArrayList;

import utilities.lib.AppUtilities;
import utilities.app.Utilities.Utilities;

public class BatteryUtilData
{
    public BatteryUtilData()
    {

    }

    public static BatteryUtilData getInstance()
    {
        BatteryUtilData batteryUtilData = new BatteryUtilData();
        return batteryUtilData;
    }

    public ArrayList<PropertyModel> getDeviceBatteryProperties(Activity activity)
    {
        ArrayList<PropertyModel> properties = new ArrayList<>();
        Utilities utilities = Utilities.getInstance();

        utilities.addProperty(properties, "Battery Percent", AppUtilities.getDBatteryUtil().getBatteryPercent(activity));

        utilities.addProperty(properties, "Is Phone Charging", AppUtilities.getDBatteryUtil().isPhoneCharging(activity));

        utilities.addProperty(properties, "Battery Health", AppUtilities.getDBatteryUtil().getBatteryHealth(activity));

        utilities.addProperty(properties, "Battery Technology", AppUtilities.getDBatteryUtil().getBatteryTechnology(activity));

        utilities.addProperty(properties, "Battery Temperature", AppUtilities.getDBatteryUtil().getBatteryTemperature(activity));

        utilities.addProperty(properties, "Battery Voltage", AppUtilities.getDBatteryUtil().getBatteryVoltage(activity));

        utilities.addProperty(properties, "Charging Source", AppUtilities.getDBatteryUtil().getChargingSource(activity));

        utilities.addProperty(properties, "Is Battery Present", AppUtilities.getDBatteryUtil().isBatteryPresent(activity));

        return properties;
    }

}


















