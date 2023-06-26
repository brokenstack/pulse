package pw.alk.pulse.network

import androidx.compose.runtime.Composable

data class Post(
    val author_image: String,
    val author_name: String,
    val post_type: String,
    val likes: Int,
    val text: String,
    val attachment_type: String,
    val attachments: List<String>,
    val comments: List<Comment>,
    val postedOn: String
)

data class Comment(
    val username: String,
    val user_image: String,
    val comment: String,
    val likes: Int
)

data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit
)