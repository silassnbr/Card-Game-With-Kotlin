package com.sila.kotlindeneme1

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Base64
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
//import jdk.nashorn.internal.objects.NativeRegExp.source
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.*
import kotlinx.android.synthetic.main.secenek.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import kotlin.random.Random


class tekMod : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyun_sayfasi)
        var dizi1 = ArrayList<Bitmap>()
        var dizi2 = ArrayList<Bitmap>()
        var dizi3 = ArrayList<Bitmap>()
        var dizi4 = ArrayList<Bitmap>()
        var dizi_son = ArrayList<Bitmap>()
        var random_foto = ArrayList<Int>()
        var fiBase = Firebase.firestore
        val g = fiBase.collection("GRYFFİNDOR")
        val h = fiBase.collection("HUFFLEPUFF")
        val r = fiBase.collection("RAVENCLAW")
        val s = fiBase.collection("SLYTHERİN")
        var level_number= intent.getStringExtra( "level_number")
        var griffind=intent.getStringArrayListExtra("name1")as ArrayList
        var huffle=intent.getStringArrayListExtra("name2")as ArrayList
        var raven=intent.getStringArrayListExtra("name3")as ArrayList
        var slyt=intent.getStringArrayListExtra("name4")as ArrayList
        var kul = ArrayList<Int>()
        var kul1 = ArrayList<Int>()
        var kul2 = ArrayList<Int>()
        var kul3 = ArrayList<Int>()
        var random_sec = ArrayList<Int>()
        var a=Integer.parseInt(level_number)
        var i=0
        while(i<a) {
            var sayi = Random.nextInt(0, 11)
            if (!(kul.contains(sayi))) {
                kul.add(sayi)
                i = i + 1
            }
        }
        i=0
        while(i<a) {
            var sayi = Random.nextInt(0, 11)
            if (!(kul1.contains(sayi))) {
                kul1.add(sayi)
                i = i + 1
            }
        }
        i=0
        while(i<a) {
            var sayi = Random.nextInt(0, 11)
            if (!(kul2.contains(sayi))) {
                kul2.add(sayi)
                i = i + 1
            }
        }
        i=0
        while(i<a) {
            var sayi = Random.nextInt(0, 11)
            if (!(kul3.contains(sayi))) {
                kul3.add(sayi)
                i = i + 1
            }
        }
        println(kul)
        println(kul1)
        println(kul2)
        println(kul3)

        GlobalScope.launch(Dispatchers.IO) {
            delay(1000)
            var gri = g.get().await().toObjects(fBase::class.java)
            var huf = h.get().await().toObjects(fBase::class.java)
            var rav = r.get().await().toObjects(fBase::class.java)
            var sly = s.get().await().toObjects(fBase::class.java)
            withContext(Dispatchers.Main)
            {
                for(i in 0..Integer.parseInt(level_number)/2-1){
                    var imageBytes = Base64.decode(gri[kul[i]].image.toString(), 0)
                    var image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    //dizi1.add(image)
                    var imageBytes1 = Base64.decode(huf[kul1[i]].image.toString(), 0)
                    var image1 = BitmapFactory.decodeByteArray(imageBytes1, 0, imageBytes1.size)
                    //dizi2.add(image1)
                    var imageBytes2 = Base64.decode(rav[kul2[i]].image.toString(), 0)
                    var image2 = BitmapFactory.decodeByteArray(imageBytes2, 0, imageBytes2.size)
                    //dizi3.add(image2)
                    var imageBytes3 = Base64.decode(sly[kul3[i]].image.toString(), 0)
                    var image3 = BitmapFactory.decodeByteArray(imageBytes3, 0, imageBytes3.size)
                    //dizi4.add(image3)
                    for (i in 0..1){
                        dizi_son.add(image)
                        dizi_son.add(image1)
                        dizi_son.add(image2)
                        dizi_son.add(image3)
                    }
                    if(Integer.parseInt(level_number)==8 && i==3){
                        imageBytes2 = Base64.decode(rav[kul2[i+1]].image.toString(), 0)
                        image2 = BitmapFactory.decodeByteArray(imageBytes2, 0, imageBytes2.size)
                        //dizi3.add(image2)
                        imageBytes3 = Base64.decode(sly[kul3[i+1]].image.toString(), 0)
                        image3 = BitmapFactory.decodeByteArray(imageBytes3, 0, imageBytes3.size)
                        //dizi4.add(image3)
                        for (i in 0..1){
                            dizi_son.add(image2)
                            dizi_son.add(image3)
                        }
                    }

                }
                println(dizi_son)

                if(Integer.parseInt(level_number)==2){

                    i=0
                    var a=Integer.parseInt(level_number)
                    while(i<a) {
                        var sayi=Random.nextInt(0,4)
                        if(!(random_sec.contains(sayi))) {
                        random_sec.add(sayi)
                        i=i+1 }

                    }

                    println(random_sec)
                    random_foto.add(random_sec[0]*2)
                    random_foto.add(random_sec[1]*2)
                    random_foto.add(random_sec[0]*2)
                    random_foto.add(random_sec[1]*2)
                    random_foto.shuffle()
                    var image_list = arrayOf(imageViewbir, imageViewiki, imageViewüc, imageViewdort)
                    for(i in 0..3){
                        image_list[i].visibility=View.VISIBLE
                        image_list[i].setImageBitmap(dizi_son[random_foto[i]])
                    }

                }
                /*if(Integer.parseInt(level_number)==4){
                    for(i in 0..15)
                        random_foto.add(i)
                    random_foto.shuffle()
                    var image_list =
                        arrayOf(bir,iki,üc,dört,bes,altı,yedi,sekiz,dokuz,on,onbir,oniki,onüc,ondört,onbes,onaltı)
                    for(i in 0..15){
                        image_list[i].visibility=View.VISIBLE
                        image_list[i].setImageBitmap(dizi_son[random_foto[i]])
                    }

                }
                if(Integer.parseInt(level_number)==6){
                    for(i in 0..35)
                        random_foto.add(i)
                    random_foto.shuffle()
                    var image_List= arrayOf(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a25
                    ,a18,a19,a20,a21,a22,a23,a24,a26,a27,a28,a29,a30,a31,a32,a33,a34,a35,a36)
                    for(i in 0..35){
                        image_List[i].visibility=View.VISIBLE
                        image_List[i].setImageBitmap(dizi_son[random_foto[i]])
                    }
                }*/

            }

            println(dizi1)
            println(dizi2)
            println(dizi3)
            println(dizi4)
        }

        println(griffind)
        println(huffle)
        println(raven)
        println(slyt)
        println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")

        val random=List(5){ Random.nextInt(0,11) }
        //println(random)
        //set back button
        mediaPlayer=MediaPlayer.create(applicationContext,R.raw.happy)
        mediaPlayer.start()
        object : CountDownTimer(45000, 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(i: Long) {
                textView14.text=( i / 1000).toString()
            }

            override fun onFinish() {
                textView14.text=(0).toString()
            }
        }.start()
        switch3.setOnCheckedChangeListener { compoundButton, b ->
            if(!this::mediaPlayer.isInitialized)
                mediaPlayer=MediaPlayer.create(applicationContext,R.raw.happy)
            if(b){
                imageView3.setImageResource(R.drawable.ses_kapali)
                mediaPlayer!!.pause()
            }
            else{
                imageView3.setImageResource(R.drawable.ses_acik)
                mediaPlayer.start()
            }

        }
    }



    private fun pauseMusic() {
        //diğer müzikler de kapatılacak
        mediaPlayer!!.pause()
        mediaPlayer!!.seekTo(0)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        mediaPlayer.reset()
        return true
    }

}

