package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;
import me.proxer.library.entitiy.manga.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben Gees
 */
final class PageAdapter {

    @FromJson
    List<Page> fromJson(final String[][] json) {
        final List<Page> result = new ArrayList<>();

        for (String[] jsonPage : json) {
            if (jsonPage.length != 3) {
                throw new JsonDataException("Page array length is " + json.length + " instead of 3.");
            }

            result.add(new Page(jsonPage[0], Integer.parseInt(jsonPage[1]), Integer.parseInt(jsonPage[2])));
        }

        return result;
    }
}
