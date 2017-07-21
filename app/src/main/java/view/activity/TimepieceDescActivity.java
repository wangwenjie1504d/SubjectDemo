package view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.wangwenjie.subjectdemo.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import modle.bean.TimepriceDescClass;
import view.adpter.TimepieceListAdapter;

public class TimepieceDescActivity extends AppCompatActivity {

    @BindView(R.id.photo)
    ImageView photo;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.from)
    TextView from;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.shopdesc)
    TextView shopdesc;
    String url = "http://169.254.154.120/mobile/index.php?act=goods&op=goods_detail";
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.leftView1)
    ImageView leftView1;
    @BindView(R.id.rightViewxin)
    ImageView rightViewxin;
    @BindView(R.id.rightViewshare)
    ImageView rightViewshare;
    private ArrayList<TimepriceDescClass.DatasBean.GoodsCommendListBean> commendListBeen;
    private TimepieceListAdapter listAdapter;
    private String url2;
    private TimepriceDescClass.DatasBean.GoodsInfoBean goods_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepiece_desc);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        url2 = url + "&goods_id=" + id;
        //详情页请求数据
        addDescData(url2);
        commendListBeen = new ArrayList<>();
        listAdapter = new TimepieceListAdapter(TimepieceDescActivity.this, commendListBeen);
        //添加下面listview页面
        addTimeListData(url2);
        lv.setAdapter(listAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String goods_id = commendListBeen.get(position).getGoods_id();
                url2 = url + "&goods_id=" + goods_id;
                addDescData(url2);
            }
        });
    }

    private void addDescData(String url2) {
        RequestParams params = new RequestParams(url2);
        x.http().get(params, new Callback.CommonCallback<String>() {



            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                TimepriceDescClass descClass = gson.fromJson(result, TimepriceDescClass.class);
                goods_info = descClass.getDatas().getGoods_info();
                TimepriceDescClass.DatasBean datas = descClass.getDatas();
                Glide.with(TimepieceDescActivity.this).load(datas.getGoods_image()).into(photo);
                desc.setText(goods_info.getGoods_name());
                from.setText(goods_info.getGoods_jingle());
                price.setText(goods_info.getGoods_price());
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

    private void addTimeListData(String url2) {
        RequestParams params = new RequestParams(url2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                TimepriceDescClass descClass = gson.fromJson(result, TimepriceDescClass.class);
                commendListBeen.addAll(descClass.getDatas().getGoods_commend_list());
                listAdapter.notifyDataSetChanged();
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

    @OnClick({R.id.leftView1, R.id.rightViewxin, R.id.rightViewshare,R.id.shopdesc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopdesc:
                Intent intent1 = new Intent(TimepieceDescActivity.this,WebViewActivity.class);
                String goodsUrl = goods_info.getGoods_url();
                intent1.putExtra("K",goodsUrl);
                startActivity(intent1);
                break;
            case R.id.leftView1:
                finish();
                break;
            case R.id.rightViewxin:
                //跳到登陆页面
                Intent intent = new Intent(TimepieceDescActivity.this, LognActivity.class);
                startActivity(intent);
                break;
            case R.id.rightViewshare:
                break;
        }
    }

}
