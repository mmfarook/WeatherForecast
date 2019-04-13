package com.example.weatherforecast.ui;


import com.example.weatherforecast.model.Forecast;
import com.example.weatherforecast.repository.ForecastRepository;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;


public class MainViewModel extends ViewModel {

    private ForecastRepository forecastRepository;

    @Inject
    public MainViewModel(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    public Observable<Forecast> getForecasts() {
        return forecastRepository.getForecasts();
    }


}
