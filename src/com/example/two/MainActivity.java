package com.example.two;




import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends Activity {
	private Button cityBtn, registerBtn;
	private EditText name, psd, psd2, city;
	private RadioButton male;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cityBtn=(Button)findViewById(R.id.cityBtn);
        registerBtn=(Button)findViewById(R.id.registerBtn);
        name=(EditText)findViewById(R.id.name);
        psd=(EditText)findViewById(R.id.psd);
        psd2=(EditText)findViewById(R.id.psd2);
        city=(EditText)findViewById(R.id.city);
        male=(RadioButton)findViewById(R.id.male);
        registerBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String checkResult=checkInfo();
				if(checkResult!=null)
				{
					Builder builder =new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("出错提示");
					builder.setMessage(checkResult);
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							psd.setText("");
							psd2.setText("");							
						}
					});
					builder.create().show();
				}
				else{
					Intent intent=new Intent(MainActivity.this,ResultActivity.class);
					intent.putExtra("name", name.getText().toString());
					intent.putExtra("psd", psd.getText().toString());
					String gender=male.isChecked()?"男":"女";
					intent.putExtra("gender", gender);
					intent.putExtra("city",city.getText().toString());
					startActivity(intent);
				}
			}
		});
        cityBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ChooseCityActivity.class);
				startActivityForResult(intent , 0);					
			
		}      
	    });   
	}
        
        
	
	    public String checkInfo() {
		System.out.println(name);
		if (name.getText().toString() == null||name.getText().toString().equals("")) {
			System.out.println("***********");
			return "用户名不能为空";
		}
		if (psd.getText().toString().trim().length() < 6
				|| psd.getText().toString().trim().length() > 15){
			return "密码位数应该6~15之间";
		}
		if(!psd.getText().toString().equals(psd2.getText().toString())){
			return "两次输入的密码不一致";
		}
			return null;
	    }
	    
	    
	    public void onActivityResult(int requestCode , int resultCode, Intent intent){
			if (requestCode == 0&& resultCode == 0){
				Bundle data = intent.getExtras();
				String resultCity = data.getString("city");
				city.setText(resultCity);
			}
	    }
}

	  
