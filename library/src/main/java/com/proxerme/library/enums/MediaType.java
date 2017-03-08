package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum of the available media types. Similiar to {@link Medium}
 * <p>
 * Created by Desnoo on 07.03.2017.
 */
public enum MediaType {

    @Json(name = "animeseries")ANIMESERIES,
    @Json(name = "movie")MOVIE,
    @Json(name = "ova")OVA,
    @Json(name = "hentai")HENTAI,
    @Json(name = "mangaseries")MANGASERIES,
    @Json(name = "oneshot")ONESHOT,
    @Json(name = "doujin")DOUJIN,
    @Json(name = "hmanga")HMANGA,
    @Json(name = "all-anim")ALL_ANIME,
    @Json(name = "all-manga")ALL_MANGA,
    @Json(name = "all")ALL,
    @Json(name = "all18")ALL_WITH_HENTAI;
}
