package com.autoSerwis;

public enum PersonJob {
    UNKNOWN("-------"),
    GUEST("Go??"),
    STUDENT("Student"),
    TEACHER("Nauczyciel"),
    MANAGER("Kierownik"),
    DIRECTOR("Dyrektor");

    String jobName;

    private PersonJob(String job_name) {
        jobName = job_name;
    }


    @Override
    public String toString() {
        return jobName;
    }

}  // koniec klasy enum Job