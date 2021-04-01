#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(SkylabReactNativeClient, NSObject)

RCT_EXTERN_METHOD(initialize:(NSString *)apiKey resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

@end
