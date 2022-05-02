package com.gbnsolutions.qrscanner

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.gbnsolutions.qrscanner.Model.Productinf
import com.google.firebase.database.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Result  : AppCompatActivity() {
    lateinit var expDate: TextView
    lateinit var proInf: TextView
    lateinit var manInf: TextView
    lateinit var conInf: TextView
    lateinit var a:String
    lateinit var refdt:DatabaseReference
    lateinit var rTTs: TextToSpeech
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resukt)
        expDate = findViewById(R.id.expiredate)
        proInf = findViewById(R.id.product_inf)
        manInf= findViewById(R.id.man_inf)
        conInf = findViewById(R.id.con_inf)
        rTTs = TextToSpeech(this,TextToSpeech.OnInitListener {  status->
            if (status!=TextToSpeech.ERROR){
                rTTs.language = Locale.getDefault()
            }
        })
        if (intent!=null){
             a = intent.getStringExtra("path").toString()
        }
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatted = current.format(formatter)
        refdt= FirebaseDatabase.getInstance().reference.child("ProductInf").child(a)
        refdt.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val data: Productinf? = snapshot.getValue(Productinf::class.java)
                    expDate.text = data!!.getExpdt()
                    proInf.text = data.getProinf()
                    manInf.text = data.getManinf()
                    conInf.text = data.getConinf()
                    if (data.getExpdt()!! <= formatted) {
                        Toast.makeText(this@Result,"Expired!!",Toast.LENGTH_SHORT).show()
                    }
                 else{
                    rTTs.speak(
                        "Product Name" + data.getProinf() + "." +
                                "Expires on" + data.getExpdt(), TextToSpeech.QUEUE_FLUSH, null
                    )
                }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}