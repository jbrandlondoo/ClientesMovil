package com.example.clientestopicos.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.clientestopicos.Model.Client
import com.example.clintesmovil.R
import kotlinx.android.synthetic.main.row_layout.view.*


class Adapter(
    internal var activity: Activity,
    internal var lista:List<Client>,
    internal var name:EditText,
    internal var lastName:EditText,
    internal var phone:EditText,
    internal var mail:EditText,
    internal var nIdentification:EditText
    ): BaseAdapter() {

    internal var inflater:LayoutInflater
        init {
            inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val rowView: View
        rowView = inflater.inflate(R.layout.row_layout,null)
        rowView.textName.text = lista[position].name.toString()
        rowView.textLName.text = lista[position].lastName.toString()
        rowView.textID.text = lista[position].nIdentification.toString()
        rowView.textMail.text = lista[position].email.toString()
        rowView.textPhone.text = lista[position].phone.toString()

        rowView.setOnClickListener{
            name.setText(rowView.textName.text.toString())
            lastName.setText( rowView.textLName.text.toString())
            phone.setText(rowView.textPhone.text.toString())
            mail.setText(rowView.textMail.text.toString())
            nIdentification.setText(rowView.textID.text.toString())
        }
        return rowView
    }

    override fun getItem(position: Int): Any {
        return lista[position]
    }

    override fun getItemId(position: Int): Long {
        return lista[position].nIdentification!!.toLong()
    }

    override fun getCount(): Int {
        return lista.size
    }

}