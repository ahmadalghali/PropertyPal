package com.greenwich.madpropertypal.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.greenwich.madpropertypal.data.ReportRepository;
import com.greenwich.madpropertypal.model.Report;

import java.util.List;

public class ReportViewModel extends AndroidViewModel {


    private ReportRepository reportRepository;
    private LiveData<List<Report>> allReports;

    public ReportViewModel(@NonNull Application application) {
        super(application);

        reportRepository = new ReportRepository(application);

        allReports = reportRepository.getAllReports();
    }


    public void insert(Report report){
        reportRepository.insert(report);
    }

    public void update(Report report){
        reportRepository.update(report);
    }

    public void delete(Report report){
        reportRepository.delete(report);
    }

    public void deleteAll(){
        reportRepository.deleteAll();
    }

    public LiveData<List<Report>> getAllReports(){
        return  allReports;
    }

    public LiveData<List<Report>> getPropertyReportsById(int propertyId){
        return  reportRepository.getPropertyReportsById(propertyId);
    }

}
