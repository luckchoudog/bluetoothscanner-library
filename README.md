# [https://github.com/luckchoudog/bluetoothscanner-library](https://github.com/luckchoudog/bluetoothscanner-library)
## HOW TO USE：
### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

    allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
		}
	}
### Step 2. Add the dependency
	dependencies {
	        compile 'com.github.luckchoudog:bluetoothscanner-library:-SNAPSHOT'
	}
##### NOTE  ：Need dependence
    compile 'com.android.support:support-annotations:25.0.0'
## EXAMPLE：
demo 0:

        final BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();
        scanner.startScan(scanCallback);
        
demo 1:

        final BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();
		final ScanSettings settings = new ScanSettings.Builder()
		    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
		    .setReportDelay(1000)
		    .setUseHardwareBatchingIfSupported(false)
		    .build();
		final List<ScanFilter> filters = new ArrayList<>();
		filters.add(new ScanFilter.Builder().setServiceUuid(mUuid).build());
		scanner.startScan(filters, settings, scanCallback);

## ScanFilter :
### setDeviceName(String deviceName)
Set filter on device name.
### setDeviceAddress(String deviceAddress) 
Set filter on device address.The device Bluetooth address for the filter. It needs to be in the format of "01:02:03:AB:CD:EF". The device address can be validated using {BluetoothAdapter.checkBluetoothAddress(deviceAddress)}
### setServiceUuid(ParcelUuid serviceUuid) 
Set filter on service uuid.
### setServiceUuid(ParcelUuid serviceUuid, ParcelUuid uuidMask)
Set filter on partial service uuid. The uuidMask is the bit mask for the serviceUuid. Set any bit in the mask to 1 to indicate a match is needed for the bit in serviceUuid, and 0 to ignore that bit.
### setServiceData(ParcelUuid serviceDataUuid, byte[] serviceData) 
Set filtering on service data.
### setServiceData(ParcelUuid serviceDataUuid, byte[] serviceData, byte[] serviceDataMask)
et partial filter on service data. For any bit in the mask, set it to 1 if it needs to match the one in service data, otherwise set it to 0 to ignore that bit. The serviceDataMask must have the same length of the serviceData.
### setManufacturerData(int manufacturerId, byte[] manufacturerData)
Set filter on on manufacturerData. A negative manufacturerId is considered as invalid id. Note the first two bytes of the manufacturerData is the manufacturerId.
### setManufacturerData(int manufacturerId, byte[] manufacturerData, byte[] manufacturerDataMask)
Set filter on partial manufacture data. For any bit in the mask, set it the 1 if it needs to match the one in manufacturer data, otherwise set it to 0. The manufacturerDataMask must have the same length of manufacturerData.

## ScanSettings :
### setScanMode(int scanMode)
Set scan mode for Bluetooth LE scan. The scan mode can be one of {ScanSettings.SCAN_MODE_LOW_POWER, ScanSettings.SCAN_MODE_BALANCED , ScanSettings.SCAN_MODE_LOW_LATENCY}.
    
    /**
	 * A special Bluetooth LE scan mode. Applications using this scan mode will passively listen for
	 * other scan results without starting BLE scans themselves.
	 */
	public static final int SCAN_MODE_OPPORTUNISTIC = -1;

	/**
	 * Perform Bluetooth LE scan in low power mode. This is the default scan mode as it consumes the
	 * least power.
	 */
	public static final int SCAN_MODE_LOW_POWER = 0;

	/**
	 * Perform Bluetooth LE scan in balanced power mode. Scan results are returned at a rate that
	 * provides a good trade-off between scan frequency and power consumption.
	 */
	public static final int SCAN_MODE_BALANCED = 1;

	/**
	 * Scan using highest duty cycle. It's recommended to only use this mode when the application is
	 * running in the foreground.
	 */
	public static final int SCAN_MODE_LOW_LATENCY = 2;
	
### setCallbackType(int callbackType)
Set callback type for Bluetooth LE scan.
### setReportDelay(long reportDelayMillis)
Set report delay timestamp for Bluetooth LE scan. ReportDelay must be > 0.
### setNumOfMatches(int numOfMatches)
Set the number of matches for Bluetooth LE scan filters hardware match.The num of matches can be one of{ScanSettings.MATCH_NUM_ONE_ADVERTISEMENT , ScanSettings.MATCH_NUM_FEW_ADVERTISEMENT , ScanSettings.MATCH_NUM_MAX_ADVERTISEMENT}
    	
    /**
	 * Match one advertisement per filter
	 */
	public static final int MATCH_NUM_ONE_ADVERTISEMENT = 1;

	/**
	 * Match few advertisement per filter, depends on current capability and availibility of
	 * the resources in hw
	 */
	public static final int MATCH_NUM_FEW_ADVERTISEMENT = 2;

	/**
	 * Match as many advertisement per filter as hw could allow, depends on current
	 * capability and availability of the resources in hw
	 */
	public static final int MATCH_NUM_MAX_ADVERTISEMENT = 3;

### setMatchMode(int matchMode) 
Set match mode for Bluetooth LE scan filters hardware match. The match mode can be one of{ScanSettings.MATCH_MODE_AGGRESSIVE , ScanSettings.MATCH_MODE_STICKY}
### setUseHardwareFilteringIfSupported(boolean use)
Several phones may have some issues when it comes to offloaded filtering. Even if it should be supported, it may not work as expected. It has been observed for example, that setting 2 filters with different devices addresses on Nexus 6 with Lollipop gives no callbacks if one or both devices advertise. See [https://code.google.com/p/android/issues/detail?id=181561](https://code.google.com/p/android/issues/detail?id=181561 "").<p> * True to enable (default) hardware offload filtering.</p>
<p> * If false a compat software filtering will be used (uses much more resources).</p>
### setUseHardwareBatchingIfSupported(boolean use) 
Some devices, for example Samsung S6 and S6 Edge with Lollipop, return always the same RSSI value for all devices if offloaded batching is used. Batching may also be emulated using a compat mechanism - a periodically called timer. Timer approach requires more resources but reports devices in constant delays and works on devices that does not support offloaded batching. In comparison, when setReportDelay(..) is called with parameter 1000 the standard, hardware triggered callback will be called every 1500ms +-200ms.
<p> * True to enable (default) hardware offloaded batching if they are supported. </p><p> * False to always use compat mechanism.</p>
### setUseHardwareCallbackTypesIfSupported(boolean use)
This method may be used when callback type is set to a value different than {@link #CALLBACK_TYPE_ALL_MATCHES}. When disabled, the Scanner Compat itself will take care of reporting first match and match lost. The compat behaviour may differ from the one natively supported on Android Marshmallow.  Also, in compat mode values set by setMatchMode(int) and setNumOfMatches(int) are ignored. Instead use setMatchOptions(long, long) to set timer options.
<p> * Use true to enable (default) the offloaded match reporting if hardware supports it.</p><p> * False to enable compat implementation.</p>
### setPowerSave(final long scanInterval, final long restInterval)
Pre-Lollipop scanning requires a wakelock and the CPU cannot go to sleep. To conserve power we can optionally scan for a certain duration (scan interval) and then rest for a time before starting scanning again. Won't affect Lollipop or later devices.
### setMatchOptions(final long deviceTimeoutMillis, final long taskIntervalMillis)
The match options are used when the callback type has been set to ScanSettings.CALLBACK_TYPE_FIRST_MATCH or  ScanSettings.CALLBACK_TYPE_MATCH_LOST and hardware does not support those types. In that case  BluetoothLeScannerCompat starts a task that runs periodically and calls ScanCallback.onScanResult(int, ScanResult) with type CALLBACK_TYPE_MATCH_LOST if a device has not been seen for at least given time.

    /**
	 * Trigger a callback for every Bluetooth advertisement found that matches the filter criteria.
	 * If no filter is active, all advertisement packets are reported.
	 */
	public static final int CALLBACK_TYPE_ALL_MATCHES = 1;

	/**
	 * A result callback is only triggered for the first advertisement packet received that matches
	 * the filter criteria.
	 */
	public static final int CALLBACK_TYPE_FIRST_MATCH = 2;

	/**
	 * Receive a callback when advertisements are no longer received from a device that has been
	 * previously reported by a first match callback.
	 */
	public static final int CALLBACK_TYPE_MATCH_LOST = 4;

