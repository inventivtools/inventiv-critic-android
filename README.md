# Inventiv Critic Android Library

## Installation
1. Add the Inventiv repository to your `build.gradle` file.
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://repo.inventiv.io/' }
    }
}
```
2. Add the following dependency to your `app/build.gradle` file.
```
    dependencies {
        implementation 'io.inventiv.critic.android:critic-android:0.0.7'
    }
```
3. Add the following permissions to your `app/src/main/AndroidManifest.xml` file.
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="18" />
```
4. Find your Product Access Token in the [Critic Web Portal](https://critic.inventiv.io/products) by viewing your Product's details.
5. Initialize Critic by starting it from the `onCreate()` method of your main Application class.
```
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Critic.initialize(this, "YOUR_PRODUCT_ACCESS_TOKEN");
    }
}
```

## Sending Customer Feedback Reports
Enable shake detection in your main Application class to show a feedback report screen when the user shakes their device.
```
    // do this after you call Critic.initialize(this, "YOUR_PRODUCT_ACCESS_TOKEN");
    Critic.startShakeDetection();
```

Alternatively, you can show the default feedback report screen any time you like by calling the following method.
```
    Critic.showFeedbackReportActivity();
```

## Customizing Feedback Reports
Use the Product Access Token to submit a feedback report. Perform this work on a background thread.
```
    String description = "Text provided by your user.";

    JsonObject metadata = new JsonObject();
    metadata.addProperty("star_rating", 5);
    metadata.addProperty("user_id", "adbe342-93245-32549324-aefff3490");    

    List<File> files = new ArrayList<File>();
    files.add(new File("/path/to/a/file/to/attach"));
    files.add(new File("/path/to/another/file/to/attach"));
    
    Report report = new ReportCreator()
        .description(description)
        .metadata(metadata)
        .attachments(files)
    .create(mContext); // mContext is a Context object such as your current Activity.
```

The `ReportCreator.create()` call will return a Report object if successful. Otherwise, a `ReportCreationException` will be thrown.

## License
This library is released under the MIT License.
