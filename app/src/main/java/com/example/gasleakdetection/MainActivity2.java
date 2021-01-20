package com.example.gasleakdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.macroyau.thingspeakandroid.model.ChannelFeed;


//import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.model.ChannelFeed;
public class MainActivity2 extends AppCompatActivity {

    ThingSpeakChannel tsChannel ;
    Button botton;
    ProgressBar PB;
    TextView dataReceived;
    ProgressBar PB2;
    TextView dataReceived2;
    HorizontalScrollView HSView;
    LinearLayout LinLayHScroll;
    long CHANNEL_ID= 1277610;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        getSupportActionBar().hide();
        dataReceived = (TextView) findViewById(R.id.dataReceived);
        PB = (ProgressBar) findViewById(R.id.progressBar);
        PB.setProgress(0);
        dataReceived2 = (TextView) findViewById(R.id.dataReceived4);
        PB2 = (ProgressBar) findViewById(R.id.progressBar4);
        PB2.setProgress(0);
        tsChannel = new ThingSpeakChannel(CHANNEL_ID);
        botton= (Button) findViewById(R.id.button);
        HSView = findViewById(R.id.Hscroll);
        LinLayHScroll = findViewById(R.id.LinHscroll);
        tsChannel.loadChannelFeed();

        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String per = tsChannel.loadLastEntryInChannelFeed();
                if(per!=null) {
                    Log.v("ss",per+"");
                    String[] data = per.split(" ");
                    int num = Integer.parseInt(data[0]);
                    dataReceived.setText(data[0]);
                    PB.setProgress(num);
                    num = Integer.parseInt(data[1]);
                    dataReceived2.setText("Servo "+data[1]);
                    PB2.setProgress(num);
                    addGasReading(data);
                }
            }
        });




    }
    private  void  addGasReading(String[] data){
        TextView tv = new TextView(getApplicationContext());
        tv.setText(data[0] + "\n" + data[2]);
        HSView.addView(tv);
    }

}
