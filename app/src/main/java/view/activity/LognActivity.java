package view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.wangwenjie.subjectdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LognActivity extends AppCompatActivity {

    @BindView(R.id.leftView1)
    ImageView leftView1;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.btlogin)
    Button btlogin;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.findpass)
    TextView findpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logn);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.leftView1, R.id.name, R.id.pass, R.id.btlogin, R.id.zhuce, R.id.findpass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftView1:
                finish();
                break;
            case R.id.name:
                break;
            case R.id.pass:
                break;
            case R.id.btlogin:
                break;
            case R.id.zhuce:
                break;
            case R.id.findpass:
                break;
        }
    }
}
