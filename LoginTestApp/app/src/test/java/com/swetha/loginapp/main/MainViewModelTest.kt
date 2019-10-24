package com.swetha.loginapp.main

import com.swetha.loginapp.user.UserDataRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever

class MainViewModelTest {

    private lateinit var userDataRepository: UserDataRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        userDataRepository = mock(UserDataRepository::class.java)
        viewModel = MainViewModel(userDataRepository,viewModel.userManager)
    }

    @Test
    fun `Welcome text returns right text`() {
        whenever(userDataRepository.username).thenReturn("username")

        assertEquals("Hello username!", viewModel.welcomeText)
    }

}
