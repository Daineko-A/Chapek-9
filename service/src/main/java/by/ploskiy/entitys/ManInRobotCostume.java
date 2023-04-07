package by.ploskiy.entitys;

import java.util.Random;

public class ManInRobotCostume extends BaseRobot {

    private StringBuilder tmpStringBuilder = new StringBuilder();

    @Override
    public void doTask() {
        if (getTask().getType().equals(TaskTypeEnum.SELF_DESTRUCTION.toString())) {
            robotLogClearAll();

            switch (new Random().nextInt(3)) {
                case 0:
                    tmpStringBuilder.append(getName() + "получил задание: " + getTask().getTitle() + "\n" + "Нужно где то спрятаться ...");
                    break;
                case 1:
                    tmpStringBuilder.append(getName() + "получил задание: " + getTask().getTitle() + "\n" + "Маскируемся деревом ... ");
                    break;
                case 2:
                    tmpStringBuilder.append(getName() + "получил задание: " + getTask().getTitle() + "\n" + "Смотрите! Человеки!");
                    break;
                default:
                    tmpStringBuilder.append(getName() + "получил задание: " + getTask().getTitle() + "\n" + "Паника ... Началась охота на \"Человеков\"");
            }

            addRobotLogString(tmpStringBuilder.toString());
            tmpStringBuilder.setLength(0);

            robotDestructor(getName());

        } else {
            robotLogClearAll();

            switch (new Random().nextInt(3)) {
                case 0:
                    tmpStringBuilder.append(getName() + " получил задание:" + getTask().getTitle() + "\n"
                            + "Нужно притвориться, что я делаю работу" + "\n"
                            + "Пик, ПИК, пик. Что это вообще за задание такое?  ¯\\_(ツ)_/¯" + "\n"
                            + "Закончил задание.");
                    break;
                case 1:
                    tmpStringBuilder.append(getName() + " получил задание: " + getTask().getTitle() + ".\n"
                            + " А, ну тут всё просто ...  ");
                    break;
                default:
                    tmpStringBuilder.append(getName() + " получил задание: " + getTask().getTitle() + ".\n"
                            + " Пока они не заподозрили, что я не робот, нужно что то придумать ");
            }

            addRobotLogString(tmpStringBuilder.toString());
            tmpStringBuilder.setLength(0);
        }
    }
}
