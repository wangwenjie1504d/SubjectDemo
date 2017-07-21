package view.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.wangwenjie.subjectdemo.R;

import java.util.List;

import modle.bean.ClassTwoData;
import view.interfaceview.OnelistIntfaceView;

/**
 * 类描述：二级列表适配器
 * 创建人：wangwenjie
 * 创建时间：2017/7/18/0018 15:43
 */

public class ClassTwolistAdapter extends  RecyclerView.Adapter<ClassTwolistAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<ClassTwoData.DatasBean.ClassListBean> twoarraylist;
    OnelistIntfaceView.OnLongClickListener longClickListener;
    OnelistIntfaceView.OnClickListener clickListener;

    public void setLongClickListener(OnelistIntfaceView.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(OnelistIntfaceView.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ClassTwolistAdapter(Context context, List<ClassTwoData.DatasBean.ClassListBean> twoarraylist) {
        this.context = context;
        this.twoarraylist = twoarraylist;
    }

    @Override
    public ClassTwolistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.classtwonetlistadapter,null);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        ClassTwolistAdapter.ViewHolder holder = new ClassTwolistAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClassTwolistAdapter.ViewHolder holder, int position) {
        holder.textView.setText(twoarraylist.get(position).getGc_name());
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return twoarraylist.size();
    }

    @Override
    public void onClick(View v) {
        if (clickListener!=null){
            clickListener.onIteamChick(v, (Integer) v.getTag());
        }

    }

    @Override
    public boolean onLongClick(View v) {
        if (longClickListener!=null){
            longClickListener.onLongIteamChick(v, (Integer) v.getTag());
        }
        return true;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.classtwotext);
        }
    }
}
