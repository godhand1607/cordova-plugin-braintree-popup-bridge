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
package org.apache.cordova.popupbridge;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.webkit.WebView;
import androidx.fragment.app.FragmentActivity;

// import com.braintreepayments.popupbridge.PopupBridge;
import com.braintreepayments.api.PopupBridgeClient;


public class PopupBridgePlugin extends CordovaPlugin {
    public static final String TAG = "PopupBridge";
    private PopupBridgeClient mPopupBridgeClient;

    /**
     * Constructor.
     */
    public PopupBridgePlugin() { }

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        // PopupBridge.newInstance(cordova.getActivity(), (WebView) webView.getEngine().getView());
        mPopupBridgeClient = new PopupBridgeClient(
            (FragmentActivity) cordova.getActivity(),
            (WebView) webView.getEngine().getView(),
            cordova.getActivity().getPackageName() + ".popupbridge"
        );

        // register error listener
        mPopupBridgeClient.setErrorListener(error -> showDialog(error.getMessage()));
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);

        // call 'deliverResult' in onResume to capture a pending result
        mPopupBridgeClient.deliverPopupBridgeResult((FragmentActivity) this.cordova.getActivity());
    }

    public void showDialog(String message) {
        new AlertDialog.Builder((FragmentActivity) this.cordova.getActivity())
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
            .show();
    }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("getInfo".equals(action)) {
            JSONObject r = new JSONObject();

            callbackContext.success(r);
        }
        else {
            return false;
        }
        return true;
    }
}
