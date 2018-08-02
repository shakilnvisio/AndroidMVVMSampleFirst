package com.nvisio.mvvm.androidmvvmsamplefirst.basic.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.nvisio.mvvm.androidmvvmsamplefirst.basic.model.Project;
import com.nvisio.mvvm.androidmvvmsamplefirst.basic.model.webservice.ProjectRepository;

public class ProjectViewModel extends AndroidViewModel {
    private final LiveData<Project> projectObservable;
    private final String projectId;

    public ObservableField<Project> project = new ObservableField<>();

    public ProjectViewModel(Application application, String projectId){
        super(application);
        this.projectId = projectId;
        projectObservable = ProjectRepository.getInstance().getProjectDetails("Google",projectId);

    }

    public LiveData<Project> getProjectObservable() {
        return projectObservable;
    }

    public void setProject(Project project){
        this.project.set(project);
    }

    /**
     * A creator is used to inject the project ID into the viewmodel
     */

    public static class Factory extends ViewModelProvider.NewInstanceFactory{
        @NonNull
        private final  Application application;

        private final String projectID;

        public Factory(@NonNull Application application, String projectID) {
            this.application = application;
            this.projectID = projectID;
        }

        @Override
        public <T extends ViewModel> T create (Class<T> modelClass){
            //no inspection unchecked
            return (T) new ProjectViewModel(application,projectID);
        }
    }
}