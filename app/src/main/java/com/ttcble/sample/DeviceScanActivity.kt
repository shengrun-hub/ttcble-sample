package com.ttcble.sample

import com.ble.ble.scan.LeScanResult
import com.ttcble.leui.ScanActivity
import com.ttcble.leui.entity.LEDevice

class DeviceScanActivity : ScanActivity() {

    override fun onFilterDevice(scanResult: LeScanResult): Boolean {
        scanResult.leScanRecord?.serviceUuids?.also {
            if (it.isNotEmpty() && it[0].toString() == "00001000-0000-1000-8000-00805f9b34fb") {
                return true
            }
        }
        return false
    }


    override fun onDeviceClick(device: LEDevice) {
        startDeviceActivity(device, MainActivity::class.java)
    }
}