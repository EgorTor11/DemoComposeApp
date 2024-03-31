@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.democompouseapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.democompouseapp.R
import com.example.democompouseapp.presentation.model.CharacterUI
import com.example.democompouseapp.presentation.state.ErrorItem
import com.example.democompouseapp.presentation.state.LoadingItem
import com.example.democompouseapp.presentation.state.LoadingView
import com.example.democompouseapp.ui.theme.Purple40
import kotlinx.coroutines.flow.Flow


@Preview
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_title)) }
            )
        },
        bottomBar = {},
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.Center,
        snackbarHost = {}

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) { CharacterList(mainViewModel.characters) }

    }
}

@Composable
fun CharacterList(characters: Flow<PagingData<CharacterUI>>) {
    val characterItems: LazyPagingItems<CharacterUI> = characters.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(3.dp)) {
        items(count = characterItems.itemCount) { index ->
            val character = characterItems[index]
            character?.let { characterUI -> CharacterItem(characterUI) }
        }

        characterItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage
                                ?: stringResource(id = R.string.error),
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage
                                ?: stringResource(id = R.string.error),
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(item: CharacterUI) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Purple40

        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .background(colorResource(id = R.color.purple_200))
        ) {
            CharacterImage(imageUrl = item.image)

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = item.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .width(10.dp)
                            .background(colorResource(id = item.getStatusColor())),
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = item.getCharacterStatus(LocalContext.current))
                }

                Text(text = stringResource(id = R.string.from), fontWeight = FontWeight.Bold)

                Text(text = item.getCharacterFrom())
            }
        }
    }
}

@Composable
fun CharacterImage(imageUrl: String) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp),
    ) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    size(OriginalSize)
                    placeholder(R.drawable.ic_launcher_background)
                },
            ),
            contentDescription = stringResource(id = R.string.cd_character_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}