import Foundation
import AmplitudeSkylab
import Amplitude

@objc(SkylabReactNativeClient)
class SkylabReactNativeClient: NSObject {
    @objc static func requiresMainQueueSetup() -> Bool {
        return false
    }

    @objc
    func initialize(_ apiKey: String,
                    config: [String:Any]?,
                    resolver resolve: RCTPromiseResolveBlock,
                    rejecter reject: RCTPromiseRejectBlock) -> Void {
        let config = SkylabConfig(
            serverUrl:config?["serverUrl"] as! String? ?? SkylabConfig.Defaults.ServerUrl
        )
        let _ = Skylab.initialize(apiKey: apiKey, config: config)
        resolve(true)
    }
    
    @objc
    func start(_ user: [String: Any],
                 resolver resolve: @escaping RCTPromiseResolveBlock,
                 rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        let u = SkylabUser(deviceId: user["device_id"] as! String?,
                           userId: user["user_id"] as! String?,
                           version: user["version"] as! String?,
                           country: user["country"] as! String?,
                           region: user["region"] as! String?,
                           dma: user["dma"] as! String?,
                           city: user["city"] as! String?,
                           language: user["language"] as! String?,
                           platform: user["platform"] as! String?,
                           os: user["os"] as! String?,
                           deviceFamily: user["device_family"] as! String?,
                           deviceType: user["device_type"] as! String?,
                           deviceManufacturer: user["device_manufacturer"] as! String?,
                           deviceModel: user["device_model"] as! String?,
                           carrier: user["carrier"] as! String?,
                           userProperties: user["user_properties"] as! [String: String]?)
        Skylab.getInstance()?.start(user: u, completion:{
            resolve(true);
        })
    }
    
    @objc
    func setUser(_ user: [String: Any],
                 resolver resolve: @escaping RCTPromiseResolveBlock,
                 rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        let u = SkylabUser(deviceId: user["device_id"] as! String?,
                           userId: user["user_id"] as! String?,
                           version: user["version"] as! String?,
                           country: user["country"] as! String?,
                           region: user["region"] as! String?,
                           dma: user["dma"] as! String?,
                           city: user["city"] as! String?,
                           language: user["language"] as! String?,
                           platform: user["platform"] as! String?,
                           os: user["os"] as! String?,
                           deviceFamily: user["device_family"] as! String?,
                           deviceType: user["device_type"] as! String?,
                           deviceManufacturer: user["device_manufacturer"] as! String?,
                           deviceModel: user["device_model"] as! String?,
                           carrier: user["carrier"] as! String?,
                           userProperties: user["user_properties"] as! [String: String]?)

        Skylab.getInstance()?.setUser(user: u, completion: {
            resolve(true)
        })
    }
    
    @objc
    func getVariant(_ flagKey: String,
                    resolver resolve: RCTPromiseResolveBlock,
                    rejecter reject: RCTPromiseRejectBlock) -> Void {
        let variant = Skylab.getInstance()?.getVariant(flagKey, fallback: nil)
        
        var variantMap = [String: Any]()
        variantMap["value"] = variant?.value
        variantMap["payload"] = variant?.payload
        resolve(variantMap)
    }

    @objc
    func getVariantWithFallback(_ flagKey: String,
                    fallback: String,
                    resolver resolve: RCTPromiseResolveBlock,
                    rejecter reject: RCTPromiseRejectBlock) -> Void {
        let variant = Skylab.getInstance()?.getVariant(flagKey, fallback: fallback)
        
        var variantMap = [String: Any]()
        variantMap["value"] = variant?.value
        variantMap["payload"] = variant?.payload
        resolve(variantMap)
    }
    
    @objc
    func getVariantWithFallbackPayload(_ flagKey: String,
                    fallbackPayload: [String: Any],
                    resolver resolve: RCTPromiseResolveBlock,
                    rejecter reject: RCTPromiseRejectBlock) -> Void {
        let fallbackVariant = Variant.init(fallbackPayload["value"] as! String, payload: fallbackPayload["payload"])
        
        let variant = Skylab.getInstance()?.getVariant(flagKey, fallback: fallbackVariant)
        
        var variantMap = [String: Any]()
        variantMap["value"] = variant?.value
        variantMap["payload"] = variant?.payload
        resolve(variantMap)
    }


    @objc
    func getVariants(_ resolve: RCTPromiseResolveBlock,
                       rejecter reject: RCTPromiseRejectBlock) -> Void {
        let variants = Skylab.getInstance()?.getVariants()
        
        if let variantsList = variants {
            var map = [String: Any]()
            for (key,value) in variantsList {
                var variantMap = [String: Any]()
                variantMap["value"] = value.value
                variantMap["payload"] = value.payload
                map[key] = variantMap
            }
            resolve(map)
        } else {
            resolve(nil)
        }
    }
    
    @objc
    func refetchAll(_ resolve: @escaping RCTPromiseResolveBlock,
                      rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        Skylab.getInstance()?.refetchAll(completion: {
            resolve(true)
        })
    }

    @objc
    func setAmplitudeContextProvider(_ amplitudeInstanceName: String?,
                              resolver resolve: RCTPromiseResolveBlock,
                              rejecter reject: RCTPromiseRejectBlock) -> Void {
        let amplitudeInstance = Amplitude.instance();
        let contextProvider = AmplitudeContextProvider(amplitudeInstance)
        let _ = Skylab.getInstance()?.setContextProvider(contextProvider)
        resolve(true);
    }
}
