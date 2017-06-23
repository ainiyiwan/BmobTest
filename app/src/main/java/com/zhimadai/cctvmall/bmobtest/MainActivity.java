package com.zhimadai.cctvmall.bmobtest;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.zhimadai.cctvmall.bmobtest.entity.GameScore;
import com.zhimadai.cctvmall.bmobtest.permission.PermissionListener;
import com.zhimadai.cctvmall.bmobtest.permission.PermissionManager;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA = 0x00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //第一：默认初始化
        Bmob.initialize(this, "59af671bb1ca249b015dbebf558712f2");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
        //设置appkey
        .setApplicationId("Your Application ID")
        //请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(30)
        //文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(1024*1024)
        //文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(2500)
        .build();
        Bmob.initialize(config);
        //通知
        noti();
        //权限
        per();
        //增
        add();
        //删
        del();
        //改
        update();
        //查
        query();

    }

    private void query() {

    }

    private void update() {

    }

    private void del() {
    }

    private void add() {
        GameScore gameScore = new GameScore();
//注意：不能调用gameScore.setObjectId("")方法
        gameScore.setPlayerName("比目");
        gameScore.setScore(89);
        gameScore.setIsPay(false);
        gameScore.save(new SaveListener<String>() {

            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this, "创建数据成功：" + objectId, Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    private void noti() {
        NotificationManager notificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(MainActivity.this, MainActivity.class), 0);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setTicker("更新啦")
                .setContentTitle("标题")
                .setContentText("内容")
                .setSmallIcon(R.mipmap.ic_launcher_round);
        Notification notification = builder.build();
        notificationManager.notify(0, notification);
    }

    private void per() {

        PermissionManager helper;
        helper = PermissionManager.with(MainActivity.this)
                //添加权限请求码
                .addRequestCode(MainActivity.REQUEST_CODE_CAMERA)
                //设置权限，可以添加多个权限
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                //设置权限监听器
                .setPermissionsListener(new PermissionListener() {

                    @Override
                    public void onGranted() {
                        //当权限被授予时调用
                        Toast.makeText(MainActivity.this, "Storage Permission granted",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onDenied() {
                        //用户拒绝该权限时调用
                        Toast.makeText(MainActivity.this, "Storage Permission denied",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onShowRationale(String[] permissions) {
                        //当用户拒绝某权限时并点击`不再提醒`的按钮时，下次应用再请求该权限时，需要给出合适的响应（比如,给个展示对话框来解释应用为什么需要该权限）

                    }
                })
                //请求权限
                .request();
    }
}
