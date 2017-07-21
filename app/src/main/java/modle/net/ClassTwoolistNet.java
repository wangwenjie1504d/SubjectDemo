package modle.net;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import modle.bean.ClassTwoData;
import view.adpter.ClassTwolistAdapter;

/**
 * 类描述：二级网络请求数据
 * 创建人：wangwenjie
 * 创建时间：2017/7/18/0018 11:27
 */

public class ClassTwoolistNet {
    //请求数据
   public void net2Data(String url2, final List<ClassTwoData.DatasBean.ClassListBean> twoarraylist, final ClassTwolistAdapter classTwolistAdapter) {
        RequestParams params=new RequestParams(url2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ClassTwoData classData = gson.fromJson(result, ClassTwoData.class);
                twoarraylist.addAll(classData.getDatas().getClass_list());
                classTwolistAdapter.notifyDataSetChanged();
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
