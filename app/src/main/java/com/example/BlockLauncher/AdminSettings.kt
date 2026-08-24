package com.example.BlockLauncher

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.UserManager
import android.provider.Settings
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt

class AdminSettings : AppCompatActivity() {

    private val ownerPassword = "1111"
    private lateinit var devicePolicyManager: DevicePolicyManager
    private lateinit var adminComponent: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_settings)

        val statusOwner: TextView = findViewById(R.id.statusOwner)
        statusOwner.text = getString(R.string.status_owner_no)
        statusOwner.setBackgroundColor("#FF0000".toColorInt())

        val checkboxSetCameraDisabled: CheckBox = findViewById(R.id.checkboxSetCameraDisabled)
        val checkboxSetKeyguardDisabled: CheckBox = findViewById(R.id.checkboxSetKeyguardDisabled)
        val checkboxSetStatusBarDisabled: CheckBox = findViewById(R.id.checkboxSetStatusBarDisabled)
        val checkboxDisallowAdjustVolume: CheckBox = findViewById(R.id.checkboxDisallowAdjustVolume)
        val checkboxDisallowBluetooth: CheckBox = findViewById(R.id.checkboxDisallowBluetooth)
        val checkboxDisallowAddUser: CheckBox = findViewById(R.id.checkboxDisallowAddUser)
        val checkboxDisallowAirplaneMode: CheckBox = findViewById(R.id.checkboxDisallowAirplaneMode)
        val checkboxDisallowAppsControl: CheckBox = findViewById(R.id.checkboxDisallowAppsControl)
        val checkboxDisallowConfigCredentials: CheckBox = findViewById(R.id.checkboxDisallowConfigCredentials)
        val checkboxDisallowConfigDateTime: CheckBox = findViewById(R.id.checkboxDisallowConfigDateTime)
        val checkboxDisallowConfigLocale: CheckBox = findViewById(R.id.checkboxDisallowConfigLocale)
        val checkboxDisallowConfigLocation: CheckBox = findViewById(R.id.checkboxDisallowConfigLocation)
        val checkboxDisallowConfigMobileNetworks: CheckBox = findViewById(R.id.checkboxDisallowConfigMobileNetworks)
        val checkboxDisallowConfigPrivateDns: CheckBox = findViewById(R.id.checkboxDisallowConfigPrivateDns)
        val checkboxDisallowConfigScreenTimeout: CheckBox = findViewById(R.id.checkboxDisallowConfigScreenTimeout)
        val checkboxDisallowConfigTethering: CheckBox = findViewById(R.id.checkboxDisallowConfigTethering)
        val checkboxDisallowConfigVpn: CheckBox = findViewById(R.id.checkboxDisallowConfigVpn)
        val checkboxDisallowConfigWifi: CheckBox = findViewById(R.id.checkboxDisallowConfigWifi)
        val checkboxDisallowContentCapture: CheckBox = findViewById(R.id.checkboxDisallowContentCapture)
        val checkboxDisallowContentSuggestions: CheckBox = findViewById(R.id.checkboxDisallowContentSuggestions)
        val checkboxDisallowDataRoaming: CheckBox = findViewById(R.id.checkboxDisallowDataRoaming)
        val checkboxDisallowDebuggingFeatures: CheckBox = findViewById(R.id.checkboxDisallowDebuggingFeatures)
        val checkboxDisallowFactoryReset: CheckBox = findViewById(R.id.checkboxDisallowFactoryReset)
        val checkboxDisallowInstallApps: CheckBox = findViewById(R.id.checkboxDisallowInstallApps)
        val checkboxDisallowInstallUnknownSources: CheckBox = findViewById(R.id.checkboxDisallowInstallUnknownSources)
        val checkboxDisallowInstallUnknownSourcesGlobally: CheckBox = findViewById(R.id.checkboxDisallowInstallUnknownSourcesGlobally)
        val checkboxDisallowModifyAccounts: CheckBox = findViewById(R.id.checkboxDisallowModifyAccounts)
        val checkboxDisallowMountPhysicalMedia: CheckBox = findViewById(R.id.checkboxDisallowMountPhysicalMedia)
        val checkboxDisallowNetworkReset: CheckBox = findViewById(R.id.checkboxDisallowNetworkReset)
        val checkboxDisallowOutgoingBeam: CheckBox = findViewById(R.id.checkboxDisallowOutgoingBeam)
        val checkboxDisallowOutgoingCalls: CheckBox = findViewById(R.id.checkboxDisallowOutgoingCalls)
        val checkboxDisallowRemoveUser: CheckBox = findViewById(R.id.checkboxDisallowRemoveUser)
        val checkboxDisallowSafeBoot: CheckBox = findViewById(R.id.checkboxDisallowSafeBoot)
        val checkboxDisallowSetUserIcon: CheckBox = findViewById(R.id.checkboxDisallowSetUserIcon)
        val checkboxDisallowSetWallpaper: CheckBox = findViewById(R.id.checkboxDisallowSetWallpaper)
        val checkboxDisallowShareLocation: CheckBox = findViewById(R.id.checkboxDisallowShareLocation)
        val checkboxDisallowSms: CheckBox = findViewById(R.id.checkboxDisallowSms)
        val checkboxDisallowUninstallApps: CheckBox = findViewById(R.id.checkboxDisallowUninstallApps)
        val checkboxDisallowUsbFileTransfer: CheckBox = findViewById(R.id.checkboxDisallowUsbFileTransfer)
        val accessPassword: EditText = findViewById(R.id.accessPassword)
        val setRecommended: Button = findViewById(R.id.setRecommended)
        val setClear: Button = findViewById(R.id.setClear)
        val saveSettings: Button = findViewById(R.id.saveSettings)

        devicePolicyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
        adminComponent = ComponentName(this, BlockDeviceAdminReceiver::class.java)

        if (devicePolicyManager.isAdminActive(adminComponent)) {

            statusOwner.text = getString(R.string.status_owner_yes)
            statusOwner.setBackgroundColor("#077770".toColorInt())

            devicePolicyManager.setOrganizationName(adminComponent,R.string.launcherOrgName.toString())
//---------------------------------------------
            //Disable Sound
//            val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
//            audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
            //Set Screen Off Time
            devicePolicyManager.setSystemSetting(adminComponent,Settings.System.SCREEN_OFF_TIMEOUT,"120000")
            //Set TimeZone
            devicePolicyManager.setTimeZone(adminComponent, "Europe/Moscow")
            //Disable Auto Brightness
            devicePolicyManager.setSystemSetting(adminComponent,Settings.System.SCREEN_BRIGHTNESS_MODE,Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL.toString())
//---------------------------------------------
            val intentFilter = IntentFilter(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                addCategory(Intent.CATEGORY_DEFAULT)
            }
            val launcherComponent = ComponentName("com.example.blocklauncher", "com.example.blocklauncher.MainActivity")
            devicePolicyManager.addPersistentPreferredActivity(adminComponent, intentFilter, launcherComponent)

            val packagesToSuspend = arrayOf(
                "com.android.fmradio",
                "com.android.soundrecorder",
                "com.google.android.apps.adm",
                "com.google.android.apps.docs",
                "com.google.android.apps.maps",
                "com.google.android.apps.photos",
                "com.google.android.apps.safetyhub",
                "com.google.android.apps.tachyon",
                "com.google.android.calendar",
                "com.google.android.gm",
                "com.blackview.bvworkspace",
                "com.blackview.surfline",
                "com.blackview.userfeedback",
                // "com.google.android.apps.nbu.files",
                "com.google.android.googlequicksearchbox"
            )

            devicePolicyManager.setPackagesSuspended(adminComponent, packagesToSuspend, true)

            devicePolicyManager.setApplicationHidden(adminComponent, "club.dexp.minimarket2", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "ru.vk.store", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "cn.wps.moffice_eng", true)

            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.ai.doki", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.ai.imagex", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.ai.soundle", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.ai.vidgen", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.dokegamecenter", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.gamemode", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.frozenapp", true)

            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.easytrans", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.filetrans", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.note", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.weather", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.userfeedback", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.blackview.userguide", true)

            devicePolicyManager.setApplicationHidden(adminComponent, "com.google.android.apps.fitness", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.google.android.apps.googleassistant", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.google.android.apps.translate", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.google.android.apps.youtube.music", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.google.android.videos", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.google.android.youtube", true)
            devicePolicyManager.setApplicationHidden(adminComponent, "com.google.android.keep", true)

            checkboxSetCameraDisabled.isChecked = devicePolicyManager.getCameraDisabled(null)
            checkboxSetKeyguardDisabled.isChecked = devicePolicyManager.getKeyguardDisabledFeatures(adminComponent) <= 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                checkboxSetStatusBarDisabled.isChecked = devicePolicyManager.isStatusBarDisabled
            } else {
                checkboxSetStatusBarDisabled.isChecked = false
            }
            val restrictions = devicePolicyManager.getUserRestrictions(adminComponent)
            checkboxDisallowAdjustVolume.isChecked = restrictions.getBoolean(UserManager.DISALLOW_ADJUST_VOLUME)
            checkboxDisallowBluetooth.isChecked = restrictions.getBoolean(UserManager.DISALLOW_BLUETOOTH)
            checkboxDisallowAddUser.isChecked = restrictions.getBoolean(UserManager.DISALLOW_ADD_USER)
            checkboxDisallowAirplaneMode.isChecked = restrictions.getBoolean(UserManager.DISALLOW_AIRPLANE_MODE)
            checkboxDisallowAppsControl.isChecked = restrictions.getBoolean(UserManager.DISALLOW_APPS_CONTROL)
            checkboxDisallowConfigCredentials.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_CREDENTIALS)
            checkboxDisallowConfigDateTime.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_DATE_TIME)
            checkboxDisallowConfigLocale.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_LOCALE)
            checkboxDisallowConfigLocation.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_LOCATION)
            checkboxDisallowConfigMobileNetworks.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_MOBILE_NETWORKS)
            checkboxDisallowConfigPrivateDns.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_PRIVATE_DNS)
            checkboxDisallowConfigScreenTimeout.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_SCREEN_TIMEOUT)
            checkboxDisallowConfigTethering.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_TETHERING)
            checkboxDisallowConfigVpn.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_VPN)
            checkboxDisallowConfigWifi.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONFIG_WIFI)
            checkboxDisallowContentCapture.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONTENT_CAPTURE)
            checkboxDisallowContentSuggestions.isChecked = restrictions.getBoolean(UserManager.DISALLOW_CONTENT_SUGGESTIONS)
            checkboxDisallowDataRoaming.isChecked = restrictions.getBoolean(UserManager.DISALLOW_DATA_ROAMING)
            checkboxDisallowDebuggingFeatures.isChecked = restrictions.getBoolean(UserManager.DISALLOW_DEBUGGING_FEATURES)
            checkboxDisallowFactoryReset.isChecked = restrictions.getBoolean(UserManager.DISALLOW_FACTORY_RESET)
            checkboxDisallowInstallApps.isChecked = restrictions.getBoolean(UserManager.DISALLOW_INSTALL_APPS)
            checkboxDisallowInstallUnknownSources.isChecked = restrictions.getBoolean(UserManager.DISALLOW_INSTALL_UNKNOWN_SOURCES)
            checkboxDisallowInstallUnknownSourcesGlobally.isChecked = restrictions.getBoolean(UserManager.DISALLOW_INSTALL_UNKNOWN_SOURCES_GLOBALLY)
            checkboxDisallowModifyAccounts.isChecked = restrictions.getBoolean(UserManager.DISALLOW_MODIFY_ACCOUNTS)
            checkboxDisallowMountPhysicalMedia.isChecked = restrictions.getBoolean(UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA)
            checkboxDisallowNetworkReset.isChecked = restrictions.getBoolean(UserManager.DISALLOW_NETWORK_RESET)
            checkboxDisallowOutgoingBeam.isChecked = restrictions.getBoolean(UserManager.DISALLOW_OUTGOING_BEAM)
            checkboxDisallowOutgoingCalls.isChecked = restrictions.getBoolean(UserManager.DISALLOW_OUTGOING_CALLS)
            checkboxDisallowRemoveUser.isChecked = restrictions.getBoolean(UserManager.DISALLOW_REMOVE_USER)
            checkboxDisallowSafeBoot.isChecked = restrictions.getBoolean(UserManager.DISALLOW_SAFE_BOOT)
            checkboxDisallowSetUserIcon.isChecked = restrictions.getBoolean(UserManager.DISALLOW_SET_USER_ICON)
            checkboxDisallowSetWallpaper.isChecked = restrictions.getBoolean(UserManager.DISALLOW_SET_WALLPAPER)
            checkboxDisallowShareLocation.isChecked = restrictions.getBoolean(UserManager.DISALLOW_SHARE_LOCATION)
            checkboxDisallowSms.isChecked = restrictions.getBoolean(UserManager.DISALLOW_SMS)
            checkboxDisallowUninstallApps.isChecked = restrictions.getBoolean(UserManager.DISALLOW_UNINSTALL_APPS)
            checkboxDisallowUsbFileTransfer.isChecked = restrictions.getBoolean(UserManager.DISALLOW_USB_FILE_TRANSFER)
        }

        setRecommended.setOnClickListener {
            checkboxSetCameraDisabled.isChecked = false
            checkboxDisallowConfigWifi.isChecked = true
            checkboxDisallowBluetooth.isChecked = true
            checkboxDisallowConfigLocation.isChecked = true
            checkboxDisallowShareLocation.isChecked = true
            checkboxDisallowAirplaneMode.isChecked = true
            checkboxDisallowConfigMobileNetworks.isChecked = true
            checkboxDisallowOutgoingBeam.isChecked = true
            checkboxDisallowConfigTethering.isChecked = true
            checkboxDisallowConfigScreenTimeout.isChecked = true
            checkboxDisallowSetWallpaper.isChecked = true
            checkboxSetKeyguardDisabled.isChecked = true
            checkboxSetStatusBarDisabled.isChecked = true
            checkboxDisallowAdjustVolume.isChecked = true
            checkboxDisallowSetUserIcon.isChecked = true
            checkboxDisallowAddUser.isChecked = true
            checkboxDisallowModifyAccounts.isChecked = true
            checkboxDisallowRemoveUser.isChecked = true
            checkboxDisallowConfigCredentials.isChecked = true
            checkboxDisallowConfigPrivateDns.isChecked = true
            checkboxDisallowConfigVpn.isChecked = true
            checkboxDisallowConfigDateTime.isChecked = true
            checkboxDisallowConfigLocale.isChecked = true
            checkboxDisallowContentCapture.isChecked = true
            checkboxDisallowContentSuggestions.isChecked = true
            checkboxDisallowSms.isChecked = true
            checkboxDisallowOutgoingCalls.isChecked = true
            checkboxDisallowInstallApps.isChecked = true
            checkboxDisallowInstallUnknownSources.isChecked = true
            checkboxDisallowInstallUnknownSourcesGlobally.isChecked = true
            checkboxDisallowAppsControl.isChecked = true
            checkboxDisallowUninstallApps.isChecked = true
            checkboxDisallowMountPhysicalMedia.isChecked = true
            checkboxDisallowNetworkReset.isChecked = true
            checkboxDisallowSafeBoot.isChecked = true
            checkboxDisallowDataRoaming.isChecked = true
            checkboxDisallowDebuggingFeatures.isChecked = true
            checkboxDisallowFactoryReset.isChecked = true
            checkboxDisallowUsbFileTransfer.isChecked = false
        }

        setClear.setOnClickListener {
            checkboxSetCameraDisabled.isChecked = false
            checkboxDisallowConfigWifi.isChecked = false
            checkboxDisallowBluetooth.isChecked = false
            checkboxDisallowConfigLocation.isChecked = false
            checkboxDisallowShareLocation.isChecked = false
            checkboxDisallowAirplaneMode.isChecked = false
            checkboxDisallowConfigMobileNetworks.isChecked = false
            checkboxDisallowOutgoingBeam.isChecked = false
            checkboxDisallowConfigTethering.isChecked = false
            checkboxDisallowConfigScreenTimeout.isChecked = false
            checkboxDisallowSetWallpaper.isChecked = false
            checkboxSetKeyguardDisabled.isChecked = false
            checkboxSetStatusBarDisabled.isChecked = false
            checkboxDisallowAdjustVolume.isChecked = false
            checkboxDisallowSetUserIcon.isChecked = false
            checkboxDisallowAddUser.isChecked = false
            checkboxDisallowModifyAccounts.isChecked = false
            checkboxDisallowRemoveUser.isChecked = false
            checkboxDisallowConfigCredentials.isChecked = false
            checkboxDisallowConfigPrivateDns.isChecked = false
            checkboxDisallowConfigVpn.isChecked = false
            checkboxDisallowConfigDateTime.isChecked = false
            checkboxDisallowConfigLocale.isChecked = false
            checkboxDisallowContentCapture.isChecked = false
            checkboxDisallowContentSuggestions.isChecked = false
            checkboxDisallowSms.isChecked = false
            checkboxDisallowOutgoingCalls.isChecked = false
            checkboxDisallowInstallApps.isChecked = false
            checkboxDisallowInstallUnknownSources.isChecked = false
            checkboxDisallowInstallUnknownSourcesGlobally.isChecked = false
            checkboxDisallowAppsControl.isChecked = false
            checkboxDisallowUninstallApps.isChecked = false
            checkboxDisallowMountPhysicalMedia.isChecked = false
            checkboxDisallowNetworkReset.isChecked = false
            checkboxDisallowSafeBoot.isChecked = false
            checkboxDisallowDataRoaming.isChecked = false
            checkboxDisallowDebuggingFeatures.isChecked = false
            checkboxDisallowFactoryReset.isChecked = false
            checkboxDisallowUsbFileTransfer.isChecked = false
        }

        saveSettings.setOnClickListener {
            if (accessPassword.text.toString().trim() == ownerPassword) {

                devicePolicyManager.setCameraDisabled(adminComponent,checkboxSetCameraDisabled.isChecked)
                devicePolicyManager.setKeyguardDisabled(adminComponent,checkboxSetKeyguardDisabled.isChecked)
                devicePolicyManager.setStatusBarDisabled(adminComponent,checkboxSetStatusBarDisabled.isChecked)

                if (checkboxDisallowAdjustVolume.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_ADJUST_VOLUME)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_ADJUST_VOLUME)
                }
                if (checkboxDisallowBluetooth.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_BLUETOOTH)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_BLUETOOTH)
                }
                if (checkboxDisallowAddUser.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_ADD_USER)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_ADD_USER)
                }
                if (checkboxDisallowAirplaneMode.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_AIRPLANE_MODE)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_AIRPLANE_MODE)
                }
                if (checkboxDisallowAppsControl.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_APPS_CONTROL)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_APPS_CONTROL)
                }
                if (checkboxDisallowConfigCredentials.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_CREDENTIALS)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_CREDENTIALS)
                }
                if (checkboxDisallowConfigDateTime.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_DATE_TIME)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_DATE_TIME)
                }
                if (checkboxDisallowConfigLocale.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_LOCALE)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_LOCALE)
                }
                if (checkboxDisallowConfigLocation.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_LOCATION)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_LOCATION)
                }
                if (checkboxDisallowConfigMobileNetworks.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_MOBILE_NETWORKS)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_MOBILE_NETWORKS)
                }
                if (checkboxDisallowConfigPrivateDns.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_PRIVATE_DNS)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_PRIVATE_DNS)
                }
                if (checkboxDisallowConfigScreenTimeout.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_SCREEN_TIMEOUT)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_SCREEN_TIMEOUT)
                }
                if (checkboxDisallowConfigTethering.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_TETHERING)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_TETHERING)
                }
                if (checkboxDisallowConfigVpn.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_VPN)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_VPN)
                }
                if (checkboxDisallowConfigWifi.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_WIFI)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_WIFI)
                }
                if (checkboxDisallowContentCapture.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONTENT_CAPTURE)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONTENT_CAPTURE)
                }
                if (checkboxDisallowContentSuggestions.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_CONTENT_SUGGESTIONS)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONTENT_SUGGESTIONS)
                }
                if (checkboxDisallowDataRoaming.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_DATA_ROAMING)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_DATA_ROAMING)
                }
                if (checkboxDisallowDebuggingFeatures.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_DEBUGGING_FEATURES)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_DEBUGGING_FEATURES)
                }
                if (checkboxDisallowFactoryReset.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_FACTORY_RESET)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_FACTORY_RESET)
                }
                if (checkboxDisallowInstallApps.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_INSTALL_APPS)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_INSTALL_APPS)
                }
                if (checkboxDisallowInstallUnknownSources.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_INSTALL_UNKNOWN_SOURCES)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_INSTALL_UNKNOWN_SOURCES)
                }
                if (checkboxDisallowInstallUnknownSourcesGlobally.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_INSTALL_UNKNOWN_SOURCES_GLOBALLY)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_INSTALL_UNKNOWN_SOURCES_GLOBALLY)
                }
                if (checkboxDisallowModifyAccounts.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_MODIFY_ACCOUNTS)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_MODIFY_ACCOUNTS)
                }
                if (checkboxDisallowMountPhysicalMedia.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA)
                }
                if (checkboxDisallowNetworkReset.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_NETWORK_RESET)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_NETWORK_RESET)
                }
                if (checkboxDisallowOutgoingBeam.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_OUTGOING_BEAM)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_OUTGOING_BEAM)
                }
                if (checkboxDisallowOutgoingCalls.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_OUTGOING_CALLS)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_OUTGOING_CALLS)
                }
                if (checkboxDisallowRemoveUser.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_REMOVE_USER)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_REMOVE_USER)
                }
                if (checkboxDisallowSafeBoot.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_SAFE_BOOT)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_SAFE_BOOT)
                }
                if (checkboxDisallowSetUserIcon.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_SET_USER_ICON)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_SET_USER_ICON)
                }
                if (checkboxDisallowSetWallpaper.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_SET_WALLPAPER)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_SET_WALLPAPER)
                }
                if (checkboxDisallowShareLocation.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_SHARE_LOCATION)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_SHARE_LOCATION)
                }
                if (checkboxDisallowSms.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_SMS)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_SMS)
                }
                if (checkboxDisallowUninstallApps.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_UNINSTALL_APPS)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_UNINSTALL_APPS)
                }
                if (checkboxDisallowUsbFileTransfer.isChecked) {
                    devicePolicyManager.addUserRestriction(adminComponent, UserManager.DISALLOW_USB_FILE_TRANSFER)
                } else {
                    devicePolicyManager.clearUserRestriction(adminComponent, UserManager.DISALLOW_USB_FILE_TRANSFER)
                }
                Toast.makeText(this, R.string.save_settings_yes, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.save_settings_no, Toast.LENGTH_SHORT).show()
            }
        }

    }

}
