package com.greenwich.madpropertypal.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.greenwich.madpropertypal.model.Report;

import java.util.List;

public class ReportRepository {


    private ReportDao reportDao;
    private LiveData<List<Report>> allReports;


    public ReportRepository(Application application){
        PropertyDatabase propertyDatabase =  PropertyDatabase.getPropertyDatabaseInstance(application);

        reportDao = propertyDatabase.reportDao();

        allReports = reportDao.getAllReports();
    }

    public void insert(Report report){
        new ReportRepository.InsertReportAsyncTask(reportDao).execute(report);
    }

    public void update(Report report){
        new ReportRepository.UpdateReportAsyncTask(reportDao).execute(report);
    }

    public void delete(Report report){
        new ReportRepository.DeleteReportAsyncTask(reportDao).execute(report);
    }

    public void deleteAll(){
        new ReportRepository.DeleteAllReportAsyncTask(reportDao).execute();
    }

    public LiveData<List<Report>> getAllReports(){
        return allReports;
    }

    public LiveData<List<Report>> getPropertyReportsById(int propertyId) {
        return reportDao.getPropertyReportsById(propertyId);
    }

    public static class InsertReportAsyncTask extends AsyncTask<Report, Void, Void>{

        private ReportDao reportDao;

        private InsertReportAsyncTask(ReportDao reportDao){
            this.reportDao = reportDao;

        }

        @Override
        protected Void doInBackground(Report... reports) {
            reportDao.insertReport(reports[0]);
            return null;
        }
    }

    public static class UpdateReportAsyncTask extends AsyncTask<Report, Void, Void>{

        private ReportDao reportDao;

        private UpdateReportAsyncTask(ReportDao reportDao){
            this.reportDao = reportDao;

        }

        @Override
        protected Void doInBackground(Report... reports) {
            reportDao.updateReport(reports[0]);
            return null;
        }
    }

    public static class DeleteReportAsyncTask extends AsyncTask<Report, Void, Void>{

        private ReportDao reportDao;

        private DeleteReportAsyncTask(ReportDao reportDao){
            this.reportDao = reportDao;

        }

        @Override
        protected Void doInBackground(Report... reports) {
            reportDao.deleteReportById(reports[0].getId());
            return null;
        }
    }

    public static class DeleteAllReportAsyncTask extends AsyncTask<Void, Void, Void>{

        private ReportDao reportDao;

        private DeleteAllReportAsyncTask(ReportDao reportDao){
            this.reportDao = reportDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            reportDao.deleteAllReports();
            return null;
        }
    }

    //    public static class GetPropertyReportsByIdAsyncTask extends AsyncTask<Report, Void , Void>{
//
//        private ReportDao reportDao;
//
//        private GetPropertyReportsByIdAsyncTask(ReportDao reportDao){
//            this.reportDao = reportDao;
//
//        }
//
//        @Override
//        protected Void doInBackground(Report... reports) {
//
//            return reportDao.getPropertyReportsById(reports[0].getPropertyId());
//        }
//    }

}
