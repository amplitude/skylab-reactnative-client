import Foundation
import Skylab

@objc(SkylabReactNativeClient)
class SkylabReactNativeClient: NSObject {
    @objc static func requiresMainQueueSetup() -> Bool {
        return false
    }

    @objc
    func initialize(_ apiKey: String,
                    resolver resolve: RCTPromiseResolveBlock,
                    rejecter reject: RCTPromiseRejectBlock) -> Void {
        let config = SkylabConfig()
        let _ = Skylab.initialize(apiKey: apiKey, config: config)
    }
}
