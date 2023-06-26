package pw.alk.pulse.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pw.alk.pulse.network.Post

class SharedViewModel : ViewModel() {
    var post by mutableStateOf<Post?>(null)
        private set

    fun addPost(newPost: Post) {
        post = newPost
    }
}