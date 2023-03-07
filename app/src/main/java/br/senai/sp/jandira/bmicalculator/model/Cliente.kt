package br.senai.sp.jandira.bmicalculator.model

data class Cliente (
    var id:Int,
    var name: String,
    var salary: Double
    //var salary: Double = 0.0
    //para não precisar declarar o salary la na main
        )