package com.example.WhatWeather;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WhatWeather extends Activity implements ViewWeatherInterface, ObserverWeatherInterface {

    //main Activity. move some methods to another classes
    WeatherParser weatherParser = new WeatherParser();

    ModelWeatherInterface model;
    List<WeatherDay> data; //TODO:data to model, and then get data from model

    /**
     *add gui
     */
    TextView dayNameTv;
    TextView humidityTv;
    TextView windTv;
    TextView morningTempTv;
    TextView dayTempTv;
    TextView evTempTv;
    TextView fallsTv;



    public WhatWeather(ModelWeatherInterface model) {

        super();
        this.model = model;
    }

    public WhatWeather() {
        super();
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //init gui
        dayNameTv = (TextView)findViewById(R.id.DayTextView);
        humidityTv = (TextView)findViewById(R.id.HumidityTextView);
        windTv = (TextView)findViewById(R.id.WindTextView);
        morningTempTv = (TextView)findViewById(R.id.MorningTempTextView);
        dayTempTv = (TextView)findViewById(R.id.DayTempTextView);
        evTempTv = (TextView)findViewById(R.id.EveTempTextView);
        fallsTv = (TextView)findViewById(R.id.FallsTextView);
        //data = new ArrayList<WeatherDay>();


        try {
            data = weatherParser.getParsedData();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        update();
    }

    @Override
    public void update() {

       dayNameTv.setText(data.get(0).getDay());
        //dayNameTv.setText("WowWow");


    }

    @Override
    public void display() {

    }
}
