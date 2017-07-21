package view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.wangwenjie.subjectdemo.R;

/**
 * 类描述：个人
 * 创建人：Administrator
 * 创建时间：2017/7/11/0011 20:30
 */

public class PersonalFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.personalfragment,null);
        return view;
    }
}
