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

		nameEditext = (EditText) this.findViewById(R.id.nameEditext);// �õ���ʾ�ؼ�
		ageEditText = (EditText) this.findViewById(R.id.ageEditext);

		Map<String, String> preferences = openPrefercences();// ��Map���մӱ�����ļ��ж�ȡ����������

		nameEditext.setText(preferences.get("name")+"�ճ�¯��");// �����ݴ�Map���ó��˷ŵ���ʾ�ؼ���
		ageEditText.setText(preferences.get("age")+"�ճ�¯��");
	}

	/**
	 * ������水ťִ�д˷���
	 * 
	 * @param v
	 */
	public void save(View v) {
		savePreferences(nameEditext.getText().toString(), ageEditText.getText()
				.toString());// ���ñ������ݷ���
		Toast.makeText(getApplicationContext(), R.string.success,
				Toast.LENGTH_LONG).show();// ��ʾ����ɹ�
	}

	/**
	 * ��������
	 * 
	 * @param name
	 * @param age
	 */

	private void savePreferences(String name, String age) {
		// ͨ�������ĵõ�SharedPreferencesʵ����csaΪ�����XML�ļ����ƣ�Context.MODE_PRIVATEΪ�ļ�����ģʽ
		SharedPreferences sharedPreferences = this.getSharedPreferences("csa",
				Context.MODE_PRIVATE);

		Editor editor = sharedPreferences.edit(); // �õ��༭��

		editor.putString("name", name);
		editor.putString("age", age); // ���༭���з�������

		editor.commit();// �ύ����
	}

	/**
	 * �ó����������
	 * 
	 * @return
	 */
	private Map<String, String> openPrefercences() {
		Map<String, String> preferences = new HashMap<String, String>();

		SharedPreferences sharedPreferences = this.getSharedPreferences("csa",
				Context.MODE_PRIVATE);

		String name = sharedPreferences.getString("name", "");// �ڶ��������ǵ���һ������name��ֵΪ�յ�ʱ����ʾ�յڶ���������ֵ
		String age = sharedPreferences.getString("age", String.valueOf(0));

		preferences.put("name", name);// ���ó��������ݷŵ�Map��
		preferences.put("age", age);

		return preferences;
	}
}