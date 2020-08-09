package com.example.github.ui.profile

import com.airbnb.mvrx.*
import com.example.github.MvRxViewModel
import com.example.github.data.model.Profile
import com.example.github.data.network.ApiService
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.Observable

data class ProfileState(
    val profile: Async<Profile> = Uninitialized
) : MvRxState


class ProfileViewModel @AssistedInject constructor(
    @Assisted state: ProfileState,
    private val apiService: ApiService
) : MvRxViewModel<ProfileState>(state) {

    fun getUserProfileByLogin(login: String) {
        apiService
            .getUserProfile(login)
            .execute {
                copy(profile = it)
            }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: ProfileState): ProfileViewModel
    }

    companion object : MvRxViewModelFactory<ProfileViewModel, ProfileState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: ProfileState
        ): ProfileViewModel? {
            return (viewModelContext as FragmentViewModelContext)
                .fragment<ProfileFragment>()
                .viewModelFactory.create(state)
        }
    }
}