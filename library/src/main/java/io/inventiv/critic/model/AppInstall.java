package io.inventiv.critic.model;

import com.google.gson.annotations.SerializedName;

public class AppInstall {

    @SerializedName("id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Wrapper for Report objects to use prior to serialization of JSON payloads in some Retrofit web requests.
     */
    public static class Wrapper {

        @SerializedName("app_install")
        private AppInstall appInstall;

        public Wrapper(AppInstall appInstall) {
            setAppInstall(appInstall);
        }

        public AppInstall getAppInstall() {
            return appInstall;
        }

        public void setAppInstall(AppInstall appInstall) {
            this.appInstall = appInstall;
        }
    }
}
