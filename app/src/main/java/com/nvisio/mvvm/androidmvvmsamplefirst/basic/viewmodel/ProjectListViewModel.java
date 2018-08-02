package com.nvisio.mvvm.androidmvvmsamplefirst.basic.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.nvisio.mvvm.androidmvvmsamplefirst.basic.model.Project;
import com.nvisio.mvvm.androidmvvmsamplefirst.basic.model.webservice.ProjectRepository;

import java.util.List;

public class ProjectListViewModel extends AndroidViewModel {
    private final LiveData<List<Project>> projectListObservable;

    public ProjectListViewModel (Application application){
        super(application);
        projectListObservable = ProjectRepository.getInstance().getProjectList("Google");

    }

    public LiveData<List<Project>> getProjectListObservable() {
        return projectListObservable;
    }
}
