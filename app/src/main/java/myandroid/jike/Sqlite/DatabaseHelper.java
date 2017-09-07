package myandroid.jike.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context,"AttentionList", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     sqLiteDatabase.execSQL("create table if not exists AttentionListData" +
             "(_id integer primary key autoincrement," +
             "item varchar)");
        Log.e("TAG","create");
    }

    public boolean insertData(List<String> list){
        SQLiteDatabase database = getWritableDatabase();

        if(list.size()<=0){
            return false;
        }else{
            for(int j = 0;j < list.size();j++){
                ContentValues contentValues = new ContentValues();
                contentValues.put("item",list.get(j));
                //Log.e("TAG",list.get(i));
                database.insert("AttentionListData",null,contentValues);
            }
        }
        Log.e("TAG","保存成功");
        return true;
    }


    //用数据库获取关注的列表
    public List<String> getAttentionList(){
       SQLiteDatabase database = getWritableDatabase();
        List<String> stringList = new ArrayList<>();
        String s;
        Cursor cursor = database.query("AttentionListData",null,null,null,null,null,"item "+"ASC");

        if(cursor != null){   //开始查找
            while(cursor.moveToNext()){
                s = cursor.getString(cursor.getColumnIndex("item"));
                stringList.add(s);
            }
            cursor.close();
        }
        Log.e("TAG",stringList.toString());
        return stringList;
    }

    public Cursor getAllData(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query("AttentionListData",null,null,null,null,null,"item "+"ASC");
    }

    public void deleteAllData(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete("AttentionListData",null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
