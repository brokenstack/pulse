package pw.alk.pulse.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pw.alk.pulse.R
import pw.alk.pulse.ui.theme.Outfit

@Composable
fun IconButton(
    title: String,
    @DrawableRes icon: Int,
    iconDescription: String,
    onClick: () -> Unit = { }
) {
    Row(
        modifier = Modifier
            .clickable() { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = iconDescription,
            modifier = Modifier.size(23.dp),
        )
        Text(
            title,
            modifier = Modifier.padding(horizontal = 5.dp),
            style = MaterialTheme.typography.labelLarge,
            fontFamily = Outfit
        )
    }
}


@Composable
fun BackButton(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_arrow_left),
            contentDescription = "Back Arrow",
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    onClick()
                },
        )
        Text(
            title,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 20.dp),
            fontFamily = Outfit,
            color = Color(0xFF4B556F),
            fontSize = 22.sp
        )
    }

}