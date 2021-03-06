package me.proxer.library.api.messenger;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for modifying a conference in various ways.
 * <p>
 * Possible modifications are:
 * - Mark as read
 * - Mark as unread
 * - Block
 * - Unblock
 * - Mark as favourite
 * - Unmark as favourite
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class ConferenceModificationEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;
    private final ConferenceModification modification;

    ConferenceModificationEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id,
                                   @NotNull final ConferenceModification modification) {
        this.internalApi = internalApi;
        this.id = id;
        this.modification = modification;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<Void> build() {
        switch (modification) {
            case READ:
                return internalApi.markConferenceAsRead(id);
            case UNREAD:
                return internalApi.unmarkConferenceAsRead(id);
            case BLOCK:
                return internalApi.markConferenceAsBlocked(id);
            case UNBLOCK:
                return internalApi.unmarkConferenceAsBlocked(id);
            case FAVOUR:
                return internalApi.markConferenceAsFavorite(id);
            case UNFAVOUR:
                return internalApi.unmarkConferenceAsFavorite(id);
            default:
                throw new IllegalArgumentException("Illegal modification: " + modification);
        }
    }

    enum ConferenceModification {
        READ,
        UNREAD,
        BLOCK,
        UNBLOCK,
        FAVOUR,
        UNFAVOUR
    }
}
