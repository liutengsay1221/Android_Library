package com.rd.lottery.fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.baseline.framework.logic.photo.CapturePhotoHelper;
import com.android.baseline.framework.ui.activity.BasicFragment;
import com.android.baseline.framework.ui.activity.annotations.ViewInject;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.rd.lottery.R;
import com.rd.lottery.adapter.TakePhotoAdapter;
import com.rd.lottery.model.MyAnswerInfo;
import com.rd.lottery.model.QuestionInfo;
import com.rd.lottery.view.OnItemCliclkListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 拍照题
 *
 * @author liuteng
 * @version [2017/6/27 10:26]
 */

public class TakePhotoFragment extends BasicFragment implements OnItemCliclkListener {
    private QuestionInfo questionInfo;
    private int position;
    @ViewInject(R.id.photo_txt)
    private TextView photoTxt;
    @ViewInject(R.id.recycler_photo_view)
    private RecyclerView recyclerView;
    private List<String> strings = new ArrayList<>();
    private TakePhotoAdapter photoAdapter;
    private CapturePhotoHelper photoHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflate(inflater, container, R.layout.fragment_take_photo, this);
    }

    @Override
    protected void afterSetContentView(View view) {
        super.afterSetContentView(view);
        questionInfo = (QuestionInfo) getArguments().getSerializable("questionInfo");
        position = getArguments().getInt("position", 0);
        if (questionInfo != null) {
            photoTxt.setText(position + 1 + "." + questionInfo.getQuestionName());
        }
        if (strings.size() == 0) {
            strings.add("addPic");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        photoAdapter = new TakePhotoAdapter(getActivity(), strings, R.layout.credentials_pic_item);
        photoAdapter.setOnItemCliclkListener(this);
        recyclerView.setAdapter(photoAdapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.delect_pic:
                strings.remove(position);
                questionInfo.getMyAnswerIds().clear();
                for (String str : strings) {
                    if (!str.equals("addPic")) {
                        MyAnswerInfo myAnswerInfo = new MyAnswerInfo();
                        myAnswerInfo.setAnswerContent(imgToBase64(str));
                        questionInfo.getMyAnswerIds().add(myAnswerInfo);
                    }
                }
                photoAdapter.setDataSource(strings);
                photoAdapter.notifyDataSetChanged();
                break;
            case R.id.select_pic:
                if (position == 0) {
                    showDialog();
                } else {
                    List<String> watchPicList = new ArrayList<>();
                    watchPicList.addAll(strings);
                    watchPicList.remove(0);
//                    PicturePreviewActivity.actionStart(TopicConfigureActivity.this, watchPicList, position - 1);
                }
                break;
        }
    }

    public void showDialog() {
        LayoutInflater inflaterDl = LayoutInflater.from(getActivity());
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(R.layout.select_topic_pic_dialog_view, null);
        //对话框
        final Dialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        dialog.getWindow().setContentView(layout);
        Window dialogWindow = dialog.getWindow();
        WindowManager wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        lp.width = (int) (width * 0.9);
        dialogWindow.setAttributes(lp);
        Button pTxt = (Button) dialog.findViewById(R.id.dialog_pai_zhao);
        Button xTxt = (Button) dialog.findViewById(R.id.dialog_xiangce);
        pTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                photoHelper = new CapturePhotoHelper();
                photoHelper.onClick(getTakePhoto(), true, 1, 1, false);
            }
        });
        xTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                photoHelper = new CapturePhotoHelper();
                photoHelper.onClick(getTakePhoto(), true, 0, 10, false);
            }
        });
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        ArrayList<TImage> tImages = result.getImages();
        for (int i = 0; i < tImages.size(); i++) {
            if (strings.size() < 11) {
                strings.add(tImages.get(i).getCompressPath());
            } else {
                break;
            }
        }
        questionInfo.getMyAnswerIds().clear();
        for (String str : strings) {
            if (!str.equals("addPic")) {
                MyAnswerInfo myAnswerInfo = new MyAnswerInfo();
                myAnswerInfo.setAnswerContent(imgToBase64(str));
                questionInfo.getMyAnswerIds().add(myAnswerInfo);
            }
        }
        photoAdapter.setDataSource(strings);
        photoAdapter.notifyDataSetChanged();
    }

    /**
     * @param imgPath
     * @return
     */
    public static String imgToBase64(String imgPath) {
        Bitmap bitmap = null;
        if (imgPath != null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        }
        if (bitmap == null) {
            //bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }
}
