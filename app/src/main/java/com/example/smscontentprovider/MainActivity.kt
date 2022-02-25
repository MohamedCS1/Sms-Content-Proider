package com.example.smscontentprovider

import android.app.UiModeManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.provider.Telephony
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        if (ContextCompat.checkSelfPermission(this , android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this , arrayOf(android.Manifest.permission.READ_SMS) ,11)
        }
        else
        {
            Thread(Runnable {

                val smsadapter = SmsAdapter()

                val rv = findViewById<RecyclerView>(R.id.SmsRecyclerView)

                val lm = LinearLayoutManager(this)

                rv.layoutManager = lm

                smsadapter.setList(readSms())

                rv.adapter = smsadapter

            }).start()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 11 && grantResults[0] != PackageManager.PERMISSION_GRANTED)
        {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    private fun readSms(): ArrayList<String> {
        val numberCol = Telephony.TextBasedSmsColumns.ADDRESS
        val textCol = Telephony.TextBasedSmsColumns.BODY
        val typeCol = Telephony.TextBasedSmsColumns.TYPE

        val projection = arrayOf(numberCol, textCol, typeCol)

        val arraysms = arrayListOf<String>()

        val cursor = contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            projection, null, null, null
        )

        val numberColIdx = cursor!!.getColumnIndex(numberCol)
        val textColIdx = cursor.getColumnIndex(textCol)
        val typeColIdx = cursor.getColumnIndex(typeCol)

        while (cursor.moveToNext()) {
            val number = cursor.getString(numberColIdx)
            val text = cursor.getString(textColIdx)
            val type = cursor.getString(typeColIdx)

            arraysms.add(text)

            Log.d("Mess", " $text ")
        }
        cursor.close()

        return arraysms
    }}