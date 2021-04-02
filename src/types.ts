type userProperties = Record<string, any>;

type Variant = {
  value: string;
  payload?: any;
};
export interface SkylabReactNativeClientModule {
  initialize(apiKey: string): void;
  start(user: userProperties): Promise<boolean>;
  setUser(user: userProperties): Promise<boolean>;
  getVariant(flagKey: string): Promise<boolean>;
  getVariantWithFallback(flagKey: string, fallback: string): Promise<boolean>;
  getVariantWithFallbackPayload(
    flagKey: string,
    fallback: Variant
  ): Promise<boolean>;
  getVariants(): Promise<boolean>;
  refetchAll(): Promise<boolean>;
  //setContextProvider(amplitudeInstanceName: string): Promise<boolean>;
  //setListener(callback: Function): void;
}
