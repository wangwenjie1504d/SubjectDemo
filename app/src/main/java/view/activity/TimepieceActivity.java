package view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.wangwenjie.subjectdemo.R;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import modle.bean.TimepieceClass;
import view.adpter.TimepieceAdapter;
import view.interfaceview.OnelistIntfaceView;

/**
 * 类描述： 钟表列表
 * 创建人：wangwenjie
 * 创建时间：2017/7/20/0020
 */
public class TimepieceActivity extends AppCompatActivity {
    String url = "http://169.254.154.120/mobile/index.php?act=goods&op=goods_list&page=100";
    @BindView(R.id.ordertext)
    TextView ordertext;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.leftView)
    ImageView leftView;
    @BindView(R.id.rightView)
    ImageView rightView;
    @BindView(R.id.RecyclerViewtime)
    RecyclerView RecyclerViewtime;
    @BindView(R.id.activity_timepiece)
    LinearLayout activityTimepiece;
    private List<TimepieceClass.DatasBean.GoodsListBean> timearrayliat;
    private TimepieceAdapter timepieceAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepiece);
        ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewtime);
        //加载表的数据
        addTimepieceData();
        //添加监听
        addRecyclerClickListener();
    }
    private void addRecyclerClickListener() {
        timepieceAdapter.setClickListener(new OnelistIntfaceView.OnClickListener() {
            @Override
            public void onIteamChick(View view, int position) {
                Intent intent = new Intent(TimepieceActivity.this,TimepieceDescActivity.class);
                String id = timearrayliat.get(position).getGoods_id();
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }
    private void addTimepieceData() {
        timearrayliat = new ArrayList<>();
        timepieceAdapter = new TimepieceAdapter(TimepieceActivity.this, timearrayliat);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                TimepieceClass timepieceClass = gson.fromJson(result, TimepieceClass.class);
                timearrayliat.addAll(timepieceClass.getDatas().getGoods_list());
                timepieceAdapter.notifyDataSetChanged();
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
        //线性布局
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //设置recyclerView分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(timepieceAdapter);
    }

    @OnClick({R.id.ordertext, R.id.check, R.id.leftView,R.id.rightView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftView:
                finish();
                break;
            case R.id.rightView:
               Toast.makeText(TimepieceActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ordertext:
                //弹出popuwindow
                addPopupWindow();
                break;
            case R.id.check:
                if (!check.isChecked()) {
                    recyclerView.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void addPopupWindow() {
        View view = View.inflate(TimepieceActivity.this, R.layout.popuwindow, null);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        //LTGRAY
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        popupWindow.setFocusable(true);
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            //Gravity.CENTER 中心 Gravity.BOTTOM 父控件底部
            // popupWindow.showAsDropDown(ordertext);
            popupWindow.showAtLocation(View.inflate(TimepieceActivity.this, R.layout.activity_timepiece, null), Gravity.NO_GRAVITY, 0, 200);
        }
    }

}
