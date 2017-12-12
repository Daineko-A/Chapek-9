package by.ploskiy.entitys;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@ToString
public class SimpleRobot implements BaseRobot, Runnable {

    public SimpleRobot() {
    }

    private static int count = 0;
    private List<String> robotLog = new ArrayList<String>();
    private Task task = new Task();

    public void setTaskForRobot(Task task) {
        this.task = task;
    }


    public void doTask(Task task) {
        if(task.getType().equals(TaskTypeEnum.SELF_DESTRUCTION)) {
            robotLog.add("Получил задание:" + task.getTitle());
            robotLog.add("Самоуничтожение..." );
        } else {
            showLog();
            robotLog.add("Получил задание:" + task.getTitle());
            showLog();
            robotLog.add("Делаю..." );
            showLog();
            loadProcess();
            robotLog.add("Закончил задание.");
            showLog();

            System.out.println(count + "*******************************************");
        }

    }

    public void run() {
        System.out.printf("Поток %s начал работу... \n", Thread.currentThread().getName());
        robotLog.add("Питание подано");

        if (task != null){
            doTask(task);
        }

        robotLog.add("Загрузка процессора");
    }

    public void loadProcess(){
        StringBuilder stringBuilder = new StringBuilder("");
        count++;

        for (int i = 0; i <= 10; i++){
            for (int l = 0; l < i; l++) {
                stringBuilder.append("=");
            }
            stringBuilder.append("" + i * 10 + "%");

//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            robotLog.add(stringBuilder.toString());
            showLog();
            stringBuilder.setLength(0);
        }
    }

    public void showLog() {
        for (String s : robotLog ) {
            System.out.println(s);
        }
        System.out.println("-=-=---=-=-=");
    }
}
