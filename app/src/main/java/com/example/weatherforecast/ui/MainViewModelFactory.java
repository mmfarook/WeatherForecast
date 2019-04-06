package com.example.weatherforecast.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.weatherforecast.repository.ForecastRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private ForecastRepository mForecastRepository;

    public MainViewModelFactory(ForecastRepository forecastRepository) {
        this.mForecastRepository = forecastRepository;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MainViewModel(mForecastRepository);
    }
}
