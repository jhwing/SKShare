package stark.skshare.demo;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;


/**
 * Created by jihongwen on 16/8/4.
 */

public class ShareSheetDialog {

    static BottomSheetDialogFixed sheetDialog;

    static ShareLayout shareLayout;

    public static void createSheetDialog(Context context) {
        shareLayout = new ShareLayout(context);
        shareLayout.bindView((ShareLayout.IShareView) context);
        sheetDialog = new BottomSheetDialogFixed(context);
        sheetDialog.setContentView(shareLayout);
        sheetDialog.setBehavior(BottomSheetBehavior.from((View) shareLayout.getParent()));
    }

    public static void show() {
        if (sheetDialog != null) {
            if (sheetDialog.isShowing()) {
                sheetDialog.dismiss();
            } else {
                sheetDialog.show();
            }
        }
    }
}
