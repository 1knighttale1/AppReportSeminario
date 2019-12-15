package com.example.homes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.homes.Fragments.MainFragment;
import com.example.homes.Fragments.MapFragment;



public class FragmentsMapsActivity extends AppCompatActivity {

    private Fragment currentFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_maps);


    }




    //Menu fragmentos

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreatePanelMenu(featureId, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu:{
                currentFragment = new MainFragment();
                break;
            }
            case R.id.map_menu:{
                currentFragment = new MapFragment();
                break;
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container,currentFragment).commit();
        return super.onOptionsItemSelected(item);
    }
}
