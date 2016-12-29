## Android Utilities

While developing Andorid apps, I gathered the functionalities which were repeatedly used and kept them in a utility class. The number of these functions grew, in addition there was need to manage the “Permissions” in a clean easy way. Therefore, I created a library which currently provides the following utilities:

| Utility     |
|:------------|
| Permissions	| 
| Memory 	    | 
| Battery     | 
| Device Info | 
| Network	    | 
| SoftKeyboard|	    | 


## Installation
In the app build.gradle add the following:
  a. Add JitPack repository at the end of repositories 

  ```java
  repositories 
  {
     maven { url 'https://jitpack.io' }
  } 
  
  ```
  b. Add the following dependency
 
  ```java
  dependencies 
  {
     compile 'com.github.Jagerfield:Android-Utilities:v1.0'
  }
  
  ```


<h2>Memory Util</h2>

Accessing a utility fucntion example:
```
AppUtilities.getMemoryUtil().getTotalRAM();

```
Available functions:

| Value      | Function's Name | Returns  |
| :------------- |:-------------| :-----|
| Total RAM      | ```getTotalRAM()```| long |
| Available External Memory Size      | ```getAvailableExternalMemorySize()``` | long |
| Has External SD Card      | ```isHasExternalSDCard()``` | boolean |
| Available Internal Memory Size      | ```getAvailableInternalMemorySize()``` | long |
| Total Internal Memory Size      | ```getTotalInternalMemorySize()``` | long |
| Total External Memory Size      | ```getTotalExternalMemorySize()``` | String |


<h2>Battery Util</h2>

Accessing a utility fucntion example:
```
AppUtilities.getDBatteryUtil().isBatteryPresent();

```
Available functions:

| Value         | Function Name | Returns  |
| :------------- |:-------------| :-----|
| Is Battery Present   |```isBatteryPresent()``` | boolean |
| Battery Temperature      | ```getBatteryTemperature()``` | float |
| Battery Percent      | ```getBatteryPercent()``` | int |
| Is Phone Charging      | ```isPhoneCharging()``` | boolean |
| Battery Health      | ```getBatteryHealth()``` | String |
| Battery Voltage      |```getBatteryVoltage()``` | int |
| Charging Source      | ```getChargingSource()``` | String |
| Battery Technology      | ```getBatteryTechnology()``` | String |



<h2>Device Info Util</h2>

Accessing a utility fucntion example:
```
AppUtilities.getDeviceUtil().getDeviceName();
```
Available functions:

| Value         | Function Name | Returns  |
| :------------- |:-------------| :-----|
| Device Name | ```getDeviceName()``` | String |
| Version Name   | ```getVersionName(activity)``` | String |
| Serial Number| ```getSerial()``` | String |
| Sdk Version   | ```getSdkVersion()``` | int |
| Is Running On Emulator | ```isRunningOnEmulator()``` | boolean |
| Release Build Version  | ```getReleaseBuildVersion()``` | String |
| Package Name   | ```getPackageName(activity)``` | String |
| Build Version Code Name| ```getBuildVersionCodeName``` | String |
| Manufacturer  | ```getManufacturer()``` | String |
| Model    | ```getModel()``` | String |
| Product  | ```getProduct()``` | String |
| Finger print | ```getFingerprint()``` | String |
| Hardware | ```getHardware()``` | String |
| RadioVer | ```getRadioVer()``` | String |
| Device   | ```getDevice()``` | String |
| Board    | ```getBoard()``` | String |
| Display Version  | ```getDisplayVersion()``` | String |
| Build Host  | ```getBuildHost()``` | String |
| Build Time  | ```getBuildTime()``` | long |
| Build User  | ```getBuildUser()``` | String |
| OS Version  | ```getOsVersion()``` | String |
| Language    | ```getLanguage()``` | String |
| Screen Density | ```getScreenDensity(activity)``` | String |
| Screen Height  | ```getScreenHeight(activity)``` | int |
| Screen Width   | ```getScreenWidth(activity)``` | int |
| Version Code   | ```getVersionCode(activity)``` | Integer |
| Activity Name  | ```getActivityName(activity)``` | String |
| App Name  | ```getAppName(activity)``` | String |
| Device Ringer Mode  | ```getDeviceRingerMode(activity)``` | String |
| Is Device Rooted    | ```isDeviceRooted()``` | boolean |
| Android Id   | ```getAndroidId(activity)``` | String |
| Installation Source | ```getInstallSource(activity)``` | String |
| User Agent | ```getUserAgent(activity)``` | String |
| GSF Id | ```getDeviceUtil().getGSFId(activity)``` | String |


<h2>Network Util</h2>

Accessing a utility fucntion example:

```
AppUtilities.getDeviceUtil().getDeviceName();

```
Available functions:
| Value         | Function Name | Returns  |
| :------------- |:-------------| :-----|
| IMEI      | ```getIMEI(activity)``` | String |
| IMSI      | ```getIMSI(activity)``` | String |
| Phone Type      | ```getPhoneType(activity)``` | String |
| Phone Number      | ```getPhoneNumber(activity)``` | String |
| Operator      | ```getOperator(activity)``` | String |
| SIM Serial      | ```getsIMSerial(activity)``` | String |
| Is SIM Locked      | ```isSimNetworkLocked(activity)``` | boolean |
| Is Wifi Enabled      | ```isWifiEnabled(activity)``` | boolean |
| Network Class      | ```getNetworkClass(activity)``` | String |
| Is Nfc Present      | ```isNfcPresent(activity)``` | boolean |
| Is Nfc Enabled      | ```isNfcEnabled(activity)``` | boolean |
| Internet Connection Status      | ```getInternetConnectionStatus(activity)``` | String |
| Bluetooth MAC    | ```getBluetoothMAC(activity)``` | String |
| Wifi Mac Address   | ```getWifiMacAddress(activity)``` | String |




