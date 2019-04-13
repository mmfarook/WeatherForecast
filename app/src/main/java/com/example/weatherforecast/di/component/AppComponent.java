package com.example.weatherforecast.di.component;

import android.app.Application;

import com.example.weatherforecast.di.module.ActivityContributorModule;
import com.example.weatherforecast.di.module.AppModule;
import com.example.weatherforecast.di.module.RetrofitModule;
import com.example.weatherforecast.di.module.ViewModelModule;
import com.example.weatherforecast.di.scopes.AppScoped;


import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@AppScoped
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityContributorModule.class,
        ViewModelModule.class,
        RetrofitModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(DaggerApplication application);

        AppComponent build();
    }

}