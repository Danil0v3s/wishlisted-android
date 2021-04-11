package app.wishlisted.android.app.home.deals

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.wishlisted.android.app.extensions.layoutInflater
import app.wishlisted.android.databinding.GameBasicDetailsItemBinding
import app.wishlisted.android.domain.model.Game
import app.wishlisted.android.domain.model.GameImagePurpose
import app.wishlisted.android.domain.model.getImage
import coil.load

class DealsPagedAdapter constructor(
    private val onGameClicked: (Game) -> Unit
) :
    PagingDataAdapter<Game, DealsPagedAdapter.GameBasicViewHolder>(GAME_COMPARATOR) {

    override fun onBindViewHolder(holder: GameBasicViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameBasicViewHolder {
        return GameBasicViewHolder(
            GameBasicDetailsItemBinding.inflate(parent.context.layoutInflater, parent, false),
            onGameClicked
        )
    }

    class GameBasicViewHolder(
        private val binding: GameBasicDetailsItemBinding,
        private val onGameClicked: (Game) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var game: Game? = null

        init {
            binding.root.setOnClickListener {
                game?.also(onGameClicked)
            }
        }

        fun bind(game: Game) {
            this.game = game
            with(binding) {
                imgCover.load(game.getImage(GameImagePurpose.Poster))
                txtTitle.text = game.productTitle
            }
        }
    }

    companion object {
        val GAME_COMPARATOR = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean =
                oldItem.productId == newItem.productId

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean =
                oldItem == newItem
        }
    }
}
