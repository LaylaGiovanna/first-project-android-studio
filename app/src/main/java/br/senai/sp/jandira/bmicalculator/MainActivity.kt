package br.senai.sp.jandira.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.bmicalculator.calculate.calculate
import br.senai.sp.jandira.bmicalculator.calculate.getBmiClassification
import br.senai.sp.jandira.bmicalculator.model.Cliente
import br.senai.sp.jandira.bmicalculator.model.Product
import br.senai.sp.jandira.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val p1 = Product()
        p1.id = 100
        p1.productName = "Mouse sem fio"

        val c1 = Cliente(
            100,
            "Pedro",
            1500.0
        )

        setContent {
            BMICalculatorTheme {
                //como criamos a função separada lá em baixo, temos que chamar
                //ela aqui em cima, pois o onCreate é a primeira função a ser executada
                CalculatorScreen()

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CalculatorScreen() {
//    var heigthState = rememberSaveable {
//        mutableStateOf("")
//    }

    var weigthState by rememberSaveable {
        mutableStateOf("")
    }
    var heigthState by rememberSaveable {
        mutableStateOf("")
    }
    var bmiState by rememberSaveable {
        mutableStateOf("0.0")
    }

    var bmiClassificationState by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current.applicationContext
    val context2 = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {
        //HEADER
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bmi),
                    contentDescription = "",
                )
                Text(
                    text = stringResource(id = R.string.title),
                    fontSize = 32.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp
                )


            }
            //FORM
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Text(
                    text = stringResource(id = R.string.weigth_label),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = weigthState,
                    onValueChange = {
                        Log.i("ds2m", it)
                        weigthState = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(13.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Text(
                    text = stringResource(id = R.string.heigth_label),
                    modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)
                )
                OutlinedTextField(
                    value = heigthState,
                    onValueChange = {
                        heigthState = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(13.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(42.dp))
                Button(

                    onClick = {

                        var w = weigthState.toDouble()
                        var h = heigthState.toDouble()
                        var bmi = calculate(w, h)
                        bmiState = String.format("%.2f", bmi)
                        bmiClassificationState = getBmiClassification(bmi, context)

                        //mesma forma de fazer o cod acima, porém de forma melhorada
//                        bmiState.value = calculate(
//                            weight = weigthState.value.toDouble(),
//                            heigth = heigthState.value.toDouble()
//                        ).toString()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                )
                {
                    Text(
                        text = stringResource(id = R.string.button_calculate),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            //FOOTER
            Column() {
                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(
                        topStart = 36.dp, topEnd = 36.dp
                    ),
                    backgroundColor = Color(
                        red = 79,
                        green = 54,
                        blue = 232
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.your_score),
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = bmiState,
                            color = Color.White,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = bmiClassificationState,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                        Row() {
                            Button(
                                onClick = {
                                    weigthState = ""
                                    heigthState = ""
                                    bmiClassificationState = ""
                                    bmiState = "0.0"

                                }) {
                                Text(text = stringResource(id = R.string.reset))
                            }
                            //SPACER -
                            Spacer(modifier = Modifier.width(42.dp))

                            Button(
                                onClick = {
                                val openOther = Intent(context2, SignUpActivity::class.java)
                                context2.startActivity(openOther)
                            }) {
                                Text(text = stringResource(id = R.string.share))
                            }

                        }
                    }
                }
            }
        }
    }
}