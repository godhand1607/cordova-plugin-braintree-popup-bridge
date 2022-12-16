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

#include <sys/types.h>
#include <sys/sysctl.h>
#include "TargetConditionals.h"

#import <Cordova/CDV.h>
#import "CDVPopupBridgePlugin.h"

#import <PopupBridge/POPPopupBridge.h>

@interface CDVPopupBridgePlugin () <POPPopupBridgeDelegate>
    @property (nonatomic, strong) POPPopupBridge *popupBridge;
@end


@implementation CDVPopupBridgePlugin

- (void)pluginInitialize
{
    self.popupBridge = [[POPPopupBridge alloc] initWithWebView:(WKWebView*)self.webView delegate:self];
}

- (void)popupBridge:(POPPopupBridge *)bridge requestsPresentationOfViewController:(UIViewController *)viewController
{
    UIViewController *rootViewController = [[[[UIApplication sharedApplication] delegate] window] rootViewController];

    [rootViewController presentViewController:viewController animated:YES completion:nil];
}

- (void)popupBridge:(POPPopupBridge *)bridge requestsDismissalOfViewController:(UIViewController *)viewController
{
    [viewController dismissViewControllerAnimated:YES completion:nil];
}

- (void)getInfo:(CDVInvokedUrlCommand*)command
{
    NSDictionary* info = @{};
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:info];

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
