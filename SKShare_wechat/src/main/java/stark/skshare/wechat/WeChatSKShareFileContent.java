package stark.skshare.wechat;

import stark.skshare.SKShareModel;

/**
 * Created by jihongwen on 16/8/10.
 */

public class WeChatSKShareFileContent extends SKShareModel {

    public byte[] fileData;

    public String filePath;

    protected WeChatSKShareFileContent(Builder builder) {
        super(builder);
        this.fileData = builder.fileData;
        this.filePath = builder.filePath;
    }

    public static final class Builder extends SKShareModel.Builder<WeChatSKShareFileContent, Builder> {

        byte[] fileData;

        String filePath;

        public Builder setFileData(byte[] fileData) {
            this.fileData = fileData;
            return this;
        }

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        @Override
        public WeChatSKShareFileContent build() {
            return new WeChatSKShareFileContent(this);
        }
    }
}
