package io.bot.lightWeightBot.scenes;

public class PressTrainingAction extends Scenes {
    private final StringBuilder training = new StringBuilder();
    private int workingWeight;
    private int weightMax;

    public PressTrainingAction(String message, String name) {
        super(message, name);
        workingWeight = tryToGetWeight();
    }

    private int tryToGetWeight() {
        try {
            weightMax = Integer.parseInt(message);
            workingWeight = ((int) (weightMax * 0.8) + 2) / 5 * 5;
        } catch (NumberFormatException e) {
            return -1;
        }
        if (weightMax < 0) return -1;
        else if (weightMax < 51) return 0;
        else if (weightMax > 300) return 1;
        return workingWeight;
    }

    public String getPressTraining() {
        switch (workingWeight) {
            case -1:
                return "Введи нормальное число";
            case 0:
                return "Иди лучше поотжимайся";
            case 1:
                return "Тут тебе уже не штанги тягать, а самолет";
            default:
                training.setLength(0);
                training.append("""
                        Перейдем к тренировке.
                        Между подходами отдыхаем от 3 до 5 минут!
                        Здесь ты должен строго фиксировать свое количество повторений в первом подходе, чтобы наблюдать за прогрессом. Если их количество стало 10 раз и больше, то пробуй пожать новый максимум!
                        Итак, приступим. Все подходы делаем до отказа (пока не сможем пожать):
                        """);
                training.append("1) ").append(workingWeight).append("\n");
                int newWeight = ((int) (workingWeight * 0.9) + 2) / 5 * 5;
                training.append("2) ").append(newWeight).append("\n");
                newWeight = ((int) (workingWeight * 0.7) + 2) / 5 * 5;
                training.append("3) ").append(newWeight).append("\n");
                return training.toString();
        }
    }

    public String getWarmUp() {
        if (!(weightMax > 50 && weightMax < 300)) return null;
        training.setLength(0);
        training.append("""
                Разминка:
                1) 20 кг на 20 повторений
                2)""").append(" " + ((int) (workingWeight * 0.5) + 2) / 5 * 5).append(" кг на 10 повторений\n").append("3) ").append(((int) (workingWeight * 0.8) + 2) / 5 * 5).append(" кг на 6 повторений\n").append("4) ").append(((int) (workingWeight * 0.9) + 2) / 5 * 5).append(" кг на 4 повторения\n");
        return training.toString();
    }

}
