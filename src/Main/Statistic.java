package Main;

import java.util.*;

public class Statistic {
    public String getStatisticWithText(CountTasks countTasks) {
        String str = (countTasks.countAllTasks > 0) ? "Молодец! Продолжай в том же духе :)" : "Поднажми! Совсем ведь всё по нулям :(";
        return String.format("Общее количество правильно решенных задач - %s.\nВ частности, примеров - %s, последовательностей - %s, " +
                "загадок - %s.", countTasks.countAllTasks, countTasks.countExamples,
                countTasks.countSequences, countTasks.countIssues) + "\n\n" + str;
    }
    public CountTasks getCountTasks(HashMap<Type.TypeTask, ArrayList<String>> usedTasks){
        return new CountTasks(usedTasks.get(Type.TypeTask.Example).size(),
                usedTasks.get(Type.TypeTask.Sequence).size(), usedTasks.get(Type.TypeTask.Issue).size());
    }
    public CountTasks getCountTasks(CountTasks beforeCount, CountTasks afterCount){
        return new CountTasks(afterCount.countExamples - beforeCount.countExamples,
                afterCount.countSequences - beforeCount.countSequences,
                afterCount.countIssues - beforeCount.countIssues);
    }
    public String getComparativeStatistic(Map<String, UserData> usersData, UserData thisUserData){
        var countTasksThisUser = thisUserData.GetLastStatistic().countAllTasks;
        double countUsersWithFewerQuantity = 0.0;
        double countUsersWithSameQuantity = 0.0;
        ArrayList<UserData> userData = new ArrayList<UserData>(usersData.values());
        for (UserData user: userData){
            var statOfAnotherUser = user.GetLastStatistic();
            if (statOfAnotherUser.countAllTasks < countTasksThisUser){
                countUsersWithFewerQuantity+=1;
            }
            if (statOfAnotherUser.countAllTasks.equals(countTasksThisUser)){
                countUsersWithSameQuantity+=1;
            }
        }
        var percentFewer = usersData.size()>1 ? (countUsersWithFewerQuantity)/usersData.size()*100 : 100;
        var percentSame = usersData.size()>1 ? (countUsersWithSameQuantity - 1)/usersData.size()*100 : 0;
        return String.format("Ты лучше %s%% пользователей; имеешь одинаковые баллы с %s%%. Всего пользователей - %s.", percentFewer, percentSame, usersData.size());
    }
}

