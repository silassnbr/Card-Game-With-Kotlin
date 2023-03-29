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
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.imageView3
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.switch3
import kotlinx.android.synthetic.main.activity_oyun_sayfasi.textView14
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import kotlin.properties.Delegates
import kotlin.random.Random


class dort : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer
    lateinit var mediaPlayer3: MediaPlayer
    lateinit var mediaPlayer4: MediaPlayer
    var timeLeft by Delegates.notNull<Long>()
    var toplam:Int = 0
    lateinit var timerExt:CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dort)

        var image_list =
            arrayOf(bir,iki,üc,dört,bes,altı,yedi,sekiz,dokuz,on,onbir,oniki,onüc,ondört,onbeş,onaltı)
        var p1 = ArrayList<fBase>()
        var p2 = ArrayList<fBase>()
        var p3 = ArrayList<fBase>()
        var p4 = ArrayList<fBase>()
        var dizi1 = ArrayList<Bitmap>()
        var dizi2 = ArrayList<Bitmap>()
        var dizi3 = ArrayList<Bitmap>()
        var dizi4 = ArrayList<Bitmap>()
        var open_card = ArrayList<Int>()
        var dizi_son = ArrayList<Bitmap>()
        var puanson= ArrayList<Int>()
        var suffle_name = ArrayList<String>()
        var suffle_puan=ArrayList<Int>()
        var evKatsayi=ArrayList<Int>()
        var suffle_ev=ArrayList<Int>()
        var mod=intent.getStringExtra("mod")
        var sure:Int
        if(mod=="tek"){
            sure=45000
        }
        else
        {sure=60000
            textView15.visibility=View.VISIBLE
            textView19.visibility=View.VISIBLE
        }
        var puan=0
        var sira=1
        var finish_counter=0
        var d1 = ArrayList<String>()
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
        var random_sec = ArrayList<Int>()
        //var a=Integer.parseInt(level_number)
        var i=0
        while(i<2) {
            var sayi = Random.nextInt(0, 11)
            if (!(kul.contains(sayi))) {
                kul.add(sayi)
                i = i + 1
            }
        }
        i=0
        while(i<2) {
            var sayi = Random.nextInt(0, 11)
            if (!(kul1.contains(sayi))) {
                kul1.add(sayi)
                i = i + 1
            }
        }
        i=0
        while(i<2) {
            var sayi = Random.nextInt(0, 11)
            if (!(kul2.contains(sayi))) {
                kul2.add(sayi)
                i = i + 1
            }
        }
        i=0
        while(i<2) {
            var sayi = Random.nextInt(0, 11)
            if (!(kul3.contains(sayi))) {
                kul3.add(sayi)
                i = i + 1
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
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
                for(i in 0..1){
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

                }

                for(i in 0..15)
                    random_foto.add(i)
                random_foto.shuffle()
                for(i in 0..15){

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

                    var a=textViewPuan2.text.toString()
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
                    textViewPuan2.text=toplam.toString()
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
                fun arti_coklu(i:Int,sira:Int)
                {if (sira==1)
                {
                    var ev=suffle_ev[i]%2
                    if(ev==0) ev=2
                    var a=textViewPuan2.text.toString()
                    var v=(2*suffle_puan[i]*ev)
                    toplam=(Integer.parseInt(a) + ((2*suffle_puan[i]*ev)))
                    textViewPuan2.text=toplam.toString()
                }
                else
                {
                    var ev=suffle_ev[i]%2
                    if(ev==0) ev=2
                    var a=textView19.text.toString()
                    var v=(2*suffle_puan[i]*ev)
                    toplam=(Integer.parseInt(a) + ((2*suffle_puan[i]*ev)))
                    textView19.text=toplam.toString()
                }
                }
                fun eksi_coklu(kart1:Int,kart2:Int,sir:Int)
                {   var ev1=suffle_ev[kart1]%2
                    var ev2=suffle_ev[kart2]%2
                    if(ev1==0) ev1=2
                    if(ev2==0) ev2=2
                    if (sir==1)
                    {   var kartPuan=(suffle_puan[kart1]+suffle_puan[kart2])
                        var a=textViewPuan2.text.toString()
                        var v =0
                        if(suffle_ev[kart1]==suffle_ev[kart2]){
                            toplam=(Integer.parseInt(a)-(kartPuan/ev1))

                        }
                        else{
                            toplam=(Integer.parseInt(a)-kartPuan/2*ev1*ev2)

                        }
                        textViewPuan2.text=toplam.toString()
                        sira=2

                    }
                    else{var kartPuan=(suffle_puan[kart1]+suffle_puan[kart2])
                        var a=textView19.text.toString()
                        var v =0
                        if(suffle_ev[kart1]==suffle_ev[kart2]){
                            toplam=(Integer.parseInt(a)-(kartPuan/ev1))
                        }
                        else{
                            toplam=(Integer.parseInt(a)-kartPuan/2*ev1*ev2)
                        }
                        textView19.text=toplam.toString()
                        sira=1


                    }
                }
                fun puan_artı(i: Int) {
                    print("girdi")
                    var ev=suffle_ev[i]%2
                    if(ev==0) ev=2
                    println("doğru")
                    var a=textViewPuan2.text.toString()
                    var v=((2*suffle_puan[i]*ev)*(timeLeft/ 10.0))
                    toplam = (Integer.parseInt(a) + ((2*suffle_puan[i]*ev)*(timeLeft/ 10.0))).toInt()
                    textViewPuan2.text=toplam.toString()
                    println(suffle_ev[i])
                    println(suffle_puan[i])
                    println(suffle_name[i])
                    println(timeLeft)
                    println("artı $v")
                    println(toplam)

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
                            println("kjldfkdfkldsfldslşds")
                            println(mod)
                            if(mod=="tek")
                            {
                                puan_artı(number)
                            }
                            else{arti_coklu(number,sira)}
                            finish_counter+=1
                            if (finish_counter==8){
                                timerExt.cancel()
                                mediaPlayer4.start()
                                SüreBitti2.visibility=View.VISIBLE
                                SüreBitti2.text="Tebrikler"
                                buttonYeniOyun2.visibility=View.VISIBLE
                                buttonYeniOyun2.setOnClickListener() {
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

                bir.setOnClickListener(){if(bir.isClickable) control(0)}
                iki.setOnClickListener(){if(iki.isClickable)control(1)}
                üc.setOnClickListener(){if(bir.isClickable) control(2)}
                dört.setOnClickListener(){if(bir.isClickable) control(3)}
                bes.setOnClickListener(){if(bir.isClickable) control(4)}
                altı.setOnClickListener(){if(bir.isClickable) control(5)}
                yedi.setOnClickListener(){if(bir.isClickable) control(6)}
                sekiz.setOnClickListener(){if(bir.isClickable) control(7)}
                dokuz.setOnClickListener(){if(bir.isClickable) control(8)}
                on.setOnClickListener(){if(bir.isClickable) control(9)}
                onbir.setOnClickListener(){if(bir.isClickable) control(10)}
                oniki.setOnClickListener(){if(bir.isClickable) control(11)}
                onüc.setOnClickListener(){if(bir.isClickable) control(12)}
                ondört.setOnClickListener(){if(bir.isClickable) control(13)}
                onbeş.setOnClickListener(){if(bir.isClickable) control(14)}
                onaltı.setOnClickListener(){if(bir.isClickable) control(15)}

            }
            button.setOnClickListener()
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
                for(i in 0..15) image_list[i].visibility = View.INVISIBLE
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

