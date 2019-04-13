package com.example.weatherforecast.di.module;



import com.example.weatherforecast.di.scopes.AppScoped;
import com.example.weatherforecast.ui.MainViewModel;
import com.example.weatherforecast.ui.MainViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMessageViewModel(MainViewModel messageViewModel);

    @Binds
    @AppScoped
    abstract ViewModelProvider.Factory bindViewModelFactory(MainViewModelFactory factory);
}