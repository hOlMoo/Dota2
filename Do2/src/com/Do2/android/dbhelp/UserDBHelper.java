package com.Do2.android.dbhelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 用户DB数据库
 * @author zhoushuai email: 657523985@qq.com
 * Date: 14-07-14
 * Time: 上午9:56
 */
public class UserDBHelper extends SQLiteOpenHelper{
	private final static String DB_NAME = "GATEWAY.db"; //数据库名
	private final static int DB_VERSION = 1; //数据库版本
	private final static String TABLE_NAME = "gateways_table"; //表名
	private final static String _ID = "_id"; //自增长的id
	private final static String GATEWAY_ID = "gateway_id" ; //网关id
	private final static String GATEWAY_NAME = "gateway_name"; //网关名称
	private final static String GATEWAY_IP = "gateway_ip";	//网关ip地址
	
	private String DB_CREAT = "CREATE TABLE "
			+ TABLE_NAME + "(" 
			+ _ID + " INTEGER PRIMARY KEY,"
			+ GATEWAY_ID + " TEXT,"
			+ GATEWAY_NAME + " TEXT,"
			+ GATEWAY_IP + " TEXT)";
	
	private SQLiteDatabase db;
	/**
	 * @param context 
	 * @param name 数据库名称
	 * @param factory 查询数据库的游标工厂,一般情况用sdk默认的
	 * @param version 数据库的版本 版本号必须大于1
	 */
	public UserDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		db = this.getWritableDatabase();
	}
	//在数据库第一次被创建的时候，会执行onCreat
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREAT);
	}
	//软件升级时改变版本号
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS" + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}
	//查询
	public Cursor select(){
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}
	//增加
	public long insert(String gatewayid, String gatewayname,String gatewayip){
		ContentValues cv = new ContentValues();
		cv.put(GATEWAY_ID, gatewayid);
		cv.put(GATEWAY_NAME, gatewayname);
		cv.put(GATEWAY_IP, gatewayip);
		long row = db.insert(TABLE_NAME, null, cv);
		return row;
	}
	//删除
	public void delete(int id){
		String where = _ID + " = ?";
		String[] whereValue = {Integer.toString(id)};
		db.delete(TABLE_NAME, where, whereValue);
	}
	//修改
	public void update(int id, String gatewayid, String gatewayname,String gatewayip){
		String where = _ID + " = ?";
		String[] whereValue = {Integer.toString(id)};
		ContentValues cv = new ContentValues();
		cv.put(GATEWAY_ID, gatewayid);
		cv.put(GATEWAY_NAME, gatewayname);
		cv.put(GATEWAY_IP, gatewayip);
		db.update(TABLE_NAME, cv, where, whereValue);
	}
	/**
	 * 关闭数据库
	 */
	public void close(){
		if (null != db) {
			db.close();
		}
	}
}
