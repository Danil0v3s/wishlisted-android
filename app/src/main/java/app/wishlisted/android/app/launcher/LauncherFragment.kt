package app.wishlisted.android.app.launcher

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.wishlisted.android.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherFragment : Fragment(R.layout.launcher_fragment) {

    private val viewModel by viewModels<LauncherViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                LauncherViewModel.State.Done -> {
                    findNavController().navigate(LauncherFragmentDirections.actionLauncherFragmentToDealsFragment())
                }
                LauncherViewModel.State.Error -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                LauncherViewModel.State.Loading -> {
                }
            }
        }
    }
}
