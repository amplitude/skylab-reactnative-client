import { Skylab } from 'skylab-reactnative-client';

let skylabClient: Skylab;
export const initSkylab = (): Skylab => {
  skylabClient = new Skylab();
  skylabClient.init('client-IAxMYws9vVQESrrK88aTcToyqMxiiJoR');
  return skylabClient;
};
export { skylabClient };
