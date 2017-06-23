package com.zhimadai.cctvmall.bmobtest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.zhimadai.cctvmall.bmobtest.R;
import com.zhimadai.cctvmall.bmobtest.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.add2_btn)
    Button add2Btn;
    @BindView(R.id.del2_btn)
    Button del2Btn;
    @BindView(R.id.update2_btn)
    Button update2Btn;
    @BindView(R.id.qurey2_btn)
    Button qurey2Btn;
    @BindView(R.id.next2_act)
    Button next2Act;
    private Random random;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        random = new Random();
        list.add(0,"张三");
        list.add(1,"李四");
        list.add(2,"王五");
        list.add(3,"薛六");
    }

    @OnClick(R.id.add2_btn)
    public void onAdd2BtnClicked() {
        Person person = new Person();
        person.setAge(random.nextInt(100));
        person.setSex("male");
        person.setName(list.get(random.nextInt(3)));
        person.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(Main2Activity.this, "创建数据成功：" + s, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });

    }

    @OnClick(R.id.del2_btn)
    public void onDel2BtnClicked() {

    }

    @OnClick(R.id.update2_btn)
    public void onUpdate2BtnClicked() {

    }

    @OnClick(R.id.qurey2_btn)
    public void onQurey2BtnClicked() {
    }


    @OnClick(R.id.next2_act)
    public void onNext2ActClicked() {

    }
}
