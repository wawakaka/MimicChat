package com.example.wawakaka.mimicchat;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText editText;


    private ArrayList<Message> messageItem = new ArrayList<Message>();
    private MessagesListAdapter messagesListAdapter;
    private ListView listMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set toolbar
        toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        //initialize
        editText = (EditText)findViewById(R.id.editText);
        listMessage = (ListView)findViewById(R.id.listMessage);


        //check if internet connection avaliable
        if (InternetConnection.checkConnection(getApplicationContext())) {
            new GetDataTask().execute();
            messagesListAdapter = new MessagesListAdapter(this,messageItem);
            listMessage.setAdapter(messagesListAdapter);
        }else {
            Toast.makeText(MainActivity.this, "not connected", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    class GetDataTask extends AsyncTask<Void,Void,Void>{
        ProgressDialog progressDialog;
        // make progress dialog for handling network bottleneck
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            JSONArray jsonArray = JSONParser.getArray();
            try{
                if(jsonArray != null){
                    int length = jsonArray.length();
                    if(length > 0){
                       for(int i = 0; i < length; i++){
                           Message message = new Message();

                           //parsing json array
                           JSONObject jsonObject = jsonArray.getJSONObject(i);

                           String sender = jsonObject.getString("sender");
                           String content = jsonObject.getString("message");
                           boolean is_me = jsonObject.getBoolean("is_from_me");
                           String time = jsonObject.getString("time");

                           //parsing time
                           Date dIn = null;
                           SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
                           dIn = inFormat.parse(time);
                           SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
                           String dtOut = outFormat.format(dIn);
                           Log.d("time after ",""+dtOut);


                           //extracting url
                           String regex = Patterns.WEB_URL.toString();
                           Matcher m = Pattern.compile(regex).matcher(content);

                           if (m.find()) {
                               String url = m.group();

                               Document document = Jsoup.connect(url).get();
                               String urlTitle = document.title();
                               String description = document.select("meta[name=description]")
                                       .get(0)
                                       .attr("content");


                               message.setWebTitle(urlTitle);
                               message.setWebDesc(description);


                               Log.d("url",""+url);
                               Log.d("url title",""+urlTitle);
                               Log.d("url description",""+description);
                           }

                           //setdata
                           message.setSender(sender);
                           message.setMessage(content);
                           message.setIs_from_me(is_me);
                           message.setTime(dtOut);


                           messageItem.add(message);
                       }
                    }
                }
                Log.d("do in background ","finsh parsing data");

                return null;
            }catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // closing progress dialog after adding data to the arraylist
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            if(messageItem.size()>0){
                messagesListAdapter.notifyDataSetChanged();
            }else {
                Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG).show();
            }
        }
    }
}
