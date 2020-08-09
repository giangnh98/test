package com.example.github.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import com.example.github.R
import com.example.github.data.model.User
import com.example.github.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseMvRxFragment(R.layout.fragment_home), UserAdapter.UserAdapterListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: UsersViewModel.Factory

    private val viewModel: UsersViewModel by fragmentViewModel()

    @Inject
    lateinit var userAdapterFactory: UserAdapter.Factory

    private val userAdapter: UserAdapter by lazy { userAdapterFactory.create(this@HomeFragment) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = userAdapter
        }
        viewModel.getUsers()
    }

    private fun showLoading(isVisible: Int) {
        binding.spinKit.visibility = isVisible
    }

    override fun invalidate() = withState(viewModel) { state ->
        when (state.users) {
            is Loading -> showLoading(View.VISIBLE)
            is Success -> {
                showLoading(View.GONE)
                userAdapter.submitList(state.users.invoke().toMutableList())
            }
            is Fail -> {
                showLoading(View.GONE)
                Toast.makeText(requireContext(), "Loading data fail...", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(view: View, user: User) {
        findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment(user.login))
    }
}