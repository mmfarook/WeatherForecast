package com.example.weatherforecast.di.module;

import com.example.weatherforecast.di.scopes.ActivityScoped;
import com.example.weatherforecast.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityContributorModule {

    @ActivityScoped
    @ContributesAndroidInjector (modules = {FragmentModule.class})
    abstract MainActivity contributeMainActivity();


}
