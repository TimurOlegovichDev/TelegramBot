package io.bot.lightWeightBot.scenes;

import io.bot.lightWeightBot.scenes.PressTrainingAction;
import io.bot.lightWeightBot.scenes.StartAction;
import lombok.Setter;
import lombok.Getter;

public class Scenes {

    public enum Actions {
        START, TRAINING, WARMUP, DEFAULT;
    }

    protected final String message;
    protected String name;

    public Scenes(String message, String name) {
        this.message = message;
        this.name = name;
    }

    @Setter
    private Actions action;

    public String getAnswer() {
        return switch (action) {
            case START -> {
                StartAction startAction = new StartAction(message, name);
                yield startAction.startCommand();
            }
            case TRAINING -> {
                PressTrainingAction pressTrainingAction = new PressTrainingAction(message, name);
                yield pressTrainingAction.getPressTraining();
            }
            case WARMUP -> {
                PressTrainingAction pressTrainingAction = new PressTrainingAction(message, name);
                yield pressTrainingAction.getWarmUp();
            }
            default -> null;
        };
    }
}
