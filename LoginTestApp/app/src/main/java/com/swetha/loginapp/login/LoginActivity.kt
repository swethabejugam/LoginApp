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

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.swetha.loginapp.MyApplication
import com.example.android.dagger.R
import com.swetha.loginapp.main.MainActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    // @Inject annotated fields will be provided by Dagger
    @Inject
    lateinit var loginViewModel: LoginViewModel
lateinit var binding:com.example.android.dagger.databinding.ActivityLoginBinding
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        // Creates an instance of Login component by grabbing the factory from the app graph
        // and injects this activity to that Component
        (application as MyApplication).appComponent.loginComponent().create().inject(this)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        super.onCreate(savedInstanceState)
        binding.username.text=resources.getString(R.string.prompt_username,loginViewModel.getUsername())

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            if (loginState.passwordError != null) {
                binding.loading.visibility = View.GONE

                showToast(getString(loginState.passwordError))
            }
        })
        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            binding.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser()
            }
            setResult(Activity.RESULT_OK)

            navigateToNextScreen()
            //Complete and destroy login activity once successful
            //finish()
        })

        binding.login.setOnClickListener {
            binding.loading.visibility = View.VISIBLE

            loginViewModel.login(loginViewModel.getUsername(), binding.password.text.toString())

        }

    }

    private fun showToast(string: String) {
        Toast.makeText(applicationContext,string,Toast.LENGTH_LONG).show()

    }

    private fun navigateToNextScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun updateUiWithUser() {
        showToast(resources.getString(R.string.login_success))

    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

}