# Android SDK Integration Sample
This is a sample that shows how an application can integrate with Android Talkdesk SDK.

## How to run
This sample app is designed to work with the [reference authentication server](https://github.com/Talkdesk/mobile-sdk-ruby-server-sample).
It's recommended to configure the authentication server first in order to have a better understanding of the properties that 
should be provided to have the sample Android application running.

In order to run this sample the following properties should be provided through the **gradle.properties** file:
* **API_HOSTNAME** - Reference authentication server host name;
* **API_APP_ID** - Application id which helps to identify your application on the server side, in case you have multiple applications;
* **API_AUTH_USERNAME** - Basic HTTP authentication username to prevent unwanted calls to the reference authentication server;
* **API_AUTH_PASSWORD** - Basic HTTP authentication password to prevent unwanted calls to the reference authentication server.
The Android Talkdesk SDK needs to authenticate with Talkdesk via an external service. This would typically be hosted on the mobile app 
backend or other customer-maintained server.

After that you are able to press the IDE's **Run button** to start the sample.

## How to configure an intention
To change the intention which you want to test it's necessary to change the `INTENTION_NAME` constant value which is located  in the `ExampleFragment.java` class.

You also can pass some additional configurations - for example, interaction reopen or autofill data - when you open the Interaction Channel.
For more information check the [SDK documentation](http://mobile-dev.talkdeskapp.com/android/index.html).

## Authentication diagrams
In the following diagram you can see how the authentication is performed and which components are involved.
![Talkdesk Id Authentication](art/auth.png)

Also we recommend more sophisticated but much secure implementation with push notifications.
![Talkdesk Id Push Authentication](art/auth-push.png)
