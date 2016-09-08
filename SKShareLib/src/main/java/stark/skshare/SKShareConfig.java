package stark.skshare;

import android.content.Context;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by jihongwen on 16/8/27.
 */

public class SKShareConfig extends Properties {

    public void loadConfig(Context context) {
        try {
            // 不需要上下文的加载方式
            load(SKShareConfig.class.getResourceAsStream("/assets/share.properties"));
            //load(context.getAssets().open("share.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
