package com.example.weatherforecast.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherforecast.R;
import com.example.weatherforecast.model.Forecast;

public class ForecastAdapter extends ListAdapter<Forecast, ForecastAdapter.ForecastViewHolder> {

    final static DiffUtil.ItemCallback<Forecast> diffCallback = new DiffUtil.ItemCallback<Forecast>() {
        @Override
        public boolean areItemsTheSame(Forecast oldItem, Forecast newItem) {
            return oldItem.getMaxTemp() == newItem.getMaxTemp();
        }

        @Override
        public boolean areContentsTheSame(Forecast oldItem, Forecast newItem) {
            return areItemsTheSame(oldItem, newItem);

        }
    };

    public ForecastAdapter() {
        super(diffCallback);
    }


    class ForecastViewHolder extends RecyclerView.ViewHolder {
        private TextView dateView;
        private TextView maxTempView;


        public ForecastViewHolder(View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.date_view);
            maxTempView = itemView.findViewById(R.id.max_temp);
        }


        void bindTo(ForecastViewHolder forecastViewHolder, Forecast forecast) {
            dateView.setText(forecast.getDate());
            maxTempView.setText(Double.toString(forecast.getMaxTemp()));
        }
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);
        return new ForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder mholder, int position) {
        Forecast forecast = getItem(position);
        mholder.bindTo(mholder, forecast);
    }
}
