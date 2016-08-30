package stark.skshare.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import stark.skshare.DefaultShareCallback;
import stark.skshare.Platform;
import stark.skshare.SKShare;
import stark.skshare.SKShareContent;
import stark.skshare.tencent.SKQQShare;
import stark.skshare.tencent.SKQzoneShare;
import stark.skshare.wechat.WeChatSKShareContent;
import stark.skshare.wechat.WeChatShare;
import stark.skshare.weibo.WeiboSKShareContent;
import stark.skshare.weibo.WeiboShare;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ShareLayout.IShareView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SKShare.init(this);
        findViewById(R.id.wxShareTest).setOnClickListener(this);
        findViewById(R.id.weiboShareTest).setOnClickListener(this);
        findViewById(R.id.qqShareTest).setOnClickListener(this);
        ShareSheetDialog.createSheetDialog(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wxShareTest:
                startActivity(new Intent(this, WeixinShareActivity.class));
                break;
            case R.id.weiboShareTest:
                startActivity(new Intent(this, WeiboShareActivity.class));
                break;
            case R.id.qqShareTest:
                startActivity(new Intent(this, QQShareActivity.class));
                break;
        }
    }

    @Override
    public void onShareItemClick(int platform) {
        switch (platform) {
            case Platform.QQ:
                SKQQShare skqqShare = SKShare.create(SKQQShare.class, this);
                if (skqqShare != null) {
                    skqqShare.shareToQQ(new SKShareContent.Builder()
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
                    weChatShare.shareText(new WeChatSKShareContent.Builder()
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
                    weChatShare.shareText(new WeChatSKShareContent.Builder()
                            .setUrl("")
                            .setTitle("")
                            .setContent("")
                            .build(), true);
                }
                break;
            case Platform.QZONE:
                SKQzoneShare skQzoneShare = SKShare.create(SKQzoneShare.class, this);
                if (skQzoneShare != null) {
                    skQzoneShare.shareToQzone(new SKShareContent.Builder()
                            .setUrl("")
                            .setTitle("")
                            .setContent("")
                            .build(), this, new DefaultShareCallback());
                }
                break;
            case Platform.WEIBO:
                WeiboShare weiboShare = SKShare.create(WeiboShare.class, this);
                if (weiboShare != null) {
                    weiboShare.share(new WeiboSKShareContent.Builder()
                            .setUrl("")
                            .setTitle("")
                            .setContent("")
                            .build(), this, new DefaultShareCallback());
                }
                break;
        }
    }
}
