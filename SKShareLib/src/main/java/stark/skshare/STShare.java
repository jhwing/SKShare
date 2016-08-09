package stark.skshare;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jihongwen on 16/8/4.
 */

public class STShare {

    private static List<ResultListener> resultListeners = new ArrayList<>();

    public static void removeListener(ResultListener listener) {
        resultListeners.remove(listener);
    }

    public interface ResultListener {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    public interface IShare<T> extends ResultListener {
        T init(Context context);
    }

    public interface ShareCallback {

        void onSuccess();

        void onCancel();

        void onError();

    }

    public static <T extends IShare> T create(Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            resultListeners.add(t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void shareCallBack(int requestCode, int resultCode, Intent data) {
        for (ResultListener listener : resultListeners) {
            listener.onActivityResult(requestCode, resultCode, data);
        }
    }
}
