package com.example.weatherforecast.di.module;

import com.example.weatherforecast.di.scopes.FragmentScoped;
import com.example.weatherforecast.ui.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();
}
