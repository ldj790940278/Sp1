package org.csa.sharedpreferences;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText nameEditext = null;
	private EditText ageEditText = null;

	@Override
	public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		nameEditext = (EditText) this.findViewById(R.id.nameEditext);// 得到显示控件
		ageEditText = (EditText) this.findViewById(R.id.ageEditext);

		Map<String, String> preferences = openPrefercences();// 用Map接收从保存的文件中读取出来的数据

		nameEditext.setText(preferences.get("name")+"刚出炉的");// 将数据从Map中拿出了放到显示控件上
		ageEditText.setText(preferences.get("age")+"刚出炉的");
	}

	/**
	 * 点击保存按钮执行此方法
	 * 
	 * @param v
	 */
	public void save(View v) {
		savePreferences(nameEditext.getText().toString(), ageEditText.getText()
				.toString());// 调用保存数据方法
		Toast.makeText(getApplicationContext(), R.string.success,
				Toast.LENGTH_LONG).show();// 提示保存成功
	}

	/**
	 * 保存数据
	 * 
	 * @param name
	 * @param age
	 */

	private void savePreferences(String name, String age) {
		// 通过上下文得到SharedPreferences实例，csa为保存的XML文件名称，Context.MODE_PRIVATE为文件操作模式
		SharedPreferences sharedPreferences = this.getSharedPreferences("csa",
				Context.MODE_PRIVATE);

		Editor editor = sharedPreferences.edit(); // 得到编辑器

		editor.putString("name", name);
		editor.putString("age", age); // 往编辑器中放入数据

		editor.commit();// 提交数据
	}

	/**
	 * 拿出保存的数据
	 * 
	 * @return
	 */
	private Map<String, String> openPrefercences() {
		Map<String, String> preferences = new HashMap<String, String>();

		SharedPreferences sharedPreferences = this.getSharedPreferences("csa",
				Context.MODE_PRIVATE);

		String name = sharedPreferences.getString("name", "");// 第二个参数是当第一个参数name的值为空的时候，显示空第二个参数的值
		String age = sharedPreferences.getString("age", String.valueOf(0));

		preferences.put("name", name);// 将拿出来的数据放到Map中
		preferences.put("age", age);

		return preferences;
	}
}