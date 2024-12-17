package com.example.trabajounidad3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        menu.add(Menu.NONE,1,Menu.NONE,"Carta").setIcon(android.R.drawable.ic_menu_myplaces);
        menu.add(Menu.NONE,2,Menu.NONE,"Proximamente").setIcon(android.R.drawable.ic_menu_my_calendar);
        menu.add(Menu.NONE,3,Menu.NONE,"Proximamente").setIcon(android.R.drawable.ic_menu_mylocation);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragmento = null;

                switch (item.getItemId()) {
                    case 1:
                        fragmento = new MiFragmento();
                        break;
                    case 2:
                        fragmento = new MiFragmento2();
                        break;
                    case 3:
                        fragmento = new MiFragmento3();
                        break;
                }

                if (fragmento != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView, fragmento);
                    transaction.commit();
                }
                return true;
            }
        });


        bottomNavigationView.setSelectedItemId(1);



    }


}
