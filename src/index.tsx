import { NativeModules } from 'react-native';

type SkylabReactnativeClientType = {
  multiply(a: number, b: number): Promise<number>;
};

const { SkylabReactnativeClient } = NativeModules;

export default SkylabReactnativeClient as SkylabReactnativeClientType;
