package com.ttcble.sample

import com.ttcble.leui.DeviceActivity
import com.ttcble.leui.DeviceFragment

class MainActivity : DeviceActivity() {

    override fun createFragment(): DeviceFragment {
        return MainFragment()
    }
}