package com.luckchoudog.nordicsemi.blutoothscanner;

import java.util.List;

/**
 * Bluetooth LE scan callbacks. Scan results are reported using these callbacks.
 */
public abstract class ScanCallback {
	/**
	 * Fails to start scan as BLE scan with the same settings is already started by the app.
	 */
	public static final int SCAN_FAILED_ALREADY_STARTED = 1;

	/**
	 * Fails to start scan as app cannot be registered.
	 */
	public static final int SCAN_FAILED_APPLICATION_REGISTRATION_FAILED = 2;

	/**
	 * Fails to start scan due an internal error
	 */
	public static final int SCAN_FAILED_INTERNAL_ERROR = 3;

	/**
	 * Fails to start power optimized scan as this feature is not supported.
	 */
	public static final int SCAN_FAILED_FEATURE_UNSUPPORTED = 4;

	/**
	 * Fails to start scan as it is out of hardware resources.
	 */
	public static final int SCAN_FAILED_OUT_OF_HARDWARE_RESOURCES = 5;

	/**
	 * Callback when a BLE advertisement has been found.
	 *
	 * @param callbackType Determines how this callback was triggered. Could be one of
	 *            {@link ScanSettings#CALLBACK_TYPE_ALL_MATCHES},
	 *            {@link ScanSettings#CALLBACK_TYPE_FIRST_MATCH} or
	 *            {@link ScanSettings#CALLBACK_TYPE_MATCH_LOST}
	 * @param result A Bluetooth LE scan result.
	 */
	public void onScanResult(int callbackType, ScanResult result) {
	}

	/**
	 * Callback when batch results are delivered.
	 *
	 * @param results List of scan results that are previously scanned.
	 */
	public void onBatchScanResults(List<ScanResult> results) {
	}

	/**
	 * Callback when scan could not be started.
	 *
	 * @param errorCode Error code (one of SCAN_FAILED_*) for scan failure.
	 */
	public void onScanFailed(int errorCode) {
	}
}
