package com.example.unit2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit2.ui.theme.Unit2Theme
import java.text.NumberFormat
import kotlin.math.ceil

class CalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Unit2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}
@Composable
fun TipTimeLayout(){
    var amountInput by remember{ mutableStateOf("") }
    var tipInput by remember{ mutableStateOf("") }
    var roundUp by remember{ mutableStateOf(false)}
    val amount=amountInput.toDoubleOrNull() ?:0.0
    val tipPercent=tipInput.toDoubleOrNull() ?:0.0

    val tip=calculateTip(amount,tipPercent,roundUp)
    Column(modifier = Modifier
        .padding(50.dp)
        .verticalScroll(rememberScrollState()).safeDrawingPadding(),
    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = stringResource(id = R.string.calculate_tip), modifier = Modifier
            .padding(bottom = 16.dp, top = 40.dp)
            .align(alignment = Alignment.Start))
        EditNumberField(label = R.string.bill_amount, leadingIcon = R.drawable.money,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next), value = amountInput, onValueChanged ={amountInput=it}, modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),)
        EditNumberField(label = R.string.how_was_the_service, leadingIcon = R.drawable.percent,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done), value = amountInput, onValueChanged ={tipInput=it}, modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),)
        RoundTheTipRow(round=roundUp, onRoundChanged = { roundUp = it },modifier=Modifier.padding(bottom = 32.dp))
        Text(text = stringResource(id = R.string.tip_amout, tip), style = MaterialTheme.typography.bodySmall)
        Spacer(modifier =Modifier.height(150.dp))
    }
}
@Composable
fun RoundTheTipRow(round: Boolean, onRoundChanged: (Boolean) -> Unit, modifier: Modifier) {

    Row(modifier= modifier
        .fillMaxWidth()
        .size(48.dp), verticalAlignment = Alignment.CenterVertically){
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(checked = round, onCheckedChange = onRoundChanged,modifier= Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End))
    }
}

fun calculateTip(amount: Double, tipPercent: Double=15.0,round: Boolean): String {
    var tip = amount*tipPercent/100

    if(round){
        tip= ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun EditNumberField(
    @StringRes label:Int,
    @DrawableRes leadingIcon:Int,
    keyboardOptions: KeyboardOptions,
    value:String,
    onValueChanged:(String)->Unit,
    modifier: Modifier=Modifier
){
    TextField(
        value = value,
        singleLine = true,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    Unit2Theme {
        TipTimeLayout()
    }
}