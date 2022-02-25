package com.example.smscontentprovider

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       if (ContextCompat.checkSelfPermission(this , android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
       {
           ActivityCompat.requestPermissions(this , arrayOf(android.Manifest.permission.READ_SMS) ,11)
       }
       else
       {
           TODO()
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
}