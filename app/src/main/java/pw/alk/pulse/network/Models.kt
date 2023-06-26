package pw.alk.pulse.network

data class Post(
    val author_image: String,
    val author_name: String,
    val post_type: String,
    val likes: Int,
    val text: String,
    val attachment_type: String,
    val attachments: List<String>,
    val comments: List<Comment>
)

data class Comment(
    val username: String,
    val user_image: String,
    val comment: String,
    val likes: Int
)