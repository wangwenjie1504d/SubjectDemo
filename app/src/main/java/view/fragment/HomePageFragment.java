package view.fragment;

import android.view.View;

import com.bawei.wangwenjie.subjectdemo.R;

/**
 * 类描述：首页
 * 创建人：Administrator
 * 创建时间：2017/7/11/0011 20:26
 */

public class HomePageFragment extends BaseFragment {

    private View view;

    @Override
    View onCreateView() {
        view = View.inflate(getActivity(), R.layout.homepagefragment,null);
        return view;
    }
}
