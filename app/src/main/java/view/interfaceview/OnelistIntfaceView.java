package view.interfaceview;

import android.view.View;

/**
 * 类描述：设置recyclerview设置接口监听回调
 * 创建人：Administrator
 * 创建时间：2017/7/18/0018 14:47
 */

public class OnelistIntfaceView {
    //点击
    public interface OnClickListener{
        void onIteamChick(View view,int position);
    }
    //长按
    public interface OnLongClickListener{
        void onLongIteamChick(View view,int position);
    }
}
