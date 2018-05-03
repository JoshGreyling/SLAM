package com.example.SLAM;


        import android.content.ContentValues;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;

public class lecturer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_activity);

        ContentValues params = new ContentValues();
        params.put("PASSWD","THE BOooooooooi");

        AsyncHTTPPost asyncHttpPost = new AsyncHTTPPost(
                "http://lamp.ms.wits.ac.za/~s1610338/DisplayAssign.php",params) {
            @Override
            protected void onPostExecute(String output) {
                Fill_List(output);
            }
        };
        asyncHttpPost.execute();
    }

    public void Fill_List(String output){
        ListView assign = (ListView)findViewById(R.id.lstView_Assign);
        assign.removeAllViews();
        ArrayList<String> values = new ArrayList<>();
        try{
            JSONArray items = new JSONArray(output);
            for (int i = 0; i < items.length(); ++i){
                JSONObject json = (JSONObject)items.get(i);
                values.add(json.getString(("text")));
            }
        } catch (JSONException e) {
            
            e.printStackTrace();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, values );
        assign.setAdapter(arrayAdapter);
    }


}