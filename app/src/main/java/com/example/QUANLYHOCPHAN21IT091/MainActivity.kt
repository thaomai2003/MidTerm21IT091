package com.example.QUANLYHOCPHAN21IT091

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.thigk21it270.R
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase
    lateinit var rs : Cursor
    lateinit var adapter: SimpleCursorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)

        var helper = MyHelper(applicationContext)
        db = helper.readableDatabase
        rs = db.rawQuery("SELECT * FROM HOCPHAN LIMIT 20",null)
        // xem danh sách học phần
        btnViewAll.setOnClickListener {
            val i = Intent(this,ListActivity::class.java)
            startActivity(i)
//            lvFull.visibility = View.VISIBLE
//            adapter.notifyDataSetChanged()
        }
        //đầu
        btnFirst.setOnClickListener {
            if (rs.moveToFirst()){
                maHP.setText(rs.getString(1))
                tenHP.setText(rs.getString(2))
                soTC.setText(rs.getString(3))
                loaiHP.setText(rs.getString(4))
            }
            else
                Toast.makeText(applicationContext,"Hết dữ liệu", Toast.LENGTH_SHORT).show()
        }
        //tiếp
        btnNext.setOnClickListener {
            if (rs.moveToNext()){
                maHP.setText(rs.getString(1))
                tenHP.setText(rs.getString(2))
                soTC.setText(rs.getString(3))
                loaiHP.setText(rs.getString(4))
            }
            else if (rs.moveToFirst()){
                maHP.setText(rs.getString(1))
                tenHP.setText(rs.getString(2))
                soTC.setText(rs.getString(3))
                loaiHP.setText(rs.getString(4))
            }
            else
                Toast.makeText(applicationContext,"Hết dữ liệu",Toast.LENGTH_SHORT).show()
        }
        //lùi
        btnPrev.setOnClickListener {
            if (rs.moveToPrevious()){
                maHP.setText(rs.getString(1))
                tenHP.setText(rs.getString(2))
                soTC.setText(rs.getString(3))
                loaiHP.setText(rs.getString(4))
            }
            else if (rs.moveToLast()){
                maHP.setText(rs.getString(1))
                tenHP.setText(rs.getString(2))
                soTC.setText(rs.getString(3))
                loaiHP.setText(rs.getString(4))
            }
            else
                Toast.makeText(applicationContext,"Hết dữ liệu",Toast.LENGTH_SHORT).show()
        }
        //thêm
        btnInsert.setOnClickListener {
            var cv = ContentValues()
            if (maHP.text.toString().isEmpty()||tenHP.text.toString().isEmpty()||soTC.text.toString().isEmpty()||loaiHP.text.toString().isEmpty()){
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Lỗi")
                ad.setMessage("Bạn phải nhập đầy đủ")
                ad.setPositiveButton("Chấp nhận", DialogInterface.OnClickListener { dialog, which ->
                })
                ad.show()
            }else if (soTC.text.toString()>=5.toString()){
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Lỗi")
                ad.setMessage("Bạn phải nhập số tính chỉ bé hơn 5")
                ad.setPositiveButton("Chấp nhận", DialogInterface.OnClickListener { dialog, which ->
                })
                ad.show()
            }else{
                cv.put("Mã học phần", maHP.text.toString())
                cv.put("Tên học phần", tenHP.text.toString())
                cv.put("Số tín chỉ", soTC.text.toString())
                cv.put("Loại học phần", loaiHP.text.toString())
                db.insert("HOCPHAN", null, cv)
                rs.requery()
                Toast.makeText(this,"Đã thêm vào danh sách",Toast.LENGTH_SHORT).show()
            }
        }
        //sửa
        btnUpdate.setOnClickListener {
            var cv = ContentValues()
            if (maHP.text.toString().isEmpty()||tenHP.text.toString().isEmpty()||soTC.text.toString().isEmpty()||loaiHP.text.toString().isEmpty()){
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Lỗi")
                ad.setMessage("Bạn phải nhập đầy đủ")
                ad.setPositiveButton("Chấp nhận", DialogInterface.OnClickListener { dialog, which ->
                })
                ad.show()
            }else if (soTC.text.toString()>=5.toString()){
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Lỗi")
                ad.setMessage("Bạn phải nhập số tín chỉ bé hơn 5")
                ad.setPositiveButton("Chấp nhận", DialogInterface.OnClickListener { dialog, which ->
                })
                ad.show()
            } else{
                cv.put("Mã học phần", maHP.text.toString())
                cv.put("Tên học phần", tenHP.text.toString())
                cv.put("Số tín chỉ", soTC.text.toString())
                cv.put("Loại học phần", loaiHP.text.toString())
                db.update("HOCPHAN",cv,"_id=?", arrayOf(rs.getString(0)))
                rs.requery()
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Cập nhật")
                ad.setMessage("Bạn có chắc cập thật thông tin ?")
                ad.setPositiveButton("Chấp nhận", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this,"Đã cập nhật thông tin",Toast.LENGTH_SHORT).show()
                    maHP.setText("")
                    tenHP.setText("")
                    soTC.setText("")
                    loaiHP.setText("")
                    maHP.requestFocus()
                })
                ad.show()
            }

        }
        //xóa
        btnDelete.setOnClickListener {
            db.delete("HOCPHAN","_id=?", arrayOf(rs.getString(0)))
            rs.requery()
//            adapter.notifyDataSetChanged()
            //thông báo
            var ad = AlertDialog.Builder(this)
            ad.setTitle("Xóa")
            ad.setMessage("Xóa khỏi danh sách")
            ad.setPositiveButton("Chấp nhận", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this,"Đã xóa thông tin",Toast.LENGTH_SHORT).show()
                if (rs.moveToFirst()){
                    maHP.setText("")
                    tenHP.setText("")
                    soTC.setText("")
                    loaiHP.setText("")
                    maHP.requestFocus()
                }
                else
                {
                    maHP.setText("hết dữ liệu")
                    tenHP.setText("hết dữ liệu")
                    soTC.setText("hết dữ liệu")
                    loaiHP.setText("hết dữ liệu")
                }

            })
            ad.show()
        }
    }
}