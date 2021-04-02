type SkylabUser = {
  deviceId?: string;
  userId?: string;
  version?: string;
  country?: string;
  region?: string;
  dma?: string;
  city?: string;
  language?: string;
  platform?: string;
  os?: string;
  deviceFamily?: string;
  deviceType?: string;
  deviceManufacturer?: string;
  deviceModel?: string;
  carrier?: string;
  userProperties?: Record<string, string>;
};

type Variant = {
  value: string;
  payload?: any;
};
export interface SkylabReactNativeClientModule {
  initialize(apiKey: string): void;
  start(): Promise<boolean>;
  setUser(user: SkylabUser): Promise<boolean>;
  getVariant(flagKey: string, fallback?: Variant | string): Promise<boolean>;
  getVariants(): Promise<boolean>;
  refetchAll(): Promise<boolean>;
  //setContextProvider(amplitudeInstanceName: string): Promise<boolean>;
  //setListener(callback: Function): void;
}
