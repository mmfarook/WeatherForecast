package com.example.weatherforecast.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.weatherforecast.R;
import com.example.weatherforecast.adapter.ForecastAdapter;
import com.example.weatherforecast.model.Forecast;
import com.example.weatherforecast.repository.ForecastRepository;

import java.util.List;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    public ForecastRepository mForecastRepository;
    private RecyclerView mRecyclerView;
    private ForecastAdapter mForecastAdapter;
    private TextView mDateView;
    private TextView mMaxTempView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mDateView = findViewById(R.id.date_view);
        mMaxTempView = findViewById(R.id.max_temp);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mForecastAdapter = new ForecastAdapter();
        mRecyclerView.setAdapter(mForecastAdapter);
        mForecastRepository = new ForecastRepository();
        mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(mForecastRepository)).get(MainViewModel.class);
        mainViewModel.getForecasts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Forecast>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Forecast> forecasts) {
                        Forecast forecast = forecasts.get(0);
                        mDateView.setText(forecast.getDate());
                        mMaxTempView.setText(Double.toString(forecast.getMaxTemp()));
                        forecasts.remove(forecast);
                        mForecastAdapter.submitList(forecasts);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
