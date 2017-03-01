package stark.skshare.wechat;

import android.graphics.Bitmap;

import java.io.File;

import stark.skshare.SKShareModel;

/**
 * Created by jihongwen on 16/8/10.
 */

public class WeChatSKShareContent extends SKShareModel {
    // 缩略图
    public byte[] thumbData;
    public String thumbPath;
    public File thumbFile;

    // 原图 gif 表情
    public byte[] imageData;
    public Bitmap imageBitmap;
    public File imageFile;

    // 文件
    public byte[] fileData;
    public String filePath;

    // 音乐
    public String musicUrl;

    protected WeChatSKShareContent(Builder builder) {
        super(builder);
        this.thumbData = builder.thumbData;
        this.imageData = builder.imageData;
        this.imageFile = builder.imageFile;
        this.imageBitmap = builder.imageBitmap;
        this.fileData = builder.fileData;
        this.filePath = builder.filePath;
        this.musicUrl = builder.musicUrl;
    }

    public static final class Builder extends SKShareModel.Builder<WeChatSKShareContent, Builder> {

        byte[] thumbData;
        String thumbPath;
        File thumbFile;

        byte[] imageData;
        Bitmap imageBitmap;
        File imageFile;

        byte[] fileData;
        String filePath;

        String musicUrl;

        public Builder setThumbData(byte[] thumbData) {
            this.thumbData = thumbData;
            return this;
        }

        public Builder setThumbPath(String thumbPath) {
            this.thumbPath = thumbPath;
            return this;
        }

        public Builder setThumbFile(File thumbFile) {
            this.thumbFile = thumbFile;
            return this;
        }

        public Builder setImageData(byte[] imageData) {
            this.imageData = imageData;
            return this;
        }

        public Builder setFileData(byte[] fileData) {
            this.fileData = fileData;
            return this;
        }

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
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
