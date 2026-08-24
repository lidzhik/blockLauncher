package com.example.BlockLauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var secretClickCount = 0
    private var secretMaxClickCount = 5
    private var lastClickTime: Long = 0
    private val adminPassword = "1111"

    private val blacklistedPackages = setOf(
        "com.example.blocklauncher",
        "com.blackview.systemmanager",
        "com.blackview.pictorial.client",
        "com.google.android.gm",
        "com.google.android.apps.tachyon",
        "com.google.android.apps.docs",
        "com.google.android.calendar",
        "com.google.android.apps.maps",
        "com.google.android.apps.adm",
        "com.android.vending",
        "com.android.chrome",
        "com.google.android.contacts",
        "com.blackview.bvworkspace",
        "com.google.android.apps.messaging",
        "com.google.android.apps.photos",
        "com.google.android.dialer",
        "com.yandex.searchapp",
        "com.google.android.googlequicksearchbox",
        "com.android.fmradio",
        "com.android.soundrecorder",
        "com.blackview.surfline",
        "ru.yandex.yandexmaps",
        "com.blackview.useguide",
        "com.google.android.apps.safetyhub",

//        "com.google.android.apps.nbu.files",
//        "com.yandex.browser",
//        "com.android.settings",
//        "com.android.deskclock",
//        "com.android.calculator2",
//        "com.android.camera2",

        "com.android.chrome"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adminLine: TextView = findViewById(R.id.adminLine)
        adminLine.setOnClickListener {
            handleSecretClick()
        }

        val recyclerView: RecyclerView = findViewById(R.id.appsRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 5)

        val installedApps = getInstalledApps().sortedBy { it.appName.lowercase() }
        recyclerView.adapter = AppAdapter(installedApps) { launchIntent ->
            startActivity(launchIntent)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
    private fun handleSecretClick() {
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastClickTime > 500) {
            secretClickCount = 0
        }

        secretClickCount++
        lastClickTime = currentTime

        if (secretClickCount == secretMaxClickCount) {
            secretClickCount = 0
            showPasswordDialog()
        }
    }

    private fun showPasswordDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.accessTitle)
        builder.setMessage(R.string.accessMessage)

        val input = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        val container = FrameLayout(this)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            leftMargin = 50
            rightMargin = 50
        }
        input.layoutParams = params
        container.addView(input)
        builder.setView(container)

        builder.setPositiveButton(R.string.accessButton) { dialog, _ ->
            val enteredPassword = input.text.toString()
            if (enteredPassword == adminPassword) {

                val intent = Intent(this, AdminSettings::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.accessDeied, Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton(R.string.accessCancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun getInstalledApps(): List<AppModel> {
        val appsList = mutableListOf<AppModel>()
        val pm = packageManager

        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfos: List<ResolveInfo> = pm.queryIntentActivities(intent, 0)

        for (info in resolveInfos) {
            val packageName = info.activityInfo.packageName

            if (!blacklistedPackages.contains(packageName)) {

                val launchIntent = pm.getLaunchIntentForPackage(packageName)
                if (launchIntent != null) {

                    val appName = info.loadLabel(pm).toString()

                    appsList.add(
                        AppModel(
                            icon = info.loadIcon(pm),
                            launchIntent = launchIntent,
                            appName = appName
                        )
                    )
                }
            }
        }
        return appsList
    }
}

data class AppModel(
    val icon: Drawable,
    val launchIntent: Intent,
    val appName: String
)
class AppAdapter(
    private val apps: List<AppModel>,
    private val onAppClick: (Intent) -> Unit
) : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconView: ImageView = view.findViewById(R.id.appIcon)
        val nameTextView: TextView = view.findViewById(R.id.appPackageName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.iconView.setImageDrawable(app.icon)

        holder.nameTextView.text = app.appName

        holder.itemView.setOnClickListener { onAppClick(app.launchIntent) }
    }

    override fun getItemCount(): Int = apps.size
}