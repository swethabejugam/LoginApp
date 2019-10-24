/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.swetha.loginapp.login

import android.content.Context
import android.net.ConnectivityManager
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.dagger.R
import com.swetha.loginapp.user.UserManager
import javax.inject.Inject

/**
 * LoginViewModel is the ViewModel that [LoginActivity] uses to
 * obtain information of what to show on the screen and handle complex logic.
 */
class LoginViewModel @Inject constructor(private val userManager: UserManager) {

    private val _loginForm = MutableLiveData<LoginFormState>()

    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun unregister() {
        userManager.unregister()
    }

    fun getUsername(): String = userManager.username


    /*For Retrofit to work*/
    /* fun login( username:String, password:String) {
    if(isOnline){
        val call: Call<LoginResponseDataModel> = ApiClient.getClient.getLoginData(username,password)
        call.enqueue(object : Callback<LoginResponseDataModel> {

            override fun onResponse(call: Call<LoginResponseDataModel>?, response: Response<LoginResponseDataModel>?) {
                if ((response!!.body()!!.statusCode.toInt())==200) {
                    _loginState.value = LoginSuccess
                } else {
                    _loginState.value = LoginError
                }
            }

            override fun onFailure(call: Call<LoginResponseDataModel>?, t: Throwable?) {
            }

        })
        }else{

        }

    }*/
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job

        if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {

            val result = userManager.loginUser(username, password)

            if (result) {
                _loginForm.value = LoginFormState(isDataValid = true)
                _loginResult.value = LoginResult(success = R.string.login_success)
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5;

    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

