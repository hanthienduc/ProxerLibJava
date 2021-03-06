package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available states of a project.
 *
 * @author Ruben Gees
 */
public enum ProjectState {
    @Json(name = "0")UNKNOWN,
    @Json(name = "1")FINISHED,
    @Json(name = "2")ONGOING,
    @Json(name = "3")PLANNED,
    @Json(name = "4")CANCELLED,
    @Json(name = "5")LICENSED
}
