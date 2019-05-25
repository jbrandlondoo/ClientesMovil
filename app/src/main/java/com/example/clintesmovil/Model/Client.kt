package com.example.clientestopicos.Model

class Client{
    var name:String?=null
    var lastName:String?=null
    var phone:String?=null
    var email:String?=null
    var nIdentification:Int?=0

    constructor(){


    }
    constructor(name:String,lastName:String,phone:String,email:String,nIdentification:Int){

        this.name = name
        this.lastName = lastName
        this.phone = phone
        this.email = email
        this.nIdentification = nIdentification
    }
}