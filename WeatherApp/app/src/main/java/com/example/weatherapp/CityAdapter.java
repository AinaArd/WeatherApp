package com.example.weatherapp;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

/**
 * Created by Aina on 02.12.2018.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private List<City> listOfCities;
    private MyCallback callback;

    public CityAdapter(List<City> listOfCities) {
        this.listOfCities = listOfCities;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.bindView(listOfCities.get(id));
        }
    }

    @Override
    public int getItemCount() {
        return listOfCities.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView cityName;
        private TextView cityInfo;

        private CityViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.tv_name);
            cityInfo = itemView.findViewById(R.id.tv_info);
        }

        private void bindView(City city) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cityName.setText(Html.fromHtml(city.getName(), Html.FROM_HTML_MODE_LEGACY));
                cityInfo.setText(Html.fromHtml(String.valueOf(city.getTemp()), Html.FROM_HTML_MODE_LEGACY));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String infoFromUser = cityInfo.getText().toString();
                    callback.callback(infoFromUser);
                }
            });
        }
    }

    public void setCities(Collection<City> cities) {
        listOfCities.addAll(cities);
        notifyDataSetChanged(); // to let adapter know when smth is changed
    }
}
