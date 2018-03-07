package com.mybeats.fixkoro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mybeats.fixkoro.Adapter.TechListCardAdapter;
import com.mybeats.fixkoro.Model.TechListModel;
import com.mybeats.fixkoro.util.AppController;
import com.mybeats.fixkoro.util.ConstantStuffs;
import com.mybeats.fixkoro.util.InputChecker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import musabbir.mybeats.fixkoro.R;

/**
 * Created by MUSABBIR on 27-Dec-17.
 */

public class TechListCard extends Activity {

    private RecyclerView recyclerView;
    private TechListCardAdapter adapter;
    private List<TechListModel> itemList;
    private ProgressDialog spotDlg;
    final Context context = this;
    int fbClick = 0;
    private int MY_SOCKET_TIMEOUT_MS = 15000;
    EditText search_et;
    private static String url_item_list = "";
    InputMethodManager imm;
    String search_word = "";
    private static String url_tech_list = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tech_list_card_mother);

        InputChecker inpChckObj = new InputChecker();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        search_et = (EditText) findViewById(R.id.search_et);
        search_et.setFilters(new InputFilter[]{inpChckObj.filter});


        //String subcatname = extras.getString("subcatname");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        itemList = new ArrayList<>();
        adapter = new TechListCardAdapter(this, itemList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        getItemList();

        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search_word = search_et.getText().toString().trim();
                    if (search_word.length() < 4) {
                        Toast.makeText(context, "Please type atleast four letters", Toast.LENGTH_SHORT).show();
                    } else {
                        getItemList(search_word);
                    }
                }
                return false;
            }
        });
    }


    private void getItemList() {
        spotDlg = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
        spotDlg.setMessage("Loading items...");
        spotDlg.show();

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
                            itemsObj.setSpecial_on(obj2.getString("special_on"));
                            itemsObj.setWorking_area(obj2.getString("working_area"));
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


    private void getItemList(String search_word) {
        spotDlg = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
        spotDlg.setMessage("Loading items...");
        spotDlg.show();

//        url_item_list = "http://diapersbd.com/app_admin/index.php/Mobileapi/itemBySubcategory?id_subcategory=" + subcatid;

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
                            if (obj2.getString("lattitude").equals("") || obj2.getString("longitude").equals("")) {
                                itemsObj.setLattitude("0.0");
                                itemsObj.setLongitude("0.0");
                            } else {
                                itemsObj.setLattitude(obj2.getString("lattitude"));
                                itemsObj.setLongitude(obj2.getString("longitude"));
                            }
                            itemsObj.setSpecial_on(obj2.getString("special_on"));
                            itemsObj.setWorking_area(obj2.getString("working_area"));
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
                MY_SOCKET_TIMEOUT_MS,
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
        if (spotDlg != null) {
            spotDlg.dismiss();
            spotDlg = null;
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}