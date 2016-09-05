package stark.skshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jihongwen on 16/8/4.
 */

public class SKShare {

    private static Map<Integer, ResultListener> resultListeners = new HashMap<>();

    public static void removeListener(ResultListener listener) {
        resultListeners.remove(listener);
    }

    public interface ResultListener {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    public interface IShare<T> extends ResultListener {
        T init(Context context, SKShareConfig shareConfig);

        int requestCode();
    }

    public interface ShareCallback {

        void onSuccess();

        void onCancel();

        void onError();

    }

    static SKShareConfig shareConfig = new SKShareConfig();


    public static void init(Context context) {
        shareConfig.loadConfig(context);
    }

    public static SKShareConfig getShareConfig() {
        return shareConfig;
    }

    public static <T extends IShare> T create(Class<T> clazz, Activity activity) {
        try {
            T t = clazz.newInstance();
            t.init(activity, shareConfig);
            resultListeners.put(t.requestCode(), t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void shareCallBack(int requestCode, int resultCode, Intent data) {
        ResultListener listener = resultListeners.get(requestCode);
        if (listener != null) {
            listener.onActivityResult(requestCode, resultCode, data);
        }
    }
}
