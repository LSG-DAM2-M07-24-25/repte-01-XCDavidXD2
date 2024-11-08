import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.Color
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStudioKoalaTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PersonalizacionIcono(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PersonalizacionIcono(modifier: Modifier = Modifier) {
    var min by remember { mutableStateOf("") }
    var max by remember { mutableStateOf("") }
    var sliderValue: Float by remember{ mutableStateOf(0f) }
    var finishValue: String by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Call") }
    var selectedIcon by remember { mutableStateOf(Icons.Default.Call) }

    val options = listOf(
        "Call" to Icons.Default.Call,
        "Email" to Icons.Default.Email,
        "Home" to Icons.Default.Home,
        "Add" to Icons.Default.Add,
        "Notifications" to Icons.Default.Notifications
    )


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth(),
            label = { Text("Select Option") },
            leadingIcon = {
                Icon(imageVector = selectedIcon, contentDescription = selectedText)
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { (text, icon) ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = text
                        selectedIcon = icon
                        expanded = false
                    },
                    text = { Text(text) },
                    leadingIcon = { Icon(icon, contentDescription = text) }
                )
            }
        }
        Row {
            Text("Max:")
            TextField(
                value = max, onValueChange = {max = it}

            )
            Text("Min:")
            TextField(
                value = min, onValueChange = {min = it}
            )
        }
        Row {
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                onValueChangeFinished = { finishValue = sliderValue.toString() },
                valueRange = 0f..10f,
                steps = 9,
                enabled = true
            )

            Text(text = finishValue)
        }
        BadgedBox(modifier = Modifier
            .padding(20.dp, 0.dp),
            badge = {
                Badge(
                    containerColor = Color.Yellow,
                    contentColor = Color.Yellow,
                )
            }
        ) {
           
        }
    }

}