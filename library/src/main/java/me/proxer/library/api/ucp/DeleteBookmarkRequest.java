package me.proxer.library.api.ucp;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for deleting a bookmark.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class DeleteBookmarkRequest implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    DeleteBookmarkRequest(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<Void> build() {
        return internalApi.deleteBookmark(id);
    }
}
