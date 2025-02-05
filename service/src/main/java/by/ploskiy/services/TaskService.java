package by.ploskiy.services;

import by.ploskiy.entitys.Task;
import by.ploskiy.entitys.TaskTypeEnum;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TaskService {

    public Task createTaskFromString(String data) {
        Pattern regEx = Pattern.compile("(task=)(\\D+)&(name=)(\\D+\\d+)");
        Matcher matcher = regEx.matcher(data);
        matcher.find();

        Task task = new Task();
        task.setTitle(TaskTypeEnum.valueOf(matcher.group(2)).getDescription());
        task.setType(matcher.group(2));
        
        return task;
    }
    
    public String extractRobotNameFromString(String data) {
        Pattern regEx = Pattern.compile("(task=)(\\D+)&(name=)(\\D+\\d+)");
        Matcher matcher = regEx.matcher(data);
        matcher.find();
        
        return matcher.group(4);
    }
}
