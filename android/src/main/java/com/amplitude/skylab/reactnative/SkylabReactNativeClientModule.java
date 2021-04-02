package com.amplitude.skylab.reactnative;

import android.app.Application;

import androidx.annotation.NonNull;

import com.amplitude.api.Amplitude;
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
import java.util.concurrent.Future;

@ReactModule(name = SkylabReactNativeClientModule.NAME)
public class SkylabReactNativeClientModule extends ReactContextBaseJavaModule {
    public static final String NAME = "SkylabReactNativeClient";
    private ReactApplicationContext reactContext;

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
    public void initialize(String apiKey, Promise promise) throws JSONException {
        /*Variant fallbackVariant = null;
        if (config.getType("fallbackVariant") == ReadableType.String) {
            fallbackVariant = new Variant(config.getString("fallbackVariant"));
        } else if (config.getType("fallbackVariant") == ReadableType.Map) {
            fallbackVariant =
                    Variant.fromJsonObject(ReactNativeHelper.convertMapToJson(config.getMap(
                    "fallbackVariant")));
        }
        SkylabConfig convertedConfig = SkylabConfig.builder()
                .setFallbackVariant(fallbackVariant)
                .setServerUrl(config.getString("serverUrl")).build();
                */
        SkylabConfig convertedConfig = SkylabConfig.builder().build();
        Skylab.init((Application) this.reactContext.getApplicationContext(), apiKey,
            convertedConfig);
    }

    @ReactMethod
    public void start(ReadableMap user, Promise promise) throws JSONException {
        Future<SkylabClient> future = Skylab.getInstance().start(convertSkylabUser(user));
        promise.resolve(true);
    }

    @ReactMethod
    public void setUser(ReadableMap user, Promise promise) throws JSONException {
        Future<SkylabClient> future = Skylab.getInstance().setUser(convertSkylabUser(user));
        promise.resolve(true);
    }

    @ReactMethod
    public void getVariant(String flagKey, Promise promise) throws JSONException {
        Variant variant = Skylab.getInstance().getVariant(flagKey);
        promise.resolve(variantToMap(variant));
    }

    @ReactMethod
    public void getVariantWithFallback(String flagKey, String fallback, Promise promise) throws JSONException {
        Variant fallbackVariant = new Variant(fallback);
        Variant variant = Skylab.getInstance().getVariant(flagKey, fallbackVariant);
        promise.resolve(variantToMap(variant));
    }

    @ReactMethod
    public void getVariantWithFallbackPayload(String flagKey, ReadableMap fallback, Promise promise) throws JSONException {
        Variant fallbackVariant =
                Variant.fromJsonObject(ReactNativeHelper.convertMapToJson(fallback));
        Variant variant = Skylab.getInstance().getVariant(flagKey, fallbackVariant);
        promise.resolve(variantToMap(variant));
    }

    @ReactMethod
    public void getVariants(Promise promise) throws JSONException {
        WritableMap map = new WritableNativeMap();
        Map<String, Variant> variants = Skylab.getInstance().getVariants();
        for (Map.Entry<String, Variant> entry : variants.entrySet()) {
            map.putMap(entry.getKey(), variantToMap(entry.getValue()));
        }
        promise.resolve(map);
    }

    @ReactMethod
    public void refetchAll(Promise promise) {
        Future<SkylabClient> future = Skylab.getInstance().refetchAll();
        promise.resolve(true);
    }

    @ReactMethod
    public void setContextProvider(String amplitudeInstanceName, Promise promise) {
        Skylab.getInstance().setContextProvider(new AmplitudeContextProvider(Amplitude.getInstance(amplitudeInstanceName)));
        promise.resolve(true);
    }

    @ReactMethod
    public void setListener(Callback listener) {
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
    }
 
    // Conversion methods
    private SkylabUser convertSkylabUser(ReadableMap user) throws JSONException {
        SkylabUser convertedUser = SkylabUser.builder()
                .setDeviceId(user.getString(SkylabUser.DEVICE_ID))
                .setUserId(user.getString(SkylabUser.USER_ID))
                .setVersion(user.getString(SkylabUser.VERSION))
                .setCountry(user.getString(SkylabUser.COUNTRY))
                .setRegion(user.getString(SkylabUser.REGION))
                .setDma(user.getString(SkylabUser.DMA))
                .setCity(user.getString(SkylabUser.CITY))
                .setLanguage(user.getString(SkylabUser.LANGUAGE))
                .setPlatform(user.getString(SkylabUser.PLATFORM))
                .setOs(user.getString(SkylabUser.OS))
                .setDeviceFamily(user.getString(SkylabUser.DEVICE_FAMILY))
                .setDeviceType(user.getString(SkylabUser.DEVICE_TYPE))
                .setDeviceBrand(user.getString(SkylabUser.DEVICE_BRAND))
                .setDeviceManufacturer(user.getString(SkylabUser.DEVICE_MANUFACTURER))
                .setDeviceModel(user.getString(SkylabUser.DEVICE_MODEL))
                .setCarrier(user.getString(SkylabUser.CARRIER))
                .setLibrary(user.getString(SkylabUser.LIBRARY))
                .setUserProperties(ReactNativeHelper.convertMapToJson(user.getMap(SkylabUser.USER_PROPERTIES))).build();
        return convertedUser;
    }

    private WritableMap variantToMap(Variant variant) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (variant.value != null) {
            jsonObject.put("value", variant.value);
        }
        if (variant.payload != null) {
            jsonObject.put("payload", variant.payload);
        }
        return ReactNativeHelper.convertJsonToMap(jsonObject);
    }
}
