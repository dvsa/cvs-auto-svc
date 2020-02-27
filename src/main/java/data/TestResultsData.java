package data;

import data.config.BaseData;
import data.config.DataMapper;
import model.testresults.TestResultsGet;

public class TestResultsData {


    public static TestResultsGet.Builder buildTestResultsSubmittedDataWithCalculated() {

        TestResultsGet.Builder testResults = buildTestResultsSubmittedData();

        return testResults;
    }


    public static TestResultsGet.Builder buildTestResultsCancelleddDataWithCalculated() {

        TestResultsGet.Builder testResults = buildTestResultsCancelledData();

        return testResults;
    }

    public static TestResultsGet.Builder buildTestResultsSubmittedData() {

        TestResultsGet.Builder testResults = DataMapper.getValue(TestResultsGet.Builder.class, "loader/" + BaseData.getDataLocation() + "/test-results_submitted.json");

        return testResults;

    }

    public static TestResultsGet.Builder buildTestResultsSubmittedDataOld() {

        TestResultsGet.Builder testResults = DataMapper.getValue(TestResultsGet.Builder.class, "loader/" + BaseData.getDataLocation() + "/test-results_submitted_old.json");

        return testResults;

    }

    public static TestResultsGet.Builder buildTestResultsCancelledData() {


        TestResultsGet.Builder testResults = DataMapper.getValue(TestResultsGet.Builder.class, "loader/" + BaseData.getDataLocation() + "/test-results_cancelled.json");

        return testResults;
    }

    public static TestResultsGet.Builder buildTestResultsCancelledDataOld() {


        TestResultsGet.Builder testResults = DataMapper.getValue(TestResultsGet.Builder.class, "loader/" + BaseData.getDataLocation() + "/test-results_cancelled_old.json");

        return testResults;
    }
}
