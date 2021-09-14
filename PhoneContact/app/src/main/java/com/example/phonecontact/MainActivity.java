package com.example.phonecontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mContactList = new ArrayList<>();
    public static final int REQ_CODE_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAdapter();
        checkContactPermission();
    }

    private void initView() {
        listView = findViewById(R.id.contact_list);
    }

    private void initAdapter() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mContactList);
        listView.setAdapter(mAdapter);
    }

    private void checkContactPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQ_CODE_CONTACT);
        }
        else {
            query();
        }
    }

    private void query() {
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        Log.d("SmallLetters", ContactsContract.CommonDataKinds.Phone.CONTENT_URI.toString());
        if(cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                mContactList.add("name:" + name + " phone-number:" + number);
            }
            mAdapter.notifyDataSetChanged();
            cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQ_CODE_CONTACT && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            query();
        }
        else {
            Toast.makeText(this, "未获取到联系人权限", Toast.LENGTH_SHORT).show();
        }
    }
}