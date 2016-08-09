package stark.skshare.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import stark.skshare.DefaultShareCallback;
import stark.skshare.Platform;
import stark.skshare.STShare;
import stark.skshare.ShareContent;
import stark.skshare.tencent.TencentShare;
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
                STShare.create(TencentShare.class).init(this).share(new ShareContent.Builder()
                        .setUrl("url")
                        .setTitle("title")
                        .setContent("content")
                        .setImageUrl("")
                        .setAppName("app")
                        .build(), this, new DefaultShareCallback());
                break;
            case Platform.PYP:
                WeChatShare weChatShare = STShare.create(WeChatShare.class);
                if (weChatShare != null) {
                    weChatShare.init(this).shareText(new ShareContent.Builder().setUrl("").setTitle("").setContent("").build(), false);
                }
                break;
            case Platform.WECHAT:
                STShare.create(WeChatShare.class).init(this).shareText(new ShareContent.Builder().setUrl("").setTitle("").setContent("").build(), true);
                break;
            case Platform.QZONE:
                STShare.create(TencentShare.class).init(this).share(new ShareContent.Builder().setUrl("").setTitle("").setContent("").build(), this, new DefaultShareCallback());
                break;
            case Platform.WEIBO:
                STShare.create(WeiboShare.class).init(this).share(new ShareContent.Builder().setUrl("").setTitle("").setContent("").build(), this, new DefaultShareCallback());
                break;
        }
    }
}
