package com.example.WhatWeather;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class WhatWeather extends Activity implements ViewWeatherInterface, ObserverWeatherInterface {

    //main Activity. move some methods to another classes

    ModelWeatherInterface model;
    List<WeatherDay> allDaysForecast;
    /**
     *add gui
     */
    TextView dayNameTv;
    TextView humidityTv;
    TextView windTv;
    TextView pressureTv;
    TextView morningTempTv;
    TextView dayTempTv;
    TextView evTempTv;
    TextView fallsTv;


    public WhatWeather() {
        super();
        allDaysForecast = new ArrayList<WeatherDay>();
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //init gui
        dayNameTv = (TextView)findViewById(R.id.DayTextView);
        humidityTv = (TextView)findViewById(R.id.HumidityTextView);
        windTv = (TextView)findViewById(R.id.WindTextView);
        pressureTv = (TextView)findViewById(R.id.PressTextView);
        morningTempTv = (TextView)findViewById(R.id.MorningTempTextView);
        dayTempTv = (TextView)findViewById(R.id.DayTempTextView);
        evTempTv = (TextView)findViewById(R.id.EveTempTextView);
        fallsTv = (TextView)findViewById(R.id.FallsTextView);
        //data = new ArrayList<WeatherDay>();

        model = new WeatherModel();
        model.addObserver(this);
        model.collectData();  //TODO: вывести в отдельный поток, не выполнять в граф потоке!
    }

    /**
     * update data from model
     */
    @Override
    public void update() {

        /**
         * static final String ITEM = "timestep";
         static final String DATE_TIME = "datetime"; //<datetime>2014-2-10 19:00</datetime>
         static final String HOUR_TIME = "G"; // 19; 7;
         static final String CLOUD_COVER = "cloud_cover"; //73 %
         static final String PRESSURE = "pressure"; //765mm
         static final String TEMPERATURE = "temperature";
         static final String HUMIDITY = "humidity"; //81 ?
         static final String WIND_DIRECTION = "wind_direction"; // С-З
         static final String WIND_VELOCITY = "wind_velocity"; //2 (m/c)
         static final String FALLS = "falls"; //0 ?
         static final String DROPS = "drops"; //0  ?
         static final String END = "point";
         */


       if(model instanceof WeatherModel){
           Log.d("WHAT_WEATHER","********Fill allDaysForecast");
           allDaysForecast = ((WeatherModel) model).getAllDaysData();
       }

       //dayNameTv.setText(allDaysForecast.get(0).getDay());
        //dayNameTv.setText("WowWow");

        for(WeatherDay weatherDay : allDaysForecast){

            Log.i("XmlPullParser", "Date time: " + weatherDay.getDay()+
                                    "\nHour : " + weatherDay.getTime()+
                                    "\nCloud Cover : " + weatherDay.getCloudCover()+
                    "\nPressure : " + weatherDay.getPressure() + " мм.р.ст" +
                    "\nTemperature: " + weatherDay.getTemp() + " C" +
                    "\nHumidity: " + weatherDay.getHumidity() +
                    "\nWind Direction: " + weatherDay.getWindDirection() +
                    "\nWind Velocity: " + weatherDay.getWindVelocity() +
                    "\nFalls : " + weatherDay.getFalls() +
                    "\nDrops : " + weatherDay.getDrops()
            );
        }

        display();


    }

    @Override
    public void display() {

        dayNameTv.setText(allDaysForecast.get(0).getDay());
        humidityTv.setText(allDaysForecast.get(0).getHumidity() + " %");
        windTv.setText(allDaysForecast.get(0).getWindDirection() + allDaysForecast.get(0).getWindVelocity() + "\nм/с");
        pressureTv.setText(allDaysForecast.get(0).getPressure()+"\nммрст");


    }
}
