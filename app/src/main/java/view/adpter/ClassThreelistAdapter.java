package view.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.wangwenjie.subjectdemo.R;

import java.util.List;

import modle.bean.ClassThreeData;
import view.activity.TimepieceActivity;

/**
 * 类描述：三级列表适配器
 * 创建人：wangwenjie
 * 创建时间：2017/7/18/0018 15:43
 */

public class ClassThreelistAdapter extends BaseAdapter {
    private Context context;
    List<ClassThreeData.DatasBean.ClassListBean> threearraylist;

    public ClassThreelistAdapter(Context context, List<ClassThreeData.DatasBean.ClassListBean> threearraylist) {
        this.context = context;
        this.threearraylist = threearraylist;
    }

    @Override
    public int getCount() {
        return threearraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return threearraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(context, R.layout.classthreenetlistadapter,null);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.classthreetext);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(threearraylist.get(position).getGc_name());
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,threearraylist.get(position).getGc_name(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, TimepieceActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView textView;
    }
}
