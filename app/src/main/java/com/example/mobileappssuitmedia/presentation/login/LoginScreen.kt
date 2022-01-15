package com.example.mobileappssuitmedia.presentation.login

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mobileappssuitmedia.R
import com.example.mobileappssuitmedia.navigation.NavigatorHolder
import com.example.mobileappssuitmedia.paliandrome.PaliandromeChecker
import com.example.mobileappssuitmedia.ui.theme.btnColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(nav: NavHostController, vm: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val focusMgr = LocalFocusManager.current
    val requester = BringIntoViewRequester()
    val scope = rememberCoroutineScope()
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background images",
            contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 180.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusMgr.clearFocus()
                    })
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.btnadd),
                contentDescription = "add", modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = vm.nameText.value,
                onValueChange = vm::setNameText,
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .bringIntoViewRequester(requester)
                    .onFocusEvent {
                        if (it.isFocused) {
                            scope.launch {
                                delay(200)
                                requester.bringIntoView()
                            }
                        }
                    },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black
                ),
                maxLines = 1,
                singleLine = true,
                placeholder = { Text(text = stringResource(R.string.nametextkeyboard)) },
                shape = RoundedCornerShape(18.dp), keyboardActions = KeyboardActions(onDone = {
                    focusMgr.clearFocus()
                })
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = vm.paliandromeText.value,
                onValueChange = vm::setPaliandromeChange,
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .bringIntoViewRequester(requester)
                    .onFocusEvent {
                        if (it.isFocused) {
                            scope.launch {
                                delay(200)
                                requester.bringIntoView()
                            }
                        }
                    },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black
                ),
                maxLines = 1,
                singleLine = true,
                placeholder = { Text(text = stringResource(R.string.paliandrometext)) },
                shape = RoundedCornerShape(18.dp), keyboardActions = KeyboardActions(onDone = {
                    focusMgr.clearFocus()
                })
            )
            Spacer(modifier = Modifier.height(27.dp))
            Button(
                onClick = {
                    val res = PaliandromeChecker.paliandromeCheck(vm.paliandromeText.value)
                    if (res) Toast.makeText(context, context.getString(R.string.paliandromedetected), Toast.LENGTH_SHORT).show()
                    else Toast.makeText(context, context.getString(R.string.notpaliandrome), Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = btnColor),
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Check", fontSize = 17.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    if (vm.nameText.value.isNotEmpty()) nav.navigate(
                        "${NavigatorHolder.SecondScreen.route}/${vm.nameText.value}"
                    )
                    else Toast.makeText(context, context.getString(R.string.namewarning), Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = btnColor),
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Next", fontSize = 17.sp, color = Color.White)
            }
        }
    }
}

