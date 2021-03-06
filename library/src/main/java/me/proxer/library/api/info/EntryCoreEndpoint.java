package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.Entry;
import me.proxer.library.entitiy.info.EntryCore;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for retrieving the most important information of an {@link Entry}.
 * <p>
 * If you need everything, consider using the {@link EntryEndpoint}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class EntryCoreEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    EntryCoreEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<EntryCore> build() {
        return internalApi.entryCore(id);
    }
}
