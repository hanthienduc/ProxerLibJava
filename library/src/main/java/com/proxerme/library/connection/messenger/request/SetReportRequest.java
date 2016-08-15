package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.messenger.result.SetActionResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * The class that represents the SetReportRequest. Use this to report a conference with a reason.
 *
 * @author Desnoo
 */
public class SetReportRequest extends ProxerRequest<SetActionResult> {

    private static final String SET_REPORT_URL = "/api/v1/messenger/report";
    private static final String CONFERENCE_ID = "conference_id";
    private static final String REASON = "text";

    private String conferenceId;
    private String reason;

    /**
     * Package constructor.
     */
    SetReportRequest() {
    }

    /**
     * The constructor.
     *
     * @param conferenceId The conference id of the conference to mark as unread.
     * @param reason       The reason for the report.
     */
    public SetReportRequest(@NonNull String conferenceId, @NonNull String reason) {
        this.conferenceId = conferenceId;
        this.reason = reason;
    }

    /**
     * Returns the id of the conference.
     *
     * @return The conference id.
     */
    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }

    /**
     * Returns the Reason.
     *
     * @return The Reason.
     **/
    @NonNull
    public String getReason() {
        return reason;
    }

    @Override
    protected int getTag() {
        return ProxerTag.MESSENGER_SET_REPORT;
    }

    @Override
    protected SetActionResult parse(@NonNull Response response) throws Exception {
        return response.asClass(SetActionResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + SET_REPORT_URL;
    }

    @Nullable
    @Override
    protected Form getBody() {
        return new Form()
                .add(CONFERENCE_ID, conferenceId)
                .add(REASON, reason);
    }
}