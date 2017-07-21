package view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.wangwenjie.subjectdemo.R;

import view.activity.ScannerActivity;

/**
 * 类描述：
 * 创建人：wangwenjie
 * 创建时间：2017/7/12/0012 20:5
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = onCreateView();
        initView(view);
        return view;
    }
    //初始化布局
    abstract View onCreateView();

    //初始化扫描控件设监听
    private void initView(View view) {
        view.findViewById(R.id.topInclude).findViewById(R.id.leftImageView)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到扫描
                        Intent intent = new Intent(getActivity(), ScannerActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
