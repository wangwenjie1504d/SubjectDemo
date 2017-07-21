package modle.net;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import modle.bean.ClassData;
import view.adpter.ClassOnelistAdapter;

/**
 * 类描述：一级网络请求数据
 * 创建人：wangwenjie
 * 创建时间：2017/7/18/0018 11:27
 */

public class ClassOnelistNet {
    //请求数据
   public void netData(String url, final List<ClassData.DatasBean.ClassListBean> classListBeen, final ClassOnelistAdapter classOnelistAdapter) {
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ClassData classData = gson.fromJson(result, ClassData.class);
                classListBeen.addAll(classData.getDatas().getClass_list());
                classOnelistAdapter.notifyDataSetChanged();
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
