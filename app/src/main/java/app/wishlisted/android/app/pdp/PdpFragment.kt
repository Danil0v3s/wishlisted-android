package app.wishlisted.android.app.pdp

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import app.wishlisted.android.R
import app.wishlisted.android.databinding.PdpFragmentBinding
import app.wishlisted.android.domain.model.GameImagePurpose
import app.wishlisted.android.domain.model.getImage
import coil.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PdpFragment : Fragment(R.layout.pdp_fragment) {

    private val args by navArgs<PdpFragmentArgs>()
    private val viewModel by viewModels<PdpViewModel>()
    private lateinit var binding: PdpFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()

        binding = PdpFragmentBinding.bind(view)
        binding.imgCover.transitionName = "${args.productId}_transition"

        viewModel.setParams(args.productId)

        viewModel.game.observe(viewLifecycleOwner) {
            binding.imgCover.load(it.getImage(GameImagePurpose.Poster))
            binding.txtTitle.text = it.productTitle

            view.startEnterTransition()
        }
    }

    private fun View.startEnterTransition() {
        doOnPreDraw {
            startPostponedEnterTransition()
        }
    }
}

