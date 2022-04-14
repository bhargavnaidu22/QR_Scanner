package com.gbnsolutions.qrscanner.Model

class Productinf {
    private var expdt: String =""
    private var proinf: String = ""
    private var maninf: String = ""
    private var coninf: String = ""

    constructor(expdt: String, proinf: String, maninf: String, coninf: String) {
        this.expdt = expdt
        this.proinf = proinf
        this.maninf = maninf
        this.coninf = coninf
    }

    constructor()

    fun getExpdt():String?{
        return expdt
    }
    fun setExpdt(expdt: String){
        this.expdt = expdt
    }
    fun getProinf(): String?{
        return proinf
    }
    fun setProinf(proinf: String){
        this.proinf = proinf
    }
    fun getManinf(): String?{
        return maninf
    }
    fun setManinf(maninf: String){
        this.maninf = maninf
    }
    fun getConinf():String?{
        return coninf
    }
    fun setConinf(coninf: String){
        this.coninf = coninf
    }
}