package stark.skshare.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import stark.skshare.SKShare;
import stark.skshare.wechat.WeChatSKShareContent;
import stark.skshare.wechat.WeChatShare;
import stark.skshare.weibo.WeiboShare;

/**
 * Created by jihongwen on 16/8/25.
 */

public class WeixinShareActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_share);
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("weixin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void testShare(View view) {
        WeChatShare share = SKShare.create(WeChatShare.class, this);
        share.shareText(new WeChatSKShareContent.Builder()
                .setContent("微信分享测试")
                .build(), true);

    }
}
