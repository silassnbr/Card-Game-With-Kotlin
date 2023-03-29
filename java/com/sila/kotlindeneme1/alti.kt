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
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_alti.*
import kotlinx.android.synthetic.main.activity_dort.*
//import jdk.nashorn.internal.objects.NativeRegExp.source
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.*
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.imageView3
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.switch3
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.textView14
import kotlinx.android.synthetic.main.secenek.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import kotlin.properties.Delegates
import kotlin.random.Random


class alti : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer
    lateinit var mediaPlayer3: MediaPlayer
    lateinit var mediaPlayer4: MediaPlayer
    var timeLeft by Delegates.notNull<Long>()
    var toplam:Int = 0
    lateinit var timerExt:CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alti)

        var image_list= arrayOf(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17
            ,a18,a19,a20,a21,a22,a23,a24,a25,a26,a27,a28,a29,a30,a31,a32,a33,a34,a35,a36)
        var p1 = ArrayList<fBase>()
        var p2 = ArrayList<fBase>()
        var p3 = ArrayList<fBase>()
        var p4 = ArrayList<fBase>()
        var sure:Int
        var mod=intent.getStringExtra("mod")
        if(mod=="tek"){
            sure=45000
        }
        else
        {sure=60000
            textView191.visibility=View.VISIBLE
            textView20.visibility=View.VISIBLE
        }
        var dizi1 = ArrayList<Bitmap>()
        var dizi2 = ArrayList<Bitmap>()
        var dizi3 = ArrayList<Bitmap>()
        var dizi4 = ArrayList<Bitmap>()
        var d1 = ArrayList<String>()
        var dizi_son = ArrayList<Bitmap>()
        var random_foto = ArrayList<Int>()
        var fiBase = Firebase.firestore
        val g = fiBase.collection("GRYFFİNDOR")
        val h = fiBase.collection("HUFFLEPUFF")
        val r = fiBase.collection("RAVENCLAW")
        val s = fiBase.collection("SLYTHERİN")
        val onYuz = fiBase.collection("onYuz")
        var level_number= intent.getStringExtra( "level_number")
        var kul = ArrayList<Int>()
        var kul1 = ArrayList<Int>()
        var kul2 = ArrayList<Int>()
        var kul3 = ArrayList<Int>()
        var sira=1
        var open_card = ArrayList<Int>()
        var puanson= ArrayList<Int>()
        var suffle_name = ArrayList<String>()
        var suffle_puan=ArrayList<Int>()
        var evKatsayi=ArrayList<Int>()
        var suffle_ev=ArrayList<Int>()
        var puan=0
        var finish_counter=0
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
            var onn=onYuz.get().await().toObjects(fBase::class.java)
            var onimg = Base64.decode(onn[0].image.toString(), 0)
            var on_yuz = BitmapFactory.decodeByteArray(onimg, 0, onimg.size)
            evKatsayi= arrayListOf(2,2,1,1,3,3,4,4,2,2,1,1,3,3,4,4,2,2,1,1,3,3,4,4,2,2,1,1,3,3,4,4,3,4,3,4)
            withContext(Dispatchers.Main)
            {
                for(i in 0..3){
                    p1.add(fBase(gri[kul[i]].image,gri[kul[i]].name,gri[kul[i]].puan))
                    p2.add(fBase( huf[kul1[i]].image,huf[kul1[i]].name,huf[kul1[i]].puan))
                    p3.add(fBase( rav[kul2[i]].image,rav[kul2[i]].name,rav[kul2[i]].puan))
                    p4.add(fBase( sly[kul3[i]].image,sly[kul3[i]].name,sly[kul3[i]].puan))

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
                    dizi_son.add(image)
                    dizi_son.add(image)
                    dizi_son.add(image1)
                    dizi_son.add(image1)
                    dizi_son.add(image2)
                    dizi_son.add(image2)
                    dizi_son.add(image3)
                    dizi_son.add(image3)
                    puanson.add(p1[i].puan.toString().toInt())
                    puanson.add(p1[i].puan.toString().toInt())
                    puanson.add(p2[i].puan.toString().toInt())
                    puanson.add(p2[i].puan.toString().toInt())
                    puanson.add(p3[i].puan.toString().toInt())
                    puanson.add(p3[i].puan.toString().toInt())
                    puanson.add(p4[i].puan.toString().toInt())
                    puanson.add(p4[i].puan.toString().toInt())
                    d1.add(p1[i].name.toString())
                    d1.add(p1[i].name.toString())
                    d1.add(p2[i].name.toString())
                    d1.add(p2[i].name.toString())
                    d1.add(p3[i].name.toString())
                    d1.add(p3[i].name.toString())
                    d1.add(p4[i].name.toString())
                    d1.add(p4[i].name.toString())
                    if(i==3){
                        p3.add(fBase( rav[kul2[i]].image,rav[kul2[i]].name,rav[kul2[i]].puan))
                        p4.add(fBase( sly[kul3[i]].image,sly[kul3[i]].name,sly[kul3[i]].puan))
                        imageBytes2 = Base64.decode(rav[kul2[i+1]].image.toString(), 0)
                        image2 = BitmapFactory.decodeByteArray(imageBytes2, 0, imageBytes2.size)
                        //dizi3.add(image2)
                        imageBytes3 = Base64.decode(sly[kul3[i+1]].image.toString(), 0)
                        image3 = BitmapFactory.decodeByteArray(imageBytes3, 0, imageBytes3.size)
                        //dizi4.add(image3)
                        dizi_son.add(image2)
                        dizi_son.add(image3)
                        puanson.add(p3[4].puan.toString().toInt())
                        puanson.add(p4[4].puan.toString().toInt())
                        d1.add(p3[4].name.toString())
                        d1.add(p4[4].name.toString())
                        dizi_son.add(image2)
                        dizi_son.add(image3)
                        puanson.add(p3[4].puan.toString().toInt())
                        puanson.add(p4[4].puan.toString().toInt())
                        d1.add(p3[4].name.toString())
                        d1.add(p4[4].name.toString())
                    }

                }
                println(dizi_son)

                for(i in 0..35)
                    random_foto.add(i)
                random_foto.shuffle()

                for(i in 0..35){
                    image_list[i].visibility=View.VISIBLE
                    image_list[i].setImageBitmap(on_yuz)//dizi_son[random_foto[i]]
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

                    var a=textViewPuan3.text.toString()
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
                    textViewPuan3.text=toplam.toString()
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
                    var a=textViewPuan3.text.toString()
                    var v=((2*suffle_puan[i]*ev)*(timeLeft/ 10.0))
                    toplam = (Integer.parseInt(a) + ((2*suffle_puan[i]*ev)*(timeLeft/ 10.0))).toInt()
                    textViewPuan3.text=toplam.toString()
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
                    var a=textViewPuan3.text.toString()
                    var v=(2*suffle_puan[i]*ev)
                    toplam=(Integer.parseInt(a) + ((2*suffle_puan[i]*ev)))
                    textViewPuan3.text=toplam.toString()
                }
                else
                {
                    var ev=suffle_ev[i]%2
                    if(ev==0) ev=2
                    var a=textView191.text.toString()
                    var v=(2*suffle_puan[i]*ev)
                    toplam=(Integer.parseInt(a) + ((2*suffle_puan[i]*ev)))
                    textView191.text=toplam.toString()
                }
                }
                fun eksi_coklu(kart1:Int,kart2:Int,sir:Int)
                {   var ev1=suffle_ev[kart1]%2
                    var ev2=suffle_ev[kart2]%2
                    if(ev1==0) ev1=2
                    if(ev2==0) ev2=2
                    if (sir==1)
                    {   var kartPuan=(suffle_puan[kart1]+suffle_puan[kart2])
                        var a=textViewPuan3.text.toString()
                        var v =0
                        if(suffle_ev[kart1]==suffle_ev[kart2]){
                            toplam=(Integer.parseInt(a)-(kartPuan/ev1))

                        }
                        else{
                            toplam=(Integer.parseInt(a)-kartPuan/2*ev1*ev2)

                        }
                        textViewPuan3.text=toplam.toString()
                        sira=2

                    }
                    else{var kartPuan=(suffle_puan[kart1]+suffle_puan[kart2])
                        var a=textView191.text.toString()
                        var v =0
                        if(suffle_ev[kart1]==suffle_ev[kart2]){
                            toplam=(Integer.parseInt(a)-(kartPuan/ev1))
                        }
                        else{
                            toplam=(Integer.parseInt(a)-kartPuan/2*ev1*ev2)
                        }
                        textView191.text=toplam.toString()
                        sira=1


                    }
                }
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
                            if (finish_counter==16){
                                timerExt.cancel()
                                mediaPlayer4.start()
                                textViewSüreBitti3.visibility=View.VISIBLE
                                textViewSüreBitti3.text="Tebrikler"
                                buttonYeniOyun3.visibility=View.VISIBLE
                                buttonYeniOyun3.setOnClickListener() {
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
                a1.setOnClickListener(){if(a1.isClickable) control(0)}
                a2.setOnClickListener(){if(a2.isClickable)control(1)}
                a3.setOnClickListener(){if(a3.isClickable) control(2)}
                a4.setOnClickListener(){if(a4.isClickable) control(3)}
                a5.setOnClickListener(){if(a5.isClickable) control(4)}
                a6.setOnClickListener(){if(a6.isClickable) control(5)}
                a7.setOnClickListener(){if(a7.isClickable) control(6)}
                a8.setOnClickListener(){if(a8.isClickable) control(7)}
                a9.setOnClickListener(){if(a9.isClickable) control(8)}
                a10.setOnClickListener(){if(a10.isClickable) control(9)}
                a11.setOnClickListener(){if(a11.isClickable) control(10)}
                a12.setOnClickListener(){if(a12.isClickable) control(11)}
                a13.setOnClickListener(){if(a13.isClickable) control(12)}
                a14.setOnClickListener(){if(a14.isClickable) control(13)}
                a15.setOnClickListener(){if(a15.isClickable) control(14)}
                a16.setOnClickListener(){if(a16.isClickable) control(15)}
                a17.setOnClickListener(){if(a17.isClickable) control(16)}
                a18.setOnClickListener(){if(a18.isClickable)control(17)}
                a19.setOnClickListener(){if(a19.isClickable) control(18)}
                a20.setOnClickListener(){if(a20.isClickable) control(19)}
                a21.setOnClickListener(){if(a21.isClickable) control(20)}
                a22.setOnClickListener(){if(a22.isClickable) control(21)}
                a23.setOnClickListener(){if(a23.isClickable) control(22)}
                a24.setOnClickListener(){if(a24.isClickable) control(23)}
                a25.setOnClickListener(){if(a25.isClickable) control(24)}
                a26.setOnClickListener(){if(a26.isClickable) control(25)}
                a27.setOnClickListener(){if(a27.isClickable) control(26)}
                a28.setOnClickListener(){if(a28.isClickable) control(27)}
                a29.setOnClickListener(){if(a29.isClickable) control(28)}
                a30.setOnClickListener(){if(a30.isClickable) control(29)}
                a31.setOnClickListener(){if(a31.isClickable) control(30)}
                a32.setOnClickListener(){if(a32.isClickable) control(31)}
                a33.setOnClickListener(){if(a33.isClickable) control(32)}
                a34.setOnClickListener(){if(a34.isClickable) control(33)}
                a35.setOnClickListener(){if(a35.isClickable) control(34)}
                a36.setOnClickListener(){if(a36.isClickable) control(35)}

            }
            button6.setOnClickListener()
            {
                mediaPlayer.reset()
                mediaPlayer2.reset()
                mediaPlayer3.reset()
                mediaPlayer4.reset()
                var intent = Intent(applicationContext,baslan::class.java)
                startActivity(intent)
            }

        }

        println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")
        //val random=List(5){ Random.nextInt(0,11) }
        timerExt=object : CountDownTimer(sure.toLong(), 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(i: Long) {
                textView14.text=( i / 1000).toString()
                timeLeft=i/1000
            }

            override fun onFinish() {
                textView14.text=(0).toString()
                mediaPlayer.reset()
                mediaPlayer4.reset()
                mediaPlayer2.reset()
                mediaPlayer3.start()
                for(i in 0..35) image_list[i].visibility = View.INVISIBLE
                textViewSüreBitti3.visibility=View.VISIBLE
                buttonYeniOyun3.visibility=View.VISIBLE
                buttonYeniOyun3.setOnClickListener() {
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

