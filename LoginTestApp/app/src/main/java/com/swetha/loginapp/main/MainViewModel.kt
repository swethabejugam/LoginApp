package com.swetha.loginapp.main

import com.swetha.loginapp.user.UserDataRepository
import com.swetha.loginapp.user.UserManager
import javax.inject.Inject

/**
 * MainViewModel is the ViewModel that [MainActivity] uses to
 * obtain information of what to show on the screen.
 *
 * @Inject tells Dagger how to provide instances of this type. Dagger also knows
 * that UserDataRepository is a dependency.
 */
class MainViewModel @Inject constructor(private val userDataRepository: UserDataRepository, val userManager: UserManager) {

    val welcomeText: String
        get() = "Hello ${userDataRepository.username}!"

    fun logout() {
        userManager.logout()
    }
}

