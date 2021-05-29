package app.wishlisted.android.app.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import app.wishlisted.android.app.ui.home.deals.DealsScreen
import app.wishlisted.android.app.ui.home.deals.DealsViewModel
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.model.GameImagePurpose
import app.wishlisted.android.domain.model.getImage
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(
	viewModel: DealsViewModel,
	scaffoldState: ScaffoldState = rememberScaffoldState(),
	onItemClick: (Game) -> Unit
) {
	val selectedGame = remember { mutableStateOf<Game?>(null) }
	val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
	val scope = rememberCoroutineScope()

	ModalBottomSheetLayout(
		sheetState = state,
		sheetContent = {
			Column {
				GameBottomSheet(selectedGame.value)
			}
		}
	) {
		Box(
			modifier = Modifier.fillMaxSize()
		) {
			SearchField()
			DealsScreen(
				deals = viewModel.deals,
				contentPadding = PaddingValues(top = 84.dp),
				onItemClick = {
					selectedGame.value = it
					scope.launch { state.show() }
				}
			)
		}
	}
}

@Composable
private fun GameBottomSheet(
	game: Game?
) {
	Row(modifier = Modifier.padding(16.dp)) {
		Surface(
			elevation = 12.dp,
			modifier = Modifier
				.width(90.dp)
				.zIndex(1f)
		) {
			Image(
				painter = rememberCoilPainter(game?.getImage(GameImagePurpose.Poster).orEmpty()),
				contentDescription = null,
				modifier = Modifier.fillMaxWidth()
			)
		}

		Column(modifier = Modifier.padding(start = 8.dp)) {
			Text(text = game?.productTitle.orEmpty())
			Text(text = game?.publisher.orEmpty())
			Spacer(Modifier.height(10.dp))
			Text(
				modifier = Modifier
					.background(Color.Green)
					.padding(4.dp)
					.fillMaxWidth(0.33f),
				textAlign = TextAlign.Center,
				text = "$ ${game?.price?.gameListPrice}"
			)
		}
	}
}

@ExperimentalAnimationApi
@Composable
private fun SearchField(
	modifier: Modifier = Modifier
) {
	val text = remember { mutableStateOf("") }
	val interactionSource = remember { MutableInteractionSource() }
	val isInputFocused = interactionSource.collectIsFocusedAsState()
	val recentSearches = remember { listOf(1, 2, 3) }

	RoundedCornerSurface(modifier) {
		Column {
			TextField(
				modifier = Modifier.fillMaxWidth(),
				value = text.value,
				onValueChange = { value -> text.value = value },
				placeholder = { Text(text = "Search") },
				colors = TextFieldDefaults.textFieldColors(
					textColor = Color.Gray,
					disabledTextColor = Color.Transparent,
					backgroundColor = Color.White,
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent,
					disabledIndicatorColor = Color.Transparent
				),
				trailingIcon = {
					Icon(
						imageVector = Icons.Filled.Search,
						contentDescription = null
					)
				},
				interactionSource = interactionSource
			)

			RecentSearches(isInputFocused, recentSearches)
		}
	}
}

@ExperimentalAnimationApi
@Composable
private fun RecentSearches(
	isInputFocused: State<Boolean>,
	recentSearches: List<Int>
) {
	AnimatedVisibility(visible = isInputFocused.value) {
		LazyColumn {
			itemsIndexed(recentSearches) { index, item ->
				Row(
					modifier = Modifier
						.padding(
							start = 16.dp,
							end = 16.dp,
							top = 8.dp,
							bottom = if (index == recentSearches.size - 1) 16.dp else 8.dp
						)
						.fillMaxWidth()
				) {
					Text(text = "Test")
				}
			}
		}
	}
}

@Composable
private fun RoundedCornerSurface(modifier: Modifier = Modifier, children: @Composable () -> Unit) {
	val shape = remember { RoundedCornerShape(12.dp) }
	Surface(
		shape = shape,
		elevation = 8.dp,
		modifier = Modifier
			.padding(all = 16.dp)
			.fillMaxWidth()
			.border(1.dp, Color.LightGray, shape)
			.zIndex(1f)
			.composed { modifier }
	) {
		children()
	}
}
