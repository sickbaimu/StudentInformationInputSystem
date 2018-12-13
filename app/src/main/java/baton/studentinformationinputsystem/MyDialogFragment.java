package baton.studentinformationinputsystem;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MyDialogFragment extends DialogFragment implements android.content.DialogInterface.OnClickListener{
    TextView content;
    Button b_sure,b_cancel;
    String info = "";
    StudentInfoEntities entity;
    private MyDatabaseHelper dbHelper;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /*初始化界面*/
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog,null);
        content = (TextView)view.findViewById(R.id.content);
        b_sure = (Button)view.findViewById(R.id.sure);
        b_cancel = (Button)view.findViewById(R.id.cancel);
        dbHelper = new MyDatabaseHelper(getActivity(),"StudentInfo.db",null,1);
        builder.setView(view);
        /*传入学生信息内容*/
        Bundle bundle = getArguments();
        if (bundle != null) {
            info = (String)bundle.getSerializable("info");
            entity = (StudentInfoEntities)bundle.getSerializable("entity");
            content.setText(info);
        }
        /**
         * 确定按钮的响应函数
         * 将新建的学生信息实体类对象发送广播
         */
        b_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("name",entity.getName());
                values.put("student_id",entity.getStudent_id());
                values.put("id_number",entity.getId_number());
                values.put("sex",entity.getSex());
                values.put("native_place",entity.getNative_place());
                values.put("birthday",entity.getBirthdaybyString());
                values.put("school",entity.getSchool());
                values.put("major",entity.getMajor());
                values.put("student_class",entity.getStudent_class());
                values.put("phone",entity.getPhone());
                values.put("email",entity.getEmail());
                values.put("weixin_number",entity.getWeixin_number());
                values.put("special_skill",entity.getSpecial_skill());
                db.insert("Student",null,values);
                values.clear();
                dismiss();
                Toast.makeText(getActivity(),"成功添加学生信息",Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 取消按钮的响应函数
         * 将新建的学生信息实体类对象发送广播
         */
        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                startActivity(new Intent(getActivity(),BasicInfoActivity.class));
            }
        });
        return builder.create();
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
