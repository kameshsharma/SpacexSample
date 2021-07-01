package com.example.samplespacex.features.rocket
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.samplespacex.R
import com.example.samplespacex.data.model.Rocket
import com.example.samplespacex.databinding.RocketFragmentBinding
import com.example.samplespacex.features.detail.RocketDetailFragment
import com.example.samplespacex.features.rocket.RocketAdapter.OnRocketClickListener
import com.example.samplespacex.util.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketFragment : Fragment(R.layout.rocket_fragment), OnRocketClickListener {

    private val viewModel: RocketViewModel by viewModels()

    private var _binding: RocketFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = RocketFragmentBinding.bind(view)
        val rocketAdapter = RocketAdapter(this)
        with(binding) {
            rvRocket.apply {
                adapter = rocketAdapter
                setHasFixedSize(true)
            }
        }

        viewModel.rockets.observe(viewLifecycleOwner) { result ->
            rocketAdapter.submitList(result.data)
            with(binding) {
                circularProgressBar.isVisible = result is Result.Loading<*> && result.data.isNullOrEmpty()
                tvError.isVisible = result is Result.Error<*> && result.data.isNullOrEmpty()
                tvError.text = result.error?.localizedMessage
            }
        }
    }



    override fun onClick(rocket: Rocket) {
        parentFragment?.childFragmentManager?.beginTransaction()
            ?.add(R.id.fragmentContainer,RocketDetailFragment.newInstance(rocket.id))?.addToBackStack("RocketDetailFragment")?.commitAllowingStateLoss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}