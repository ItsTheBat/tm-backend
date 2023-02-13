package com.zdf.taskmanager.service;

import org.springframework.stereotype.Service;

import com.zdf.taskmanager.util.GsonContainer;

@Service
public class BaseResponseBuilder {

    public <T> String buildBaseResponse(T entity) {
        GsonContainer gsonContainer = new GsonContainer();
        return gsonContainer.getGson().toJson(entity);
    }

}
