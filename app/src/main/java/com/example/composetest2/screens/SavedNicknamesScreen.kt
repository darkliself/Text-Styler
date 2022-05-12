package com.example.composetest2.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composetest2.R
import com.example.composetest2.navigation.Screen
import com.example.composetest2.components.*
import com.example.composetest2.util.TextStyler
import com.example.composetest2.model.nickname.NicknameData
import com.example.composetest2.model.screendata.ScreenData
import com.example.composetest2.viewmodel.NicknameViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun SavedNicknamesScreen(navController: NavController) {
    val repo = NicknameViewModel(LocalContext.current)
    val scope = rememberCoroutineScope()
    var result by remember { mutableStateOf(mutableMapOf<String, NicknameData>()) }
    scope.launch {
        if (repo.readFromProto().isNotEmpty()) result = repo.readFromProto().toMutableMap()
    }

    Background(image = R.drawable.view_03_08_bg)

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f),
    ) {
        SmallButton(
            modifier = Modifier.fillMaxHeight(),
            image = R.drawable.arrow_left_icon,
            onClick = {
                navController.popBackStack()
            }
        )
        Header(
            "Saved",// stringResource(id = R.string.view_08_btn_header),
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
    Column(
        Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.fillMaxHeight(0.1f))

        if (result.any()) {
            LazyColumn(
                Modifier.fillMaxSize(),
            ) {
                var index = 0
                result.forEach { (key, value) ->
                    item() {
                        LazyColumnItem2(
                            modifier = Modifier.padding(start = 20.dp),
                            text = "${value.prefix}${
                                TextStyler.rebuildToString(
                                    value.rootAsCodeList,
                                    value.alphabetIndex
                                )
                            }${value.suffix}",
                            onIconClick = {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "data",
                                    ScreenData(
                                        prefix = value.prefix,
                                        suffix = value.suffix,
                                        rootAsCodeList = value.rootAsCodeList,
                                        alphabetIndex = value.alphabetIndex,
                                        rootNode = Screen.SavedNicknamesScreen.route
                                    )
                                )
                                navController.navigate(Screen.CustomizeNickNameScreen.route)
                            },
                            onRemoveIconClick = {
                                scope.launch {
                                    result.remove(key)
                                    repo.removeFromProto(key)
                                }
                            },
                            selected = true
                        )
                    }
                }
            }
        } else {
            var mutable by remember { mutableStateOf(false) }
            scope.launch {
                delay(200)
                mutable = true
            }
            if (mutable) {
                Text("Your storage is empty")
            } else {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize().align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SavedNicknamesScreen(navController = NavController(LocalContext.current))
}

