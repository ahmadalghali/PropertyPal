package com.greenwich.madpropertypal.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.greenwich.madpropertypal.model.Report;

import java.util.List;

@Dao
public interface ReportDao {

    String REPORT_TABLE = "report";

    @Insert
    void insertReport(Report report);

    @Update
    void updateReport(Report... report);

    @Query("SELECT * FROM " + REPORT_TABLE)
    LiveData<List<Report>> getAllReports();

    @Query("SELECT * FROM " + REPORT_TABLE + " WHERE report.id = :reportId" )
    Report findReportById(int reportId);

    @Query("DELETE FROM " + REPORT_TABLE + " WHERE report.id = :reportId" )
    void deleteReportById(int reportId);

    @Query("DELETE FROM " + REPORT_TABLE)
    void deleteAllReports();

    @Query("SELECT * FROM " + REPORT_TABLE+ " WHERE propertyId = :propertyId")
    LiveData<List<Report>> getPropertyReportsById(int propertyId);

}
