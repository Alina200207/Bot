package main.java;

public class CountTasks {
    public final Integer countSequences;
    public final Integer countIssues;
    public final Integer countExamples;
    public final Integer countAllTasks;

    public CountTasks(Integer examplesCount, Integer sequenceCount, Integer issuesCount){
        countExamples = examplesCount;
        countSequences = sequenceCount;
        countIssues = issuesCount;
        countAllTasks = countExamples + countSequences + countIssues;
    }
}
