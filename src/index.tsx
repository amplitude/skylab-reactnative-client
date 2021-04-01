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
    promise: Promise<boolean>,
    flagKey?: string,
    fallback?: string | object,
  ): Promise<boolean | void> {
    return Promise.resolve();
  }

  refetchAll(promise: Promise<boolean>): Promise<boolean | void> {
    return Promise.resolve();
  }
}
