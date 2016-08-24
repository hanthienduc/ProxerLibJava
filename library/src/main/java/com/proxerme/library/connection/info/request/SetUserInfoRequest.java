package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.InfoRequest;
import com.proxerme.library.connection.info.result.SetUserInfoResult;
import com.proxerme.library.parameters.ViewStateParameter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request to set the user view state of an anime or manga. See the list of possible user view states.
 *
 * @author Desnoo
 */
public class SetUserInfoRequest extends InfoRequest<Void> {

    private static final String ENDPOINT = "setuserinfo";

    private static final String ID_PARAMETER = "id";
    private static final String TYPE_PARAMETER = "type";

    private String id;
    private String type;

    /**
     * The Constructor.
     *
     * @param id   The id of the anime/manga.
     * @param type The list type where the anime/manga should be add to.
     *             See {@link com.proxerme.library.parameters.ViewStateParameter.ViewState} for parameters.
     */
    public SetUserInfoRequest(@NonNull String id, @ViewStateParameter.ViewState String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    protected ProxerResult<Void> parse(@NonNull Moshi moshi, @NonNull ResponseBody body) throws IOException {
        return moshi.adapter(SetUserInfoResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Arrays.<Pair<String, ?>>asList(
                new Pair<>(ID_PARAMETER, id),
                new Pair<>(TYPE_PARAMETER, type)
        );
    }
}