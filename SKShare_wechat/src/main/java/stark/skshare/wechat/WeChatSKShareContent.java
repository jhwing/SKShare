package stark.skshare.wechat;

import android.graphics.Bitmap;

import java.io.File;

import stark.skshare.SKShareModel;

/**
 * Created by jihongwen on 16/8/10.
 */

public class WeChatSKShareContent extends SKShareModel {

    public byte[] thumbData;

    public byte[] imageData;

    public Bitmap imageBitmap;

    public File imageFile;

    public String musicUrl;

    protected WeChatSKShareContent(Builder builder) {
        super(builder);
        this.thumbData = builder.thumbData;
        this.imageData = builder.imageData;
        this.imageFile = builder.imageFile;
        this.imageBitmap = builder.imageBitmap;
        this.musicUrl = builder.musicUrl;
    }

    public static final class Builder extends SKShareModel.Builder<WeChatSKShareContent, Builder> {

        byte[] thumbData;

        byte[] imageData;

        Bitmap imageBitmap;

        File imageFile;

        String musicUrl;

        public Builder setThumbData(byte[] thumbData) {
            this.thumbData = thumbData;
            return this;
        }

        public Builder setImageData(byte[] imageData) {
            this.imageData = imageData;
            return this;
        }

        public Builder setImageBitmap(Bitmap imageBitmap) {
            this.imageBitmap = imageBitmap;
            return this;
        }

        public Builder setImageFile(File imageFile) {
            this.imageFile = imageFile;
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
