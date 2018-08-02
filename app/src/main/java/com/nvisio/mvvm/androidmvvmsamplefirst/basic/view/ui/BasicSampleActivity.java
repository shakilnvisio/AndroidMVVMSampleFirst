package com.nvisio.mvvm.androidmvvmsamplefirst.basic.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nvisio.mvvm.androidmvvmsamplefirst.MainActivity;
import com.nvisio.mvvm.androidmvvmsamplefirst.R;
import com.nvisio.mvvm.androidmvvmsamplefirst.basic.model.Project;

public class BasicSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_main_activity);

        // Add project list fragment if this is the firsttime
        if (savedInstanceState == null){
            ProjectListFragment fragment = new ProjectListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,fragment,ProjectListFragment.TAG).commit();
        }
    }

    public void show (Project project){
        ProjectFragment projectFragment = ProjectFragment.forProject(project.name);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container,
                        projectFragment,null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BasicSampleActivity.this, MainActivity.class));
        finish();
    }
}
