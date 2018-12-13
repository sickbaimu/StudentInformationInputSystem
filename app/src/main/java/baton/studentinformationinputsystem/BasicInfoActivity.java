package baton.studentinformationinputsystem;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicInfoActivity extends AppCompatActivity {

    Button b_next;
    EditText stu_id_edit,name_edit,id_number_edit,
            year_birthday_edit, month_birthday_edit, day_birthday_edit
            ;
    static EditText native_place_edit;
    RadioGroup sex_radiogroutp;

    public static void setNative_place_edit(String content) {
        native_place_edit.setText(content);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        b_next = (Button) findViewById(R.id.next);
        stu_id_edit = (EditText)findViewById(R.id.stu_id_edit);
        name_edit = (EditText)findViewById(R.id.name_edit);
        id_number_edit = (EditText) findViewById(R.id.id_number_edit);
        sex_radiogroutp = (RadioGroup)findViewById(R.id.sex_radiogroup);
        year_birthday_edit = (EditText) findViewById(R.id.year_birthday_edit);
        month_birthday_edit = (EditText) findViewById(R.id.month_birthday_edit);
        day_birthday_edit = (EditText) findViewById(R.id.day_birthday_edit);
        native_place_edit = (EditText)findViewById(R.id.native_place_edit);
        //AutoFill();//自动填写，仅供测试用
        id_number_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}//变化前
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}//变化时
            /**
             * 文本内容发生变化后的响应函数
             * 若身份证号到达18位，自动匹配籍贯和生日
             */
            @Override
            public void afterTextChanged(Editable editable) {
                if (id_number_edit.getText().toString().length() == 18)//若身份证号达到18位，自动匹配
                {
                    year_birthday_edit.setText(id_number_edit.getText().toString().substring(6, 10));
                    month_birthday_edit.setText(id_number_edit.getText().toString().substring(10, 12));
                    day_birthday_edit.setText(id_number_edit.getText().toString().substring(12, 14));
                    Intent intent = new Intent(BasicInfoActivity.this,NativePlaceService.class);
                    intent.putExtra("code",id_number_edit.getText().toString().substring(0, 6));
                    startService(intent);
                }
            }
        });

        /**
         * 下一步按钮的响应函数
         * 将新建的学生信息实体类对象通过intent传递给SchoolInfoActivity
         */
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasicInfoActivity.this, SchoolInfoActivity.class);
                StudentInfoEntities studentInfoEntities = Init_studentinfo();
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("student_1", studentInfoEntities);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化一个学生信息
     * @return 一个带有学号、身份证号、姓名、性别、籍贯和生日的学生实体对象
     */
    public StudentInfoEntities Init_studentinfo()
    {
        /*新建学生对象*/
        StudentInfoEntities studentInfoEntities= new StudentInfoEntities();
        /*设置学号*/
        studentInfoEntities.setStudent_id(stu_id_edit.getText().toString());
        /*设置身份证号*/
        studentInfoEntities.setId_number(id_number_edit.getText().toString());
        /*设置姓名*/
        studentInfoEntities.setName(name_edit.getText().toString());
        /*设置性别*/
        if(sex_radiogroutp.getCheckedRadioButtonId()==R.id.man)
            studentInfoEntities.setSex("男");
        else if(sex_radiogroutp.getCheckedRadioButtonId()==R.id.woman)
            studentInfoEntities.setSex("女");
        /*设置籍贯*/
        studentInfoEntities.setNative_place(native_place_edit.getText().toString());
        /*设置生日*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        Date date =new Date();
        try{
            date = dateFormat.parse(year_birthday_edit.getText().toString()+"-"+month_birthday_edit.getText().toString()+"-"+day_birthday_edit.getText().toString());
        }catch (ParseException e) {
            e.printStackTrace();
        }
        studentInfoEntities.setBirthday(date);
        return studentInfoEntities;
    }

    /**
     * 自动填写，仅供测试用
     */
    public void AutoFill()
    {
        stu_id_edit.setText("160606180");
        id_number_edit.setText("35030119890618006");
        name_edit.setText("张萌萌");
    }
}

