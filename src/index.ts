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

  start(user: SkylabUser): Promise<boolean> {
    return SkylabReactNativeClient.start(user.payload);
  }

  setUser(user: SkylabUser): Promise<boolean> {
    return SkylabReactNativeClient.setUser(user.payload);
  }

  getVariant(flagKey: string): Promise<boolean> {
    //if (!fallback) {
    // return SkylabReactNativeClient.getVariantNoFallback(flagKey);
    //}
    //console.log('2 element * ***** ');
    return SkylabReactNativeClient.getVariant(flagKey);
  }

  getVariantWithFallback(flagKey: string, fallback: string): Promise<boolean> {
    return SkylabReactNativeClient.getVariantWithFallback(flagKey, fallback);
  }

  getVariantWithFallbackPayload(
    flagKey: string,
    fallback: Variant
  ): Promise<boolean> {
    return SkylabReactNativeClient.getVariantWithFallbackPayload(
      flagKey,
      fallback
    );
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
