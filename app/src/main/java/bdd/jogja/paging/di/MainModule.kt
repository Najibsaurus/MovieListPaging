package bdd.jogja.paging.di

import bdd.jogja.paging.MovieViewModel
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val MainModule = module {
    viewModel<MovieViewModel>()
}
