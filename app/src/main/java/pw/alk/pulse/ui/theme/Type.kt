package pw.alk.pulse.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    displayMedium = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontWeight = FontWeight.Bold,
        fontSize = 44.sp,
        lineHeight = 52.0.sp
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        letterSpacing = 0.5.sp,
        lineHeight = 16.0.sp,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium
    )
)