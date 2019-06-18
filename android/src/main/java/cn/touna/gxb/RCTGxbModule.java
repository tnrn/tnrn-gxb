package cn.touna.gxb;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.gxb.crawler.sdk.network.entity.GxbCrawlerSdk;
import com.gxb.crawler.sdk.network.entity.GxbParams;

import java.util.HashMap;

import javax.annotation.Nonnull;

/**
 * Created on 2019-05-06.
 *
 * @author 老潘
 */
public class RCTGxbModule extends ReactContextBaseJavaModule {
    private static final String KEY_TOKEN = "token";

    public RCTGxbModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return "RCTGxbModule";
    }

    @ReactMethod
    public void auth(ReadableMap options, Promise promise) {
        if (!options.hasKey(KEY_TOKEN)) {
            promise.reject("-99", "请先获得token");
            return;
        }

        try {
            GxbParams.Builder builder = new GxbParams.Builder()
                    .setApp(getCurrentActivity().getApplication())
                    .setThemeColor("#0e000000");

            GxbCrawlerSdk.INSTANCE.init(builder.build());

            HashMap<String, String> map = new HashMap<>();
            map.put("isSDK", "true");
            GxbCrawlerSdk.INSTANCE.auth(
                    getCurrentActivity(),
                    options.getString(KEY_TOKEN),
                    map,
                    status ->  promise.resolve(status)
            );
        } catch (Exception e) {
            promise.reject("-99", e);
        }
    }
}
