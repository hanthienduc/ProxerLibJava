package me.proxer.library.api;

import com.squareup.moshi.Json;
import lombok.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ruben Gees
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class ProxerResponse<T> {

    @Getter(AccessLevel.NONE)
    @Json(name = "error")
    private boolean error;

    @Getter(value = AccessLevel.PACKAGE, onMethod = @__({@NotNull}))
    @Json(name = "message")
    private String message;

    @Getter(AccessLevel.PACKAGE)
    @Json(name = "code")
    private int code;

    @Getter(AccessLevel.PACKAGE)
    @Json(name = "data")
    private T data;

    boolean isSuccessful() {
        return !error;
    }
}
