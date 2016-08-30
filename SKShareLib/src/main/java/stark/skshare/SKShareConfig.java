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
            load(context.getAssets().open("share.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
