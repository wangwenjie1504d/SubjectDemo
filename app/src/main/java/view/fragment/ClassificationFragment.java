package view.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.bawei.wangwenjie.subjectdemo.R;

import java.util.ArrayList;
import java.util.List;

import modle.bean.ClassData;
import modle.bean.ClassThreeData;
import modle.bean.ClassTwoData;
import modle.net.ClassOnelistNet;
import modle.net.ClassThreelistNet;
import modle.net.ClassTwoolistNet;
import view.adpter.ClassOnelistAdapter;
import view.adpter.ClassThreelistAdapter;
import view.adpter.ClassTwolistAdapter;
import view.interfaceview.OnelistIntfaceView;

import static android.R.attr.id;

/**
 * 类描述：分类
 * 创建人：wangwenjie
 * 创建时间：2017/7/11/0011 20:27
 */

public class ClassificationFragment extends BaseFragment  {
    private List<ClassData.DatasBean.ClassListBean> classListBeen;
    String url="http://169.254.154.120/mobile/index.php?act=goods_class&&op=goods_list&page=100";
    private ClassTwolistAdapter classTwolistAdapter;
    private RecyclerView class2recyclerView;
    private List<ClassTwoData.DatasBean.ClassListBean> twoarraylist;
    @Override
    View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.classificationfragment,null);
        classListBeen = new ArrayList<>();
        initOneView(view);

        return view;
    }

    //初始化控件 一级列表
    private void initOneView(View view) {
        RecyclerView class1recyclerView = (RecyclerView) view.findViewById(R.id.class1RecyclerView);
        class2recyclerView = (RecyclerView) view.findViewById(R.id.class2RecyclerView);
        //twoarraylist = new ArrayList<>();
        ClassOnelistAdapter classOnelistAdapter = new ClassOnelistAdapter(getActivity(), classListBeen);
        //请求以及一级列表
        ClassOnelistNet classOnelistNet = new ClassOnelistNet();
        classOnelistNet.netData(url,classListBeen,classOnelistAdapter);
         //线性布局
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        class1recyclerView.setLayoutManager(manager);
        class1recyclerView.setAdapter(classOnelistAdapter);
        //给一级列表设置监听
        setOneChickListener(classOnelistAdapter);

    }

    private void setOneChickListener(final ClassOnelistAdapter classOnelistAdapter) {
        classOnelistAdapter.setClickListener(new OnelistIntfaceView.OnClickListener() {
            @Override
            public void onIteamChick(View view, int position) {
                //获取一级列表getGc_id()字段拼接url接口
                final String id = classListBeen.get(position).getGc_id();
                //二级列表添加数据
                ClassTwolistAdapter classTwolistAdapter = addTwoListData(id);
                //设置二级列表监听
                setTwoChickListener( classTwolistAdapter);
               /*将数组转成集合
               String text = classListBeen.get(position).getText();
                String[] split = text.split("/");
                 List<String> onelist= Arrays.asList(split);*/

            }

            @NonNull
            private ClassTwolistAdapter addTwoListData(String id) {
                twoarraylist = new ArrayList<>();
                ClassTwolistAdapter classTwolistAdapter = new ClassTwolistAdapter(getActivity(), twoarraylist);
                ClassTwoolistNet classTwoolistNet = new ClassTwoolistNet();
                classTwoolistNet.net2Data(url+"&gc_id="+id,twoarraylist,classTwolistAdapter);
                LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                class2recyclerView.setLayoutManager(manager);
                class2recyclerView.setAdapter(classTwolistAdapter);
                return classTwolistAdapter;
            }

            private void setTwoChickListener( ClassTwolistAdapter classTwolistAdapter) {
                classTwolistAdapter.setClickListener(new OnelistIntfaceView.OnClickListener() {
                    @Override
                    public void onIteamChick(View view, int position) {
                        GridView gridView = (GridView) view.findViewById(R.id.threeGridview);
                        //显示GridView布局
                        gridView.setVisibility(View.VISIBLE);
                        //获取二级列表getGc_id()字段拼接url接口
                        String gc_id = twoarraylist.get(position).getGc_id();
                        //获取三级列表数据
                        addThreeListData(gridView, gc_id);
                    }

                    private void addThreeListData(GridView gridView, String gc_id) {
                        List<ClassThreeData.DatasBean.ClassListBean> threearraylist=new ArrayList<>();
                        ClassThreelistAdapter classThreelistAdapter = new ClassThreelistAdapter(getActivity(), threearraylist);
                        ClassThreelistNet classThreelistNet = new ClassThreelistNet();
                        classThreelistNet.net3Data(url+"&gc_id="+id+"&gc_id="+gc_id,threearraylist,classThreelistAdapter);
                        //gridView.setVisibility();
                        gridView.setAdapter(classThreelistAdapter);
                    }
                });
            }
        });
    }


}
