package com.android.baseline.framework.logic.photo;

import android.net.Uri;
import android.os.Environment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

/**
 * 从相机、相册选取照片
 *
 * @author liuteng
 */
public class CapturePhotoHelper {
    /**
     * 选择照片
     *
     * @param takePhoto
     * @param isCompress 是否压缩
     * @param from       0 相册 1相机
     * @param limit      选择张数
     * @param isCrop     是否裁剪
     */
    public void onClick(TakePhoto takePhoto, boolean isCompress, int from, int limit, boolean isCrop) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        configCompress(takePhoto, isCompress);
        configTakePhotoOption(takePhoto);
        if (from == 0) { //从相机选择
            if (limit > 1) {
                if (isCrop) {
                    takePhoto.onPickMultipleWithCrop(limit, getHandCropOptions(60, 60));
                } else {
                    takePhoto.onPickMultiple(limit);
                }
                return;
            } else {
                if (isCrop) {
                    takePhoto.onPickFromGalleryWithCrop(imageUri, getHandCropOptions(60, 60));
                } else {
                    takePhoto.onPickFromGallery();
                }
            }
        } else {
            if (isCrop) {
                takePhoto.onPickFromCaptureWithCrop(imageUri, getHandCropOptions(60, 60));
            } else {
                takePhoto.onPickFromCapture(imageUri);
            }
        }
    }

    /**
     * 相册设置
     *
     * @param takePhoto
     */
    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(false);
        takePhoto.setTakePhotoOptions(builder.create());
    }

    /**
     * 是否裁剪图片
     */
    private CropOptions getHandCropOptions(int mwidth, int mheight) {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(mwidth).setAspectY(mheight);
        builder.setWithOwnCrop(true);
        return builder.create();
    }

    /**
     * 是否压缩图片
     *
     * @param takePhoto
     */
    private void configCompress(TakePhoto takePhoto, boolean isCompress) {
        if (!isCompress) {
            takePhoto.onEnableCompress(null, false);
            return;
        }
        int maxSize = 1024;
        int width = 480;
        int height = 800;
        boolean showProgressBar = false;
        boolean enableRawFile = true;
        LubanOptions option = new LubanOptions.Builder()
                .setMaxHeight(height)
                .setMaxWidth(width)
                .setMaxSize(maxSize)
                .create();
        CompressConfig config = CompressConfig.ofLuban(option);
        config.enableReserveRaw(enableRawFile);
        takePhoto.onEnableCompress(config, showProgressBar);
    }
}
