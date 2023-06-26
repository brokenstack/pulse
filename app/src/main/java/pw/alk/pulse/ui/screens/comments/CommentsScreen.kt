package pw.alk.pulse.ui.screens.comments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pw.alk.pulse.R
import pw.alk.pulse.ui.components.BackButton
import pw.alk.pulse.ui.components.BaseUserContent
import pw.alk.pulse.ui.components.IconButton
import pw.alk.pulse.ui.components.PostDivider
import pw.alk.pulse.ui.nav.Screen
import pw.alk.pulse.ui.screens.SharedViewModel
import pw.alk.pulse.ui.theme.Outfit

@Composable
fun CommentsScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val post = sharedViewModel.post!!

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackButton(title = "Comments") {
            navController.popBackStack(
                route = Screen.Home.route,
                inclusive = false
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BaseUserContent(
                author = post.author_name,
                authorImage = post.author_image,
                postType = post.post_type,
                textContent = post.text
            )
        }

        PostDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "${post.comments.size} Comments",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(10.dp),
            color = Color(0xFF52565B),
            fontFamily = Outfit
        )
        LazyColumn() {
            items(post.comments.size) { i ->
                val comment = post.comments[i]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BaseUserContent(
                        textContent = comment.comment,
                        author = comment.username,
                        authorImage = comment.user_image
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth()
                    ) {
                        IconButton(
                            title = comment.likes.toString(),
                            icon = R.drawable.ic_heart,
                            iconDescription = "Like button"
                        )
                    }
                }
            }
        }
    }
}