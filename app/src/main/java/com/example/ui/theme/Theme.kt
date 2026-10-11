package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = PureWhite,
    primaryContainer = IceBlue,
    onPrimaryContainer = PrimaryBlueDark,
    secondary = SecondaryBlue,
    onSecondary = PureWhite,
    secondaryContainer = IceBlueCard,
    onSecondaryContainer = DeepNavy,
    tertiary = CryptoTeal,
    onTertiary = PureWhite,
    background = BackgroundLight,
    onBackground = TextPrimary,
    surface = SurfaceCard,
    onSurface = TextPrimary,
    surfaceVariant = IceBlueCard,
    onSurfaceVariant = TextSecondary,
    outline = SoftBlueBorder,
    outlineVariant = DividerColor
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = PureWhite,
    primaryContainer = Color(0xFF1E293B),
    onPrimaryContainer = IceBlue,
    secondary = SecondaryBlue,
    onSecondary = PureWhite,
    background = Color(0xFF0B0F19),
    onBackground = PureWhite,
    surface = Color(0xFF111827),
    onSurface = PureWhite,
    surfaceVariant = Color(0xFF1F2937),
    onSurfaceVariant = Color(0xFF94A3B8),
    outline = Color(0xFF334155),
    outlineVariant = Color(0xFF1E293B)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
