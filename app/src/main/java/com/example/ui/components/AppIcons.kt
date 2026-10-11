package com.example.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object AppIcons {
    val Back = Icons.Default.ArrowBack
    val Check = Icons.Default.Check
    val Close = Icons.Default.Close

    // Custom crafted vector for Pulsa (Smartphone)
    val Phone: ImageVector by lazy {
        ImageVector.Builder(
            name = "Phone",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(17f, 1.01f)
                lineTo(7f, 1f)
                curveTo(5.9f, 1f, 5f, 1.9f, 5f, 3f)
                verticalLineToRelative(18f)
                curveToRelative(0f, 1.1f, 0.9f, 2f, 2f, 2f)
                horizontalLineToRelative(10f)
                curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                verticalLineTo(3f)
                curveToRelative(0f, -1.1f, -0.9f, -1.99f, -2f, -1.99f)
                close()
                moveTo(17f, 19f)
                horizontalLineTo(7f)
                verticalLineTo(5f)
                horizontalLineToRelative(10f)
                verticalLineToRelative(14f)
                close()
            }
        }.build()
    }

    // Custom crafted vector for Token PLN (Electricity Bolt)
    val Bolt: ImageVector by lazy {
        ImageVector.Builder(
            name = "Bolt",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(11f, 21f)
                horizontalLineToRelative(-1f)
                lineToRelative(1f, -7f)
                horizontalLineTo(7.5f)
                curveToRelative(-0.58f, 0f, -0.57f, -0.32f, -0.38f, -0.66f)
                curveToRelative(0.19f, -0.34f, 0.05f, -0.08f, 0.07f, -0.12f)
                curveTo(8.48f, 10.94f, 10.42f, 7.54f, 13f, 3f)
                horizontalLineToRelative(1f)
                lineToRelative(-1f, 7f)
                horizontalLineToRelative(3.5f)
                curveToRelative(0.49f, 0f, 0.56f, 0.33f, 0.47f, 0.51f)
                lineToRelative(-0.07f, 0.15f)
                curveTo(12.97f, 17.55f, 11f, 21f, 11f, 21f)
                close()
            }
        }.build()
    }

    // Custom crafted vector for Tagihan PPOB (Receipt/Bill)
    val Receipt: ImageVector by lazy {
        ImageVector.Builder(
            name = "Receipt",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(18f, 17f)
                horizontalLineTo(6f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(12f)
                verticalLineToRelative(2f)
                close()
                moveTo(18f, 13f)
                horizontalLineTo(6f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(12f)
                verticalLineToRelative(2f)
                close()
                moveTo(18f, 9f)
                horizontalLineTo(6f)
                verticalLineTo(7f)
                horizontalLineToRelative(12f)
                verticalLineToRelative(2f)
                close()
                moveTo(3f, 22f)
                lineToRelative(1.5f, -1.5f)
                lineTo(6f, 22f)
                lineToRelative(1.5f, -1.5f)
                lineTo(9f, 22f)
                lineToRelative(1.5f, -1.5f)
                lineTo(12f, 22f)
                lineToRelative(1.5f, -1.5f)
                lineTo(15f, 22f)
                lineToRelative(1.5f, -1.5f)
                lineTo(18f, 22f)
                lineToRelative(1.5f, -1.5f)
                lineTo(21f, 22f)
                verticalLineTo(2f)
                lineToRelative(-1.5f, 1.5f)
                lineTo(18f, 2f)
                lineToRelative(-1.5f, 1.5f)
                lineTo(15f, 2f)
                lineToRelative(-1.5f, 1.5f)
                lineTo(12f, 2f)
                lineToRelative(-1.5f, 1.5f)
                lineTo(9f, 2f)
                lineToRelative(-1.5f, 1.5f)
                lineTo(6f, 2f)
                lineToRelative(-1.5f, 1.5f)
                lineTo(3f, 2f)
                verticalLineToRelative(20f)
                close()
            }
        }.build()
    }

    // Custom crafted vector for Beli Stablecoin (Crypto Currency Tether/Swap)
    val Crypto: ImageVector by lazy {
        ImageVector.Builder(
            name = "Crypto",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveToRelative(4.48f, 10f, 10f, 10f)
                reflectiveCurveToRelative(10f, -4.48f, 10f, -10f)
                reflectiveCurveTo(17.52f, 2f, 12f, 2f)
                close()
                moveTo(13f, 17f)
                horizontalLineToRelative(-2f)
                verticalLineToRelative(-2f)
                curveToRelative(-1.8f, -0.1f, -3.2f, -0.7f, -3.2f, -1.4f)
                horizontalLineToRelative(2f)
                curveToRelative(0.2f, 0.3f, 1f, 0.5f, 2.2f, 0.5f)
                curveToRelative(1.1f, 0f, 1.8f, -0.2f, 1.8f, -0.6f)
                curveToRelative(0f, -0.4f, -0.6f, -0.6f, -2.2f, -0.8f)
                curveToRelative(-2.2f, -0.3f, -3.8f, -0.9f, -3.8f, -2.1f)
                curveToRelative(0f, -1.2f, 1.6f, -1.9f, 3.2f, -2.1f)
                verticalLineTo(7f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(2f)
                curveToRelative(1.5f, 0.1f, 2.7f, 0.6f, 2.9f, 1.3f)
                horizontalLineToRelative(-2f)
                curveToRelative(-0.2f, -0.2f, -0.8f, -0.4f, -1.9f, -0.4f)
                curveToRelative(-1f, 0f, -1.6f, 0.2f, -1.6f, 0.5f)
                curveToRelative(0f, 0.4f, 0.6f, 0.5f, 2.1f, 0.8f)
                curveToRelative(2.4f, 0.3f, 3.9f, 1f, 3.9f, 2.2f)
                curveToRelative(0f, 1.1f, -1.4f, 1.9f, -3.4f, 2.1f)
                verticalLineToRelative(1.5f)
                close()
            }
        }.build()
    }
}
