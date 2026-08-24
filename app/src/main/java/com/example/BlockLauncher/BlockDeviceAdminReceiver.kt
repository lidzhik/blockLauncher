package com.example.BlockLauncher

import android.app.admin.DeviceAdminReceiver
import android.content.ComponentName
import android.content.Context

class BlockDeviceAdminReceiver : DeviceAdminReceiver() {

    override fun onEnabled(context: Context, intent: android.content.Intent) {
        super.onEnabled(context, intent)
    }

    override fun onDisabled(context: Context, intent: android.content.Intent) {
        super.onDisabled(context, intent)
    }
    companion object {
        fun getComponentName(context: Context): ComponentName {
            return ComponentName(context, DeviceAdminReceiver::class.java)
        }
    }
}