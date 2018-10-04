# Android SDK Integration Sample
This sample app is an example of how you could integrate the Android Talkdesk SDK into your application.

## How to run
This sample app is provided with sample [authentication server](https://github.com/Talkdesk/mobile-sdk-ruby-server-sample), so it's highly
recomended to configure it first in order to have a better understanding of the configuration properties which you should provide to execute
the Sample Android application.

In order to run this sample you need to provide the following properties by inserting them into **gradle.properties** file:
* **API_HOSTNAME** - Middleware authentication server host name;
* **API_APP_ID** - Application id which helps to identify your application on the server side, in case you have multiple applications;
* **API_AUTH_USERNAME** - Basic HTTP authentication username to prevent unwanted calls to the middleware service;
* **API_AUTH_PASSWORD** - Basic HTTP authentication password to prevent unwanted calls to the middleware service.
As SDK authenticates with Talkdesk Id system you need a server which will act as a middleware between your application and Talkdesk Id.

After that you are able to press the **Run button**.

## How to configure an intention
To change the intention which you want to test it's necessary to change the `INTENTION_NAME` constant value which is located  in the `ExampleFragment.java` class.

You also can pass some additional configurations, for example reopen property or autofill data, when you open the Interaction Channel. For more information check the [SDK documentation](http://mobile-dev.talkdeskapp.com/android/index.html).
