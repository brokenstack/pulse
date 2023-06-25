package pw.alk.pulse.ui.theme.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pw.alk.pulse.R

@Composable
fun CardHead(name: String, subHeading: String, tag: String?, avatarURL: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = avatarURL, contentDescription = "User Avatar", modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(45.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp)
        ) {
            Text(text = name)
            Text(text = subHeading)
        }
        if (tag != null) {
            Spacer(modifier = Modifier.width(3.dp))
            Box(
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    tag, modifier = Modifier
                        .align(Alignment.Center)
                        .padding(4.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(images: List<String>) {
    val pagerState = rememberPagerState() {
        images.size
    }

    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(vertical = 10.dp)
    ) {

        Text(
            "${pagerState.currentPage + 1}/${images.size}", modifier = Modifier
                .align(Alignment.TopEnd)
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) { index ->
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize(),
                model = images[index],
                contentScale = ContentScale.Fit,
                contentDescription = "Content Image"
            )
        }
    }
}

@Composable
fun CustomCard(likes: Int, comments: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            CardHead(
                name = "Alok P.",
                subHeading = "2 hours ago",
                tag = "Question",
                avatarURL = "https://i.pravatar.cc/300"
            )

            Text("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.")
            ImageCarousel(
                images = listOf(
                    "https://images.unsplash.com/photo-1648294358557-80d5aeb2e396?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=880&q=80",
                    "https://plus.unsplash.com/premium_photo-1677693662922-3a8f7abe5f2b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxNHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=600&q=60",
                    "https://images.unsplash.com/photo-1687335545159-0c3187752189?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw4fHx8ZW58MHx8fHx8&auto=format&fit=crop&w=600&q=60"
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    title = likes.toString(),
                    icon = R.drawable.ic_thumbs_up,
                    iconDescription = "Like button",
                )
                IconButton(
                    title = comments.toString(),
                    icon = R.drawable.ic_comments,
                    iconDescription = "Comments button",
                    onClick = { Log.e("TAG", "Works??") }
                )
                IconButton(
                    title = "Share",
                    icon = R.drawable.ic_share,
                    iconDescription = "Share button"
                )
            }
        }
    }
}