package com.example.weatherforecast.ui;


import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.weatherforecast.R;
import com.example.weatherforecast.adapter.ForecastAdapter;
import com.example.weatherforecast.di.scopes.ActivityScoped;
import com.example.weatherforecast.model.Forecast;
import com.example.weatherforecast.model.ForecastDay;
import com.example.weatherforecast.model.Forecast_;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScoped
public class MainFragment extends DaggerFragment {

    private MainViewModel mainViewModel;
    private RecyclerView mRecyclerView;
    private TextView mDateView;
    private TextView mMaxTempView;
    private ProgressBar mProgressBar;

    @Inject
    ForecastAdapter mForecastAdapter;
    @Inject
    MainViewModelFactory mainViewModelFactory;

    private CompositeDisposable mSubscription = new CompositeDisposable();

    @Inject
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = root.findViewById(R.id.recycler_view);
        mProgressBar = root.findViewById(R.id.progress_bar);
        mDateView = root.findViewById(R.id.date_view);
        mMaxTempView = root.findViewById(R.id.max_temp);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mForecastAdapter);

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        bindViewModel();

    }

    @Override
    public void onPause() {
        super.onPause();
        unbindViewModel();
    }

    private void bindViewModel() {
        mSubscription.clear();
        mainViewModel.getForecasts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Forecast>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mProgressBar.setVisibility(View.VISIBLE);
                        mSubscription.add(d);
                    }

                    @Override
                    public void onNext(Forecast forecasts) {
                        mProgressBar.setVisibility(View.GONE);
                        Forecast_ forecast_ = forecasts.getForecast();
                        List<ForecastDay> forecastDayList = forecast_.getForecastday();
                        ForecastDay forecastDay = forecastDayList.get(0);
                        forecastDayList.remove(forecastDay);
                        mDateView.setText(forecastDay.getDate());
                        mMaxTempView.setText(Double.toString(forecastDay.getDay().getMaxtempC()));
                        mForecastAdapter.submitList(forecastDayList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void unbindViewModel() {
        mSubscription.dispose();
        mSubscription.clear();
    }
}
