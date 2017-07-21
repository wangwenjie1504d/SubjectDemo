package view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.wangwenjie.subjectdemo.R;
import com.hjm.bottomtabbar.BottomTabBar;

import view.fragment.ClassificationFragment;
import view.fragment.HomePageFragment;
import view.fragment.PersonalFragment;
import view.fragment.ShoppingFragment;
/**
*
* 类描述： 主页面搭建框架
* 创建人：wangwenjie
* 创建时间：2017/7/20/0020
* @version
*
*/
public class MainActivity extends AppCompatActivity {

    private BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }
    //初始化Fragment
    private void initFragment() {
        bottomTabBar.init(getSupportFragmentManager())
                .addTabItem("首页",R.drawable.homepagefragment, HomePageFragment.class)
                .addTabItem("分类",R.drawable.classificationfragment, ClassificationFragment.class)
                .addTabItem("购物车",R.drawable.shoppingfragment, ShoppingFragment.class)
                .addTabItem("个人",R.drawable.personalfragment, PersonalFragment.class);
    }
    //初始化控件
    private void initView() {
        bottomTabBar = (BottomTabBar) findViewById(R.id.bottomtabbar);
    }
}
