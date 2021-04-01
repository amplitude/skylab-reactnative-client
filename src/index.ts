import { NativeModules } from 'react-native';
import { SkylabReactNativeModule } from './types';

//export default SkylabReactNativeClient as SkylabReactNativeClientType;
const SkylabReactNativeClient: SkylabReactNativeModule =
  NativeModules.SkylabReactNativeClient;

export class Skylab {
  init(apiKey: string, config: Object): Promise<boolean | void> {
    return SkylabReactNativeClient.initialize(apiKey, config);
  }

  start(_promise: Promise<boolean>): Promise<boolean | void> {
    return Promise.resolve();
  }

  setUser(_user: Object, _promise: Promise<boolean>): Promise<boolean | void> {
    return Promise.resolve();
  }

  getVariant(
    _promise: Promise<boolean>,
    _flagKey?: string,
    _fallback?: string | object
  ): Promise<boolean | void> {
    return Promise.resolve();
  }

  refetchAll(_promise: Promise<boolean>): Promise<boolean | void> {
    return Promise.resolve();
  }
}
