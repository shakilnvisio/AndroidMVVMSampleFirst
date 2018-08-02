package com.nvisio.mvvm.androidmvvmsamplefirst.basic.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
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
import com.nvisio.mvvm.androidmvvmsamplefirst.basic.viewmodel.ProjectViewModel;
import com.nvisio.mvvm.androidmvvmsamplefirst.databinding.BasicFragmentProjectDetailsBinding;

public class ProjectFragment extends LifecycleFragment {
    private static final String KEY_PROJECT_ID = "project_id";
    private BasicFragmentProjectDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.basic_fragment_project_details,container,false);
        //create and set the adapter for the recyclerview
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProjectViewModel.Factory factory = new ProjectViewModel.Factory(getActivity().getApplication(),getArguments().getString(KEY_PROJECT_ID));
        final ProjectViewModel viewModel = ViewModelProviders.of(this,factory)
                .get(ProjectViewModel.class);

        binding.setProjectViewModel(viewModel);
        binding.setIsLoading(true);

        observeViewModel(viewModel);

    }


    private void observeViewModel(final ProjectViewModel viewModel){
        //Observe project data
        viewModel.getProjectObservable().observe(this, new Observer<Project>() {
            @Override
            public void onChanged(@Nullable Project project) {
                if (project!=null){
                    binding.setIsLoading(false);
                    viewModel.setProject(project);
                }
            }
        });
    }

    //Creates project fragment for specific project ID
    public static  ProjectFragment forProject (String projectID){
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(KEY_PROJECT_ID,projectID);
        fragment.setArguments(args);
        return fragment;
    }
}
