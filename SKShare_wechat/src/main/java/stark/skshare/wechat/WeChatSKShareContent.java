package stark.skshare.wechat;

import android.graphics.Bitmap;

import java.io.File;

import stark.skshare.SKShareModel;

/**
 * Created by jihongwen on 16/8/10.
 */

public class WeChatSKShareContent extends SKShareModel {

    public Bitmap thumbImage;

    public File thumb;

    protected WeChatSKShareContent(Builder builder) {
        super(builder);
        this.thumbImage = builder.thumbImage;
        this.thumb = builder.thumb;
    }

    public static final class Builder extends SKShareModel.Builder<WeChatSKShareContent, Builder> {

        Bitmap thumbImage;

        File thumb;

        public Builder setThumbImage(Bitmap thumbImage) {
            this.thumbImage = thumbImage;
            return this;
        }

        public Builder setThumb(File thumb) {
            this.thumb = thumb;
            return this;
        }

        @Override
        public WeChatSKShareContent build() {
            return new WeChatSKShareContent(this);
        }
    }
}
