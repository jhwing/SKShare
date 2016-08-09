package stark.skshare.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import stark.skshare.DefaultShareCallback;
import stark.skshare.Platform;
import stark.skshare.SKShare;
import stark.skshare.ShareContent;
import stark.skshare.tencent.SKQQShare;
import stark.skshare.tencent.SKQzoneShare;
import stark.skshare.wechat.WeChatShare;
import stark.skshare.weibo.WeiboShare;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ShareLayout.IShareView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.openShareSdk).setOnClickListener(this);
        ShareSheetDialog.createSheetDialog(this);
    }

    @Override
    public void onClick(View view) {
        ShareSheetDialog.show();
    }

    @Override
    public void onShareItemClick(int platform) {
        switch (platform) {
            case Platform.QQ:
                SKQQShare skqqShare = SKShare.create(SKQQShare.class, this);
                if (skqqShare != null) {
                    skqqShare.shareToQQ(new ShareContent.Builder()
                            .setUrl("url")
                            .setTitle("title")
                            .setContent("content")
                            .setImageUrl("imageUrl")
                            .setAppName("app")
                            .build(), this, new DefaultShareCallback());
                }
                break;
            case Platform.PYP: {
                WeChatShare weChatShare = SKShare.create(WeChatShare.class, this);
                if (weChatShare != null) {
                    weChatShare.shareText(new ShareContent.Builder()
                            .setUrl("")
                            .setTitle("")
                            .setContent("")
                            .build(), false);
                }
                break;
            }
            case Platform.WECHAT:
                WeChatShare weChatShare = SKShare.create(WeChatShare.class, this);
                if (weChatShare != null) {
                    weChatShare.shareText(new ShareContent.Builder()
                            .setUrl("")
                            .setTitle("")
                            .setContent("")
                            .build(), true);
                }
                break;
            case Platform.QZONE:
                SKQzoneShare skQzoneShare = SKShare.create(SKQzoneShare.class, this);
                if (skQzoneShare != null) {
                    skQzoneShare.shareToQzone(new ShareContent.Builder()
                            .setUrl("")
                            .setTitle("")
                            .setContent("")
                            .build(), this, new DefaultShareCallback());
                }
                break;
            case Platform.WEIBO:
                WeiboShare weiboShare = SKShare.create(WeiboShare.class, this);
                if (weiboShare != null) {
                    weiboShare.share(new ShareContent.Builder()
                            .setUrl("")
                            .setTitle("")
                            .setContent("")
                            .build(), this, new DefaultShareCallback());
                }
                break;
        }
    }
}
