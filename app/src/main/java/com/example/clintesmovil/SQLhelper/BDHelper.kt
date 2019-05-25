package com.example.clientestopicos.SQLhelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.clientestopicos.Model.Client

class BDHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null , DATABASE_VER){
    companion object {

        private val DATABASE_VER = 1
        private val DATABASE_NAME = "Clientes.db"
        //table
        var TABLE_NAME = "cliente"
        var COL_NAME = "Name"
        var COL_LASTNAME = "LastName"
        var COL_EMAIL = "Email"
        var COL_PHONE = "Phone"
        var COL_NIDENTIFICATION = "Nidentification"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_QUERY:String = ("CREATE TABLE "+TABLE_NAME+"("+COL_NIDENTIFICATION+" INT PRIMARY KEY NOT NULL,"+COL_NAME+" TEXT,"+COL_LASTNAME+" TEXT,"+COL_EMAIL+" TEXT,"+COL_PHONE+" TEXT)")
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME)
        onCreate(db)
    }

    val listClient:List<Client>
        get() {
            val listClient = ArrayList<Client>()
            val slectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(slectQuery,null)
            if (cursor.moveToFirst()){
                do {
                    val client = Client()
                    client.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    client.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
                    client.phone = cursor.getString(cursor.getColumnIndex(COL_PHONE))
                    client.lastName = cursor.getString(cursor.getColumnIndex(COL_LASTNAME))
                    client.nIdentification = cursor.getInt(cursor.getColumnIndex(COL_NIDENTIFICATION))

                    listClient.add(client)
                }while (cursor.moveToNext())

            }
            db.close()
            return listClient
        }
    //@Throws(SQLiteConstraintException::class)
    fun addClint(client:Client):Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME,client.name)
        values.put(COL_LASTNAME,client.lastName)
        values.put(COL_EMAIL,client.email)
        values.put(COL_PHONE,client.phone)
        values.put(COL_NIDENTIFICATION,client.nIdentification)
        db.insert(TABLE_NAME,null,values)
        db.close()
        return true
    }

    //@Throws(SQLiteConstraintException::class)
    fun updateClint(client:Client):Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME,client.name)
        values.put(COL_LASTNAME,client.lastName)
        values.put(COL_EMAIL,client.email)
        values.put(COL_PHONE,client.phone)
        values.put(COL_NIDENTIFICATION,client.nIdentification)
        return db.update(TABLE_NAME,values,"$COL_NIDENTIFICATION=?", arrayOf(client.nIdentification.toString()))

    }

    fun deleteClint(client:Client){
        val db = this.writableDatabase
        if(arrayOf(client.nIdentification.toString())==null){

        }else{
            db.delete(TABLE_NAME,"$COL_NIDENTIFICATION=?", arrayOf(client.nIdentification.toString()))
        }
        db.close()
    }

}