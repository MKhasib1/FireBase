package com.example.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import android.view.MenuItem;
import android.widget.Button;

import android.widget.Toast;

public class Sucess extends AppCompatActivity {
    private Button menu;
    private AppCompatButton add;
    private AppCompatButton remove;
    private AppCompatButton edit;
    private AppCompatButton logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu=findViewById(R.id.menu);
        add=findViewById(R.id.add);
        remove=findViewById(R.id.Remove);
        edit=findViewById(R.id.edit);
        logout=findViewById(R.id.Logout);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final PopupMenu mpopupMenu=new PopupMenu(Sucess.this,menu);

                mpopupMenu.getMenuInflater().inflate(R.menu.menu_main,mpopupMenu.getMenu());
                mpopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Toast.makeText(
                                Sucess.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });


                //showing popup menu

                mpopupMenu.show();

            }
        });
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Remove:
                Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.edit:
                Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Logout:
                Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }}
