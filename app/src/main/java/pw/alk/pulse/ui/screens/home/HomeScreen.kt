package pw.alk.pulse.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pw.alk.pulse.R
import pw.alk.pulse.ui.components.PostCard
import pw.alk.pulse.ui.nav.Screen
import pw.alk.pulse.ui.screens.SharedViewModel
import pw.alk.pulse.ui.theme.Pink

@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val viewModel = viewModel<HomeViewModel>()
    val context = LocalContext.current
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Pulse",
                style = MaterialTheme.typography.displayMedium,
                color = Pink
            )

            // placeholder user avatar
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "User Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
            )
        }

        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(count = state.posts.size) { i ->
                val post = state.posts[i]
                if (i >= state.posts.size - 1 && !state.endReached && !state.isLoading) {
                    viewModel.loadNextItems()
                }

                PostCard(
                    post,
                    onCommentClicked = {
                        sharedViewModel.addPost(post)
                        navController.navigate(route = Screen.Comments.route)
                    }
                )
            }

            item {
                if (state.isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}