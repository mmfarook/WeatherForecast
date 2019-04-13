package com.example.weatherforecast.adapter;



import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherforecast.R;
import com.example.weatherforecast.model.ForecastDay;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastAdapter extends ListAdapter<ForecastDay, ForecastAdapter.ForecastViewHolder> {

    final static DiffUtil.ItemCallback<ForecastDay> diffCallback = new DiffUtil.ItemCallback<ForecastDay>() {
        @Override
        public boolean areItemsTheSame(ForecastDay oldItem, ForecastDay newItem) {
            return TextUtils.equals(oldItem.getDate(), newItem.getDate());
        }

        @Override
        public boolean areContentsTheSame(ForecastDay oldItem, ForecastDay newItem) {
            return areItemsTheSame(oldItem, newItem);

        }
    };

    @Inject
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


        void bindTo(ForecastViewHolder forecastViewHolder, ForecastDay forecast) {
            dateView.setText(forecast.getDate());
            maxTempView.setText(Double.toString(forecast.getDay().getMaxtempF()));
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
        ForecastDay forecast = getItem(position);
        mholder.bindTo(mholder, forecast);
    }
}
