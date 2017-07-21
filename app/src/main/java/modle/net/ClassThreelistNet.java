package modle.net;

import android.util.Log;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import modle.bean.ClassThreeData;
import view.adpter.ClassThreelistAdapter;

/**
 * 类描述：三级网络请求数据
 * 创建人：wangwenjie
 * 创建时间：2017/7/18/0018 21:10
 */

public class ClassThreelistNet {
    //请求数据
    public void net3Data(String url, final  List<ClassThreeData.DatasBean.ClassListBean> threearraylist, final ClassThreelistAdapter classThreelistAdapter) {
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ClassThreeData classthreeData = gson.fromJson(result, ClassThreeData.class);
                threearraylist.addAll(classthreeData.getDatas().getClass_list());
                Log.e("aaa","."+threearraylist.toString());
                classThreelistAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
