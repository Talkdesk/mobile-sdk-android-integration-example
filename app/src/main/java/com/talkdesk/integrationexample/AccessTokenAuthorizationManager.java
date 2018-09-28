package com.talkdesk.integrationexample;

import android.os.Handler;
import com.talkdesk.sdk.Authorization;
import com.talkdesk.sdk.AuthorizationManager;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class AccessTokenAuthorizationManager extends AuthorizationManager {
    private OkHttpClient client;
    private String applicationId;
    private Handler handler;

    AccessTokenAuthorizationManager(OkHttpClient client, Handler handler, String applicationId) {
        this.client = client;
        this.handler = handler;
        this.applicationId = applicationId;
    }

    @Override
    public void requestAuthorization() {
        Request request = new Request.Builder()
                .url(BuildConfig.API_HOSTNAME + "/token/" + applicationId)
                .addHeader("Authorization", Credentials.basic(BuildConfig.API_AUTH_USERNAME, BuildConfig.API_AUTH_PASSWORD))
                .post(RequestBody.create(MediaType.parse("application/json"), ""))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final Authorization authorization;

                if (e != null) {
                    authorization = new Authorization(Authorization.Status.NETWORK_FAILED);
                } else {
                    authorization = new Authorization(Authorization.Status.FAILED);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        handleAuthorization(authorization);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    final String authToken = jsonObject.getString("auth_token");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            handleAuthorization(new Authorization(Authorization.Status.SUCCESS, authToken));
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            handleAuthorization(new Authorization(Authorization.Status.FAILED));
                        }
                    });
                }
            }
        });
    }
}
