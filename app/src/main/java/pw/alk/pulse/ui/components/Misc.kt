package pw.alk.pulse.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pw.alk.pulse.ui.theme.Gray

@Composable
fun PostDivider() {
    Box(modifier = Modifier.fillMaxWidth().background(Gray).height(2.dp))
}