package pw.alk.pulse.ui.components

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import pw.alk.pulse.R
import pw.alk.pulse.network.Post
import pw.alk.pulse.ui.theme.TagBackground

@Composable
fun CardHead(name: String, subHeading: String, tag: String? = null, avatarURL: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatarURL)
                .diskCacheKey(name)
                .memoryCacheKey(name)
                .build(),
        )
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painter,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(48.dp),
            )
            if (painter.state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator()
            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(text = subHeading, style = MaterialTheme.typography.bodySmall)
        }
        if (tag != null) {
            Spacer(modifier = Modifier.width(3.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = TagBackground,
                        shape = RoundedCornerShape(5.dp)
                    ),
            ) {
                Text(
                    tag, modifier = Modifier
                        .align(Alignment.Center)
                        .padding(6.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Black
                )
            }
        }

        Spacer(
            Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        Image(
            painter = painterResource(R.drawable.ic_menu_vertical),
            contentDescription = "Menu",
            modifier = Modifier.clickable {

            }
        )
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
            "${pagerState.currentPage + 1}/${images.size}",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(horizontal = 10.dp),
            style = MaterialTheme.typography.bodySmall,
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) { index ->
            SubcomposeAsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize(),
                model = images[index],
                contentScale = ContentScale.Fit,
                contentDescription = "Content Image"
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Error) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("Could not load image")
                    }
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
        }
    }
}

@Composable
fun BaseUserContent(
    author: String,
    authorImage: String,
    postType: String? = null,
    textContent: String,
    subHeading: String = "Public"
) {
    Column(modifier = Modifier.padding(10.dp)) {
        CardHead(
            name = author,
            subHeading = subHeading,
            tag = postType,
            avatarURL = authorImage
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(textContent, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun PostCard(post: Post, onCommentClicked: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        BaseUserContent(
            author = post.author_name,
            authorImage = post.author_image,
            postType = post.post_type,
            textContent = post.text,
            subHeading = post.postedOn
        )

        if (post.attachment_type == "image") {
            ImageCarousel(
                images = post.attachments
            )
        }

        // bottom buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                title = post.likes.toString(),
                icon = R.drawable.ic_heart,
                iconDescription = "Like button",
            )
            IconButton(
                title = post.comments.size.toString(),
                icon = R.drawable.ic_comments,
                iconDescription = "Comments button",
                onClick = {
                    onCommentClicked()
                }
            )
            IconButton(
                title = "Share",
                icon = R.drawable.ic_share,
                iconDescription = "Share button",
                onClick = {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.text)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                }
            )
        }
        Spacer(Modifier.height(5.dp))
    }
}