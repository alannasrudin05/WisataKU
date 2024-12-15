package com.praktikum.wisataku;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //    implementasi di modul 7 listView dan Autocomplete
//        autoCompleteText = findViewById(R.id.auto_complete_txt);
//
//        ArrayAdapter adapterItems = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dataGender);
//
//        autoCompleteText.setAdapter(adapterItems);
//
//        autoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
//            }
//        });
        //    End implementasi di modul 7 listView dan Autocomplete


    }

    //    implementasi di modul 7 listView dan Autocomplete
//    public void gender(View v){
//        List = (EditText) findViewById(R.id.dataListGender);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
////        builder.setTitle("Data Merk Motor");
//        builder.setItems(dataGender, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int item) {
//                List.setText(dataGender[item]);
//                dialogInterface.dismiss();
//            }
//        }).show();
//
//    }

}