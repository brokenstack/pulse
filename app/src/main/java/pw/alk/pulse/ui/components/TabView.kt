package pw.alk.pulse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomIndicator(
    modifier: Modifier = Modifier,
    height: Dp = 3.0.dp,
    color: Color =
        MaterialTheme.colorScheme.primary
) {
    Box(
        modifier
            .width(0.dp)
            .height(height)
            .background(color = color)
    )
}