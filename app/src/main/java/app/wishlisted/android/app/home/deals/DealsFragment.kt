package app.wishlisted.android.app.home.deals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app.wishlisted.android.R
import app.wishlisted.android.databinding.DealsFragmentBinding
import app.wishlisted.android.domain.model.Game
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DealsFragment : Fragment(R.layout.deals_fragment) {

    private val viewModel by viewModels<DealsViewModel>()
    private lateinit var binding: DealsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DealsFragmentBinding.bind(view)

        val adapter = DealsPagedAdapter(::navigateToPdp)
        binding.rvDeals.adapter = adapter
        binding.rvDeals.layoutManager = GridLayoutManager(context, 3)

        lifecycleScope.launchWhenCreated {
            viewModel.deals.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun navigateToPdp(it: Game) {
        findNavController().navigate(DealsFragmentDirections.actionDealsFragmentToPdpFragment(it.productId))
    }
}
