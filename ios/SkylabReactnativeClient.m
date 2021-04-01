#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(SkylabReactNativeClient, NSObject)

RCT_EXTERN_METHOD(initialize:(NSString *)apiKey resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(start:(RCTResponseSenderBlock)callback resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(setUser:(NSDictionary *)user callback:(RCTResponseSenderBlock)callback resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(getVariant:(NSString *)flagKey callback:(RCTResponseSenderBlock)callback resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(getVariants: (RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

@end
