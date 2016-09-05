package stark.skshare.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import stark.skshare.SKShare;

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
    }
}
