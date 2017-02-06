package yifanwang.sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.provider.Telephony.Mms.Part.FILENAME;




public class MainActivity extends AppCompatActivity {


    private TextView tv;
    public static ArrayList<Data> listofdata = new ArrayList<Data>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.addnew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, addnew.class));
            }
        });
        findViewById(R.id.showalldata).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, showalldata.class));
            }
        });


        tv = (TextView) findViewById(R.id.textView2);
        Integer x = listofdata.size();
        tv.setText("The number of records exist—————>  "+x);

    }



}
