package com.example.github.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.bumptech.glide.RequestManager
import com.example.github.R
import com.example.github.data.model.Profile
import com.example.github.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class ProfileFragment : BaseMvRxFragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val args: ProfileFragmentArgs by navArgs()
    private val login: String by lazy { args.login }

    @Inject
    lateinit var viewModelFactory: ProfileViewModel.Factory

    private val viewModel: ProfileViewModel by fragmentViewModel()

    @Inject
    lateinit var requestManager: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        binding.navigationIcon.setOnClickListener {
            findNavController()
                .navigateUp()
        }
        viewModel.getUserProfileByLogin(login = login)
    }

    private fun setupProfile(profile: Profile) {
        binding.run {
            subjectTextView.text = profile.name
            senderTextView.text = "${profile.login} - ${profile.company}"
            recipientTextView.text = profile.location
            requestManager
                .load(profile.avatar)
                .circleCrop()
                .into(senderProfileImageView)
        }
    }

    override fun invalidate() {
        withState(viewModel) {state ->
            when(state.profile) {
                is Success -> {
                    setupProfile(state.profile.invoke())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}