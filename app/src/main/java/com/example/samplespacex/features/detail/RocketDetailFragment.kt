package com.example.samplespacex.features.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplespacex.R
import com.example.samplespacex.data.model.Rocket
import com.example.samplespacex.databinding.RocketDetailFragmentBinding
import com.example.samplespacex.features.rocket.RocketAdapter.OnRocketClickListener
import com.example.samplespacex.util.Result
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RocketDetailFragment : Fragment(R.layout.rocket_detail_fragment), OnRocketClickListener {

    private val viewModel: RocketDetailViewModel by viewModels()

    private var _binding: RocketDetailFragmentBinding? = null
    private val binding get() = _binding!!
    var recyclerView: RecyclerView? = null
    var Manager: LinearLayoutManager? = null
    var adapter: Adapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = RocketDetailFragmentBinding.bind(view)

        arguments?.getString("rocketId")?.let {
            viewModel.getRocketDetails(it).observe(viewLifecycleOwner) { result ->
                adapter = context?.let { result.data?.images?.let { it1 -> Adapter(it1, it) } }
                recyclerView = view.findViewById(R.id.rvRocket) as RecyclerView?
                Manager = LinearLayoutManager(context)
                recyclerView?.setLayoutManager(
                    LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                )
                recyclerView?.adapter = adapter
                adapter?.notifyDataSetChanged()
                with(binding) {
                    circularProgressBar.isVisible = result is Result.Loading<*> && result.data.toString().isNullOrEmpty()
                    tvError.isVisible = result is Result.Error<*> && result.data.toString().isNullOrEmpty()
                    tvError.text = result.error?.localizedMessage
                    tvRocketName.text = getString(R.string.name)+result.data?.name?:""
                    chipLaunchSuccess.apply {
                        if (result.data?.active == true) {
                            isChecked = true
                            text = resources.getString(R.string.launch_success)
                        } else {
                            isChecked = false
                            text = resources.getString(R.string.launch_failure)
                        }
                    }
                    cost.text = getString(R.string.cost)+result.data?.costPerLaunch.toString()?:"0"
                    sucessRate.text = getString(R.string.sucess_rate)+result.data?.successRatePct.toString()?:"0"
                    description.text = getString(R.string.description)+result.data?.description?:""
                    wikipedia.text = getString(R.string.wikipedia)+result.data?.wikiUrl?:""

                }
            }
        }
    }



    companion object {
        fun newInstance(rocketId: String) = RocketDetailFragment().apply {
            arguments = Bundle().apply {
                putString("rocketId", rocketId)
            }
        }
    }

    override fun onClick(rocket: Rocket) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}