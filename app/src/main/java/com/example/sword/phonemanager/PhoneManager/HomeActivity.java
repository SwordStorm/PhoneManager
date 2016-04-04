package com.example.sword.phonemanager.PhoneManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sword.phonemanager.application.MyApplication;
import com.example.sword.phonemanager.utils.Md5Utils;

public class HomeActivity extends ActionBarActivity {

    private TextView tv_home_welcome;
    private GridView gv_home_content;

    private int[] iconarray ={R.drawable.safe,R.drawable.callmsgsafe,R.drawable.app,
            R.drawable.taskmanager,R.drawable.netmanager,R.drawable.trojan,
            R.drawable.sysoptimize,R.drawable.atools,R.drawable.settings};

    private  String[] titles={"手机防盗","通讯卫士","软件管理",
    "进程管理","流量统计","手机杀毒",
    "缓存清理","高级工具","设置中心"};

    private final int  CONTENT_NUM = 9;
    private String phonesafe_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //final ActionBar actionBar = getActionBar();
        //隐藏导航栏 使得本应用全屏显示
        final android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        tv_home_welcome = (TextView) findViewById(R.id.tv_home_welcome);
        //tv_home_welcome.setText("欢迎您,新用户,我们的应用可以保卫您手机的安全！");
        //方法2,让控件处于选中状态
        //tv_home_welcome.setSelected(true);

        //初始化九宫格
        //ListView
        //创建GridView，并添加适配器，并监听点击事件
        gv_home_content = (GridView) findViewById(R.id.gv_home_content);
        gv_home_content.setAdapter(new MyAdpter());
        gv_home_content.setOnItemClickListener(new MyItemOnClickListener());

    }
    //为GridView的每一个Item添加点击事件
    class MyItemOnClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch (position){
                case 0:
                    //Toast.makeText(HomeActivity.this,titles[position],Toast.LENGTH_LONG).show();
                    phonesafe_pwd = MyApplication.configsp.getString("phonesafe_pwd","");
                    if (phonesafe_pwd.isEmpty()){
                        //表明没有设置过，此时弹出一个框让用户去设置
                        showSetpwdDialog();
                    }
                    else
                    {
                        //看看用户输入的密码跟之前保存的是否一样
                        showinputpwdDialog();
                    }
                    break;
                case 1:
                    Toast.makeText(HomeActivity.this,titles[position],Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(HomeActivity.this,titles[position],Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(HomeActivity.this,titles[position],Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(HomeActivity.this,titles[position],Toast.LENGTH_LONG).show();
                    break;
                case 5:
                    Toast.makeText(HomeActivity.this,titles[position],Toast.LENGTH_LONG).show();
                    break;
                case 6:
                   // Toast.makeText(HomeActivity.this,titles[position],Toast.LENGTH_LONG).show();
                    startActivity(new Intent(HomeActivity.this,ClearCacheActivity.class));
                    break;
                case 7:
                    Toast.makeText(HomeActivity.this,titles[position],Toast.LENGTH_LONG).show();
                    break;
                case 8:
                    Toast.makeText(HomeActivity.this,titles[position],Toast.LENGTH_LONG).show();
                    //启动SettingActivity
                   //startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                    break;
            }
        }
    }

    private void showinputpwdDialog() {
            AlertDialog.Builder  builder = new  AlertDialog.Builder(this);

            View v= View.inflate(this,R.layout.inpwd_dialog,null);
            final EditText et_condialog_pwd= (EditText) v.findViewById(R.id.et_condialog_pwd);

            Button  bt_conpwddialog_cancle = (Button) v.findViewById(R.id.bt_conpwddialog_cancle);
            Button  bt_conpwddialog_confirm = (Button) v.findViewById(R.id.bt_conpwddialog_confirm);

            builder.setView(v);
            final AlertDialog dialog = builder.create();
            dialog.show();

            bt_conpwddialog_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pwd = et_condialog_pwd.getText().toString();

                    if (!pwd.isEmpty()){
                        String pwddigest =  Md5Utils.getMd5Digest(pwd);

                        //phonesafe_pwd 还是可能为空的。时间差
                        if (!phonesafe_pwd.isEmpty()){
                            if(phonesafe_pwd.equals(pwddigest)){
                                dialog.dismiss();
                                startActivity(new Intent(HomeActivity.this,PhoneSafeActivity.class));
                            }
                            else
                                Toast.makeText(HomeActivity.this, "输入密码有误，请重输！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(HomeActivity.this, "输入密码不能为空，请重输！", Toast.LENGTH_SHORT).show();

                }
            });

            bt_conpwddialog_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
    }

    private void showSetpwdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View v= View.inflate(this,R.layout.setpwd_dialog,null);
        final TextView et_dialog_pwd= (TextView) v.findViewById(R.id.et_dialog_pwd);
        final TextView et_dialog_pwdcon= (TextView) v.findViewById(R.id.et_dialog_pwdcon);

        Button bt_setpwddialog_confirm = (Button) v.findViewById(R.id.bt_setpwddialog_confirm);
        final Button  bt_setpwddialog_cancle = (Button) v.findViewById(R.id.bt_setpwddialog_cancle);


        builder.setView(v);
        final AlertDialog dialog = builder.create();
        dialog.show();


        bt_setpwddialog_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pwd = et_dialog_pwd.getText().toString();
                String pwdcon = et_dialog_pwdcon.getText().toString();

                if (!pwd.isEmpty() && !pwdcon.isEmpty()) {
                    //进入判断
                    if (pwd.equals(pwdcon)) {
                        MyApplication.setConfigValue("phonesafe_pwd", Md5Utils.getMd5Digest(pwd));
                        dialog.dismiss();
                    } else {
                        Toast.makeText(HomeActivity.this, "用户名或者密码不一致，请重新输入!", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(HomeActivity.this, "用户名或者密码不能为空!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_setpwddialog_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //GridView的 适配器
    class MyAdpter extends BaseAdapter{

        @Override
        public int getCount() {
            return CONTENT_NUM;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //GridView 优化
            // 方法1 ：复用convertview
            // 方法2 ：viewholder

            View v = View.inflate(HomeActivity.this,R.layout.item_gridview,null);
            ImageView iv_gv_icon = (ImageView) v.findViewById(R.id.iv_gv_icon);
            TextView tv = (TextView) v.findViewById(R.id.tv_gv_name);

            iv_gv_icon.setImageResource(iconarray[position]);
            tv.setText(titles[position]);
            return v;
        }
    }
}
