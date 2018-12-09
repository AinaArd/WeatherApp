package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp.entities.City;
import com.example.weatherapp.interfaces.MyCallback;

import java.util.List;

/**
 * Created by Aina on 02.12.2018.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private List<City> listOfCities;
    private MyCallback callback;

    public CityAdapter(List<City> listOfCities, MyCallback callback) {
        this.listOfCities = listOfCities;
        this.callback = callback;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false);
        callback = (MyCallback) view.getContext();
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int id) {
        holder.bindView(listOfCities.get(id));
    }

    @Override
    public int getItemCount() {
        return listOfCities.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView cityName;
        private TextView countryName;
        private TextView temp;

        private CityViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.tv_name);
            countryName = itemView.findViewById(R.id.tv_country);
            temp = itemView.findViewById(R.id.tv_temp);
        }

        @SuppressLint("SetTextI18n")
        private void bindView(City data) {
            cityName.setText(data.getName());
            countryName.setText(data.getSys().getCountry());
            temp.setText(data.getMain().getTemp().toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    City city = listOfCities.get(getLayoutPosition());
                    callback.callback(city);
                }
            });
        }
    }

    public void setCities(List<City> cities) {
        this.listOfCities = cities;
        notifyDataSetChanged();
    }
}
