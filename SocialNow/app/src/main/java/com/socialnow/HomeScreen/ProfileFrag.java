package com.socialnow.HomeScreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socialnow.App;

import com.socialnow.API.API;
import com.socialnow.Models.User;

import com.astuetz.PagerSlidingTabStrip;

import com.socialnow.Models.User;
import com.socialnow.PagerAdapter;
import com.socialnow.R;
import com.socialnow.Users.Utils;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.text.ParseException;

import android.support.v4.app.FragmentStatePagerAdapter;


public class ProfileFrag extends Fragment {

    private static final int SELECTED_PICTURE = 1;

    Button logout;
    User current_user;
    Long user_id;
    ImageView profile_picture;
    TextView user_name;
    TextView user_email;
    TextView user_role;
    String userName;
    String userEmail;
    String userPhoto;
    String userRole;
    Bitmap photo;



    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v =  inflater.inflate(R.layout.frag_profile,container,false);
        profile_picture = (ImageView) v.findViewById(R.id.profilepicture);

        user_name = (TextView) v.findViewById(R.id.tUserName);
        user_email = (TextView) v.findViewById(R.id.tUserEmail);
        user_role = (TextView) v.findViewById(R.id.tRole);
        current_user = Utils.getCurrentUser();

        Context context = this.getActivity();
        SharedPreferences sharedPref =   PreferenceManager.getDefaultSharedPreferences(context);




        user_id= Utils.getCurrentUser().getId();
        userName = Utils.getCurrentUser().getName();
        userEmail = Utils.getCurrentUser().getEmail();
        userPhoto = Utils.getCurrentUser().getUser_photo();
        userRole = Utils.getCurrentUser().getRole();

        user_name.setText(userName);
        user_email.setText(userEmail);
        user_role.setText(userRole);


        Log.d("photo",userPhoto + "asd");

        Picasso.with(getContext())
                .load(userPhoto)
                .resize(80, 80)
                .placeholder(R.drawable.devent)
                .centerCrop()
                .into(profile_picture);

        current_user.setId(user_id);






        //below is required when setting profile picture.
        //Intent i2 = new Intent(getActivity(), ProfilePage.class);
        //startActivity(i2);

      // getData();

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("ABOUT"));
        tabLayout.addTab(tabLayout.newTab().setText("FOLLOWERS"));
        tabLayout.addTab(tabLayout.newTab().setText("FOLLOWINGS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return v;
    }

}