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
  init(apiKey: string): void {
    SkylabReactNativeClient.initialize(apiKey);
  }

  start(): Promise<boolean> {
    return SkylabReactNativeClient.start();
  }

  setUser(user: SkylabUser): Promise<boolean> {
    return SkylabReactNativeClient.setUser(user.payload);
  }

  getVariant(flagKey: string, fallback?: Variant | string): Promise<boolean> {
    return SkylabReactNativeClient.getVariant(flagKey, fallback);
  }

  getVariants(): Promise<boolean> {
    return SkylabReactNativeClient.getVariants();
  }

  refetchAll(): Promise<boolean> {
    return SkylabReactNativeClient.refetchAll();
  }

  /*setContextProvider(amplitudeInstanceName: string): Promise<boolean> {
    return SkylabReactNativeClient.setContextProvider(amplitudeInstanceName);
  }

  setListener(callback: Function) : void {
    SkylabReactNativeClient.setListener(callback);
  }*/
}
