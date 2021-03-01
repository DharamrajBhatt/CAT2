package com.example.lab_11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//Initialise variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        drawerLayout=findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view)
    {
        //Open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view )
    {
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        //Close drawer layout
        //Check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            //When drawer is open then close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view)
    {
        //Recreate activity
        recreate();
    }

    public void ClickDashboard(View view)
    {
        //Redirect activity to dashboard
       redirectActivity(this,MainActivity2.class);
    }

    public static void redirectActivity(Activity activity, Class aclass)
    {
        //Initialize intent
        Intent i = new Intent(activity,aclass);
        //Start activity
        activity.startActivity(i);
    }

    public void ClickExit(View view)
    {
        logout(this);
    }

    public static void logout(Activity activity) {
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        //Set Title
        builder.setTitle("Exit");

        //Set message
        builder.setMessage("Are you sure you want to exit?");

        //Positive button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });
        builder.show();
    }


    @Override
    protected void onPause()
    {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public void ClickOnLocation(View view) {
        Toast.makeText(MainActivity.this, "Redirecting to location", Toast.LENGTH_SHORT).show();
        String location =  "Bangalore Karnataka";

        Uri addressUri = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }


    }

    public void ClickOncall(View view) {

        Toast.makeText(MainActivity.this, "Call button clicked", Toast.LENGTH_SHORT).show();
        String contactNum =  "7619413700";

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactNum));

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }
}