package com.sila.kotlindeneme1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Base64
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_alti.*
import kotlinx.android.synthetic.main.activity_dort.*
import kotlinx.android.synthetic.main.activity_iki.*
//import jdk.nashorn.internal.objects.NativeRegExp.source
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.*
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.imageView3
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.imageViewbir
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.imageViewdort
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.imageViewiki
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.imageViewüc
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.switch3
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.textView14
import kotlinx.android.synthetic.main.secenek.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import kotlin.properties.Delegates
import kotlin.random.Random
/*
var d2 = ArrayList<String>()
var d3 = ArrayList<String>()
var d4 = ArrayList<String>()*/
var tik1=0
var tik2=0
var tik3=0
var tik4=0
class iki : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer
    lateinit var mediaPlayer3: MediaPlayer
    lateinit var mediaPlayer4: MediaPlayer
    var timeLeft by Delegates.notNull<Long>()
    var toplam:Int = 0
    lateinit var timerExt:CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iki)
        var image_list =arrayOf(imageViewbir,imageViewiki,imageViewüc,imageViewdort)
        var p1 = ArrayList<fBase>()
        var p2 = ArrayList<fBase>()
        var p3 = ArrayList<fBase>()
        var p4 = ArrayList<fBase>()
        var open_card = ArrayList<Int>()
        var suffle_name = ArrayList<String>()
        var suffle_puan=ArrayList<Int>()
        var evKatsayi=ArrayList<Int>()
        var suffle_ev=ArrayList<Int>()
        var puan=0
        var sira=1
        var finish_counter=0
        var dizi_son = ArrayList<Bitmap>()
        var puanson= ArrayList<Int>()
        var d1 = ArrayList<String>()
        var random_foto = ArrayList<Int>()
        var fiBase = Firebase.firestore
        var donen=0
        val g = fiBase.collection("GRYFFİNDOR")
        val h = fiBase.collection("HUFFLEPUFF")
        val r = fiBase.collection("RAVENCLAW")
        val s = fiBase.collection("SLYTHERİN")
        val onYuz = fiBase.collection("onYuz")
        var level_number= intent.getStringExtra( "level_number")
        /*var kul = ArrayList<Int>()
        var kul1 = ArrayList<Int>()
        var kul2 = ArrayList<Int>()
        var kul3 = ArrayList<Int>()*/
        var random_sec = ArrayList<Int>()
        var a=Integer.parseInt(level_number)
        var i=0
        var mod=intent.getStringExtra("mod")
        var sure:Int
        if(mod=="tek"){
            sure=45000
        }
        else
        {sure=60000
            textView23.visibility=View.VISIBLE
            textView24.visibility=View.VISIBLE
        }

        var kul = Random.nextInt(5, 11)
        var kul1 = Random.nextInt(5, 11)
        var kul2 = Random.nextInt(5, 11)
        var kul3 = Random.nextInt(5, 11)

        GlobalScope.launch(Dispatchers.IO)
        {
            delay(1000)
            var gri = g.get().await().toObjects(fBase::class.java)
            var huf = h.get().await().toObjects(fBase::class.java)
            var rav = r.get().await().toObjects(fBase::class.java)
            var sly = s.get().await().toObjects(fBase::class.java)
            var onn=onYuz.get().await().toObjects(fBase::class.java)
            var onimg = Base64.decode(onn[0].image.toString(), 0)
            var on_yuz = BitmapFactory.decodeByteArray(onimg, 0, onimg.size)
            evKatsayi= arrayListOf(2,2,1,1,3,3,4,4,2,2,1,1,3,3,4,4)
            withContext(Dispatchers.Main)
            {
                p1.add(fBase(gri[kul].image,gri[kul].name,gri[kul].puan))
                p2.add(fBase( huf[kul1].image,huf[kul1].name,huf[kul1].puan))
                p3.add(fBase( rav[kul2].image,rav[kul2].name,rav[kul2].puan))
                p4.add(fBase( sly[kul3].image,sly[kul3].name,sly[kul3].puan))
                var imageBytes = Base64.decode(gri[kul].image.toString(), 0)
                var image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                var imageBytes1 = Base64.decode(huf[kul1].image.toString(), 0)
                var image1 = BitmapFactory.decodeByteArray(imageBytes1, 0, imageBytes1.size)
                var imageBytes2 = Base64.decode(rav[kul2].image.toString(), 0)
                var image2 = BitmapFactory.decodeByteArray(imageBytes2, 0, imageBytes2.size)
                var imageBytes3 = Base64.decode(sly[kul3].image.toString(), 0)
                var image3 = BitmapFactory.decodeByteArray(imageBytes3, 0, imageBytes3.size)
                dizi_son= arrayListOf(image,image,image1,image1,image2,image2,image3,image3)
                puanson= arrayListOf(p1[0].puan.toString().toInt(),p1[0].puan.toString().toInt(),p2[0].puan.toString().toInt(),
                    p2[0].puan.toString().toInt(),p3[0].puan.toString().toInt(),p3[0].puan.toString().toInt(),
                    p4[0].puan.toString().toInt(),p4[0].puan.toString().toInt()  )
                d1= arrayListOf(p1[0].name.toString(),p1[0].name.toString(),p2[0].name.toString(),p2[0].name.toString(),
                    p3[0].name.toString(),p3[0].name.toString(),p4[0].name.toString(),p4[0].name.toString() )

                i=0
                while(i<2) {
                    var sayi=Random.nextInt(0,4)
                    if(!(random_sec.contains(sayi))) {
                        random_sec.add(sayi)
                        i=i+1 }

                }


                println(random_sec)
                random_foto= arrayListOf(random_sec[0]*2,random_sec[0]*2,random_sec[1]*2,random_sec[1]*2)
                random_foto.shuffle()
                var image_list = arrayOf(imageViewbir, imageViewiki, imageViewüc, imageViewdort)
                var bir=""
                var iki=""
                for(i in 0..3){
                    image_list[i].visibility=View.VISIBLE
                    image_list[i].setImageBitmap(on_yuz)
                    suffle_name.add(d1[random_foto[i]])
                    suffle_puan.add(puanson[random_foto[i]])
                    suffle_ev.add(evKatsayi[random_foto[i]])
                }
                println(suffle_name)
                println(suffle_puan)
                println(suffle_ev)
                timerExt.start()
                fun puan_eksi(kart1:Int,kart2:Int) {
                    var ev1=suffle_ev[kart1]%2
                    var ev2=suffle_ev[kart2]%2
                    if(ev1==0) ev1=2
                    if(ev2==0) ev2=2

                    var a=textViewPuan.text.toString()
                    println("yanlışş")
                    var kartPuan=(suffle_puan[kart1]+suffle_puan[kart2])
                    var v =0
                    if(suffle_ev[kart1]==suffle_ev[kart2]){
                        toplam=(Integer.parseInt(a)-kartPuan/ev1*(45-timeLeft)/10).toInt()
                        v =(kartPuan/ev1*(45-timeLeft)/10).toInt()
                    }
                    else{
                        toplam=(Integer.parseInt(a)-kartPuan/2*ev1*ev2*(45-timeLeft)/10).toInt()
                        v =(kartPuan/2*ev1*ev2*(45-timeLeft)/10).toInt()
                    }
                    textViewPuan.text=toplam.toString()
                    println(suffle_ev[kart1])
                    println(suffle_puan[kart1])
                    println(suffle_name[kart1])
                    println(suffle_ev[kart2])
                    println(suffle_puan[kart2])
                    println(suffle_name[kart2])
                    println(timeLeft)
                    println("eksi $v")
                    println(toplam)
                }

                fun puan_artı(i: Int) {
                    var ev=suffle_ev[i]%2
                    if(ev==0) ev=2
                    println("doğru")
                    var a=textViewPuan.text.toString()
                    var v=((2*suffle_puan[i]*ev)*(timeLeft/ 10.0))
                    toplam = (Integer.parseInt(a) + ((2*suffle_puan[i]*ev)*(timeLeft/ 10.0))).toInt()
                    textViewPuan.text=toplam.toString()
                    println(suffle_ev[i])
                    println(suffle_puan[i])
                    println(suffle_name[i])
                    println(timeLeft)
                    println("artı $v")
                    println(toplam)

                }
                fun arti_coklu(i:Int,sira:Int)
                {if (sira==1)
                {
                    var ev=suffle_ev[i]%2
                    if(ev==0) ev=2
                    var a=textViewPuan.text.toString()
                    var v=(2*suffle_puan[i]*ev)
                    toplam=(Integer.parseInt(a) + ((2*suffle_puan[i]*ev)))
                    textViewPuan.text=toplam.toString()
                }
                    else
                {
                    var ev=suffle_ev[i]%2
                    if(ev==0) ev=2
                    var a=textView24.text.toString()
                    var v=(2*suffle_puan[i]*ev)
                    toplam=(Integer.parseInt(a) + ((2*suffle_puan[i]*ev)))
                    textView24.text=toplam.toString()
                }
                }
                fun eksi_coklu(kart1:Int,kart2:Int,sir:Int)
                {   var ev1=suffle_ev[kart1]%2
                    var ev2=suffle_ev[kart2]%2
                    if(ev1==0) ev1=2
                    if(ev2==0) ev2=2
                    if (sir==1)
                    {   var kartPuan=(suffle_puan[kart1]+suffle_puan[kart2])
                        var a=textViewPuan.text.toString()
                        var v =0
                        if(suffle_ev[kart1]==suffle_ev[kart2]){
                            toplam=(Integer.parseInt(a)-(kartPuan/ev1))

                        }
                        else{
                            toplam=(Integer.parseInt(a)-kartPuan/2*ev1*ev2)

                        }
                        textViewPuan.text=toplam.toString()
                        sira=2

                    }
                    else{var kartPuan=(suffle_puan[kart1]+suffle_puan[kart2])
                        var a=textView24.text.toString()
                        var v =0
                        if(suffle_ev[kart1]==suffle_ev[kart2]){
                            toplam=(Integer.parseInt(a)-(kartPuan/ev1))
                        }
                        else{
                            toplam=(Integer.parseInt(a)-kartPuan/2*ev1*ev2)
                        }
                        textView24.text=toplam.toString()
                        sira=1


                    }
                }
                @SuppressLint("SetTextI18n")
                fun control(number: Int) {
                    image_list[number].setImageBitmap(dizi_son[random_foto[number]])
                    open_card.add(number)
                    if(open_card.size==2){
                        if (suffle_name[open_card[0]].equals(suffle_name[open_card[1]])&&!(open_card[0]==open_card[1])){
                            println(suffle_name[open_card[0]])
                            println(suffle_name[open_card[1]])
                            image_list[open_card[0]].visibility=View.INVISIBLE
                            image_list[open_card[1]].visibility=View.INVISIBLE

                            if(mod=="tek")
                            {
                                puan_artı(number)
                            }
                            else{arti_coklu(number,sira)}
                            finish_counter+=1
                            if (finish_counter==2){
                                timerExt.cancel()
                                mediaPlayer4.start()
                                süreBitti.visibility=View.VISIBLE
                                süreBitti.text="Tebrikler"
                                buttonYeniOyun.visibility=View.VISIBLE
                                buttonYeniOyun.setOnClickListener() {
                                    mediaPlayer.pause()
                                    var intent = Intent(applicationContext,baslan::class.java)
                                    startActivity(intent)
                                }
                            }
                            else mediaPlayer2.start()
                        }
                        else if(!(open_card[0]==open_card[1]))
                        {  if(mod=="tek")
                        {
                            puan_eksi(open_card[0],open_card[1])
                        }
                        else{eksi_coklu(open_card[0],open_card[1],sira)}

                        }


                    }
                    if(open_card.size>=3){
                        image_list[open_card[0]].setImageBitmap(on_yuz)
                        image_list[open_card[1]].setImageBitmap(on_yuz)
                        var v=open_card[2]
                        open_card.clear()
                        if(mediaPlayer2.isPlaying) {
                            mediaPlayer2.seekTo(0)//her seferinde baştan çalmaya başlasın
                            mediaPlayer2.pause()
                        }
                        control(v)
                    }

                }

                imageViewbir.setOnClickListener(){if(imageViewbir.isClickable) control(0)}
                imageViewiki.setOnClickListener(){if(imageViewiki.isClickable)control(1)}
                imageViewüc.setOnClickListener(){if(imageViewüc.isClickable) control(2)}
                imageViewdort.setOnClickListener(){if(imageViewdort.isClickable) control(3)}

            }
            button10.setOnClickListener()
            {
                mediaPlayer.reset()
                mediaPlayer2.reset()
                mediaPlayer3.reset()
                mediaPlayer4.reset()
                var intent = Intent(applicationContext,baslan::class.java)
                startActivity(intent)
            }
        }

        timerExt=object : CountDownTimer(sure.toLong(), 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(i: Long) {
                textView14.text=( i / 1000).toString()
                timeLeft=i/1000
            }
            override fun onFinish() {//süre bitince müzikler sıfırlanır
                textView14.text=(0).toString()
                mediaPlayer.reset()
                mediaPlayer4.reset()
                mediaPlayer2.reset()
                mediaPlayer3.start()
                for(i in 0..3)
                    image_list[i].visibility = View.INVISIBLE
                SüreBitti2.visibility=View.VISIBLE
                buttonYeniOyun2.visibility=View.VISIBLE
                buttonYeniOyun2.setOnClickListener() {
                    var intent = Intent(applicationContext,baslan::class.java)
                    startActivity(intent)
                }

            }
        }
        mediaPlayer=MediaPlayer.create(applicationContext,R.raw.prologue)
        mediaPlayer.start()
        mediaPlayer2 =MediaPlayer.create(applicationContext, R.raw.happy)
        mediaPlayer3=MediaPlayer.create(applicationContext,R.raw.shocked_sound_effect)
        mediaPlayer4=MediaPlayer.create(applicationContext,R.raw.congratulations)
        switch3.setOnCheckedChangeListener { compoundButton, b ->
            if(b){
                imageView3.setImageResource(R.drawable.ses_kapali)
                mediaPlayer!!.pause()
                mediaPlayer2.reset()
                mediaPlayer3.reset()
                mediaPlayer4.reset()

            }
            else{
                imageView3.setImageResource(R.drawable.ses_acik)
                if(!this::mediaPlayer.isInitialized)
                    mediaPlayer=MediaPlayer.create(applicationContext,R.raw.prologue)
                mediaPlayer.start()
                mediaPlayer2 =MediaPlayer.create(applicationContext, R.raw.happy)
                mediaPlayer3=MediaPlayer.create(applicationContext,R.raw.shocked_sound_effect)
                mediaPlayer4=MediaPlayer.create(applicationContext,R.raw.congratulations)
            }

        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        mediaPlayer.reset()
        mediaPlayer2.reset()
        mediaPlayer3.reset()
        mediaPlayer4.reset()
        return true
    }

}