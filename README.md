# Inventiv Critic Android Library

## Requirements
This library uses Retrofit and the Gson Retrofit Converter.

## Installation
1. Add the Inventiv repository to your `build.gradle` file's list of repositories.
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://repo.inventiv.io/' }
    }
}
```
2. Add the following dependencies to your application's `app/build.gradle` file.
```
    dependencies {
        compile 'io.inventiv.critic.android:critic-android:0.0.5@aar'
        compile 'com.squareup.retrofit2:retrofit:2.3.0'
        compile 'com.squareup.retrofit2:converter-gson:2.3.0'
    }
```
3. Add the INTERNET permission to your application's `app/src/main/AndroidManifest.xml` file.
```
<uses-permission android:name="android.permission.INTERNET" />
```

## Sending Customer Feedback Reports Using the Default Screen
1. Acquire a Product Access Token from the [Critic Web Portal](https://critic.inventiv.io/products) by viewing a Product's details.
2. Use the Product Access Token to invoke the default feedback report screen.
```
    ReportCreator.showDefaultActivity(YourActivity.this, "YOUR_PRODUCT_ACCESS_TOKEN");
```

## Sending Customer Feedback Reports Your Own Way
1. Acquire a Product Access Token from the [Critic Web Portal](https://critic.inventiv.io/products) by viewing a Product's details. 
2. Use the Product Access Token to submit a feedback report. Perform this work on a background thread.
```
    String productAccessToken = "YOUR_PRODUCT_ACCESS_TOKEN"; // see https://inventiv.io/critic/critic-integration-getting-started/ for details.
    String description = "Text provided by your user.";

    JsonObject metadata = new JsonObject();
    metadata.addProperty("star_rating", 5);
    metadata.addProperty("user_id", "adbe342-93245-32549324-aefff3490");    

    List<File> files = new ArrayList<File>();
    files.add(new File("/path/to/a/file/to/attach"));
    files.add(new File("/path/to/another/file/to/attach"));
    
    Report report = new ReportCreator()
        .productAccessToken(productAccessToken)
        .description(description)
        .metadata(metadata)
        .attachments(files)
    .create(); // throws a ReportCreationException if something went wrong.
```
3. The `ReportCreator.create()` call will return a Report object if successful. Otherwise, a `ReportCreationException` will be thrown.

## General Usage
1. Authenticate using the provided `Client` class.
```
    if( Client.authenticate("me@example.com", "my super secret password") ) {
        // Authentication successful! Now you can make service calls.
    }
    else {
        // Authentication failure. Check your credentials and Internet connectivity, and then try again.
    }
```
2. Send web requests from a background thread using the Retrofit helper methods in the `Client` class. This will fail if you have not authenticated during the current user session / application lifecycle.
```
    Call<User> userCall = Client.userService().profile();
    try {
        Response<User> userResponse = userCall.execute();
        System.out.println("user response: " + userResponse.body().getEmail());
    } catch (IOException e) {
        e.printStackTrace();
    }
```
3. If you choose to store the `Client.getAuthenticationToken()` value for use in later sessions (e.g., "Remember Me" functionality), please store it in a secure/encrypted location.

If you do choose to store the authentication token for later sessions, remember to set the token in the Client before executing other web requests.
```
    Client.setAuthorizationToken("Bearer eyJhbGciOi...");
```

## License
This library is released under the MIT License.
