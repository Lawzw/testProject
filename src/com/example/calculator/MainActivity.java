package com.example.calculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements android.view.View.OnClickListener{
	Button btn0;
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	Button btn8;
	Button btn9;
	Button btn_clear;
	Button btn_del;
	Button btn_equal;
	Button btn_dot;
	Button btn_plus;
	Button btn_minus;
	Button btn_multiply;
	Button btn_divide;
	Button btn_sqr;
	Button btn_mod;
	EditText et_input;
	boolean clear_flag;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsp);
       btn0 = (Button) findViewById(R.id.btn0);
       btn1 = (Button) findViewById(R.id.btn1);
       btn2 = (Button) findViewById(R.id.btn2);
       btn3 = (Button) findViewById(R.id.btn3);
       btn4 = (Button) findViewById(R.id.btn4);
       btn5 = (Button) findViewById(R.id.btn5);
       btn6 = (Button) findViewById(R.id.btn6);
       btn7 = (Button) findViewById(R.id.btn7);
       btn8 = (Button) findViewById(R.id.btn8);
       btn9 = (Button) findViewById(R.id.btn9);
       btn_clear = (Button) findViewById(R.id.btn_clear);
       btn_del = (Button) findViewById(R.id.btn_del);
       btn_equal = (Button) findViewById(R.id.btn_equal);
       btn_dot = (Button) findViewById(R.id.btn_dot);
       btn_plus = (Button) findViewById(R.id.btn_plus);
       btn_minus = (Button) findViewById(R.id.btn_minus);
       btn_multiply = (Button) findViewById(R.id.btn_multiply);
       btn_divide = (Button) findViewById(R.id.btn_divide);
       btn_sqr = (Button) findViewById(R.id.btn_sqr);
       et_input = (EditText) findViewById(R.id.et_input);//实例化显示屏
       
       btn0.setOnClickListener(this);
       btn1.setOnClickListener(this);
       btn2.setOnClickListener(this);
       btn3.setOnClickListener(this);
       btn4.setOnClickListener(this);
       btn5.setOnClickListener(this);
       btn6.setOnClickListener(this);
       btn7.setOnClickListener(this);
       btn8.setOnClickListener(this);
       btn9.setOnClickListener(this);
       btn_clear.setOnClickListener(this);
       btn_del.setOnClickListener(this);
       btn_divide.setOnClickListener(this);
       btn_dot.setOnClickListener(this);
       btn_multiply.setOnClickListener(this);
       btn_plus.setOnClickListener(this);
       btn_minus.setOnClickListener(this);
       btn_equal.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String str = et_input.getText().toString();
		switch(v.getId()){
		case R.id.btn0:
		case R.id.btn1:
		case R.id.btn2:
		case R.id.btn3:
		case R.id.btn4:
		case R.id.btn5:
		case R.id.btn6:
		case R.id.btn7:
		case R.id.btn8:
		case R.id.btn9:
		case R.id.btn_dot:	
			if(clear_flag){
				clear_flag=false;
				str="";
				et_input.setText("");
			}
			et_input.setText(str+((Button)v).getText());
			break;
		case R.id.btn_plus:
		case R.id.btn_minus:
		case R.id.btn_multiply:
		case R.id.btn_divide:
			if(clear_flag){
				clear_flag=false;
				str="";
				et_input.setText("");
			}
			et_input.setText(str+" "+((Button)v).getText()+" ");
			break;
		case R.id.btn_clear:
			clear_flag=false;
			str="";
			et_input.setText("");
			break;
		case R.id.btn_del:
			if(clear_flag){
				clear_flag=false;
				str="";
				et_input.setText("");
			}
			else if(str!=null&&!str.equals("")){
				et_input.setText(str.substring(0, str.length()-1));
			}
			break;
		case R.id.btn_equal:
			getResult();
			break;
		}
	}

	private void getResult(){
		String exp = et_input.getText().toString();
		if(exp==null||exp.equals("")){
			return;
		}
		if(!exp.contains(" ")){
			return;
		}
		if(clear_flag){
			clear_flag=false;
			return;
		}
		clear_flag=true;
		double result=0;
		String s1 = exp.substring(0, exp.indexOf(" "));  //运算符前面的字符串
		String op = exp.substring(exp.indexOf(" ")+1, exp.indexOf(" ")+2);//运算符
		String s2 = exp.substring(exp.indexOf(" ")+3);
		if(!s1.equals("")&&!s2.equals("")){
			double d1 = Double.parseDouble(s1);
			double d2 = Double.parseDouble(s2);
			if(op.equals("+")){
				result=d1+d2;
			}
			else if(op.equals("-")){
				result=d1-d2;
			}
			else if(op.equals("×")){
				result=d1*d2;
			}
			else if(op.equals("÷")){
				if(d2==0){
					result=0;
				}
				else{
					result=d1/d2;
				}
			}
			if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷"))
			{
				int r=(int)result;
				et_input.setText(r+"");
			}
			else{
				et_input.setText(result+"");
			}
		}
		else if(!s1.equals("")&&s2.equals("")){
			et_input.setText(exp);
		}
		else if(s1.equals("")&&!s2.equals("")){
			double d2 = Double.parseDouble(s2);
			if(op.equals("+")){
				result=0.0+d2;
			
			}
			else if(op.equals("-")){
				result=0.0-d2;
			}
			else if(op.equals("×")){
				result=0;
			}
			else if(op.equals("÷")){
					result=0;
			}
			if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷"))
			{
				int r=(int)result;
				et_input.setText(r+"");
			}
			else{
				et_input.setText(result+"");
			}
		}	
		else if(s1.equals("")&s2.equals("")){
			et_input.setText("");
		}
	}	
		
    
}
