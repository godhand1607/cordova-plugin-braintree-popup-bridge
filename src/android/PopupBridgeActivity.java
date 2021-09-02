package org.apache.cordova.popupbridge;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

// import com.braintreepayments.browserswitch.BrowserSwitchActivity;

public class PopupBridgeActivity extends AppCompatActivity {
// public class PopupBridgeActivity extends BrowserSwitchActivity {

    @Override
    protected void onNewIntent(Intent newIntent) {
        super.onNewIntent(newIntent);
        setIntent(newIntent);
    }

}
