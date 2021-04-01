import { NativeModules } from 'react-native';
import { SkylabUser } from './skylabUser';
import { SkylabReactNativeClientModule } from './types';

type Variant = {
  value: string;
  payload?: any;
};

const SkylabReactNativeClient: SkylabReactNativeClientModule =
  NativeModules.SkylabReactNativeClient;

export { SkylabUser };
export class Skylab {
  init(apiKey: string): Promise<boolean> {
    return SkylabReactNativeClient.initialize(apiKey);
  }

  start(callback: Function): Promise<boolean> {
    return SkylabReactNativeClient.start(callback);
  }

  setUser(user: SkylabUser, callback: Function): Promise<boolean> {
    return SkylabReactNativeClient.setUser(user.payload, callback);
  }

  getVariant(flagKey: string, fallback?: Variant | string): Promise<boolean> {
    return SkylabReactNativeClient.getVariant(flagKey, fallback);
  }

  getVariants(): Promise<boolean> {
    return SkylabReactNativeClient.getVariants();
  }

  refetchAll(callback: Function): Promise<boolean> {
    return SkylabReactNativeClient.refetchAll(callback);
  }
}
