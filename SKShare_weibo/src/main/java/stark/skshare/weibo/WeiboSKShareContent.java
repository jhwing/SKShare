package stark.skshare.weibo;

import android.graphics.Bitmap;

import stark.skshare.SKShareModel;

/**
 * Created by jihongwen on 16/8/9.
 */

public class WeiboSKShareContent extends SKShareModel {

    public Bitmap thumbImage;

    private WeiboSKShareContent(Builder builder) {
        super(builder);
        this.thumbImage = builder.thumbImage;
    }

    public static class Builder extends SKShareModel.Builder<WeiboSKShareContent, Builder> {

        private Bitmap thumbImage;

        public Builder setThumbImage(Bitmap thumbImage) {
            this.thumbImage = thumbImage;
            return this;
        }

        @Override
        public WeiboSKShareContent build() {
            return new WeiboSKShareContent(this);
        }
    }

}
