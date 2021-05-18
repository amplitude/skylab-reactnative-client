import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { Amplitude } from '@amplitude/react-native';
import { Skylab, Variant, Variants } from 'skylab-reactnative-client';

export default function App() {
  const [variant, setVariant] = React.useState<Variant | undefined>();
  const [fallbackResult, setFallbackResult] = React.useState<
    Variant | undefined
  >();
  const [variantFallbackResult, setVariantFallbackResult] = React.useState<
    Variant | undefined
  >();
  const [
    variantWithPayloadResult,
    setVariantWithPayloadResult,
  ] = React.useState<Variant | undefined>();
  const [allVariants, setAllVariants] = React.useState<Variants | undefined>();
  React.useEffect(() => {
    (async () => {
      if (Amplitude) {
        const amplitude = Amplitude.getInstance();
        amplitude.init('a6dd847b9d2f03c816d4f3f8458cdc1d');
      }
      if (Skylab) {
        await Skylab.init('client-IAxMYws9vVQESrrK88aTcToyqMxiiJoR');
        await Skylab.setAmplitudeContextProvider();
        await Skylab.start();
        setVariant(await Skylab.getVariant('react-native'));
        setFallbackResult(
          await Skylab.getVariant('flag-does-not-exist', 'fallback')
        );
        setVariantFallbackResult(
          await Skylab.getVariant('flag-does-not-exist', { value: 'fallback' })
        );
        setVariantWithPayloadResult(await Skylab.getVariant('android-demo'));
        setAllVariants(await Skylab.getVariants());
      }
    })();
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.text}>react-native: {JSON.stringify(variant)}</Text>
      <Text style={styles.text}>
        'flag-does-not-exist' with string fallback:{' '}
        {JSON.stringify(fallbackResult)}
      </Text>
      <Text style={styles.text}>
        'flag-does-not-exist' with variant fallback:{' '}
        {JSON.stringify(variantFallbackResult)}
      </Text>
      <Text style={styles.text}>
        variant-with-payload: {JSON.stringify(variantWithPayloadResult)}
      </Text>
      <Text style={styles.text}>
        all variants: {JSON.stringify(allVariants)}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'flex-start',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  text: {
    marginVertical: 20,
  },
});
