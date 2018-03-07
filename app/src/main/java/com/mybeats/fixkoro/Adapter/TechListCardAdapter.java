package com.mybeats.fixkoro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.mybeats.fixkoro.Model.TechListModel;
import com.mybeats.fixkoro.TechProfile;
import com.mybeats.fixkoro.util.AppController;
import com.mybeats.fixkoro.util.CircularNetworkImageView;

import java.util.List;

import musabbir.mybeats.fixkoro.R;

/**
 * Created by MUSABBIR on 27-Dec-17.
 */

public class TechListCardAdapter extends RecyclerView.Adapter<TechListCardAdapter.MyViewHolder> {

    private Context mContext;
    private List<TechListModel> itemList;
    ImageLoader imageLoader;

    String name_str = "", id_str = "", phone_str = "", email_str = "", special_on_str = "", working_area_str = "", location_str = "", image_str = "", latti_str = "",
            longi_str = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, id_tech, phone, email, special_on, working_area, location, image_string, latti, longi, view_profile_btn;
        CircularNetworkImageView tech_image;

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
            view_profile_btn = (TextView) view.findViewById(R.id.view_profile_btn);

            tech_image = (CircularNetworkImageView) view.findViewById(R.id.tech_image);

            name_str = name.getText().toString();
            id_str = id_tech.getText().toString();
            phone_str = phone.getText().toString();
            email_str = email.getText().toString();
            special_on_str = special_on.getText().toString();
            working_area_str = working_area.getText().toString();
            location_str = location.getText().toString();
            image_str = image_string.getText().toString();
            latti_str = latti.getText().toString();
            longi_str = longi.getText().toString();

        }
    }


    public TechListCardAdapter(Context mContext, List<TechListModel> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @Override
    public TechListCardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tech_profile_card, parent, false);
        return new TechListCardAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TechListCardAdapter.MyViewHolder holder, int position) {
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


        // loading album cover using Glide library
        Glide.with(mContext).load(dmObj.getImage()).placeholder(R.drawable.default_image_card).error(R.drawable.default_image_card).into(holder.tech_image);

        holder.tech_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                inte.putExtra("special_on", "Speciality: " + special_on_str);
                inte.putExtra("working_area", "Working area: " + working_area_str);
                inte.putExtra("location", location_str);
                inte.putExtra("phone", phone_str);
                inte.putExtra("id_tech", id_str);
                inte.putExtra("image_str", image_str);
                inte.putExtra("latti_str", latti_str);
                inte.putExtra("longi_str", longi_str);
                mContext.startActivity(inte);
            }
        });

        holder.view_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                inte.putExtra("special_on", "Speciality: " + special_on_str);
                inte.putExtra("working_area", "Working area: " + working_area_str);
                inte.putExtra("location", location_str);
                inte.putExtra("phone", phone_str);
                inte.putExtra("id_tech", id_str);
                inte.putExtra("image_str", image_str);
                inte.putExtra("latti_str", latti_str);
                inte.putExtra("longi_str", longi_str);
                mContext.startActivity(inte);

            }
        });

        setAnimation(holder.itemView);
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private void setAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }
}