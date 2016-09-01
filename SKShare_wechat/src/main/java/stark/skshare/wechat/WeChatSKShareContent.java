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

    public String musicUrl;

    protected WeChatSKShareContent(Builder builder) {
        super(builder);
        this.thumbImage = builder.thumbImage;
        this.thumb = builder.thumb;
        this.musicUrl = builder.musicUrl;
    }

    public static final class Builder extends SKShareModel.Builder<WeChatSKShareContent, Builder> {

        Bitmap thumbImage;

        File thumb;

        String musicUrl;

        public Builder setThumbImage(Bitmap thumbImage) {
            this.thumbImage = thumbImage;
            return this;
        }

        public Builder setThumb(File thumb) {
            this.thumb = thumb;
            return this;
        }

        public Builder setMusicUrl(String musicUrl) {
            this.musicUrl = musicUrl;
            return this;
        }

        @Override
        public WeChatSKShareContent build() {
            return new WeChatSKShareContent(this);
        }
    }
}
