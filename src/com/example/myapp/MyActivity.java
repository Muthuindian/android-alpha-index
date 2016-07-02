package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MyActivity extends FragmentActivity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fl_container,new MyFragment()).commit();

    }


    static class MyFragment extends IndexFragment {
        static String[] alphas = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        /**
         * 获取字母索引
         */
        @Override
        protected String[] getAlphas() {
            return alphas;
        }

        /**
         * getView 每个Item的View
         *
         * @param data
         * @param position
         * @param convertView
         * @param parent
         */
        @Override
        protected View getView(List<IndexItem> data, int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item, null);
            }
            TextView tv_alpha = ViewHolder.get(convertView, R.id.tv_alpha);
            ImageView iv_image = ViewHolder.get(convertView, R.id.iv_image);
            TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);

            Item item = (Item) data.get(position);
            tv_alpha.setText(item.getAlpha());
            //确认alpha 是否显示
            //搜索前一个item是否为相同alpha
            boolean showAlpha = true;
            if (position != 0) {
                if (data.get(position - 1).getAlpha().equals(item.getAlpha())) {
                    showAlpha = false;
                }
            }
            if (!showAlpha) {
                tv_alpha.setVisibility(View.GONE);
            }else{
                tv_alpha.setVisibility(View.VISIBLE);
            }
            //image 不进行设置
            tv_name.setText(item.getName());
            return convertView;
        }

        /**
         * 配置数据
         */
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            List<IndexFragment.IndexItem> list = new LinkedList<IndexFragment.IndexItem>();

            for (int i = 0; i < 1000; ++i) {
                list.add(new Item(null, String.valueOf(i), alphas[i % alphas.length]));
            }
            setData(list);
        }
    }

    static public class Item implements IndexFragment.IndexItem {

        String image;
        String name;
        String alpha;

        public Item(String image, String name, String alpha) {
            this.image = image;
            this.name = name;
            this.alpha = alpha;
        }

        @Nullable
        @Override
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getAlpha() {
            return alpha;
        }

        public void setAlpha(String alpha) {
            this.alpha = alpha;
        }
    }
}
