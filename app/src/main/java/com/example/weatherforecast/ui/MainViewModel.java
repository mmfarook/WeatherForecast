package com.example.weatherforecast.ui;

import android.arch.lifecycle.ViewModel;

import com.example.weatherforecast.model.Forecast;
import com.example.weatherforecast.repository.ForecastRepository;

import java.util.List;

import io.reactivex.Observable;


public class MainViewModel extends ViewModel {

    private ForecastRepository forecastRepository;

    public MainViewModel(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    public Observable<List<Forecast>> getForecasts() {
        return forecastRepository.getForecasts();
    }


}
