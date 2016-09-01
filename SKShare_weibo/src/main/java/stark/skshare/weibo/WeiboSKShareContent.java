package stark.skshare.weibo;

import android.graphics.Bitmap;

import stark.skshare.SKShareModel;

/**
 * Created by jihongwen on 16/8/9.
 */

public class WeiboSKShareContent extends SKShareModel {

    public Bitmap thumbImage;

    public byte[] thumbData;

    public byte[] imageData;

    public String dataUrl;

    public int duration;

    private WeiboSKShareContent(Builder builder) {
        super(builder);
        this.thumbImage = builder.thumbImage;
        this.thumbData = builder.thumbData;
        this.imageData = builder.imageData;
        this.dataUrl = builder.dataUrl;
        this.duration = builder.duration;
    }

    public static class Builder extends SKShareModel.Builder<WeiboSKShareContent, Builder> {

        private Bitmap thumbImage;

        public byte[] thumbData;

        public byte[] imageData;

        public String dataUrl;

        public int duration;

        public Builder setThumbImage(Bitmap thumbImage) {
            this.thumbImage = thumbImage;
            return this;
        }

        public Builder setThumbData(byte[] thumbData) {
            this.thumbData = thumbData;
            return this;
        }

        public Builder setImageData(byte[] imageData) {
            this.imageData = imageData;
            return this;
        }

        public Builder setDataUrl(String dataUrl) {
            this.dataUrl = dataUrl;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        @Override
        public WeiboSKShareContent build() {
            return new WeiboSKShareContent(this);
        }
    }

}
