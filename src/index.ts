import { NativeModules } from 'react-native';
import { SkylabReactNativeModule } from './types';

//export default SkylabReactNativeClient as SkylabReactNativeClientType;
const SkylabReactNativeClient: SkylabReactNativeModule =
  NativeModules.SkylabReactNativeClient;

export class Skylab {
  init(apiKey: string, config: Object): Promise<boolean | void> {
    return SkylabReactNativeClient.initialize(apiKey, config);
  }

  start(promise: Promise<boolean>): Promise<boolean | void> {
    return Promise.resolve();
  }

  setUser(user: Object, promise: Promise<boolean>): Promise<boolean | void> {
    return Promise.resolve();
  }

  getVariant(
    flagKey: string,
    promise: Promise<boolean>
  ): Promise<boolean | void> {
    return Promise.resolve();
  }

  getVariantWithObjectFallback(
    flagKey: string,
    fallback: object,
    promise: Promise<boolean>
  ): Promise<boolean | void> {
    return Promise.resolve();
  }
  //public void getVariant(String flagKey, ReadableMap fallback, Promise promise)

  getVariantWithStringFallback(
    flagKey: string,
    fallback: string,
    promise: Promise<boolean>
  ): Promise<boolean | void> {
    return Promise.resolve();
  }
  //public void getVariant(String flagKey, String fallback, Promise promise)

  getVariantWithOnlyPromise(
    promise: Promise<boolean>
  ): Promise<boolean | void> {
    return Promise.resolve();
  }
  //public void getVariants(Promise promise)

  refetchAll(promise: Promise<boolean>): Promise<boolean | void> {
    return Promise.resolve();
  }
}
