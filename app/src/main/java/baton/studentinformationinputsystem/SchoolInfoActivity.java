package baton.studentinformationinputsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class SchoolInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayAdapter<String> adapter;
    private Spinner school_sp;
    private String school;
    private EditText major_edit,stu_class_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info);
        String data[] = getResources().getStringArray(R.array.schoollist);
        major_edit = (EditText)findViewById(R.id.major_edit);
        stu_class_edit = (EditText)findViewById(R.id.stu_class_edit);
        adapter = new ArrayAdapter<>(SchoolInfoActivity.this,android.R.layout.simple_list_item_1,data);
        school_sp = (Spinner)findViewById(R.id.schoollist_sp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        school_sp .setAdapter(adapter);
        school_sp .setOnItemSelectedListener(this);
        //AutoFill();
        Button b_next = (Button)findViewById(R.id.next);
        /**
         * 下一步按钮的响应函数
         * 将新建的学生信息实体类对象通过intent传递给OtherInfoActivity
         */
        b_next .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentInfoEntities studentInfoEntities = Init_studentinfo();
                Intent intent = new Intent(SchoolInfoActivity.this,OtherInfoActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("student_2", studentInfoEntities);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }
    /**
     * 当被选中时更新学院信息
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        school=adapter.getItem(position);   //获取选中的那一项
    }

    /**
     * 当没有Item被选中时不做任何事
     */
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    /**
     * 初始化一个学生信息，其中部分信息来自Intent
     * @return 一个带有学号、身份证号、姓名、性别、籍贯、生日、学院、专业、班级的学生实体对象
     */
    public StudentInfoEntities Init_studentinfo()
    {
        StudentInfoEntities studentInfoEntities = (StudentInfoEntities)getIntent().getSerializableExtra("student_1");
        studentInfoEntities.setSchool(school);
        studentInfoEntities.setMajor(major_edit.getText().toString());
        studentInfoEntities.setStudent_class(stu_class_edit.getText().toString());
        return studentInfoEntities;
    }

    /**
     * 自动填写，仅供测试用
     */
    public void AutoFill()
    {
        major_edit.setText("物联网工程");
        stu_class_edit.setText("16060616");
        school_sp.setSelection(5);
    }
}
