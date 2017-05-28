package ezzetech.com.newsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import ezzetech.com.newsdemo.adapters.NewsAdapter;
import ezzetech.com.newsdemo.model.NewsItem;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewNewsDemo;
    List<NewsItem> nItem = new ArrayList<>();
    NewsAdapter mAdapter;
    private String fullURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullURL = "http://amreenit.com/bdshorts/mobile-app/news/list?language=bn";
        initializedFields();

        parseNewsData();
    }

    private void initializedFields() {
        recyclerViewNewsDemo = (RecyclerView) findViewById(R.id.rv_newsDemo);
        mAdapter = new NewsAdapter(nItem, MainActivity.this);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(MainActivity.this);
        recyclerViewNewsDemo.setAdapter(mAdapter);
        recyclerViewNewsDemo.setLayoutManager(mLayout);
    }

    private void parseNewsData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, fullURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray newsArr = jsonObject.getJSONArray("news");
                            if (newsArr.length() > 0) {
                                for (int i = 0; i < newsArr.length(); i++) {
                                    JSONObject newsObj = newsArr.getJSONObject(i);
                                    String ntitle = newsObj.getString("title");
                                    String ndetails = newsObj.getString("details");
                                    String nmediatype = newsObj.getString("media_type");
                                    String nsourceLink = newsObj.getString("link");
                                    String nprovidername = newsObj.getString("provider_name");

                                    String imgLink = "http://amreenit.com/";
                                    try{
                                        JSONArray imgArr = newsObj.getJSONArray("images");
                                        JSONObject imgObj = imgArr.getJSONObject(0);
                                        imgLink = imgObj.getString("location");
                                    }catch (JSONException e){
                                        imgLink =  newsObj.getString("images");
                                    }

                                    NewsItem newsItem = new NewsItem();
                                    newsItem.setNewsTitle(ntitle);
                                    newsItem.setNewsDetails(ndetails);
                                    newsItem.setMediaType(nmediatype);
                                    newsItem.setSourceLink(nsourceLink);
                                    newsItem.setProviderName(nprovidername);
                                    newsItem.setImageofNews(imgLink);

                                    nItem.add(newsItem);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Internet Problem Occour", Toast.LENGTH_LONG).show();
                    }
                }) {


        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}
