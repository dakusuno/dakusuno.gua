package com.dakusuno.dakusunogua.di

import com.dakusuno.dakusunogua.view.ui.main.MainViewModel
import com.dakusuno.dakusunogua.view.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module{
    viewModel { MainViewModel(get()) }
    viewModel { UserViewModel(get()) }
}