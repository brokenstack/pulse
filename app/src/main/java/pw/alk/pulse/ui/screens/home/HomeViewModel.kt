package pw.alk.pulse.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pw.alk.pulse.network.Post
import pw.alk.pulse.network.getPosts
import pw.alk.pulse.paging.DefaultPaginator

data class ScreenState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)

class HomeViewModel : ViewModel() {
    var state by mutableStateOf(ScreenState())
    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextKey ->
            getPosts(nextKey, 4)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state.page + 1
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                posts = state.posts + items, page = newKey, endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch(Dispatchers.Main) {
            paginator.loadNextPosts()
        }
    }
}