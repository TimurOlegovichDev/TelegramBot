package io.bot.lightWeightBot.scenes;

public class StartAction extends Scenes {
    public StartAction(String message, String name) {
        super(message, name);
    }

    public String startCommand() {
        String s = """
                Добро пожаловать на программу тренировок\s
                Она направлена на рост силовых и мышц.
                Правила крайне просты:\s
                1) Я пишу - ты выполняешь!\s
                2) Если я не отвечаю - не спамить\s
                3) Следи за питанием\s
                4) Не сказал "Light Weight"- пожал меньше\s
                5) Сделай разминку перед треней\s
                6) ДОЛЖЕН БЫТЬ СТРАХУЮЩИЙ!!!\s
                7) Сразу говорить об ошибках и багах\s
                Удачи на тренировке и хороших результатов)
                Теперь, пришли мне свой максимум на 1 раз
                """;
        return "Привет, " + name + "\n" + s;
    }
}
