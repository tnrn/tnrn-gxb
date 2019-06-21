//
//  RCTGXB.m
//  RCTGXB
//
//  Created by winter on 2019/6/19.
//  Copyright © 2019 tnrn. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import <gxb_data_sdk/GXBSDK.h>

#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#else
#import "RCTBridgeModule.h"
#endif



@interface RCTGXB : NSObject <RCTBridgeModule>

@end

@implementation RCTGXB

RCT_EXPORT_MODULE(GxbModule)

- (dispatch_queue_t)methodQueue {
    return dispatch_get_main_queue();
}

RCT_EXPORT_METHOD(auth:(NSDictionary *)options
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
    NSString *token = options[@"token"];
    if (!token || [token isEqualToString:@""]) {
        reject(@"-99", @"请先获得token", nil);
    }
    
    NSMutableDictionary *config = [NSMutableDictionary dictionaryWithCapacity:1];
    NSString *title = options[@"title"];
    if (title)  {
        [config setValue:title forKey:@"title"];
    }
    
    GXBSDK *gxb = [GXBSDK sharedInstance];
    gxb.themeColor = [UIColor whiteColor];
    
    [gxb startWithToken:token Config:config Callback:^(SDKResult result) {
        // result为SDK返回参数，成功为1；失败为0;用户点击返回为-1；
        resolve([NSNumber numberWithInteger:result]);
    }];
}

@end
