package utilities.app.Properties;

import android.app.Activity;
import java.util.ArrayList;
import jagerfield.library.AppUtilities;
import utilities.app.Utilities.Utilities;

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
        Utilities utilities = Utilities.getInstance();

        utilities.addProperty(properties, "Release Build Version", AppUtilities.getDeviceUtil().getReleaseBuildVersion());

        utilities.addProperty(properties, "Device Name", AppUtilities.getDeviceUtil().getDeviceName());

        utilities.addProperty(properties, "Build Version Code Name", AppUtilities.getDeviceUtil().getBuildVersionCodeName());

        utilities.addProperty(properties, "Manufacturer", AppUtilities.getDeviceUtil().getManufacturer());

        utilities.addProperty(properties, "Model", AppUtilities.getDeviceUtil().getModel());

        utilities.addProperty(properties, "Product", AppUtilities.getDeviceUtil().getProduct());

        utilities.addProperty(properties, "Finger print", AppUtilities.getDeviceUtil().getFingerprint());

        utilities.addProperty(properties, "Hardware", AppUtilities.getDeviceUtil().getHardware());

        utilities.addProperty(properties, "RadioVer", AppUtilities.getDeviceUtil().getRadioVer());

        utilities.addProperty(properties, "Device", AppUtilities.getDeviceUtil().getDevice());

        utilities.addProperty(properties, "Board", AppUtilities.getDeviceUtil().getBoard());

        utilities.addProperty(properties, "Display Version", AppUtilities.getDeviceUtil().getDisplayVersion());

        utilities.addProperty(properties, "Build Host", AppUtilities.getDeviceUtil().getBuildHost());

        utilities.addProperty(properties, "Build Time", AppUtilities.getDeviceUtil().getBuildTime(), 1, "sec");

        utilities.addProperty(properties, "Build User", AppUtilities.getDeviceUtil().getBuildUser());

        utilities.addProperty(properties, "Serial Number", AppUtilities.getDeviceUtil().getSerial());

        utilities.addProperty(properties, "OS Version", AppUtilities.getDeviceUtil().getOSVersion());

        utilities.addProperty(properties, "Language", AppUtilities.getDeviceUtil().getLanguage());

        utilities.addProperty(properties, "Sdk Version", AppUtilities.getDeviceUtil().getSdkVersion(), 1, "");

        utilities.addProperty(properties, "Screen Density", AppUtilities.getDeviceUtil().getScreenDensity(activity));

        utilities.addProperty(properties, "Screen Height", AppUtilities.getDeviceUtil().getScreenHeight(activity), 1 , "Pixels");

        utilities.addProperty(properties, "Screen Width", AppUtilities.getDeviceUtil().getScreenWidth(activity), 1 , "Pixels");

        utilities.addProperty(properties, "Version Name", AppUtilities.getDeviceUtil().getVersionName(activity));

        utilities.addProperty(properties, "Version Code", AppUtilities.getDeviceUtil().getVersionCode(activity));

        utilities.addProperty(properties, "Package Name", AppUtilities.getDeviceUtil().getPackageName(activity));

        utilities.addProperty(properties, "Activity Name", AppUtilities.getDeviceUtil().getActivityName(activity));

        utilities.addProperty(properties, "App Name", AppUtilities.getDeviceUtil().getAppName(activity));

        utilities.addProperty(properties, "Is Running On Emulator", AppUtilities.getDeviceUtil().isRunningOnEmulator());

        utilities.addProperty(properties, "Device Ringer Mode", AppUtilities.getDeviceUtil().getDeviceRingerMode(activity));

        utilities.addProperty(properties, "Is Device Rooted", AppUtilities.getDeviceUtil().isDeviceRooted());

        utilities.addProperty(properties, "Android Id", AppUtilities.getDeviceUtil().getAndroidId(activity));

        utilities.addProperty(properties, "Install Source", AppUtilities.getDeviceUtil().getInstallSource(activity));

        utilities.addProperty(properties, "User Agent", AppUtilities.getDeviceUtil().getUserAgent(activity));

        utilities.addProperty(properties, "Is App Installed", AppUtilities.getDeviceUtil().isAppInstalled(AppUtilities.getDeviceUtil().getPackageName(activity), activity));

        utilities.addProperty(properties, "GSF Id", AppUtilities.getDeviceUtil().getGSFId(activity));

        return properties;
    }

}












