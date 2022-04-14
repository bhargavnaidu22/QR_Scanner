package com.gbnsolutions.qrscanner

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

class DEtails : AppCompatActivity() {
    private lateinit var codescanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==
                PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),123)
        }else{
            startscanner()
        }
    }

    private fun startscanner() {
        val ui: CodeScannerView = findViewById(R.id.scanner_view)
        codescanner= CodeScanner(this,ui)
        codescanner.camera = CodeScanner.CAMERA_BACK
        codescanner.formats = CodeScanner.ALL_FORMATS
        codescanner.autoFocusMode = AutoFocusMode.SAFE
        codescanner.scanMode = ScanMode.SINGLE
        codescanner.isAutoFocusEnabled = true
        codescanner.isFlashEnabled = false
        codescanner.decodeCallback = DecodeCallback {
            val i = Intent(this,Result::class.java)
            i.putExtra("path",it.toString())
            startActivity(i)
            runOnUiThread{
                Toast.makeText(this,"Scan Result: ${it.text}",Toast.LENGTH_SHORT).show()
            }
        }
        codescanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this,"Camera initialization error: ${it.message}",Toast.LENGTH_SHORT).show()
            }
            ui.setOnClickListener {
                codescanner.startPreview()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==123){
            if (grantResults[0]==PackageManager.PERMISSION_DENIED){
                Toast.makeText(this,"Camera permission granted",Toast.LENGTH_SHORT).show()
                startscanner()
            }else{
                Toast.makeText(this,"Camera permission denied",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codescanner.isInitialized){
            codescanner?.startPreview()
        }
    }

    override fun onPause() {
        if (::codescanner.isInitialized){
            codescanner?.releaseResources()
        }
        super.onPause()
    }
}