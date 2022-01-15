package com.example.mobileappssuitmedia.presentation.second

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mobileappssuitmedia.R
import com.example.mobileappssuitmedia.navigation.NavigatorHolder
import com.example.mobileappssuitmedia.ui.theme.btnColor
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun SecondScreen(nav: NavHostController, username: String) {
    var selectedName by remember {
        mutableStateOf("Selected Username")
    }
    val observers = LocalLifecycleOwner.current
        nav.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("selected")?.
        observe(observers) {
            selectedName = it
        }
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row(
            Modifier
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { nav.popBackStack() }, modifier = Modifier.weight(1F)) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Back Nav",
                    tint = Color.Blue
                )
            }
            Text(
                text = stringResource(R.string.secondscreen),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6F),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1F))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.greetingssecondscreen),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = username,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = selectedName,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center, modifier = Modifier.weight(1F)
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
                    .weight(1F)
                    .navigationBarsPadding(), contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { nav.navigate(NavigatorHolder.ThirdScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth(0.9F)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = btnColor),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.btnchooseuser),
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}
