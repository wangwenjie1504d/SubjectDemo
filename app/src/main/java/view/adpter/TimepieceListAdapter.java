package view.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.wangwenjie.subjectdemo.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import modle.bean.TimepriceDescClass;

/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2017/7/21/0021 16:44
 */

public class TimepieceListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TimepriceDescClass.DatasBean.GoodsCommendListBean> commendListBeen;

    public TimepieceListAdapter(Context context, ArrayList<TimepriceDescClass.DatasBean.GoodsCommendListBean> commendListBeen) {
        this.context = context;
        this.commendListBeen = commendListBeen;
    }

    @Override
    public int getCount() {
        return commendListBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return commendListBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder1;
        if (convertView==null){
            viewHolder1=new ViewHolder1();
            convertView=View.inflate(context, R.layout.timepiece_listitem,null);
            viewHolder1.photo= (ImageView) convertView.findViewById(R.id.image3);
            viewHolder1.textdesc= (TextView) convertView.findViewById(R.id.desc1);
            viewHolder1.textprice= (TextView) convertView.findViewById(R.id.price1);
            convertView.setTag(viewHolder1);
        }else{
            viewHolder1= (ViewHolder1) convertView.getTag();
        }
        viewHolder1.textdesc.setText(commendListBeen.get(position).getGoods_name());
        viewHolder1.textprice.setText(commendListBeen.get(position).getGoods_promotion_price()+"");
        Glide.with(context).load(commendListBeen.get(position).getGoods_image_url()).into(viewHolder1.photo);
        return convertView;
    }
    class ViewHolder1{
        private ImageView photo;
        private TextView textdesc,textprice;
    }
}
