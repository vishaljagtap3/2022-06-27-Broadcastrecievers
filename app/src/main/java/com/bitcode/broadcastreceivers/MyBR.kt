package com.bitcode.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBR : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent : Intent?) {

        if(intent != null && intent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            var isOn = intent.getBooleanExtra( "state", false)
            Toast.makeText(
                context!!,
                if(isOn) "Airplane Mode On!" else "Airplane Mode OFF",
                Toast.LENGTH_LONG
            ).show()
        }
        else {
            Toast.makeText(context!!, intent!!.action, Toast.LENGTH_LONG).show()
        }
    }

}