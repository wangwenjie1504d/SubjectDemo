package view.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.wangwenjie.subjectdemo.R;
import com.bumptech.glide.Glide;

import java.util.List;

import modle.bean.ClassData;
import view.interfaceview.OnelistIntfaceView;

/**
 * 类描述：一级列表适配器
 * 创建人：wangwenjie
 * 创建时间：2017/7/18/0018 11:08
 */

public class ClassOnelistAdapter extends RecyclerView.Adapter<ClassOnelistAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<ClassData.DatasBean.ClassListBean> classListBeen;
    OnelistIntfaceView.OnLongClickListener longClickListener;
    OnelistIntfaceView.OnClickListener clickListener;

    public void setLongClickListener(OnelistIntfaceView.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(OnelistIntfaceView.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ClassOnelistAdapter(Context context, List<ClassData.DatasBean.ClassListBean> classListBeen) {
        this.context = context;
        this.classListBeen = classListBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.classonenetlistadapter,null);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.textView.setText(classListBeen.get(position).getGc_name());
        Glide.with(context).load(classListBeen.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return classListBeen.size();
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
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.classpic);
            textView= (TextView) itemView.findViewById(R.id.classtext);
        }
    }
}
