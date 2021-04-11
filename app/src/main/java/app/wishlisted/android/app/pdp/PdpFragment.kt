package app.wishlisted.android.app.pdp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PdpFragmentBinding.bind(view)

        viewModel.setParams(args.productId)

        viewModel.game.observe(viewLifecycleOwner) {
            binding.imgCover.load(it.getImage(GameImagePurpose.Poster))
            binding.txtTitle.text = it.productTitle
        }
    }
}
