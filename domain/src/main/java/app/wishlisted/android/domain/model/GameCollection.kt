package app.wishlisted.android.domain.model

data class GameCollection(
	val name: String,
	val uri: String,
	val template: CollectionTemplate
) {
	enum class CollectionTemplate {
		Normal, Highlight
	}
}