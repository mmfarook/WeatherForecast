package com.example.weatherforecast.service;

import com.example.weatherforecast.model.Forecast;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastService {
    @GET ("/v1/forecast.json?")
    Observable<Forecast> getForecast(@Query("q") String location, @Query("days") int days);
}
