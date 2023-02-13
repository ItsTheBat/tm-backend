package com.zdf.taskmanager.util;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

@Component
public class GsonContainer {

    public Gson getGson() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);
        TypeAdapter<Date> safeDateTypeAdapter = dateTypeAdapter.nullSafe();
        return new GsonBuilder().registerTypeAdapter(Date.class, safeDateTypeAdapter).create();
    }

}
