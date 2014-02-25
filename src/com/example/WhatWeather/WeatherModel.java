package com.example.WhatWeather;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import com.example.parser.XmlPullWeatherParser;

public class WeatherModel implements ModelWeatherInterface {

    List<ObserverWeatherInterface> observers;
    List<WeatherDay> allDaysData;
    List<WeatherDay> tomorrowDayData;
    List<WeatherDay> afterTomorrowDayData;

    WeatherModel(){
        observers = new ArrayList<ObserverWeatherInterface>();
        allDaysData = new ArrayList<WeatherDay>();

    }

        @Override
    public void addObserver(ObserverWeatherInterface o) {
            observers.add(o);

    }

    @Override
    public void delObserver(ObserverWeatherInterface o) {
        if(!observers.isEmpty()){
            observers.remove(o);
        }

    }

    @Override
    public void notifyObservers() {

        for(ObserverWeatherInterface obs : observers){
            Log.i("WEATHER_MODEL","NOTIFY_Observers");
            obs.update();

        }

    }

    @Override
    public void collectData() {

        getForecast();

    }

    private void getForecast(){

        XmlPullWeatherParser parser = new XmlPullWeatherParser("http://rp5.ru/xml/6036/00000/ru");

        allDaysData = parser.parse();
        notifyObservers();
    }

    public List<WeatherDay> getAllDaysData(){
        return allDaysData;
    }


}
