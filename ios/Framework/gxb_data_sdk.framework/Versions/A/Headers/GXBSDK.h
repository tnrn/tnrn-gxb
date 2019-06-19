//
//  GXBSDK.h
//  gxb_data_sdk
//
//  Created by David Lan on 2017/2/28.
//  Copyright © 2017年 davidlan. All rights reserved.
//

#import <Foundation/Foundation.h>


typedef NS_ENUM(NSInteger, SDKResult){
    Result_BACK = -1,//用户返回
    Result_FAIL = 0, //失败
    Result_SUCCESS,   //成功
};

@interface GXBSDK : NSObject

@property(nonatomic,assign) BOOL isDEV;

#pragma mark - SDK 通用自定义静态参数列表（可选）

@property (nonatomic,strong) UIColor *themeColor; //导航栏颜色
@property (nonatomic,copy) UIColor *titleColor;//标题颜色
@property (nonatomic,copy) UIColor *backBarColor;//返回键颜色


+ (instancetype)sharedInstance;

+ (NSString *)version;

- (void)startWithToken:(NSString*)token Config:(NSDictionary *)config Callback:(void(^)(SDKResult))callback;

- (void)exit:(SDKResult)success;

@end
