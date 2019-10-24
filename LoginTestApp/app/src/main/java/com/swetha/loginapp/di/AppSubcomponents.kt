package com.swetha.loginapp.di

import com.swetha.loginapp.login.LoginComponent
import com.swetha.loginapp.registration.RegistrationComponent
import com.swetha.loginapp.user.UserComponent
import dagger.Module

// This module tells a Component which are its subcomponents
@Module(
    subcomponents = [
        RegistrationComponent::class,
        LoginComponent::class,
        UserComponent::class
    ]
)
class AppSubcomponents
