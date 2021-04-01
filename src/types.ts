type PropertiesObject = Record<string, any>;

export interface SkylabReactNativeModule {
  initialize(apiKey: string, config: PropertiesObject): Promise<boolean>;
}
