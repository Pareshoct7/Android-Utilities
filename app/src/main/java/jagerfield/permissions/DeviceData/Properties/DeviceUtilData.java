package jagerfield.permissions.DeviceData.Properties;

import android.app.Activity;
import java.util.ArrayList;
import jagerfield.library.DeviceUtil.DeviceUtil;
import jagerfield.permissions.Fragments.PropertyModel;
import jagerfield.permissions.Utilities.Utilities;

public class DeviceUtilData
{
    public DeviceUtilData()
    {

    }

    public static DeviceUtilData getInstance()
    {
        DeviceUtilData deviceUtilData = new DeviceUtilData();
        return deviceUtilData;
    }

    public ArrayList<PropertyModel> getDeviceProperties(Activity activity)
    {
        ArrayList<PropertyModel> properties = new ArrayList<>();
        DeviceUtil deviceUtil = DeviceUtil.getInstance();
        Utilities utilities = Utilities.getInstance();

        utilities.addProperty(properties, "Release Build Version", deviceUtil.getReleaseBuildVersion());

        utilities.addProperty(properties, "Device Name", deviceUtil.getDeviceName());

        utilities.addProperty(properties, "Build Version Code Name", deviceUtil.getBuildVersionCodeName());

        utilities.addProperty(properties, "Manufacturer", deviceUtil.getManufacturer());

        utilities.addProperty(properties, "Model", deviceUtil.getModel());

        utilities.addProperty(properties, "Product", deviceUtil.getProduct());

        utilities.addProperty(properties, "Finger print", deviceUtil.getFingerprint());

        utilities.addProperty(properties, "Hardware", deviceUtil.getHardware());

        utilities.addProperty(properties, "RadioVer", deviceUtil.getRadioVer());

        utilities.addProperty(properties, "Device", deviceUtil.getDevice());

        utilities.addProperty(properties, "Board", deviceUtil.getBoard());

        utilities.addProperty(properties, "Display Version", deviceUtil.getDisplayVersion());

        utilities.addProperty(properties, "Build Host", deviceUtil.getBuildHost());

        utilities.addProperty(properties, "Build Time", deviceUtil.getBuildTime(), 1, "sec");

        utilities.addProperty(properties, "Build User", deviceUtil.getBuildUser());

        utilities.addProperty(properties, "Serial Number", deviceUtil.getSerial());

        utilities.addProperty(properties, "OS Version", deviceUtil.getOSVersion());

        utilities.addProperty(properties, "Language", deviceUtil.getLanguage());

        utilities.addProperty(properties, "Sdk Version", deviceUtil.getSdkVersion(), 1, "");

        utilities.addProperty(properties, "Screen Density", deviceUtil.getScreenDensity(activity));

        utilities.addProperty(properties, "Screen Height", deviceUtil.getScreenHeight(activity), 1 , "Pixels");

        utilities.addProperty(properties, "Screen Width", deviceUtil.getScreenWidth(activity), 1 , "Pixels");

        utilities.addProperty(properties, "Version Name", deviceUtil.getVersionName(activity));

        utilities.addProperty(properties, "Version Code", deviceUtil.getVersionCode(activity));

        utilities.addProperty(properties, "Package Name", deviceUtil.getPackageName(activity));

        utilities.addProperty(properties, "Activity Name", deviceUtil.getActivityName(activity));

        utilities.addProperty(properties, "App Name", deviceUtil.getAppName(activity));

        utilities.addProperty(properties, "Is Running On Emulator", deviceUtil.isRunningOnEmulator());

        utilities.addProperty(properties, "Device Ringer Mode", deviceUtil.getDeviceRingerMode(activity));

        utilities.addProperty(properties, "Is Device Rooted", deviceUtil.isDeviceRooted());

        utilities.addProperty(properties, "Android Id", deviceUtil.getAndroidId(activity));

        utilities.addProperty(properties, "Install Source", deviceUtil.getInstallSource(activity));

        utilities.addProperty(properties, "User Agent", deviceUtil.getUserAgent(activity));

        utilities.addProperty(properties, "GSF Id", deviceUtil.getGSFId(activity));

        utilities.addProperty(properties, "Is App Installed", deviceUtil.isAppInstalled(deviceUtil.getPackageName(activity), activity));

        return properties;
    }

}












