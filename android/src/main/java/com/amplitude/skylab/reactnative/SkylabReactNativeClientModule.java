package com.amplitude.skylab.reactnative;

import android.app.Application;

import androidx.annotation.NonNull;

import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeClient;
import com.amplitude.api.AmplitudeContextProvider;
import com.amplitude.skylab.Skylab;
import com.amplitude.skylab.SkylabClient;
import com.amplitude.skylab.SkylabConfig;
import com.amplitude.skylab.SkylabUser;
import com.amplitude.skylab.Variant;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@ReactModule(name = SkylabReactNativeClientModule.NAME)
public class SkylabReactNativeClientModule extends ReactContextBaseJavaModule {
    public static final String NAME = "SkylabReactNativeClient";
    private ReactApplicationContext reactContext;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public SkylabReactNativeClientModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void initialize(String apiKey, ReadableMap config, Promise promise) {
        try {
            SkylabConfig convertedConfig = convertSkylabConfig(config);
            Skylab.init((Application) this.reactContext.getApplicationContext(), apiKey,
                    convertedConfig);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void start(ReadableMap user, Promise promise) {
        try {
            Future<SkylabClient> future = Skylab.getInstance().start(convertSkylabUser(user));
            executorService.submit(() -> {
                try {
                    future.get();
                    promise.resolve(true);
                } catch (Exception e) {
                    promise.reject(e);
                }
            });
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setUser(ReadableMap user, Promise promise) {
        try {
            Future<SkylabClient> future = Skylab.getInstance().setUser(convertSkylabUser(user));
            executorService.submit(() -> {
                try {
                    future.get();
                    promise.resolve(true);
                } catch (Exception e) {
                    promise.reject(e);
                }
            });
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getVariant(String flagKey, Promise promise) {
        try {
            Variant variant = Skylab.getInstance().getVariant(flagKey);
            promise.resolve(variantToMap(variant));
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getVariantWithFallback(String flagKey, String fallback, Promise promise) {
        try {
            Variant fallbackVariant = new Variant(fallback);
            Variant variant = Skylab.getInstance().getVariant(flagKey, fallbackVariant);
            promise.resolve(variantToMap(variant));
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getVariantWithFallbackPayload(String flagKey, ReadableMap fallback,
                                              Promise promise) {
        try {
            Variant fallbackVariant =
                    Variant.fromJsonObject(ReactNativeHelper.convertMapToJson(fallback));
            Variant variant = Skylab.getInstance().getVariant(flagKey, fallbackVariant);
            promise.resolve(variantToMap(variant));
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getVariants(Promise promise) {
        try {
            WritableMap map = new WritableNativeMap();
            Map<String, Variant> variants = Skylab.getInstance().getVariants();
            for (Map.Entry<String, Variant> entry : variants.entrySet()) {
                map.putMap(entry.getKey(), variantToMap(entry.getValue()));
            }
            promise.resolve(map);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void refetchAll(Promise promise) {
        try {
            Future<SkylabClient> future = Skylab.getInstance().refetchAll();
            executorService.submit(() -> {
                try {
                    future.get();
                    promise.resolve(true);
                } catch (Exception e) {
                    promise.reject(e);
                }
            });
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setAmplitudeContextProvider(String amplitudeInstanceName, Promise promise) {
        try {
            AmplitudeClient amplitudeInstance = Amplitude.getInstance(amplitudeInstanceName);
            if (amplitudeInstance != null) {
                Skylab.getInstance().setContextProvider(new AmplitudeContextProvider(amplitudeInstance));
            }
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setListener(Callback listener, Promise promise) {
        try {
            Skylab.getInstance().setListener((skylabUser, variants) -> {
                try {
                    WritableMap map = new WritableNativeMap();
                    for (Map.Entry<String, Variant> entry : variants.entrySet()) {
                        map.putMap(entry.getKey(), variantToMap(entry.getValue()));
                    }
                    listener.invoke(map);
                } catch (JSONException e) {

                }
            });
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    // Conversion methods
    private SkylabConfig convertSkylabConfig(ReadableMap config) throws JSONException {
        Variant fallbackVariant = null;
        SkylabConfig.Builder builder = SkylabConfig.builder();
        if (config != null) {
            if (config.hasKey("fallbackVariant")) {
                if (config.getType("fallbackVariant") == ReadableType.String) {
                    fallbackVariant = new Variant(config.getString("fallbackVariant"));
                } else if (config.getType("fallbackVariant") == ReadableType.Map) {
                    fallbackVariant =
                            Variant.fromJsonObject(ReactNativeHelper.convertMapToJson(config.getMap(
                                    "fallbackVariant")));
                }
            }
            if (config.hasKey("serverUrl")) {
                builder.setFallbackVariant(fallbackVariant)
                        .setServerUrl(config.getString("serverUrl"));
            }
        }
        return builder.build();
    }

    private String safeGetString(ReadableMap map, String key) {
        if (map.hasKey(key)) {
            return map.getString(key);
        }
        return null;
    }

    private ReadableMap safeGetMap(ReadableMap map, String key) {
        if (map.hasKey(key)) {
            return map.getMap(key);
        }
        return null;
    }

    private SkylabUser convertSkylabUser(ReadableMap user) throws JSONException {
        SkylabUser convertedUser = SkylabUser.builder()
                .setDeviceId(safeGetString(user, SkylabUser.DEVICE_ID))
                .setUserId(safeGetString(user, SkylabUser.USER_ID))
                .setVersion(safeGetString(user, SkylabUser.VERSION))
                .setCountry(safeGetString(user, SkylabUser.COUNTRY))
                .setRegion(safeGetString(user, SkylabUser.REGION))
                .setDma(safeGetString(user, SkylabUser.DMA))
                .setCity(safeGetString(user, SkylabUser.CITY))
                .setLanguage(safeGetString(user, SkylabUser.LANGUAGE))
                .setPlatform(safeGetString(user, SkylabUser.PLATFORM))
                .setOs(safeGetString(user, SkylabUser.OS))
                .setDeviceFamily(safeGetString(user, SkylabUser.DEVICE_FAMILY))
                .setDeviceType(safeGetString(user, SkylabUser.DEVICE_TYPE))
                .setDeviceBrand(safeGetString(user, SkylabUser.DEVICE_BRAND))
                .setDeviceManufacturer(safeGetString(user, SkylabUser.DEVICE_MANUFACTURER))
                .setDeviceModel(safeGetString(user, SkylabUser.DEVICE_MODEL))
                .setCarrier(safeGetString(user, SkylabUser.CARRIER))
                .setLibrary(safeGetString(user, SkylabUser.LIBRARY))
                .setUserProperties(ReactNativeHelper.convertMapToJson(safeGetMap(user, SkylabUser.USER_PROPERTIES))).build();
        return convertedUser;
    }

    private WritableMap variantToMap(Variant variant) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (variant != null) {
            if (variant.value != null) {
                jsonObject.put("value", variant.value);
            }
            if (variant.payload != null) {
                jsonObject.put("payload", variant.payload);
            }
        }
        return ReactNativeHelper.convertJsonToMap(jsonObject);
    }
}
