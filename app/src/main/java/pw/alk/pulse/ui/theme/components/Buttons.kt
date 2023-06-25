package pw.alk.pulse.ui.theme.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
    title: String,
    @DrawableRes icon: Int,
    iconDescription: String,
    onClick: () -> Unit = { }
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable() { onClick() }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = iconDescription,
            modifier = Modifier.size(20.dp)
        )
        Text(title, modifier = Modifier.padding(horizontal = 5.dp))
    }
}