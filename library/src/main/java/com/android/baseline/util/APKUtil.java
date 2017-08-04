package com.android.baseline.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.android.baseline.AppDroid;

import java.io.File;
import java.net.URLEncoder;
import java.security.MessageDigest;

/**
 * 基础工具类 [尽量减少类似Util的类存在]
 *
 * @author hiphonezhu@gmail.com
 * @version [Android-BaseLine, 2014-8-29]
 */
public class APKUtil {
    /**
     * 获得版本号
     *
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getApplicationContext().getPackageManager()
                    .getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }

    /**
     * 获得版本名称
     *
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getApplicationContext().getPackageManager()
                    .getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * 根据版本号比较，版本号格式：数字.数字.数字
     *
     * @param innerVersion  app内部版本号
     * @param newestVersion 服务器版本号
     * @return true innerVersion < newestVersion
     */
    public static boolean compareVerName(String innerVersion, String newestVersion) {
        if (TextUtils.isEmpty(innerVersion) || TextUtils.isEmpty(newestVersion)) {
            return false;
        }
        try {
            String[] inners = innerVersion.split(".");
            String[] newests = newestVersion.split(".");
            for (int i = 0; i < newests.length; i++) {
                int newest = Integer.parseInt(newests[i]);
                int inner = 0;
                if (i < inners.length) {
                    inner = Integer.parseInt(inners[i]);
                }
                if (newest > inner) {
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 获得APP包名
     *
     * @return
     */
    public static String getPackageName(Context context) {
        return context.getApplicationContext().getPackageName();
    }

    /**
     * 获得磁盘缓存目录 [PS：应用卸载后会被自动删除]
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getApplicationContext().getExternalCacheDir().getPath();
        } else {
            cachePath = context.getApplicationContext().getCacheDir().getPath();
        }
        File dir = new File(cachePath + File.separator + uniqueName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * dip转pix
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * pix转dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 7.0目录权限适配
     *
     * @param authority
     * @param inputFile
     * @return
     */
    public static Uri getSupportUri(String authority, File inputFile) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(AppDroid.getInstance().getApplicationContext(), authority, inputFile);
        } else {
            uri = Uri.fromFile(inputFile);
        }
        return uri;
    }

    /**
     * 字符串md5
     *
     * @param s
     * @return
     */
    public static String md5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * url 中文编码转换
     *
     * @param url
     * @return
     */
    public static String UrlEncode(String url) {
        if (url != null) {
            String urlBegin = url.substring(0, url.lastIndexOf("/") + 1);
            String urlEnd = url.substring(url.lastIndexOf("/") + 1, url.length());
            String str = urlBegin + URLEncoder.encode(urlEnd).replace("+", "%20");
            return str;
        } else {
            return null;
        }
    }
}
