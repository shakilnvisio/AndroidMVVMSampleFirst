package com.nvisio.mvvm.androidmvvmsamplefirst.basic.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nvisio.mvvm.androidmvvmsamplefirst.R;
import com.nvisio.mvvm.androidmvvmsamplefirst.basic.lifecycle.LifecycleFragment;
import com.nvisio.mvvm.androidmvvmsamplefirst.basic.model.Project;
import com.nvisio.mvvm.androidmvvmsamplefirst.basic.view.adapter.ProjectAdapter;
import com.nvisio.mvvm.androidmvvmsamplefirst.basic.view.callback.ProjectClickCallback;
import com.nvisio.mvvm.androidmvvmsamplefirst.basic.viewmodel.ProjectListViewModel;
import com.nvisio.mvvm.androidmvvmsamplefirst.databinding.BasicFragmentProjectListBinding;

import java.util.List;

public class ProjectListFragment extends LifecycleFragment {

    public static final String TAG = "ProjectListFragment";
    private ProjectAdapter projectAdapter;
    private BasicFragmentProjectListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.basic_fragment_project_list,container,false);
        projectAdapter = new ProjectAdapter(projectClickCallback);
        binding.projectList.setAdapter(projectAdapter);
        binding.setIsLoading(true);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProjectListViewModel viewModel = ViewModelProviders.of(this).get(ProjectListViewModel.class);

    }

    private void observeViewModel(ProjectListViewModel viewModel){
        viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects!=null){
                    binding.setIsLoading(false);
                    projectAdapter.setProjectList(projects);
                }
            }
        });
    }

    private final ProjectClickCallback projectClickCallback = new ProjectClickCallback() {
        @Override
        public void onClick(Project project) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
                ((BasicSampleActivity) getActivity()).show(project);
            }
        }
    };
}
