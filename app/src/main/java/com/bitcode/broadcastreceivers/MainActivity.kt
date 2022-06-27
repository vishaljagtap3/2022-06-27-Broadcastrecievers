package com.bitcode.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bitcode.broadcastreceivers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var myBr: MyBR? = null

    val brDownload = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.txtPath.setText(
                intent?.getStringExtra("path")
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*registerReceiver(
            brDownload,
            IntentFilter("in.bitcode.download.COMPLETE")
        )*/

        binding.btnRegisterDownload.setOnClickListener {
            registerReceiver(
                brDownload,
                IntentFilter("in.bitcode.download.COMPLETE")
            )
        }
        binding.btnRegister.setOnClickListener {

            myBr = MyBR()

            var intentFilter = IntentFilter(Intent.ACTION_BATTERY_LOW)
            intentFilter.addAction(Intent.ACTION_WALLPAPER_CHANGED)
            intentFilter.addAction(Intent.ACTION_LOCALE_CHANGED)
            intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            intentFilter.addAction("in.bitcode.download.COMPLETE")

            registerReceiver(myBr, intentFilter)
        }

        binding.btnUnregister.setOnClickListener {
            unregisterReceiver(myBr)
        }

    }

    override fun onDestroy() {
        unregisterReceiver(brDownload)
        super.onDestroy()
    }
}