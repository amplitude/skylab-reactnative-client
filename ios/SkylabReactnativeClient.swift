import Foundation

@objc(SkylabReactNativeClient)
class SkylabReactNativeClient: NSObject {
    @objc static func requiresMainQueueSetup() -> Bool {
        return false
    }

    @objc
    func initialize(_ apiKey: String,
                    config: [String: Any],
                    resolver resolve: RCTPromiseResolveBlock,
                    rejecter reject: RCTPromiseRejectBlock) -> Void {
        
    }
}
