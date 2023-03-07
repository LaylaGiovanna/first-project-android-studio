package br.senai.sp.jandira.bmicalculator.calculate

import android.content.Context
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import br.senai.sp.jandira.bmicalculator.R
import kotlin.math.pow


fun calculate(weight: Double, heigth: Double) = weight / (heigth / 100).pow(2)
                                                        //pow - potencia

fun getBmiClassification(bmi: Double, context: Context) : String{

    return if (bmi < 18.5){
        context.getString(R.string.underweight)
    }else if (bmi >= 18.5 && bmi < 25.0) {
        context.getString(R.string.normal_weight)
    }else if(bmi >= 25.0 && bmi < 30.0){
        context.getString(R.string.over_weight)
    }else if(bmi >= 30.0 && bmi < 40.0){
        context.getString(R.string.obesity)
    }else{
        context.getString(R.string.morbid_obesity)
    }

}

//fun calculate(weight: Double, heigth: Double): Double {
//    return weight / heigth.pow(2)
//}