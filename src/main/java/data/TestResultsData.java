package data;

import data.config.DataMapper;
import model.testresults.*;
import util.DataLoader;

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

        TestResultsGet.Builder testResults = DataMapper.getValue(TestResultsGet.Builder.class, "loader/" + DataLoader.getDataLocation() + "/test-results_submitted.json");

        return testResults;

    }

    public static TestResultsGet.Builder buildTestResultsCancelledData() {


        TestResultsGet.Builder testResults = DataMapper.getValue(TestResultsGet.Builder.class, "loader/" + DataLoader.getDataLocation() + "/test-results_cancelled.json");

        return testResults;
    }
}
