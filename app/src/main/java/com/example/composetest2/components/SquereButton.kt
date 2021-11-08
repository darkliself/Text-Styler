package com.example.composetest2.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun SquareButton(
    text: String,
    modifier: Modifier,
    @DrawableRes image: Int
) {
    Box(
        modifier
            .fillMaxWidth(0.48f)
            .fillMaxHeight(0.15f),
        Alignment.Center
    ) {
        Image(
            ImageVector.vectorResource(id = image),
            contentDescription = null
        )
        Text(text, Modifier.align(BiasAlignment(0f, 0.25f)))
    }
}