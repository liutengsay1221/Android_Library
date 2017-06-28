package com.rd.lottery;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.baseline.framework.ui.activity.BasicActivity;
import com.android.baseline.framework.ui.activity.annotations.ViewInject;
import com.android.baseline.framework.ui.adapter.MultiTypeSupport;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuteng
 * @version [2017/6/20 15:51]
 */

public class TextActivity extends BasicActivity {
    @ViewInject(R.id.recyclerview)
    private RecyclerView recyclerView;
    private TextAdapter textAdapter;
    private List<TextDemo> textDemos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_activity);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        Log.i("转码",forUTF("%f0%9f%98%94%f0%9f%98%80%f0%9f%98%9d%f0%9f%90%af%f0%9f%90%ae%f0%9f%90%b4%e2%99%89%ef%b8%8f"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textDemos = new ArrayList<>();
        TextDemo textDemo1 = new TextDemo();
        textDemo1.setTitle(0 + "测试布局一");
        textDemo1.setType(1);
        TextDemo textDemo4 = new TextDemo();
        textDemo4.setTitle(4 + "测试布局一");
        textDemo4.setType(1);
        TextDemo textDemo5 = new TextDemo();
        textDemo5.setTitle(5 + "测试布局一");
        textDemo5.setType(1);
        TextDemo textDemo2 = new TextDemo();
        textDemo2.setTitle(1 + "测试布局二");
        textDemo2.setType(2);
        TextDemo textDemo3 = new TextDemo();
        textDemo3.setTitle(2 + "测试布局三");
        textDemo3.setType(3);
        textDemos.add(textDemo1);
        textDemos.add(textDemo2);
        textDemos.add(textDemo3);
        textDemos.add(textDemo4);
        textDemos.add(textDemo5);
        MultiTypeSupport<TextDemo> typeSupport = new MultiTypeSupport<TextDemo>() {
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == 1) {
                    return R.layout.layout_text_1;
                } else if (itemType == 2) {
                    return R.layout.layout_text_2;
                } else if (itemType == 3) {
                    return R.layout.layout_text_3;
                }
                return 0;
            }

            @Override
            public int getItemViewType(TextDemo item, int position) {
                item = textDemos.get(position);
                return item.getType();
            }

            @Override
            public int getViewTypeCount() {
                return 3;
            }
        };
        textAdapter = new TextAdapter(this, textDemos, typeSupport);
        recyclerView.setAdapter(textAdapter);
    }

    public static String forUTF(String txt) {
        String result = "";
        try {
            result = URLDecoder.decode(txt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
