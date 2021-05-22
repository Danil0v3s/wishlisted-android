package app.wishlisted.android.app.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun HomeScreen() {

	Column {
		SearchField()
	}
}

@ExperimentalAnimationApi
@Composable
private fun SearchField() {
	val text = remember { mutableStateOf("") }
	val interactionSource = remember { MutableInteractionSource() }
	val isInputFocused = interactionSource.collectIsFocusedAsState()
	val recentSearches = remember { listOf(1, 2, 3) }

	RoundedCornerSurface {
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
	}
}

@Composable
private fun RoundedCornerSurface(children: @Composable () -> Unit) {
	val shape = remember { RoundedCornerShape(12.dp) }
	Surface(
		shape = shape,
		elevation = 8.dp,
		modifier = Modifier
			.padding(all = 16.dp)
			.fillMaxWidth()
			.border(1.dp, Color.LightGray, shape)
	) {
		children()
	}
}