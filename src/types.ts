export type SkylabUser = {
  device_id?: string;
  user_id?: string;
  country?: string;
  city?: string;
  region?: string;
  dma?: string;
  language?: string;
  platform?: string;
  os?: string;
  device_family?: string;
  device_type?: string;
  device_model?: string;
  carrier?: string;
  library?: string;
  user_properties?: {
    [propertyName: string]:
      | string
      | number
      | boolean
      | Array<string | number | boolean>;
  };
};

export type Variant = {
  value: string;
  payload?: any;
};

export type Variants = {
  [flagKey: string]: Variant;
};

export type SkylabConfig = {
  fallbackVariant?: Variant | string;
  serverUrl?: string;
};

export interface SkylabReactNativeClientModule {
  initialize(apiKey: string, config?: SkylabConfig): Promise<boolean>;
  start(user: SkylabUser): Promise<boolean>;
  setUser(user: SkylabUser): Promise<boolean>;
  getVariant(flagKey: string): Promise<Variant>;
  getVariantWithFallback(flagKey: string, fallback: string): Promise<Variant>;
  getVariantWithFallbackPayload(
    flagKey: string,
    fallback: Variant
  ): Promise<Variant>;
  getVariants(): Promise<Variants>;
  refetchAll(): Promise<boolean>;
  //setContextProvider(amplitudeInstanceName: string): Promise<boolean>;
  //setListener(callback: Function): void;
}
