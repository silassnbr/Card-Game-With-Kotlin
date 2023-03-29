package com.sila.kotlindeneme1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class kayitOl : AppCompatActivity()
{
    private lateinit var et_password: EditText
    private lateinit var et_user_name: EditText
    private lateinit var et_gmail: EditText
    private lateinit var dbref: DatabaseReference
    private lateinit var  auth: FirebaseAuth
    private lateinit var baslik:TextView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kayit_ekran)
        dbref = FirebaseDatabase.getInstance().getReference("oyuncuEkle")
        et_user_name = findViewById(R.id.editTextTextPersonName5)
        et_password = findViewById(R.id.editTextTextPersonName7)
        et_gmail = findViewById(R.id.editTextTextPersonName8)
        var butonKyt = findViewById(R.id.button3) as Button
        butonKyt.setOnClickListener()
        {
            var userName = et_user_name.text.toString();
            var password = et_password.text.toString();
            var email = et_gmail.text.toString();
            if (userName.isEmpty() || password.isEmpty() || email.isEmpty())
                {
                et_user_name.error = "Kullanıcı Adın ve parola ve mail bos birakılamaz"
                }
            else if (password.length<6)
            {   val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("şifre en az 6 karakterli olmalıdır")
                    .setCancelable(false)
                    .setPositiveButton(
                        "Tamam",
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                        })
                val alert = dialogBuilder.create()
                alert.setTitle("UYARI")
                alert.show()
            }
            else
                {
                    dbref = Firebase.database.getReference("oyuncuEkle")
                    var getdata = object : ValueEventListener {
                        @SuppressLint("SetTextI18n")
                        override fun onDataChange(snapshot: DataSnapshot) {
                            //var sb=StringBuilder()
                            for (i in snapshot.children) {
                                var usname = i.child("kulAdı").getValue().toString()
                                if (userName == usname) {
                                    et_user_name.error="Kullanıcı adı geçersiz"
                                    userName=""
                                    break
                                }
                            }
                            if(userName!="") {
                                    FirebaseAuth.getInstance()
                                        .createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                var kulId = dbref.push().key
                                                var oyuncu =
                                                    oyuncuEkle(kulId, userName, password, email)
                                                dbref.child(kulId.toString()).setValue((oyuncu))
                                            }

                                        }
                                    baslik.setText("Kayıt Oluşturuldu")
                                    //kulAdiListe.add("${i.key} $usname ")
                                }

                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    }

                    dbref.addListenerForSingleValueEvent(getdata)

                    }
                }
        }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    }


