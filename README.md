# io.inventiv.critic Android Library

## Requirements
This library uses Retrofit and the Gson Retrofit Converter.

## Installation
1. Add the following dependencies to your application's `app/build.gradle` file.
```
    dependencies {
        compile 'com.squareup.retrofit2:retrofit:2.3.0'
        compile 'com.squareup.retrofit2:converter-gson:2.3.0'
    }
```
1. Add the INTERNET permission to your applications `app/src/main/AndroidManifest.xml` file.
```
<uses-permission android:name="android.permission.INTERNET" />
```

## Usage
1. Authenticate using the provided `Client` class.
```
    if( Client.authenticate("me@example.com", "my super secret password") ) {
        // Authentication successful! Now you can make service calls.
    }
    else {
        // Authentication failure. Check your credentials and Internet connectivity, and then try again.
    }
```
2. Send web requests using the Retrofit helper methods in the `Client` class. This will fail if you have not authenticated during the current user session / application lifecycle.
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
