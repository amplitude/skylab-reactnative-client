import { NativeModules } from 'react-native';

type SkylabReactNativeClientType = {
  multiply(a: number, b: number): Promise<number>;
};

const { SkylabReactNativeClient } = NativeModules;

export default SkylabReactNativeClient as SkylabReactNativeClientType;
