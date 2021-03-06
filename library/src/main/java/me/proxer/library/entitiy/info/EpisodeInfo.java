package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Entity containing information about the available episodes or chapters of an media entry.
 *
 * @author Ruben Gees.
 */
@Value
public class EpisodeInfo {

    /**
     * Returns the first available episode.
     */
    @Json(name = "start")
    private int firstEpisode;

    /**
     * Returns the last available episode.
     */
    @Json(name = "end")
    private int lastEpisode;

    /**
     * Returns the category of the associated media entry.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the available languages.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "lang")
    private Set<MediaLanguage> availableLanguages;

    /**
     * Returns the progress, the user has made on this media entry so far.
     */
    @Json(name = "state")
    private int userProgress;

    /**
     * Returns the actual list of episodes.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "episodes")
    private List<Episode> episodes;
}
