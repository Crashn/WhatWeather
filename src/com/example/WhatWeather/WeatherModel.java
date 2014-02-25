package com.example.WhatWeather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.text.format.DateUtils;
import android.util.Log;
import com.example.parser.XmlPullWeatherParser;

public class WeatherModel implements ModelWeatherInterface {

    static final int TODAY_DATE_TIME = 1;
    static final int TOMORROW_DATE_TIME = 2;
    static final int AFTER_TOMORROW_DATE_TIME = 3;

    List<ObserverWeatherInterface> observers;
    List<WeatherDay> allDaysData;

    List<WeatherDay> todayDayData;
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

        try {
            whatDate(allDaysData.get(3).getDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        notifyObservers();
    }

    public List<WeatherDay> getAllDaysData(){
        return allDaysData;
    }

    private void fillTodayForecast(){

    }
    private void fillNextDayForecast(){

    }
    private void fillAfterNextDayForecast(){

    }

    private int whatDate(String date) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,0);

        Date dateNow = dateFormat.parse(date);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE,1);

        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DATE, 2);

        String today = dateFormat.format(cal.getTime());
        String dayToCheck = dateFormat.format(dateNow);
        String tomorrow = dateFormat.format(cal2.getTime());
        String afterTomorrow = dateFormat.format(cal3.getTime());

       Log.i("DAY_FORMAT: ", today + "\n" + dayToCheck + "\ntomorrow: " + tomorrow + "\n afterTomorrow" + afterTomorrow);

        if(dayToCheck.equals(today)){
            Log.i("DAY_FORMAT: ", " today");
            return 1;
        }
        else if(dayToCheck.equals(tomorrow)){
            Log.i("DAY_FORMAT", " ***tomorrow***");
            return 2;
        }
        else if(dayToCheck.equals(afterTomorrow)){
            Log.i("DAY_FORMAT", " ***after tomorrow***");
            return 3;
        }
        else{
            Log.i("DAY_FORMAT", "\nDAY1: " + today + "\nDAY2: " + dayToCheck + "\n");
        }
        return 4;

    }


}
