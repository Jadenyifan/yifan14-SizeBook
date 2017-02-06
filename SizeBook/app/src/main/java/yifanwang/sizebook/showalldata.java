package yifanwang.sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class showalldata extends AppCompatActivity {
    public static ListView showdata;
    public static ArrayAdapter<Data> adapter;
    public static ArrayList<Data> listofdata = new ArrayList<Data>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showalldata);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(showalldata.this, MainActivity.class));
            }
        });

        //listofdata = new ArrayList<Data>();
        showdata = (ListView) findViewById(R.id.haha);

        //showdata.setAdapter(adapter);
        showdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(showalldata.this,edit.class);
                i.putExtra("rank", position);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        //learn from http://stackoverflow.com/questions/20290735/java-lang-classcastexception-android-widget-linearlayout-cannot-be-cast-to-andr
        adapter = new ArrayAdapter<Data>(this, R.layout.show,R.id.textView, listofdata);
        showdata.setAdapter(adapter);
    }



    //form lonelyTwitter https://github.com/Jadenyifan/git_cmput/blob/master/lonelyTwitter/lonelyTwitter/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Data>>(){}.getType();

            listofdata = gson.fromJson(in,listType);
            fis.close();

        } catch (FileNotFoundException e) {
            listofdata= new ArrayList<Data>();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
