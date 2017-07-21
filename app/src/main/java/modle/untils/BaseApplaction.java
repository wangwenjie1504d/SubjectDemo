package modle.untils;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2017/7/18/0018 10:56
 */

public class BaseApplaction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
