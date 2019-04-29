package com.example.aksy.myapplication.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * @description: 功能类，封装一些功能函数
 */

public class Util {

    /**
     * @param input
     * @return
     * @description 字符串处理，防止SQL注入
     */
    public static String StringHandle(String input) {
        String output;
        // 将包含有 单引号(')，分号(;) 和 注释符号(--)的语句替换掉
        output = input.trim().replaceAll(".*([';]+|(--)+).*", " ");
        return output;
    }

    /**
     * Toast封装
     *
     * @param context
     * @param msg
     */
    public static void makeToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据生日推算年龄
     *
     * @param birthDay
     * @return
     */
    public static int getAgeFromDate(Date birthDay) {
        int age;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthDay);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }


    /**
     * 判断sd卡是否存在
     *
     * @return
     */
    public static boolean isSdcardExisting() {
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 创建一个文件的 uri，供视频录制完保存
     */
//    public static String createVideo() {
//        // 如果 SD 卡存在，则在外部存储建立一个文件夹用于存放视频
//        if ((Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))) {
//            // 选择自己的文件夹
//            String path = Environment.getExternalStorageDirectory().getPath();
//            // Constants.video_url 是一个常量，代表存放视频的文件夹
//            File mediaStorageDir = new File(path + Config.video_url);
//            if (!mediaStorageDir.exists()) {
//                if (!mediaStorageDir.mkdirs()) {
//                    Log.e("TAG", "文件夹创建失败");
//                    return null;
//                }
//            }
//            // 文件根据当前的毫秒数给自己命名
//            String timeStamp = String.valueOf(System.currentTimeMillis());
//            timeStamp = timeStamp.substring(7);
//            String imageFileName = "V" + timeStamp;
//            String suffix = ".mp4";
//            return mediaStorageDir + File.separator + imageFileName + suffix;
//        }
//        return null;
//    }


    /**
     * 创建一个文件的 uri，供视频录制完保存
     */
//    @Nullable
//    public static File createMediaFile() throws IOException {
//        // 如果 SD 卡存在，则在外部存储建立一个文件夹用于存放视频
//        if ((Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))) {
//            // 选择自己的文件夹
//            String path = Environment.getExternalStorageDirectory().getPath();
//            // Constants.video_url 是一个常量，代表存放视频的文件夹
////            File mediaStorageDir = new File(path + Config.video_url);
////            if (!mediaStorageDir.exists()) {
////                if (!mediaStorageDir.mkdirs()) {
////                    Log.e("TAG", "文件夹创建失败");
////                    return null;
////                }
//            }
//
//            // 文件根据当前的毫秒数给自己命名
//            String timeStamp = String.valueOf(System.currentTimeMillis());
//            timeStamp = timeStamp.substring(7);
//            String imageFileName = "V" + timeStamp;
//            String suffix = ".mp4";
//            File mediaFile = new File(mediaStorageDir + File.separator + imageFileName + suffix);
//            return mediaFile;
//        }
//        return null;
//    }
}
