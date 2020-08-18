package com.ttcble.sample

import android.bluetooth.BluetoothGattCharacteristic
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ble.api.DataUtil
import com.ble.ble.BleCallBack
import com.ttcble.leui.DeviceFragment
import com.ttcble.leui.LeProxy
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : DeviceFragment() {
    companion object {
        private const val TAG = "MainFragment"
    }


    private val mBleCallBack = object : BleCallBack() {

        override fun onDisconnected(address: String) {
            appendLog("Disconnected")
        }

        override fun onServicesDiscovered(address: String) {
            appendLog("onServicesDiscovered")

            LeProxy.instance.enableNotification(address)
        }


        override fun onCharacteristicChanged(address: String, c: BluetoothGattCharacteristic) {
            appendLog(" <<< " + DataUtil.byteArrayToHex(c.value))
        }


        override fun onCharacteristicWrite(
            address: String,
            c: BluetoothGattCharacteristic,
            status: Int
        ) {
            appendLog("status=$status >>> " + DataUtil.byteArrayToHex(c.value))
        }
    }


    private fun appendLog(s: String) {
        Log.i(TAG, s)
        activity?.runOnUiThread {
            logView?.append(Log.DEBUG, s)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LeProxy.instance.addCallback(mBleCallBack)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSend.setOnClickListener {
            //Send data
            val s = edtTxValue.text.toString().trim()
            if (s.isNotEmpty()) {
                try {
                    LeProxy.instance.send(mAddress, DataUtil.hexToByteArray(s))
                } catch (e: Exception) {
                    LeProxy.instance.send(mAddress, s.toByteArray())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LeProxy.instance.removeCallback(mBleCallBack)
    }
}