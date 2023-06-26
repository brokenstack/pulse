package pw.alk.pulse.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pw.alk.pulse.network.TabItem
import pw.alk.pulse.ui.components.CustomIndicator
import pw.alk.pulse.ui.components.PostCard
import pw.alk.pulse.ui.components.PostDivider
import pw.alk.pulse.ui.nav.Screen
import pw.alk.pulse.ui.screens.SharedViewModel
import pw.alk.pulse.ui.theme.Outfit


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val tabs = listOf(
        TabItem(
            "charcha",
            screen = {
                CharchaScreen(navController, sharedViewModel)
            }
        ),
        TabItem(
            "bazaar",
            screen = {
            }
        ),
        TabItem(
            "profile",
            screen = {
            }
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { tabs.size }

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(6.dp)) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = @Composable { tabPositions ->
                val selectedTabIndex = pagerState.currentPage
                if (selectedTabIndex < tabPositions.size) {
                    CustomIndicator(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .padding(horizontal = 8.dp)
                    )
                }
            },
            divider = @Composable {
                Box(modifier = Modifier.width(0.dp))
            }
        ) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = index == pagerState.currentPage,
                    text = { Text(text = item.title, fontFamily = Outfit) },
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                )
            }
        }
        HorizontalPager(state = pagerState, userScrollEnabled = false) {
            tabs[pagerState.currentPage].screen()
        }
    }
}

@Composable
fun CharchaScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val viewModel = viewModel<HomeViewModel>()
    val context = LocalContext.current
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                PostDivider()
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