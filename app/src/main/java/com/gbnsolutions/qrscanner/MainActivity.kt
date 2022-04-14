package com.gbnsolutions.qrscanner

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var btnScanBarcode:Button
    lateinit var ivQRcode:Image
    lateinit var tvResult: TextView

    private val CAMERA_PERMISSION_CODE=123
    private val STORAGE_PERMISSION_CODE=113

    private lateinit var cameralauncher:ActivityResultLauncher<Intent>
    private lateinit var gallerylauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnScanBarcode=findViewById(R.id.btnscanBarcode)
//        ivQRcode=findViewById(R.id.ivQRcode)
        tvResult=findViewById(R.id.tvResult)


        cameralauncher=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            object : ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult?) {
                    TODO("Not yet implemented")
                }

            }
        )
        gallerylauncher=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            object : ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult?) {
                    TODO("Not yet implemented")
                }

            }
        )
        }

//        btnScanBarcode.setOnClickListener {
//            val options= arrayOf("camers","gallery")
//            val builder=AlertDialog(this@MainActivity)
//            builder.setTitle("Pick a option")
//
//            builder.setItems()
//        }

    }

//    override fun onResume() {
//        super.onResume()
//
//        checkPermission(android.Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE)
//    }
//    private fun checkPermission(permission:String,requestCode:Int){
//        if(ContextCompat.checkSelfPermission(this@MainActivity,permission)==PackageManager.PERMISSION_DENIED){
//            //Take Action
//
//            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission),requestCode)
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if(requestCode==CAMERA_PERMISSION_CODE){
//            if(grantResults.isEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                checkPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE,STORAGE_PERMISSION_CODE)
//            }else{
//                Toast.makeText(this@MainActivity,"camers access denied",Toast.LENGTH_SHORT).show()
//            }
//        }else if(requestCode=STORAGE_PERMISSION_CODE){
//            if(!(grantResults.isEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)){
//                Toast.makeText(this@MainActivity,"Storage Permission Denied",Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}