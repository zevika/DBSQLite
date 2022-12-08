package com.zevika.dbsqlite;

import static com.zevika.dbsqlite.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

        DatabaseHelper myDb;

        EditText editNama, editKelas, editNohp, editTextnim;
        Button btnAddData;
        Button btnViewAll;
        Button btnUpdate;
        Button btnDelete;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(layout.activity_main);
            myDb = new DatabaseHelper(this);
            editNama = (EditText)findViewById(id.editTextNama);
            editKelas = (EditText)findViewById(id.editText_kelas);
            editNohp = (EditText)findViewById(id.editText_nohp);
            editTextnim = (EditText)findViewById(id.edittextnim);
            btnAddData = (Button)findViewById(id.button_add);
            btnViewAll = (Button)findViewById(id.button_view);
            btnUpdate = (Button)findViewById(id.button_update);
            btnDelete = (Button)findViewById(id.button_delete);
            AddData();
            viewAll();
            UpdateData();
            deleteData();
        }


        public void deleteData() {
            btnDelete.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer deletedRows = myDb.deleteData(editTextnim.getText().toString());
                            if (deletedRows > 0)
                                Toast.makeText(MainActivity.this,"Data De leted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this,"Data Failed to  Deleted!",Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }


        public void UpdateData() {
            btnUpdate.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isUpdate = myDb.updateData(editTextnim.getText().toString(),
                                    editNama.getText().toString(), editKelas.getText().toString(),
                                    editNohp.getText().toString());
                           // MODUL PEMROGRAMAN MOBILE I STMIK AMIK RIAU ILMU KOMPUTER
                            if(isUpdate == true)
                                Toast.makeText(MainActivity.this,"Data Up dated",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this,"Data Failed to  Update",Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }

        public void AddData() {
            btnAddData.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isInserted = myDb.insertData(editTextnim.getText().toString(),
                                    editNama.getText().toString(), editKelas.getText().toString(),
                                    editNohp.getText().toString() );
                            if(isInserted == true)
                                Toast.makeText(MainActivity.this,"Data  Iserted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this,"Data Not  Iserted",Toast.LENGTH_LONG).show();
                            editNama.setText("");
                            editKelas.setText("");
                            editTextnim.setText("");
                            editNohp.setText("");
                        }
                    }
            );
        }
        public void viewAll() {
            btnViewAll.setOnClickListener(
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Cursor res = myDb.getAllData();
                            if(res.getCount() == 0) {
                                // show message
                                showMessage("Error","Noting Found");
                                return;
                            }


                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext() ) {
                                buffer.append("NIM :"+ res.getString(0)+"\n");  buffer.append("NAMA :"+ res.getString(1)+"\n");  buffer.append("KELAS :"+ res.getString(2)+"\n");  buffer.append("NOHP :"+
                                                //MODUL PEMROGRAMAN MOBILE I STMIK AMIK RIAU ILMU KOMPUTER
                                        res.getString(3)+"\n\n");
                            }
// show all data
                            showMessage("Data Mahasiswa",buffer.toString());  }
                    }
            );
        }
        //membuat alert dialog
        public void showMessage(String title, String Message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);  builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
        }
    }




