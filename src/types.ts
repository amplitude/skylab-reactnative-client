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
  initialize(apiKey: string): Promise<boolean>;
  start(callback: Function): Promise<boolean>;
  setUser(user: SkylabUser, callback: Function): Promise<boolean>;
  getVariant(flagKey: string, fallback?: Variant | string): Promise<boolean>;
  getVariants(): Promise<boolean>;
  refetchAll(callback: Function): Promise<boolean>;
}
