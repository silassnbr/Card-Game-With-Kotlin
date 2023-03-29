package com.sila.kotlindeneme1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.secenek.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class secenek : AppCompatActivity() {
    lateinit var fiBase: FirebaseFirestore

    var i: Int = 0
    val variable: fBase = fBase()

    var griffindor = ArrayList<fBase>()
    var hufflepuf = ArrayList<fBase>()
    var ravenclavn = ArrayList<fBase>()
    var slytrein = ArrayList<fBase>()
    var gri = ArrayList<fBase>()
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secenek)
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
                griffindor.add(fBase(gri[i].image, gri[i].name))
                hufflepuf.add(fBase(huf[i].image, huf[i].name))
                ravenclavn.add(fBase(rav[i].image, rav[i].name))
                slytrein.add(fBase(sly[i].image, sly[i].name))
            }
                println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
            }
        }
        imageView.setOnClickListener(){
            val imageBytes = Base64.decode(griffindor[2].image, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            imageView.setImageBitmap(decodedImage)
        }
        /*var m = "tek"

        var tek = findViewById(R.id.button5) as Button
        tek.setOnClickListener() {
            var bundle = Bundle()
            bundle.putString("Mod", m)
            val intent = Intent(this, baslan::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        var cok = findViewById<Button>(R.id.button4)
        cok.setOnClickListener() {
            val intent_b = Intent(this, baslan::class.java)
            intent_b.putExtra("Mod", "cok")
            startActivity(intent_b)
        }*/
        var btn = findViewById(R.id.button9) as Button
        btn.setOnClickListener()
        {
            println("ggggggggggggggggggggggggggg")
            for (i in 0..10)
            {
                println("1    "+griffindor[i].name+"   "+griffindor[i].image)
                println("2    "+hufflepuf[i].name+" "+hufflepuf[i].image)
                println("3    "+ravenclavn[i].name+" "+ravenclavn[i].image)
                println("4    "+slytrein[i].name+" "+slytrein[i].image)
                /*val imageBytes2 = Base64.decode(griffindor[i].image, Base64.DEFAULT)
                val decodedImage2 = BitmapFactory.decodeByteArray(imageBytes2, 0, imageBytes2.size)
                imageView2.setImageBitmap(decodedImage2)*/

                val imageBytes = Base64.decode(griffindor[i].image, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                imageView.setImageBitmap(decodedImage)
                //imageView.layoutParams=(AbsListView.LayoutParams(100,100))
                val random=List(5){ Random.nextInt(0,11) }
                println(random)
                break
            }
        }
    }
}
