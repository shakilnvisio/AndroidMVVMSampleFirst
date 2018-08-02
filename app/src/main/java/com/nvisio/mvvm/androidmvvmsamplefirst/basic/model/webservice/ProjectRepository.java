package com.nvisio.mvvm.androidmvvmsamplefirst.basic.model.webservice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.nvisio.mvvm.androidmvvmsamplefirst.basic.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepository {
    private GithubService githubService;
    private static ProjectRepository projectRepository;

    private ProjectRepository(){
        // this githubService instance will be injected using Dagger in #Advance part
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        githubService = retrofit.create(GithubService.class);
    }

    public synchronized  static ProjectRepository getInstance(){
        // no need to implement this singleton in #Advance part, since Dagger will handle it

        if (projectRepository == null){
            if (projectRepository == null){
                projectRepository = new ProjectRepository();
            }
        }

        return projectRepository;

    }

    public LiveData<List<Project>> getProjectList(String userid){

        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        githubService.getProjectList(userid).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                // better error handling in #Advance part
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Project> getProjectDetails(String userid, String projectName){
        final MutableLiveData<Project> data = new MutableLiveData<>();

        githubService.getProjectDetails(userid,projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
            // better error handling in #Advance part
                data.setValue(null);
            }
        });
        return data;
    }


    private void simulateDelay(){
        try
        {
            Thread.sleep(10);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
