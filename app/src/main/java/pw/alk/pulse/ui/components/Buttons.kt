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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pw.alk.pulse.R

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
            modifier = Modifier.size(20.dp),
        )
        Text(
            title,
            modifier = Modifier.padding(horizontal = 5.dp),
            style = MaterialTheme.typography.labelMedium
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
                .size(25.dp)
                .clickable {
                    onClick()
                },
        )
        Text(
            title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }

}