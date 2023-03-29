package com.sila.kotlindeneme1

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity()
{   lateinit var et_password:EditText
    lateinit var et_user_name:EditText
    lateinit var  auth: FirebaseAuth
    val kulAdiListe = ArrayList<String>()
    lateinit var email2 :String

    private lateinit var dbref: DatabaseReference

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        println("ooooooooooooooooooo")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        dbref=FirebaseDatabase.getInstance().getReference("oyuncuEkle")

        // Şifre kısmı yazısı
        val mTextView = findViewById<TextView>(R.id.textView4)
        val sifreDegistirme = "Şifre değiştirmek için tıklayınız"
        val mSpannableString = SpannableString(sifreDegistirme)

        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
        mTextView.text = mSpannableString
        mTextView.setOnClickListener(){
            val bundle2 = Bundle()
            val intent2 = Intent(applicationContext, sifreSayfa::class.java)
            intent2.putExtras(bundle2)
            startActivity(intent2)
        }
        et_user_name = findViewById(R.id.editTextTextPersonName)
        et_password = findViewById(R.id.editTextTextPersonName2)
        val btn_1 = findViewById(R.id.button1) as Button
        val intent= Intent(this,baslan::class.java)
        btn_1.setOnClickListener(){
            val userName = et_user_name.text.toString()
            val password = et_password.text.toString();

            if (userName.isEmpty()||password.isEmpty()){
                et_user_name.error="Kullanıcı Adını ve parola bos birakılamaz"
            }


            else
            {
                dbref = Firebase.database.getReference("oyuncuEkle")
                var getdata = object : ValueEventListener{

                    override fun onDataChange(snapshot: DataSnapshot) {
                        //var sb=StringBuilder()

                        for(i in snapshot.children){
                            println(i)
                            var usname=i.child("kulAdı").getValue()
                            if(userName==usname){
                                println(usname)
                                email2= i.child("mail").getValue().toString()
                                println(email2)
                                auth= FirebaseAuth.getInstance()
                                auth.signInWithEmailAndPassword(email2,password).addOnCompleteListener { task ->
                                    if(task.isSuccessful){
                                        startActivity(intent)
                                    }
                                    else{
                                        et_user_name.error="Lütfen Kullanıcı Adı ve şifreyi kontrol ediniz"
                                    }
                                }
                                break
                            }
                            //kulAdiListe.add("${i.key} $usname ")
                        }

                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                //dbref.addValueEventListener(getdata)
                dbref.addListenerForSingleValueEvent(getdata)

            }
        }
        var btn_2 = findViewById(R.id.button8) as Button
        btn_2.setOnClickListener(){
            val intent = Intent(applicationContext, kayitOl::class.java)
            startActivity(intent)
        }
        /*var kontrol = findViewById(R.id.button6) as Button
        kontrol.setOnClickListener() {
            val intent2 = Intent(applicationContext, secenek::class.java)
            startActivity(intent2)
        }*/
    }
}