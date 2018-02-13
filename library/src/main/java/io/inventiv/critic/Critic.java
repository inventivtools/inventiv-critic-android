package io.inventiv.critic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;
import com.squareup.seismic.ShakeDetector;

public final class Critic {
    public static final String INTENT_EXTRA_PRODUCT_ACCESS_TOKEN = "productAccessToken";

    private static JsonObject mBatteryJson = new JsonObject();
    private static Application mContext;
    private static ApplicationLifecycleTracker mApplicationLifecycleTracker = new ApplicationLifecycleTracker();
    private static String mProductAccessToken;
    private static ShakeDetector mShakeDetector;

    private Critic() {
        // stop external invocation.
    }

    public static void initialize(Application context, String productAccessToken) {

        if(productAccessToken == null || productAccessToken.length() == 0) {
            throw new AssertionError("You need to provide a Product Access Token to create a Report. See the Critic Getting Started Guide at https://inventiv.io/critic/critic-integration-getting-started/.");
        }
        else if(productAccessToken.equals("YOUR_PRODUCT_ACCESS_TOKEN")) {
            throw new AssertionError("Your Product Access Token is invalid. Please use a valid one. See the Critic Getting Started Guide at https://inventiv.io/critic/critic-integration-getting-started/.");
        }

        Critic.mContext = context;
        Critic.mProductAccessToken = productAccessToken;

        mContext.registerActivityLifecycleCallbacks(mApplicationLifecycleTracker);
        registerBatteryBroadcastReceiver();
    }

    public static void startShakeDetection() {

        if(mShakeDetector != null ) {
            // already detecting shakes.
            return;
        }

        SensorManager sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mShakeDetector = new ShakeDetector(new Shakes());
        mShakeDetector.start(sensorManager);
    }

    public static void stopShakeDetection() {
        if(mShakeDetector != null) {
            mShakeDetector.stop();
        }
        mShakeDetector = null;
    }

    public static void showFeedbackReportActivity() {
        Intent intent = new Intent(mContext, FeedbackReportActivity.class);
        intent.putExtra(Critic.INTENT_EXTRA_PRODUCT_ACCESS_TOKEN, mProductAccessToken);
        mContext.startActivity(intent);
    }

    public static String getProductAccessToken() {
        return mProductAccessToken;
    }

    public static void addStandardMetadata(JsonObject metadata) {
        addApplicationMetadata(metadata);
        addDeviceMetadata(metadata);
    }

    private static void addApplicationMetadata(JsonObject metadata) {

        JsonObject application = new JsonObject();

        ApplicationInfo applicationInfo = mContext.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        String applicationName = stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : mContext.getString(stringId);
        application.addProperty("name", applicationName);

        String packageName = mContext.getPackageName();
        application.addProperty("package", packageName);

        try {
            PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(packageName, 0);
            application.addProperty("version_code", packageInfo.versionCode);
            application.addProperty("version_name", packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            // ignore.
        }

        metadata.add("ic_application", application);
    }

    private static void addDeviceMetadata(JsonObject metadata) {

        JsonObject build = new JsonObject();
        build.addProperty("board", Build.BOARD);
        build.addProperty("bootloader", Build.BOOTLOADER);
        build.addProperty("brand", Build.BRAND);
        build.addProperty("device", Build.DEVICE);
        build.addProperty("display", Build.DISPLAY);
        build.addProperty("fingerprint", Build.FINGERPRINT);
        build.addProperty("hardware", Build.HARDWARE);
        build.addProperty("manufacturer", Build.MANUFACTURER);
        build.addProperty("model", Build.MODEL);
        build.addProperty("product", Build.PRODUCT);
        build.addProperty("tags", Build.TAGS);
        build.addProperty("time", Build.TIME);
        build.addProperty("type", Build.TYPE);
        build.addProperty("version", Build.VERSION.RELEASE);

        JsonObject device = new JsonObject();
        device.add("battery", mBatteryJson);
        device.add("build", build);
        device.addProperty("platform", "android");
        metadata.add("ic_device", device);
    }

    private static void registerBatteryBroadcastReceiver() {
        mContext.registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context ctxt, Intent intent) {
                int healthExtra = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
                String health;
                switch(healthExtra) {
                    case BatteryManager.BATTERY_HEALTH_COLD:
                        health = "Cold";
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        health = "Dead";
                        break;
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        health = "Good";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        health = "Over Voltage";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        health = "Overheat";
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        health = "Unspecified Failure";
                        break;
                    default:
                        health = "Unknown";
                        break;
                }
                mBatteryJson.addProperty("health", health);

                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                mBatteryJson.addProperty("percentage", (level / (float)scale * 100));

                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                String chargingMethod = "N/A";
                if(acCharge) {
                    chargingMethod = "AC";
                }
                else if(usbCharge) {
                    chargingMethod = "USB";
                }
                mBatteryJson.addProperty("charging_method", chargingMethod);

                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
                mBatteryJson.addProperty("charging_status", isCharging);
            }
        }, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private static class Shakes implements ShakeDetector.Listener {

        boolean mAlreadyShaken = false;

        @Override
        public void hearShake() {

            if(mApplicationLifecycleTracker.mCurrentActivity == null) {
                Log.w(Critic.class.getSimpleName(), "No current activity! Can not handle shake.");
                return;
            }

            if(mApplicationLifecycleTracker.mCurrentDialog != null) {
                Log.w(Critic.class.getSimpleName(), "Shaken repeatedly. Ignoring.");
                return;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(mApplicationLifecycleTracker.mCurrentActivity)
                    .setTitle("Easy, easy!")
                    .setMessage("Do you want to send us feedback?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Critic.showFeedbackReportActivity();
                            mAlreadyShaken = false;
                            mApplicationLifecycleTracker.mCurrentDialog = null;
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mAlreadyShaken = false;
                            mApplicationLifecycleTracker.mCurrentDialog = null;
                        }
                    }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            mAlreadyShaken = false;
                            mApplicationLifecycleTracker.mCurrentDialog = null;
                        }
                    });

            if(Build.VERSION.SDK_INT >= 17){
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        mAlreadyShaken = false;
                        mApplicationLifecycleTracker.mCurrentDialog = null;
                    }
                });
            }

            mApplicationLifecycleTracker.mCurrentDialog = builder.create();
            mApplicationLifecycleTracker.mCurrentDialog.show();
        }
    }

    private static class ApplicationLifecycleTracker implements Application.ActivityLifecycleCallbacks {

        Activity mCurrentActivity;
        AlertDialog mCurrentDialog;

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            // do nothing.
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            // do nothing.
        }

        @Override
        public void onActivityPaused(Activity activity) {
            mCurrentActivity = null;
            mCurrentDialog = null;
        }

        @Override
        public void onActivityResumed(Activity activity) {
            mCurrentActivity = activity;
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            // do nothing.
        }

        @Override
        public void onActivityStarted(Activity activity) {
            // do nothing.
        }

        @Override
        public void onActivityStopped(Activity activity) {
            // do nothing.
        }
    }
}
