package sg.rp.edu.rp.c346.id22038845.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lvResults;
    EditText et1;
    EditText et2;

    //ver 1:
    ArrayList<String> strList;

    //below is use with ver 2
    //ArrayList<Task> strList;
    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks =findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lvResults = findViewById(R.id.lv);
        et1 = findViewById(R.id.etDate);
        et2 = findViewById(R.id.etDescript);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create dbHelper Object, passing in the activity's context
                DBHelper db = new DBHelper(MainActivity.this);

                //insert task
                db.insertTask(et2.getText().toString() ,  et1.getText().toString());
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the dbhelper object, passing in the activity's context
                DBHelper db = new DBHelper(MainActivity.this);

                //displaying both arraylist and arrayadapter ver 1.
                ArrayList<Task> data = db.getTasks(asc);
                asc = !asc;
                strList = new ArrayList<String>();
                ArrayAdapter listStr = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,strList);
                lvResults.setAdapter(listStr);
                db.close();

                String txt = "";
                String m =" ";
                int n = 0;

                for(int i = 0; i<data.size(); i++){

                    for (int j = 0; j < data.size(); j++) {
                        Task task = data.get(j);

                        if (i == j) {
                            m = task.getDescription();
                            n = task.getId();
                        }
                    }
                    Log.d("Database Content", i + ". "+ data.get(i));
                    txt += n + ". " + m + "\n";
                }
                tvResults.setText(txt);
                for(int x = 0; x<data.size(); x++){
                    listStr.add(data.get(x));
                    listStr.notifyDataSetChanged();
                }



                //ver 2:
                /*ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                //LIST VIEW
                DBHelper db2 = new DBHelper(MainActivity.this);
                strList = db2.getTasks(asc);
                db2.close();
                asc = !asc;
                ArrayAdapter<String> strL = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, strList);
                lvResults.setAdapter(strL);*/

            }
        });

    }

}