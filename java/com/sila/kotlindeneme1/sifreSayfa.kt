package com.sila.kotlindeneme1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class sifreSayfa: AppCompatActivity() {
    private lateinit var isim: EditText
    private lateinit var eskiSifre: EditText
    private lateinit var yeniSifre:EditText
    private lateinit var baslik:TextView
    private lateinit var dbref: DatabaseReference
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sifre)
        isim = findViewById(R.id.editTextTextPersonName3)
        eskiSifre = findViewById(R.id.editTextTextPersonName4)
        yeniSifre = findViewById(R.id.editTextTextPersonName6)
        baslik=findViewById(R.id.textView5)
        var sifreYenile = findViewById(R.id.button2) as Button
        dbref= FirebaseDatabase.getInstance().getReference("oyuncuEkle")
        sifreYenile.setOnClickListener()
        {
            var userName = isim.text.toString();
            var passwordE = eskiSifre.text.toString();
            var passwordY = yeniSifre.text.toString();
            //var email=yeniSifre.text.toString();
            if (userName.isEmpty()||passwordE.isEmpty()||passwordY.isEmpty())
            {
                eskiSifre.error="Tüm alanları lütfen doldurunuz"

            }
            else{
                dbref = Firebase.database.getReference("oyuncuEkle")
                var getdata = object : ValueEventListener {

                    @SuppressLint("SetTextI18n")
                    override fun onDataChange(snapshot: DataSnapshot) {
                        //var sb=StringBuilder()
                        for(i in snapshot.children){
                            var usname=i.child("kulAdı").getValue().toString()
                            if(userName==usname){
                                var passwordU=i.child("sifre").getValue().toString()
                                var email2 =i.child("mail").getValue().toString()
                                var ıd=i.child("kulId").getValue().toString()
                                auth= FirebaseAuth.getInstance()
                                auth.signInWithEmailAndPassword(email2,passwordE).addOnCompleteListener { task ->
                                    if(task.isSuccessful){
                                        val user=auth.currentUser
                                        user?.updatePassword(passwordY)?.addOnCompleteListener(){
                                            if(it.isSuccessful){
                                                dbref.child(ıd).child("sifre").setValue(passwordY)
                                                val intent=Intent(applicationContext,MainActivity::class.java)
                                                baslik.setText("güncelleme başarılı")
                                            }
                                            else baslik.setText("Hatalı Deneme")
                                        }
                                    }
                                    else{
                                        isim.error="Lütfen Kullanıcı Adı ve şifreyi kontrol ediniz"
                                    }
                            }
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

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}