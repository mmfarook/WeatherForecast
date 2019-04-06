package com.example.weatherforecast.repository;

import android.util.Log;

import com.example.weatherforecast.model.Condition;
import com.example.weatherforecast.model.Forecast;


import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ForecastRepository {

    private OkHttpClient okHttpClient = new OkHttpClient();
    private static final String FORECAST_URL = "http://api.apixu.com/v1/forecast.json?key=ad714eba9cb74372b0e101947190504&q=12.98,77.58&days=10";

    public Observable<List<Forecast>> getForecasts() {

        final Request request = new Request.Builder()
                .url(FORECAST_URL)
                .get()
                .build();

        return Observable.create(new ObservableOnSubscribe<List<Forecast>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Forecast>> emitter) throws Exception {
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (!emitter.isDisposed()) {
                        JSONObject responseObject = new JSONObject(response.body().string());
                        JSONObject forecastObject = responseObject.getJSONObject("forecast");
                        JSONArray jsonArray = forecastObject.getJSONArray("forecastday");
                        JSONObject jsonObject;
                        JSONObject dayObject;
                        JSONObject conditionObject;

                        List<Forecast> forecastList = new ArrayList<>();
                        for (int i= 0; i< jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            dayObject = jsonObject.getJSONObject("day");
                            Forecast forecast = new Forecast();
                            forecast.setDate(jsonObject.optString("date"));
                            forecast.setMinTemp(dayObject.optDouble("mintemp_c"));
                            forecast.setMaxTemp(dayObject.optDouble("maxtemp_c"));

                            forecast.setAverageTemp(dayObject.optDouble("avgtemp_c"));
                            forecast.setMaxWind(dayObject.optDouble("maxwind_mph"));
                            conditionObject = dayObject.optJSONObject("condition");
                            if (conditionObject != null) {
                                Condition condition = new Condition();
                                condition.setText(conditionObject.optString("text"));
                                condition.setIconUrl(conditionObject.optString("icon"));
                                condition.setCode(conditionObject.optInt("code"));
                            }
                            forecastList.add(forecast);
                        }
                        emitter.onNext(forecastList);
                        emitter.onComplete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    emitter.onError(e);
                }
            }
        });
    }
}
