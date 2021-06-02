package app.wishlisted.android.data.src.model

import app.wishlisted.android.domain.model.GameCollection

data class GameCollectionDTO(
	val name: String,
	val uri: String,
	val template: CollectionTemplate
) {
	enum class CollectionTemplate {
		Normal, Highlight
	}
}

fun GameCollectionDTO.toDomainModel(): GameCollection {
	return GameCollection(
		name = name,
		uri = uri,
		template = GameCollection.CollectionTemplate.valueOf(template.name)
	)
}