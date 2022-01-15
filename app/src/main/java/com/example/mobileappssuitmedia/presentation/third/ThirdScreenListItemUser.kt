package com.example.mobileappssuitmedia.presentation.third

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.mobileappssuitmedia.R
import com.example.mobileappssuitmedia.ui.theme.btnColor
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ThirdScreen(nav: NavHostController, vm: ThirdViewModels = hiltViewModel()) {
    val refreshResult = vm.isRefresh.collectAsState()
    val dataResult = vm.userData.value
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { nav.popBackStack() }, modifier = Modifier.weight(1F)) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Back Nav from third", tint = Color.Blue
                )
            }
            Text(
                text = stringResource(R.string.thirdscreens),
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
        if (dataResult.isEmpty()) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1F))
                Text(
                    modifier = Modifier.weight(1F),
                    text = "Empty",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = refreshResult.value),
                onRefresh = { vm.loadData() }) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(dataResult) { out ->
                        Surface(modifier = Modifier.clickable {
                            nav.previousBackStackEntry?.savedStateHandle?.set(
                                "selected",
                                "${out.first_name} ${out.last_name}"
                            )
                            nav.popBackStack()
                        }) {
                            ListOfItems(
                                userNameFirst = out.first_name,
                                userNameLast = out.last_name,
                                images = out.avatar,
                                userEmail = out.email
                            )
                        }
                        Divider(Modifier.fillMaxSize(0.85F), thickness = 1.dp)
                    }
                }
            }
        }
        if (dataResult.isNotEmpty()) {
            Button(
                onClick = { vm.loadNextPage() },
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = btnColor
                ), shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.nextpage),
                    fontSize = 17.sp,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun ListOfItems(userNameFirst: String, userNameLast: String, images: String, userEmail: String) {
    val painter = rememberImagePainter(images)
    Card(modifier = Modifier.size(340.dp, 80.dp)) {
        Row(
            Modifier
                .fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = "userPhotos",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape), contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "$userNameFirst $userNameLast",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = userEmail,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}