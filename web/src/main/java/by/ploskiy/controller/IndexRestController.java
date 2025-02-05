package by.ploskiy.controller;

import by.ploskiy.entitys.BaseRobot;
import by.ploskiy.entitys.Task;
import by.ploskiy.entitys.TaskTypeEnum;
import by.ploskiy.services.LogController;
import by.ploskiy.services.TaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class IndexRestController {

    @Autowired
    private final TaskController taskController;

    @Autowired
    private final LogController logController;

    public IndexRestController(TaskController taskController, LogController logController) {
        this.taskController = taskController;
        this.logController = logController;
    }

    @GetMapping("/controllerTaskList")
    public List<Task> controllerTaskList() {
        return taskController.showTaskList();
    }

    @GetMapping("/controllerLogList")
    public List<String> controllerLogList() {
        return logController.getAllLogList();
    }

    @PostMapping("/addTaskToController")
    public void addTask(@RequestBody String dataButton) {
        Task task = new Task();
        task.setTitle(TaskTypeEnum.valueOf(dataButton.replaceAll("=", "")).getDescription());
        task.setType(dataButton.replaceAll("=", ""));
        taskController.addTask(task);
    }

    @PostMapping("/addTaskToRobot")
    public void addTaskToRobot(@RequestBody String dataButton) {
        Pattern regEx = Pattern.compile("(task=)(\\D+)&(name=)(\\D+\\d+)");

        Matcher matcher = regEx.matcher(dataButton);
        matcher.find();

        Task task = new Task();
        task.setTitle(TaskTypeEnum.valueOf(matcher.group(2)).getDescription());
        task.setType(matcher.group(2));

        taskController.personalRobotTasr(task, matcher.group(4));
    }

    @GetMapping("/allComandsForRobots")
    public TaskTypeEnum[] allComandsForRobots() {
        return TaskTypeEnum.values();
    }

    @GetMapping("/allTaskDescription")
    public Map<String, String> allTaskDescription() {
        Map<String, String> allTasksEnumMap = new HashMap<String, String>();
        for (TaskTypeEnum taskEnum : TaskTypeEnum.values()) {
            allTasksEnumMap.put(taskEnum.toString(), taskEnum.getDescription());
        }

        return allTasksEnumMap;
    }

    @GetMapping("/allRobots")
    public List<BaseRobot> allRobots() {
        return taskController.getRobotList();
    }

    @PostMapping("/addRobot")
    public void addRobot() {
        taskController.createRobot();
    }
}
