package com.gbnsolutions.qrscanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView
import com.gbnsolutions.qrscanner.Model.Productinf
import com.google.firebase.database.*
import java.util.*

class Result  : AppCompatActivity() {
    lateinit var expDate: TextView
    lateinit var proInf: TextView
    lateinit var manInf: TextView
    lateinit var conInf: TextView
    lateinit var a:String
    lateinit var refdt:DatabaseReference
    lateinit var rTTs: TextToSpeech
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
        refdt= FirebaseDatabase.getInstance().reference.child("ProductInf").child(a)
        refdt.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val data : Productinf? =snapshot.getValue(Productinf::class.java)
                    expDate.text = data!!.getExpdt()
                    proInf.text = data.getProinf()
                    manInf.text = data.getManinf()
                    conInf.text = data.getConinf()
                    rTTs.speak("Product Name"+data.getProinf()+"."+
                            "Manufactured By"+data.getManinf()+"."+
                            "Contents are"+data.getConinf()+"."+
                            "Expires on"+data.getExpdt(),TextToSpeech.QUEUE_FLUSH,null)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}