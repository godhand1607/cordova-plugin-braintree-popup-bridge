/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package <%PACKAGE_NAME%>;

// import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
// import android.webkit.WebView;

import org.apache.cordova.*;

// import com.braintreepayments.api.PopupBridgeClient;


public class MainActivity extends CordovaActivity
{
    // private PopupBridgeClient mPopupBridgeClient;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);

        // mPopupBridgeClient = new PopupBridgeClient(
        //     this,
        //     (WebView) this.appView.getEngine().getView(),
        //     this.getPackageName() + ".popupbridge"
        // );


        // // register error listener
        // mPopupBridgeClient.setErrorListener(error -> showDialog(error.getMessage()));
    }


    // @Override
    // public void onResume() {
    //     super.onResume();

    //     // call 'deliverResult' in onResume to capture a pending result
    //     mPopupBridgeClient.deliverPopupBridgeResult(this);
    // }

    @Override
    protected void onNewIntent(Intent newIntent) {
        super.onNewIntent(newIntent);
        setIntent(newIntent);
    }

    // public void showDialog(String message) {
    //     new AlertDialog.Builder(this)
    //         .setMessage(message)
    //         .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
    //         .show();
    // }

}
