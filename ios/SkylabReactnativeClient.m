#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(SkylabReactNativeClient, NSObject)

RCT_EXTERN_METHOD(initialize:(NSString *)apiKey resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(start:(NSDictionary *)user resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(setUser:(NSDictionary *)user resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(getVariant:(NSString *)flagKey resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(getVariantWithFallback:(NSString *)flagKey fallback:(NSString *)fallback resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(getVariantWithFallbackPayload:(NSString *)flagKey fallbackPayload:(NSDictionary *)fallbackPayload resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(getVariants: (RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(refetchAll: (RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

//RCT_EXTERN_METHOD(setContextProvider:(NSString *)amplitudeInstanceName resolver(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

@end