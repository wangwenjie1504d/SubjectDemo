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

import modle.bean.TimepieceClass;
import view.interfaceview.OnelistIntfaceView;

/**
 * 类描述：一级列表适配器
 * 创建人：wangwenjie
 * 创建时间：2017/7/18/0018 11:08
 */

public class TimepieceAdapter extends RecyclerView.Adapter<TimepieceAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<TimepieceClass.DatasBean.GoodsListBean> timearrayliat;
    OnelistIntfaceView.OnLongClickListener longClickListener;
    OnelistIntfaceView.OnClickListener clickListener;

    public void setLongClickListener(OnelistIntfaceView.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(OnelistIntfaceView.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public TimepieceAdapter(Context context, List<TimepieceClass.DatasBean.GoodsListBean> timearrayliat) {
        this.context = context;
        this.timearrayliat = timearrayliat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.timepiece_item,null);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.desc.setText(timearrayliat.get(position).getGoods_name());
        holder.price.setText(timearrayliat.get(position).getGoods_price());
        Glide.with(context).load(timearrayliat.get(position).getGoods_image_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return timearrayliat.size();
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
        private TextView desc,price;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.image2);
            desc= (TextView) itemView.findViewById(R.id.desc);
            price= (TextView) itemView.findViewById(R.id.price);
        }
    }
}
