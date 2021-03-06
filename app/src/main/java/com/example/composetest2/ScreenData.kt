package com.example.composetest2

import android.os.Parcelable
import android.provider.ContactsContract
import com.example.composetest2.logic.DecorationSide
import kotlinx.parcelize.Parcelize


@Parcelize
data class ScreenData(
    var nickname: String = "",
    val rootNode: String,
    var root: String = "",
    var prefix: String = "",
    var suffix: String = "",
    var alphabetIndex: Int = 0,
    var rootAsCodeList: List<String> = listOf(),
    var side: DecorationSide = DecorationSide.LEFT,
) : Parcelable




