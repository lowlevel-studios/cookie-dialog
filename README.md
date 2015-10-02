# cookie-dialog [![Maven Central](https://maven-badges.herokuapp.com/maven-central/st.lowlevel/cookie-dialog/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/st.lowlevel/cookie-dialog)

An Android library that displays to the user a dialog with the "cookie" message which is required by the EU laws.

## Screenshots

**Dialog:**

![Image](https://raw.githubusercontent.com/lowlevel-studios/cookie-dialog/master/art/screenshot.png)

**Overlay:**

![Image](https://raw.githubusercontent.com/lowlevel-studios/cookie-dialog/master/art/screenshot-overlay.png)

# Include in your project

## Maven

This library is available in Maven Central, so you just need to add the following dependency to your project's `build.gradle`

```
compile 'st.lowlevel:cookie-dialog:1.0.3@aar'
```

## How to use

This is a quick example of using this library in an application.

```java
public class MainActivity extends Activity {
  private CookieManager mCookieManager = new CookieManager(this);
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // your code here
    mCookieManager.showOnce(false, CookieManager.Type.DIALOG); // Or pass "true" to show the dialog only to EU users
  }
  
  @Override
  protected void onDestroy() {
    // your code here
    mCookieManager.cancel();
  }
}
```

This will show the dialog only **once**. The next time the user opens the app the dialog will not be displayed.

If instead of a dialog you prefer to display an overlay, just change `CookieManager.Type.DIALOG` to `CookieManager.Type.OVERLAY`.

## Permissions

No permissions are required if the device has a SIM card or there is no need to detect if an user is located in an EU country. Otherwise, at least one of this permissions are needed:

```
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.INTERNET" />
```

## Material design

A dialog with Material design can be achieved with two small changes.

Just add the following Maven dependency:

```
compile('st.lowlevel:cookie-dialog-material:1.0.3@aar') {
  transitive = true
}
```

And the following line of code:

```
mCookieManager.setDialog(new MaterialCookieDialog(context));
```

# Developed by

Miguel Botón (Lowlevel Studios) - mboton@lowlevel-studios.com

# License

Copyright 2015 Miguel Botón

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
