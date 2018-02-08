# Inventiv Critic Android Library

## Requirements
This library uses Retrofit and the Gson Retrofit Converter.

## Installation
1. Download this repository to your local filesystem.
2. Add the Critic library to your `settings.gradle` file.
```
    include ':critic'
    project(':critic').projectDir = new File('/YOUR_PATH_TO/inventiv-critic-android/library')
```
3. Add the following dependencies to your application's `app/build.gradle` file.
```
    dependencies {
        compile ':critic'
    }
```
4. Add the INTERNET permission to your applications `app/src/main/AndroidManifest.xml` file.
```
<uses-permission android:name="android.permission.INTERNET" />
```

## Sending Customer Feedback Reports
1. Acquire a Product Access Token from the [Critic Web Portal](https://critic.inventiv.io/products) by viewing a Product's details.
2. Use the Product Access Token to submit a feedback report. Perform this work on a background thread.
```
    String productAccessToken = "YOUR_PRODUCT_ACCESS_TOKEN";
    String description = "Text provided by your user.";
    String metadata = "{\"info_about_metadata\": \"Any valid JSON can be sent as metadata.\"}";
    
    List<File> fileAttachments = new ArrayList<File>();
    fileAttachments.add(new File("/path/to/file/you/want"));
    fileAttachments.add(new File("/path/to/another/file/you/want"));
    
    Client.createReport(productAccessToken, description, metadata, fileAttachments);
```
3. The `Client.createReport()` call will return true if successful.

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
