package com.example.challengeconexa.ui.users

import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challengeconexa.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito


@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModelTest {

    private lateinit var usersViewModel: UsersViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val applicationMock = Mockito.mock(Application::class.java)
        usersViewModel = UsersViewModel(applicationMock)
    }

    @Test
    fun `getUsers should return a list of users`() {
        usersViewModel.getUsers()
        //advanceUntilIdle()
        val users = usersViewModel.usersLiveData.getOrAwaitValue()

        assertNotNull(users)
    }

    @Test
    fun getX() {
        val x = usersViewModel.getX()
        assertNotNull(x)
    }
}