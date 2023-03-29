package com.sila.kotlindeneme1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.basla_act.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import kotlin.random.Random

class baslan:AppCompatActivity() {
    lateinit var level2 :CheckBox
    lateinit var level4 :CheckBox
    lateinit var level6:CheckBox
    lateinit var mod1:CheckBox
    lateinit var mod2:CheckBox
    lateinit var text_a:TextView
    lateinit var text_b:TextView
    lateinit var fiBase: FirebaseFirestore

    var griffindor2 = ArrayList<fBase>()
    var hufflepuf2 = ArrayList<fBase>()
    var ravenclavn2= ArrayList<fBase>()
    var slytrein2 = ArrayList<fBase>()
    var dizi1 = ArrayList<String>()
    var dizi2 = ArrayList<String>()
    var dizi3 = ArrayList<String>()
    var dizi4 = ArrayList<String>()
    var d1 = ArrayList<String>()
    var d2 = ArrayList<String>()
    var d3 = ArrayList<String>()
    var d4 = ArrayList<String>()
    var diz1 = ArrayList<String>()
    var diz2 = ArrayList<String>()
    var diz3 = ArrayList<String>()
    var diz4 = ArrayList<String>()

    lateinit var level_number: String
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState:Bundle?)
    {super.onCreate(savedInstanceState)
        setContentView(R.layout.basla_act)
        arkaPlan.alpha = 0.20f
        fiBase = Firebase.firestore
        val g = fiBase.collection("GRYFFİNDOR")
        val h = fiBase.collection("HUFFLEPUFF")
        val r = fiBase.collection("RAVENCLAW")
        val s = fiBase.collection("SLYTHERİN")
        GlobalScope.launch(Dispatchers.IO) {
            delay(1000)
            var gri = g.get().await().toObjects(fBase::class.java)
            var huf = h.get().await().toObjects(fBase::class.java)
            var rav = r.get().await().toObjects(fBase::class.java)
            var sly = s.get().await().toObjects(fBase::class.java)
            withContext(Dispatchers.Main)
            {for (i in 0..10)
            {
                griffindor2.add(fBase(gri[i].image,gri[i].name))
                hufflepuf2.add(fBase( huf[i].image,huf[i].name))
                ravenclavn2.add(fBase( rav[i].image,rav[i].name))
                slytrein2.add(fBase( sly[i].image,sly[i].name))
            }
            }
        }

        val nameGet: String? = intent.getStringExtra("Mod")
        level2=findViewById(R.id.checkBox)
        level4=findViewById(R.id.checkBox1)
        level6=findViewById(R.id.checkBox2)
        mod1=findViewById(R.id.checkBox3)
        mod2=findViewById(R.id.checkBox4)
        text_a=findViewById(R.id.textView12)
        text_b=findViewById(R.id.textView12)
        val intent_a = Intent(applicationContext, tekMod::class.java)
        var but= findViewById<Button>(R.id.button7)
        var counter=0
        var modCounter=0
        level_number=""
        but.setOnClickListener(){

            if(mod1.isChecked)
            {
                modCounter+=1
            }
            if(mod2.isChecked)
            {
                modCounter+=1
            }
            if(level2.isChecked){
                counter += 1
                level_number="2"
                }
            if(level4.isChecked){
                counter += 1
                level_number="4"            }
            if(level6.isChecked){
                counter += 1
                level_number="8"            }
            if(modCounter>1 || counter>1)
            {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Tek secim yapınız")
                    .setCancelable(false)
                    .setPositiveButton("Tamam",DialogInterface.OnClickListener { dialog, id -> dialog.cancel()})
                val alert = dialogBuilder.create()
                alert.setTitle("!!!!!!")
                alert.show()
            }
            else if(modCounter==0 || counter==0)
            {val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Lütfen oyun modu ve seviye seçiniz").setCancelable(false).setPositiveButton("Tamam",
                        DialogInterface.OnClickListener { dialog, id ->dialog.cancel()})
                val alert = dialogBuilder.create()
                alert.setTitle("Hatalı giriş")
                alert.show()}
            else {
                if(mod1.isChecked)
                {      for (i in 0..10)
                {
                }
                    if(level2.isChecked){
                        val intent = Intent(this, iki::class.java)
                        intent.putExtra("mod","tek")
                        intent.putExtra("level_number",level_number)
                        startActivity(intent)
                    }
                    if(level4.isChecked){
                        val intent = Intent(this, dort::class.java)
                        intent.putExtra("mod","tek")
                        intent.putExtra("level_number",level_number)
                        startActivity(intent)
                    }
                    if(level6.isChecked){
                        val intent = Intent(this, alti::class.java)
                        intent.putExtra("mod","tek")
                        intent.putExtra("level_number",level_number)
                        startActivity(intent)
                    }

                }
                else {if(level2.isChecked){
                    val intent = Intent(this, iki::class.java)
                    intent.putExtra("mod","cok")
                    intent.putExtra("level_number",level_number)
                    startActivity(intent)
                }
                    if(level4.isChecked){
                        val intent = Intent(this, dort::class.java)
                        intent.putExtra("mod","cok")
                        intent.putExtra("level_number",level_number)
                        startActivity(intent)
                    }
                    if(level6.isChecked){
                        val intent = Intent(this, alti::class.java)
                        intent.putExtra("mod","cok")
                        intent.putExtra("level_number",level_number)
                        startActivity(intent)
                    }}
            }
            counter=0
            modCounter=0
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}