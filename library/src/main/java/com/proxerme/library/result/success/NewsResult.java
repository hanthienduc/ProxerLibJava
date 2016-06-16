package com.proxerme.library.result.success;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.entity.News;
import com.proxerme.library.result.ProxerResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class NewsResult implements ProxerResult<News[]> {

    @Body(name = "notifications")
    News[] item;

    NewsResult() {

    }

    public NewsResult(@NonNull News[] item) {
        this.item = item;
    }

    @Override
    @NonNull
    public News[] getItem() {
        return item;
    }
}
