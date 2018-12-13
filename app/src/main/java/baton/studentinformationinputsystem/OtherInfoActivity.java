package baton.studentinformationinputsystem;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class OtherInfoActivity extends AppCompatActivity {

    EditText phone_eidt,email_eidt,weixin_edit;
    CheckBox cb_music,cb_art,cb_handwriting,cb_basketball,cb_football,cb_swimming;
    Button b_commit;
    MyBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);
        phone_eidt = (EditText)findViewById(R.id.phone_edit);
        email_eidt = (EditText)findViewById(R.id.email_edit);
        weixin_edit = (EditText)findViewById(R.id.weixin_edit);
        cb_music = (CheckBox)findViewById(R.id.music_cb);
        cb_art = (CheckBox)findViewById(R.id.art_cb);
        cb_handwriting = (CheckBox)findViewById(R.id.handwriting_cb);
        cb_basketball = (CheckBox)findViewById(R.id.basketball_cb);
        cb_football = (CheckBox)findViewById(R.id.football_cb);
        cb_swimming = (CheckBox)findViewById(R.id.swimming_cb);

        //AutoFill();

        IntentFilter filter = new IntentFilter();
        filter.addAction("MY_BROADCAST");
        mReceiver = new MyBroadcastReceiver();
        this.registerReceiver(new MyBroadcastReceiver(), filter);

        b_commit = (Button)findViewById(R.id.commit);
        /**
         * 提交按钮的响应函数
         * 将新建的学生信息实体类对象发送广播
         */
        b_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentInfoEntities entity = Init_studentinfo();
                Intent intent = new Intent("MY_BROADCAST");
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("info",GetInfo(entity));
                mBundle.putSerializable("entity",entity);
                intent.putExtras(mBundle);
                sendBroadcast(intent);
            }
        });
    }

    /**
     * 自动填写，仅供测试用
     */
    public void AutoFill(){
        phone_eidt.setText("15339171103");
        email_eidt.setText("zhangmengmeng@xatu.edu.cn");
        weixin_edit.setText("15339171103");
        cb_music.setChecked(true);
        cb_handwriting.setChecked(true);
        cb_football.setChecked(true);
        cb_swimming.setChecked(true);
    }

    /**
     * 初始化一个学生信息，其中部分信息来自Intent
     * @return 一个带有学号、身份证号、姓名、性别、籍贯、生日、学院、专业、班级、电话、电子邮箱、微信、特长的学生实体对象
     */
    public StudentInfoEntities Init_studentinfo()
    {
        /*获取SchoolActivity传递来的一个学生实体对象*/
        StudentInfoEntities studentInfoEntities =(StudentInfoEntities)getIntent().getSerializableExtra("student_2");
        /*设置电话*/
        studentInfoEntities.setPhone(phone_eidt.getText().toString());
        /*设置电子邮箱*/
        studentInfoEntities.setEmail(email_eidt.getText().toString());
        /*设置微信*/
        studentInfoEntities.setWeixin_number(weixin_edit.getText().toString());
        /*设置特长*/
        studentInfoEntities.setSpecial_skill(GetSpecial_skill());
        return studentInfoEntities;
    }

    /**
     * @return 根据用户筛选的特长，之间用顿号隔开
     */
    public String GetSpecial_skill()
    {
        String special_skill = "";
        if(cb_music.isChecked())
            special_skill += "音乐、";
        if(cb_art.isChecked())
            special_skill += "艺术、";
        if(cb_handwriting.isChecked())
            special_skill += "书法、";
        if(cb_basketball.isChecked())
            special_skill += "篮球、";
        if(cb_football.isChecked())
            special_skill += "足球、";
        if(cb_swimming.isChecked())
            special_skill += "游泳、";
        if(special_skill.equals(""))
        {
            special_skill="无";
        }
        else
            special_skill = special_skill.substring(0,special_skill.length()-1);//去掉最后一位的顿号
        return  special_skill;
    }
    /**
     * 通过一个学生信息实体类对象转化为对应字符串形式
     * @param studentInfoEntities 学生信息实体类对象
     * @return 学生信息实体类的字符串形式
     */
    public String GetInfo(StudentInfoEntities studentInfoEntities)
    {
        String info = "";
        info +="姓名："+studentInfoEntities.getName()+"\n";
        info +="学号："+studentInfoEntities.getStudent_id()+"\n";
        info +="身份证号："+studentInfoEntities.getId_number()+"\n";
        info +="性别："+studentInfoEntities.getSex()+"\n";
        info +="籍贯："+studentInfoEntities.getNative_place()+"\n";
        String birthday[] = studentInfoEntities.getBirthdaybyString().split("-");
        info +="生日："+birthday[0]+"年"+birthday[1]+"月"+birthday[2]+"日"+"\n";
        info +="所在学院："+studentInfoEntities.getSchool()+"\n";
        info +="专业："+studentInfoEntities.getMajor()+"\n";
        info +="班级："+studentInfoEntities.getStudent_class()+"\n";
        info +="电话："+studentInfoEntities.getPhone()+"\n";
        info +="电子邮箱："+studentInfoEntities.getEmail()+"\n";
        info +="微信号："+studentInfoEntities.getWeixin_number()+"\n";
        info +="特长："+studentInfoEntities.getSpecial_skill();
        return info;
    }
    /**
     * 内部类，自定义广播，弹出学生信息
     */
    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public  void  onReceive(Context context, Intent intent)
        {
            String info = intent.getStringExtra("info");
            StudentInfoEntities entity = (StudentInfoEntities)intent.getSerializableExtra("entity");
            MyDialogFragment myDialogFragment = new MyDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", info);
            bundle.putSerializable("entity", entity);
            myDialogFragment.setArguments(bundle);
            myDialogFragment.show(getFragmentManager(), "Dialog");
        }
    }
    /*
     *广播随着Activity销毁而注销
     */
    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
        }
}



