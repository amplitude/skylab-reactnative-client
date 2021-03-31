#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(SkylabReactnativeClient, NSObject)

RCT_EXTERN_METHOD(initialize:(NSString *)apiKey config:(NSDictionary *)config resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

@end
