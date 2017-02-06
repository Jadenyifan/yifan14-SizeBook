package yifanwang.sizebook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.provider.Telephony.Mms.Part.FILENAME;
import static yifanwang.sizebook.showalldata.listofdata;

public class edit extends AppCompatActivity {

    private EditText name;

    private Button selectdate;
    private EditText neck;
    private EditText bust;
    private EditText chest;
    private EditText waist;
    private EditText hip;
    private EditText inseam;
    private EditText comment;

    private Button savenew;
    private Button delete;
    Data data;
    Integer num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle extras = getIntent().getExtras();
        if (extras!= null){
            num = extras.getInt("rank");
        }
        data = listofdata.get(num);




        name = (EditText) findViewById(R.id.editname);
        neck = (EditText) findViewById(R.id.neck);
        bust = (EditText) findViewById(R.id.bust);
        chest = (EditText) findViewById(R.id.chest);
        waist = (EditText) findViewById(R.id.waist);
        hip = (EditText) findViewById(R.id.hip);
        inseam = (EditText) findViewById(R.id.inseam);
        comment = (EditText) findViewById(R.id.comment);


        savenew = (Button) findViewById(R.id.savenew);
        delete = (Button) findViewById(R.id.delete);

        selectdate = (Button) findViewById(R.id.date);
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(edit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i1, int i2, int i3) {
                        System.out.println(String.format("%d-%d-%d",i1,i2+1,i3));
                        selectdate.setText(String.format("%d-%d-%d",i1,i2+1,i3));
                    }
                },2016,1,1).show();
                //code from http://blog.csdn.net/sinat_35132697/article/details/52852964
            }
        });

        name.setText(data.getName());
        neck.setText(data.getNeck());
        bust.setText(data.getBust());
        chest.setText(data.getChest());
        waist.setText(data.getWaist());
        hip.setText(data.getHip());
        inseam.setText(data.getInseam());
        comment.setText(data.getComment());
        selectdate.setText(data.getSelectdate());


        //set onclick of "SAVE" button
        savenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listofdata.remove(data);
                Data Data = new Data(name.getText().toString());

                if (name.getText().toString().length() < 1){
                    name.setError("Enter your name please~~~");
                }
                else{
                    Data.setName(name.getText().toString());
                    Data.setSelectdate(selectdate.getText().toString());
                    Data.setNeck(neck.getText().toString());
                    Data.setBust(bust.getText().toString());
                    Data.setChest(chest.getText().toString());
                    Data.setWaist(waist.getText().toString());
                    Data.setHip(hip.getText().toString());
                    Data.setInseam(inseam.getText().toString());
                    Data.setComment(comment.getText().toString());

                    listofdata.add(Data);
                    saveInFile();
                    finish();

                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listofdata.remove(data);
                saveInFile();
                finish();
            }
        });






    }
    //form lonelyTwitter https://github.com/Jadenyifan/git_cmput/blob/master/lonelyTwitter/lonelyTwitter/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
    private void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(listofdata, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }




}
