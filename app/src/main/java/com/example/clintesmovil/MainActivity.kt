package com.example.clintesmovil

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.clientestopicos.Adapter.Adapter
import com.example.clientestopicos.Model.Client
import com.example.clientestopicos.SQLhelper.BDHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal lateinit var db: BDHelper
    internal var lista:List<Client> = ArrayList<Client>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = BDHelper(this)
        refresh()
        buttonBorrar.setOnClickListener {
            if (!nIdentification.text.isEmpty()){
                var client = Client(name.text.toString(),lastName.text.toString(),phone.text.toString(),mail.text.toString(),Integer.parseInt(nIdentification.text.toString()))
                db.deleteClint(client)}
            refresh()
        }
        button.setOnClickListener {
            addUser()
        }
        buttonUpdate.setOnClickListener {
            if (!nIdentification.text.isEmpty()){
                var client = Client(name.text.toString(),lastName.text.toString(),phone.text.toString(),mail.text.toString(),Integer.parseInt(nIdentification.text.toString()))
                db.updateClint(client)
            }
            refresh()
        }
    }
    fun addUser(){
        if(!nIdentification.text.isEmpty()){
            var client = Client(name.text.toString(), lastName.text.toString(), phone.text.toString(), mail.text.toString(), Integer.parseInt(nIdentification.text.toString()))
            db.addClint(client)
            Toast.makeText(this,"Guardado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
        }
        refresh()
    }

    fun refresh() {
        name.text = null
        lastName.text= null
        phone.text = null
        mail.text = null
        nIdentification.text = null
        lista = db.listClient
        val adapter = Adapter(this@MainActivity,lista,name,lastName,phone,mail,nIdentification)
        listaClientes.adapter = adapter
    }
}
