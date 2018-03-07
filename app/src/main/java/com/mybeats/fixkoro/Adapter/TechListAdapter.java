package com.mybeats.fixkoro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.mybeats.fixkoro.Model.TechListModel;
import com.mybeats.fixkoro.TechProfile;
import com.mybeats.fixkoro.util.AppController;
import com.mybeats.fixkoro.util.CircularNetworkImageView;

import java.util.List;

import musabbir.mybeats.fixkoro.R;

/**
 * Created by MUSABBIR on 27-Sep-17.
 */

public class TechListAdapter extends RecyclerView.Adapter<TechListAdapter.MyViewHolder> {

    private Context mContext;
    private List<TechListModel> itemList;
    ImageLoader imageLoader;

    String name_str = "", id_str = "", phone_str = "", email_str = "", special_on_str = "", working_area_str = "", location_str = "", image_str = "", latti_str = "",
            longi_str = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, id_tech, phone, email, special_on, working_area, location, image_string, latti, longi;
        CircularNetworkImageView tech_image;
        ImageView shareImg;
        LinearLayout tech_details_ll;

        public MyViewHolder(View view) {
            super(view);
            imageLoader = AppController.getInstance().getImageLoader();

            name = (TextView) view.findViewById(R.id.name);
            id_tech = (TextView) view.findViewById(R.id.id_tech);
            phone = (TextView) view.findViewById(R.id.phone);
            email = (TextView) view.findViewById(R.id.email);
            special_on = (TextView) view.findViewById(R.id.special_on);
            working_area = (TextView) view.findViewById(R.id.working_area);
            location = (TextView) view.findViewById(R.id.location);
            image_string = (TextView) view.findViewById(R.id.image_string);
            latti = (TextView) view.findViewById(R.id.latti);
            longi = (TextView) view.findViewById(R.id.longi);
            tech_details_ll = (LinearLayout) view.findViewById(R.id.tech_details_ll);

            tech_image = (CircularNetworkImageView) view.findViewById(R.id.tech_image);
            shareImg = (ImageView) view.findViewById(R.id.share_img);

            name_str = name.getText().toString();
            id_str = id_tech.getText().toString();
            email_str = email.getText().toString();
            special_on_str = special_on.getText().toString();
            working_area_str = working_area.getText().toString();
            location_str = location.getText().toString();
            phone_str = phone.getText().toString();
            image_str = image_string.getText().toString();
            latti_str = latti.getText().toString();
            longi_str = longi.getText().toString();
        }
    }

    public TechListAdapter(Context mContext, List<TechListModel> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tech_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final TechListModel dmObj = itemList.get(position);
        holder.name.setText(dmObj.getName());
        holder.id_tech.setText(dmObj.getId_technician());
        holder.phone.setText(dmObj.getPhone());
        holder.email.setText(dmObj.getEmail());
        holder.special_on.setText(dmObj.getSpecial_on());
        holder.working_area.setText(dmObj.getWorking_area());
        holder.location.setText(dmObj.getLocation());
        holder.image_string.setText(dmObj.getImage());

        holder.latti.setText(dmObj.getLattitude());
        holder.longi.setText(dmObj.getLongitude());


        holder.tech_image.setImageUrl(dmObj.getImage(), imageLoader);
        holder.tech_image.setDefaultImageResId(R.drawable.ic_user_blue);

        holder.shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dr_id_str = holder.dr_id.getText().toString();
//                dr_name_str = holder.dr_name.getText().toString();
//                dr_type_str = holder.dr_type.getText().toString();
//                dr_img_str = dmObj.gettech_image().toString();

//                Intent inte = new Intent(mContext.getApplicationContext(), Appointment.class);
//                inte.putExtra("id_doctor", dr_id_str);
//                inte.putExtra("name", dr_name_str);
//                inte.putExtra("type", dr_type_str);
//                inte.putExtra("image", dr_img_str);
//                mContext.startActivity(inte);
            }
        });

        holder.tech_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_str = holder.name.getText().toString();
                special_on_str = holder.special_on.getText().toString();
                working_area_str = holder.working_area.getText().toString();
                location_str = holder.location.getText().toString();
                phone_str = holder.phone.getText().toString();
                id_str = holder.id_tech.getText().toString();
                image_str = holder.image_string.getText().toString();
                latti_str = holder.latti.getText().toString();
                longi_str = holder.longi.getText().toString();

                Intent inte = new Intent(mContext.getApplicationContext(), TechProfile.class);
                inte.putExtra("name", name_str);
                inte.putExtra("special_on", special_on_str);
                inte.putExtra("working_area", working_area_str);
                inte.putExtra("location", location_str);
                inte.putExtra("phone", phone_str);
                inte.putExtra("id_tech", id_str);
                inte.putExtra("image_str", image_str);
                inte.putExtra("latti_str", latti_str);
                inte.putExtra("longi_str", longi_str);
                mContext.startActivity(inte);
            }
        });

        holder.tech_details_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_str = holder.name.getText().toString();
                special_on_str = holder.special_on.getText().toString();
                working_area_str = holder.working_area.getText().toString();
                location_str = holder.location.getText().toString();
                phone_str = holder.phone.getText().toString();
                id_str = holder.id_tech.getText().toString();
                image_str = holder.image_string.getText().toString();
                latti_str = holder.latti.getText().toString();
                longi_str = holder.longi.getText().toString();

                Intent inte = new Intent(mContext.getApplicationContext(), TechProfile.class);
                inte.putExtra("name", name_str);
                inte.putExtra("special_on", special_on_str);
                inte.putExtra("working_area", working_area_str);
                inte.putExtra("location", location_str);
                inte.putExtra("phone", phone_str);
                inte.putExtra("id_tech", id_str);
                inte.putExtra("image_str", image_str);
                inte.putExtra("latti_str", latti_str);
                inte.putExtra("longi_str", longi_str);
                mContext.startActivity(inte);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}