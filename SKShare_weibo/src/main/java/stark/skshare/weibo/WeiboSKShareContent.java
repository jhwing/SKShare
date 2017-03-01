package stark.skshare.weibo;

import android.graphics.Bitmap;

import java.io.File;

import stark.skshare.SKShareModel;

/**
 * Created by jihongwen on 16/8/9.
 */

public class WeiboSKShareContent extends SKShareModel {

    // 缩略图byte数据
    public byte[] thumbData;
    // 原图byte数据
    public byte[] imageData;
    // 原图Bitmap数据
    public Bitmap imageBitmap;
    // 原图File
    public File imageFile;
    //
    public String dataUrl;
    // 时长
    public int duration;

    private WeiboSKShareContent(Builder builder) {
        super(builder);
        this.thumbData = builder.thumbData;
        this.imageData = builder.imageData;
        this.imageFile = builder.imageFile;
        this.imageBitmap = builder.imageBitmap;
        this.dataUrl = builder.dataUrl;
        this.duration = builder.duration;
    }

    public static class Builder extends SKShareModel.Builder<WeiboSKShareContent, Builder> {

        byte[] thumbData;

        byte[] imageData;

        Bitmap imageBitmap;

        File imageFile;

        String dataUrl;

        int duration;

        public Builder setImageBitmap(Bitmap imageBitmap) {
            this.imageBitmap = imageBitmap;
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

        public Builder setImageFile(File imageFile) {
            this.imageFile = imageFile;
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
