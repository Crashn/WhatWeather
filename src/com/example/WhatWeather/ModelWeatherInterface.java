package com.example.WhatWeather;

public interface ModelWeatherInterface {

    public void addObserver(ObserverWeatherInterface o);
    public void delObserver(ObserverWeatherInterface o);
    public void notifyObservers();
}
