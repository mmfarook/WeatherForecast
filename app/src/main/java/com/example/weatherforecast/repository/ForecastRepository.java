package com.example.weatherforecast.repository;


import com.example.weatherforecast.model.Forecast;
import com.example.weatherforecast.service.ForecastService;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class ForecastRepository {


    Retrofit retrofit;

    @Inject
    public ForecastRepository(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Observable<Forecast> getForecasts() {
        ForecastService forecastService = retrofit.create(ForecastService.class);
        return forecastService.getForecast("12.98,77.58", 10);
    }
}
