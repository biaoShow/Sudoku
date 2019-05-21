package com.biao.sudoku;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by benxiang on 2019/5/21.
 */

public class RVadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater mLayout;
    String str_text[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "重输", "0", "删除"};
    private OnClickItem onClickItem;

    RVadapter(Context context) {
        this.context = context;
        this.mLayout = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayout.inflate(R.layout.rv_item_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder) holder).btn_num.setText(str_text[position]);
        ((MyViewHolder) holder).btn_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.clickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        Button btn_num;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_num = itemView.findViewById(R.id.btn_num);
        }
    }

    //点击事件接口
    interface OnClickItem {
        void clickItem(int position);
    }
}
