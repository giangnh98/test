package com.example.github.ui.home

import com.airbnb.mvrx.*
import com.example.github.MvRxViewModel
import com.example.github.data.model.User
import com.example.github.data.network.ApiService
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

data class UsersState(
    val users: Async<List<User>> = Uninitialized
) : MvRxState

class UsersViewModel @AssistedInject constructor(
    @Assisted state: UsersState,
    private val apiService: ApiService
) : MvRxViewModel<UsersState>(state) {

    fun getUsers() {
        apiService
            .getUsers()
            .execute { copy(users = it) }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: UsersState): UsersViewModel
    }

    companion object : MvRxViewModelFactory<UsersViewModel, UsersState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: UsersState
        ): UsersViewModel? {
            return (viewModelContext as FragmentViewModelContext)
                .fragment<HomeFragment>()
                .viewModelFactory.create(state)
        }
    }
}