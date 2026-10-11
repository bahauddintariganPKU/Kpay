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
            path(fill = SolidColor(Color.Black)) {
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
            path(fill = SolidColor(Color.Black)) {
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
            path(fill = SolidColor(Color.Black)) {
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
            path(fill = SolidColor(Color.Black)) {
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

    // --- Bottom Navigation Icons ---

    // 1. Home / Beranda
    val NavHome: ImageVector by lazy {
        ImageVector.Builder(
            name = "NavHome",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(10f, 20f)
                verticalLineToRelative(-6f)
                horizontalLineToRelative(4f)
                verticalLineToRelative(6f)
                horizontalLineToRelative(5f)
                verticalLineToRelative(-8f)
                horizontalLineToRelative(3f)
                lineTo(12f, 3f)
                lineTo(2f, 12f)
                horizontalLineToRelative(3f)
                verticalLineToRelative(8f)
                close()
            }
        }.build()
    }

    // 2. Dompet / Wallet
    val NavWallet: ImageVector by lazy {
        ImageVector.Builder(
            name = "NavWallet",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(21f, 18f)
                verticalLineToRelative(1f)
                curveToRelative(0f, 1.1f, -0.9f, 2f, -2f, 2f)
                horizontalLineTo(5f)
                curveToRelative(-1.11f, 0f, -2f, -0.9f, -2f, -2f)
                verticalLineTo(5f)
                curveToRelative(0f, -1.1f, 0.89f, -2f, 2f, -2f)
                horizontalLineToRelative(14f)
                curveToRelative(1.1f, 0f, 2f, 0.9f, 2f, 2f)
                verticalLineToRelative(1f)
                horizontalLineToRelative(-9f)
                curveToRelative(-1.11f, 0f, -2f, 0.9f, -2f, 2f)
                verticalLineToRelative(8f)
                curveToRelative(0f, 1.1f, 0.89f, 2f, 2f, 2f)
                horizontalLineToRelative(9f)
                close()
                moveTo(12f, 16f)
                horizontalLineToRelative(10f)
                verticalLineTo(8f)
                horizontalLineTo(12f)
                verticalLineToRelative(8f)
                close()
                moveTo(16f, 13.5f)
                curveToRelative(-0.83f, 0f, -1.5f, -0.67f, -1.5f, -1.5f)
                reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f)
                reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f)
                reflectiveCurveToRelative(-0.67f, 1.5f, -1.5f, 1.5f)
                close()
            }
        }.build()
    }

    // 3. QRIS (Scan QR with corners and center QR dots)
    val NavQris: ImageVector by lazy {
        ImageVector.Builder(
            name = "NavQris",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            // Top-left bracket
            path(fill = SolidColor(Color.White)) {
                moveTo(3f, 9f)
                verticalLineTo(5f)
                curveToRelative(0f, -1.1f, 0.9f, -2f, 2f, -2f)
                horizontalLineToRelative(4f)
                verticalLineToRelative(2f)
                horizontalLineTo(5f)
                verticalLineToRelative(4f)
                horizontalLineTo(3f)
                close()
            }
            // Top-right bracket
            path(fill = SolidColor(Color.White)) {
                moveTo(15f, 3f)
                horizontalLineToRelative(4f)
                curveToRelative(1.1f, 0f, 2f, 0.9f, 2f, 2f)
                verticalLineToRelative(4f)
                horizontalLineToRelative(-2f)
                verticalLineTo(5f)
                horizontalLineToRelative(-4f)
                verticalLineTo(3f)
                close()
            }
            // Bottom-left bracket
            path(fill = SolidColor(Color.White)) {
                moveTo(3f, 15f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(4f)
                horizontalLineToRelative(4f)
                verticalLineToRelative(2f)
                horizontalLineTo(5f)
                curveToRelative(-1.1f, 0f, -2f, -0.9f, -2f, -2f)
                verticalLineToRelative(-4f)
                close()
            }
            // Bottom-right bracket
            path(fill = SolidColor(Color.White)) {
                moveTo(19f, 15f)
                horizontalLineToRelative(2f)
                verticalLineToRelative(4f)
                curveToRelative(0f, 1.1f, -0.9f, 2f, -2f, 2f)
                horizontalLineToRelative(-4f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(4f)
                verticalLineToRelative(-4f)
                close()
            }
            // Center QR code blocks
            path(fill = SolidColor(Color.White)) {
                // Top-left square
                moveTo(7f, 7f)
                horizontalLineToRelative(3.5f)
                verticalLineToRelative(3.5f)
                horizontalLineTo(7f)
                close()
                // Top-right square
                moveTo(13.5f, 7f)
                horizontalLineTo(17f)
                verticalLineToRelative(3.5f)
                horizontalLineToRelative(-3.5f)
                close()
                // Bottom-left square
                moveTo(7f, 13.5f)
                horizontalLineToRelative(3.5f)
                verticalLineTo(17f)
                horizontalLineTo(7f)
                close()
                // Center point
                moveTo(13.5f, 13.5f)
                horizontalLineToRelative(3.5f)
                verticalLineTo(17f)
                horizontalLineToRelative(-3.5f)
                close()
            }
        }.build()
    }

    // 4. Transaksi (Opposite horizontal arrows inside circle)
    val NavTransactions: ImageVector by lazy {
        ImageVector.Builder(
            name = "NavTransactions",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            // Outer circle
            path(fill = SolidColor(Color.Black)) {
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveToRelative(4.48f, 10f, 10f, 10f)
                reflectiveCurveToRelative(10f, -4.48f, 10f, -10f)
                reflectiveCurveTo(17.52f, 2f, 12f, 2f)
                close()
                moveTo(12f, 20f)
                curveToRelative(-4.41f, 0f, -8f, -3.59f, -8f, -8f)
                reflectiveCurveToRelative(3.59f, -8f, 8f, -8f)
                reflectiveCurveToRelative(8f, 3.59f, 8f, 8f)
                reflectiveCurveToRelative(-3.59f, 8f, -8f, 8f)
                close()
            }
            // Top arrow right
            path(fill = SolidColor(Color.Black)) {
                moveTo(15.5f, 9.5f)
                lineToRelative(-3f, -3f)
                verticalLineToRelative(2f)
                horizontalLineTo(7.5f)
                verticalLineToRelative(2f)
                horizontalLineToRelative(5f)
                verticalLineToRelative(2f)
                close()
            }
            // Bottom arrow left
            path(fill = SolidColor(Color.Black)) {
                moveTo(8.5f, 14.5f)
                lineToRelative(3f, 3f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(5f)
                verticalLineToRelative(-2f)
                horizontalLineToRelative(-5f)
                verticalLineToRelative(-2f)
                close()
            }
        }.build()
    }

    // 5. Saya / Profile
    val NavProfile: ImageVector by lazy {
        ImageVector.Builder(
            name = "NavProfile",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(12f, 12f)
                curveToRelative(2.21f, 0f, 4f, -1.79f, 4f, -4f)
                reflectiveCurveToRelative(-1.79f, -4f, -4f, -4f)
                reflectiveCurveToRelative(-4f, 1.79f, -4f, 4f)
                reflectiveCurveToRelative(1.79f, 4f, 4f, 4f)
                close()
                moveTo(12f, 14f)
                curveToRelative(-2.67f, 0f, -8f, 1.34f, -8f, 4f)
                verticalLineToRelative(2f)
                horizontalLineToRelative(16f)
                verticalLineToRelative(-2f)
                curveToRelative(0f, -2.66f, -5.33f, -4f, -8f, -4f)
                close()
            }
        }.build()
    }
}
