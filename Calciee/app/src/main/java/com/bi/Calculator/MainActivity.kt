package com.bi.Calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bi.temperatureconverterapp.R


class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? =null
    var lastNumeric : Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput= findViewById(R.id.tvInput)
    }

    fun OnDigit(view: View){
        tvInput?.append((view as Button).text)
        lastDot=false
        lastNumeric= true
    }

    fun onClear(view:View){
        tvInput?.text= ""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot=true
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false

            }
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue= tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    var splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    var result = one.toDouble()-two.toDouble()

                    if(prefix.isNotEmpty()){
                        one =  prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot(result.toString())
                }


                else if(tvValue.contains("+")){
                    var splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    var result = one.toDouble()+two.toDouble()

                    if(prefix.isNotEmpty()){
                        one =  prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot(result.toString())
                }


                if(tvValue.contains("*")){
                    var splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    var result = one.toDouble() * two.toDouble()

                    if(prefix.isNotEmpty()){
                        one =  prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot(result.toString())
                }

                if(tvValue.contains("/")){
                    var splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    var result = one.toDouble()/two.toDouble()

                    if(prefix.isNotEmpty()){
                        one =  prefix + one
                    }



                    tvInput?.text = removeZeroAfterDot(result.toString())
                }



            }
            catch(e :ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    private fun removeZeroAfterDot(result: String): String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length-2)
        }

        return value
    }

    private fun isOperatorAdded(value : String): Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }



}