import { NativeModules } from 'react-native';
import {
  SkylabConfig,
  SkylabUser,
  Variant,
  Variants,
  SkylabReactNativeClientModule,
} from './types';

export { SkylabConfig, SkylabUser, Variant, Variants };

const SkylabReactNativeClient: SkylabReactNativeClientModule =
  NativeModules.SkylabReactNativeClient;

export const Skylab = {
  init: async (apiKey: string, config?: SkylabConfig): Promise<boolean> => {
    return SkylabReactNativeClient.initialize(apiKey, config);
  },

  start: async (user: SkylabUser): Promise<boolean> => {
    return SkylabReactNativeClient.start(user);
  },

  setUser: async (user: SkylabUser): Promise<boolean> => {
    return SkylabReactNativeClient.setUser(user);
  },

  getVariant: async (
    flagKey: string,
    fallback?: Variant | string
  ): Promise<Variant> => {
    if (typeof fallback === 'string') {
      return SkylabReactNativeClient.getVariantWithFallback(flagKey, fallback);
    } else if (fallback?.value != null) {
      return SkylabReactNativeClient.getVariantWithFallbackPayload(
        flagKey,
        fallback
      );
    } else {
      return SkylabReactNativeClient.getVariant(flagKey);
    }
  },

  getVariants: async (): Promise<Variants> => {
    return SkylabReactNativeClient.getVariants();
  },

  refetchAll: async (): Promise<boolean> => {
    return SkylabReactNativeClient.refetchAll();
  },

  /*setContextProvider(amplitudeInstanceName: string): Promise<boolean> {
    return SkylabReactNativeClient.setContextProvider(amplitudeInstanceName);
  }

  setListener(callback: Function) : void {
    SkylabReactNativeClient.setListener(callback);
  }*/
};
