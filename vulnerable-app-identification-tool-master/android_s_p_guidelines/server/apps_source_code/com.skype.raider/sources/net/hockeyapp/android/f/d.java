package net.hockeyapp.android.f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.hockeyapp.android.d.b;
import net.hockeyapp.android.d.c;
import net.hockeyapp.android.j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class d {

    private static class a {
        static final d a = new d();
    }

    /* synthetic */ d(byte b) {
        this();
    }

    private d() {
    }

    public static d a() {
        return a.a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static net.hockeyapp.android.d.d a(String feedbackResponseJson) {
        net.hockeyapp.android.d.d feedbackResponse = null;
        if (feedbackResponseJson == null) {
            return null;
        }
        net.hockeyapp.android.d.d feedbackResponse2;
        JSONObject jSONObject = new JSONObject(feedbackResponseJson);
        JSONObject feedbackObject = jSONObject.getJSONObject("feedback");
        net.hockeyapp.android.d.a feedback = new net.hockeyapp.android.d.a();
        JSONArray messagesArray = feedbackObject.getJSONArray("messages");
        ArrayList<c> messages = null;
        if (messagesArray.length() > 0) {
            messages = new ArrayList();
            for (int i = 0; i < messagesArray.length(); i++) {
                String subject = messagesArray.getJSONObject(i).getString("subject");
                String text = messagesArray.getJSONObject(i).getString("text");
                String oem = messagesArray.getJSONObject(i).getString("oem");
                String model = messagesArray.getJSONObject(i).getString("model");
                String osVersion = messagesArray.getJSONObject(i).getString("os_version");
                String createdAt = messagesArray.getJSONObject(i).getString("created_at");
                int id = messagesArray.getJSONObject(i).getInt("id");
                String token = messagesArray.getJSONObject(i).getString("token");
                int via = messagesArray.getJSONObject(i).getInt("via");
                String userString = messagesArray.getJSONObject(i).getString("user_string");
                String cleanText = messagesArray.getJSONObject(i).getString("clean_text");
                String name = messagesArray.getJSONObject(i).getString("name");
                String appId = messagesArray.getJSONObject(i).getString("app_id");
                JSONArray jsonAttachments = messagesArray.getJSONObject(i).optJSONArray("attachments");
                List feedbackAttachments = Collections.emptyList();
                if (jsonAttachments != null) {
                    feedbackAttachments = new ArrayList();
                    for (int j = 0; j < jsonAttachments.length(); j++) {
                        int attachmentId = jsonAttachments.getJSONObject(j).getInt("id");
                        int attachmentMessageId = jsonAttachments.getJSONObject(j).getInt("feedback_message_id");
                        String filename = jsonAttachments.getJSONObject(j).getString("file_name");
                        String url = jsonAttachments.getJSONObject(j).getString(j.FRAGMENT_URL);
                        String attachmentCreatedAt = jsonAttachments.getJSONObject(j).getString("created_at");
                        String attachmentUpdatedAt = jsonAttachments.getJSONObject(j).getString("updated_at");
                        b feedbackAttachment = new b();
                        feedbackAttachment.a(attachmentId);
                        feedbackAttachment.b(attachmentMessageId);
                        feedbackAttachment.a(filename);
                        feedbackAttachment.b(url);
                        feedbackAttachment.c(attachmentCreatedAt);
                        feedbackAttachment.d(attachmentUpdatedAt);
                        feedbackAttachments.add(feedbackAttachment);
                    }
                }
                c feedbackMessage = new c();
                feedbackMessage.k(appId);
                feedbackMessage.i(cleanText);
                feedbackMessage.f(createdAt);
                feedbackMessage.a(id);
                feedbackMessage.d(model);
                feedbackMessage.j(name);
                feedbackMessage.c(oem);
                feedbackMessage.e(osVersion);
                feedbackMessage.a(subject);
                feedbackMessage.b(text);
                feedbackMessage.g(token);
                feedbackMessage.h(userString);
                feedbackMessage.b(via);
                feedbackMessage.a(feedbackAttachments);
                messages.add(feedbackMessage);
            }
        }
        feedback.a((ArrayList) messages);
        try {
            feedback.a(feedbackObject.getString("name"));
        } catch (JSONException e) {
            e.f();
        }
        try {
            feedback.b(feedbackObject.getString("email"));
        } catch (JSONException e2) {
            e.f();
        }
        try {
            feedback.a(feedbackObject.getInt("id"));
            try {
                feedback.c(feedbackObject.getString("created_at"));
            } catch (JSONException e3) {
                e.f();
            }
            feedbackResponse2 = new net.hockeyapp.android.d.d();
        } catch (JSONException e4) {
        }
        try {
            feedbackResponse2.a(feedback);
            try {
                feedbackResponse2.a(jSONObject.getString("status"));
            } catch (JSONException e5) {
                e.f();
            }
            try {
                feedbackResponse2.b(jSONObject.getString("token"));
                return feedbackResponse2;
            } catch (JSONException e6) {
                e.f();
                return feedbackResponse2;
            }
        } catch (JSONException e7) {
            feedbackResponse = feedbackResponse2;
            e.f();
            return feedbackResponse;
        }
    }
}
