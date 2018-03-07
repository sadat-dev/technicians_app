package com.mybeats.fixkoro;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mybeats.fixkoro.Adapter.TechListAdapter;
import com.mybeats.fixkoro.Model.TechListModel;
import com.mybeats.fixkoro.util.AppController;
import com.mybeats.fixkoro.util.ConstantStuffs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import musabbir.mybeats.fixkoro.R;


/**
 * Created by MUSABBIR on 27-Sep-17.
 */

public class TechList extends AppCompatActivity {
    private List<TechListModel> itemList;
    private RecyclerView recyclerView;
    private TechListAdapter adapter;
    private ProgressDialog pDialog;
    final Context context = this;
    private static String url_tech_list = "";
    EditText search_et;
    SharedPreferences prefs;
    public static final String SP_COUNTRY = "country";
    String getUserCountry = "", speciality = "", search_et_str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tech_list);

        search_et = (EditText) findViewById(R.id.search_et);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        itemList = new ArrayList<>();
        adapter = new TechListAdapter(this, itemList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(TechList.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getItemList();

        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search_et_str = search_et.getText().toString().trim();
                    if (search_et_str.length() < 4) {
                        Toast.makeText(context, "Please type atleast four letters", Toast.LENGTH_SHORT).show();
                    } else {
                        getItemListOnSearch(search_et_str);
                    }
                }
                return false;
            }
        });
    }

    private void getItemList() {
        pDialog = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
        pDialog.setMessage("Loading technician list...");
        pDialog.show();

        url_tech_list = ConstantStuffs.base_url + ConstantStuffs.url_technician_list;
        StringRequest techListReq = new StringRequest(Request.Method.GET, url_tech_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("technicians");
                    if (jsonArray.length() < 1) {
                        Toast.makeText(context, "No technician available", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj2 = jsonArray.getJSONObject(i);
                            TechListModel itemsObj = new TechListModel();
                            itemsObj.setId_technician(obj2.getString("id_technician"));
                            itemsObj.setImage(ConstantStuffs.base_url_image + obj2.getString("image"));
                            itemsObj.setName(obj2.getString("name"));
                            itemsObj.setPhone(obj2.getString("phone"));
                            itemsObj.setEmail(obj2.getString("email"));
                            itemsObj.setLocation(obj2.getString("location"));
                            if (obj2.getString("lattitude").equals("") || obj2.getString("longitude").equals("")) {
                                itemsObj.setLattitude("0.0");
                                itemsObj.setLongitude("0.0");
                            } else {
                                itemsObj.setLattitude(obj2.getString("lattitude"));
                                itemsObj.setLongitude(obj2.getString("longitude"));
                            }
                            itemsObj.setSpecial_on("Speciality: " + obj2.getString("special_on"));
                            itemsObj.setWorking_area("Working area: " + obj2.getString("working_area"));
                            itemList.add(itemsObj);
                        }
                    }
                } catch (JSONException | NumberFormatException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
                hidePDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();
            }
        });
        techListReq.setRetryPolicy(new DefaultRetryPolicy(
                ConstantStuffs.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(techListReq);
    }

    private void getItemListOnSearch(String search_word) {
        pDialog = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
        pDialog.setMessage("Searching technician...");
        pDialog.show();

        url_tech_list = ConstantStuffs.base_url + ConstantStuffs.url_technician_list_on_search + "?name_or_area=" + Uri.encode(search_word);
        StringRequest techListReq = new StringRequest(Request.Method.GET, url_tech_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("technicians");
                    if (jsonArray.length() < 1) {
                        Toast.makeText(context, "No technician found", Toast.LENGTH_SHORT).show();
                    } else {
                        itemList.clear();
                        adapter.notifyDataSetChanged();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj2 = jsonArray.getJSONObject(i);
                            TechListModel itemsObj = new TechListModel();
                            itemsObj.setId_technician(obj2.getString("id_technician"));
                            itemsObj.setImage(ConstantStuffs.base_url_image + obj2.getString("image"));
                            itemsObj.setName(obj2.getString("name"));
                            itemsObj.setPhone(obj2.getString("phone"));
                            itemsObj.setEmail(obj2.getString("email"));
                            itemsObj.setLocation(obj2.getString("location"));
                            itemsObj.setSpecial_on("Speciality: " + obj2.getString("special_on"));
                            itemsObj.setWorking_area("Working area: " + obj2.getString("working_area"));
                            itemList.add(itemsObj);
                        }
                    }
                    search_et.requestFocus();
                } catch (JSONException | NumberFormatException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
                hidePDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();
            }
        });
        techListReq.setRetryPolicy(new DefaultRetryPolicy(
                ConstantStuffs.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(techListReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}