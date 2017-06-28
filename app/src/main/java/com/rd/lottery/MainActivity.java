package com.rd.lottery;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.baseline.framework.ui.activity.BasicActivity;
import com.android.baseline.framework.ui.adapter.MultiTypeSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends BasicActivity implements View.OnClickListener {
//    private LinearLayout addHotelNameView;
//    private TextAdapter textAdapter;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        JPushInterface.setAlias(this, "lt", new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//                Log.i("JPush", i + s);
//            }
//        });
//        addHotelNameView = (LinearLayout) findViewById(R.id.ll_addView);
//        findViewById(R.id.btn_getData).setOnClickListener(this);
//        findViewById(R.id.btn_getData1).setOnClickListener(this);
//        //默认添加一个Item
//        addViewItem();
//        List<String> list = new ArrayList<>();
//        MultiTypeSupport<String> multiTypeSupport = new MultiTypeSupport<String>() {
//            @Override
//            public int getLayoutId(@LayoutRes int itemType) {
//                return 0;
//            }
//
//            @Override
//            public int getItemViewType(String item, int position) {
//                return 0;
//            }
//
//            @Override
//            public int getViewTypeCount() {
//                return 0;
//            }
//        };
//        textAdapter = new TextAdapter(this,list,multiTypeSupport);
//    }
//
    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_getData://打印数据
//                addViewItem();
//                break;
//            case R.id.btn_getData1:
//                printData();
//                break;
//        }
    }
//
//    /**
//     * Item排序
//     */
//    private void sortHotelViewItem() {
//        //获取LinearLayout里面所有的view
//        for (int i = 0; i < addHotelNameView.getChildCount(); i++) {
//            final View childAt = addHotelNameView.getChildAt(i);
//            final Button btn_remove = (Button) childAt.findViewById(R.id.btn_addHotel);
//            TextView textView = (TextView) childAt.findViewById(R.id.tv_hotelName);
//            textView.setText("行" + i + " :");
//            btn_remove.setText("删除");
//            btn_remove.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //从LinearLayout容器中删除当前点击到的ViewItem
//                    addHotelNameView.removeView(childAt);
//                }
//            });
//        }
//    }
//
//    //添加ViewItem
//    private void addViewItem() {
//        View hotelEvaluateView = View.inflate(this, R.layout.item_hotel_evaluate, null);
//        addHotelNameView.addView(hotelEvaluateView);
//        sortHotelViewItem();
//    }
//
//    //获取所有动态添加的Item，找到控件的id，获取数据
//    private void printData() {
//        for (int i = 0; i < addHotelNameView.getChildCount(); i++) {
//            View childAt = addHotelNameView.getChildAt(i);
//            EditText hotelName = (EditText) childAt.findViewById(R.id.ed_hotelName);
//            showToast("行" + i + " :" + hotelName.getText().toString());
//        }
//    }
}
