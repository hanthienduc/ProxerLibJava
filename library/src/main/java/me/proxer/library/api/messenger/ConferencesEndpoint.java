package me.proxer.library.api.messenger;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.messenger.Conference;
import me.proxer.library.enums.ConferenceType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving the user's conferences.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ConferencesEndpoint implements PagingEndpoint {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer page;

    /**
     * Sets the type of conferences to load.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private ConferenceType type;

    ConferencesEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<Conference>> build() {
        return internalApi.conferences(page, type);
    }
}
