package Application;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private int thread = 0;
    private List<Profile> profiles = new ArrayList<>();

    public Controller() {
    }

    public void setThread(int thread) {
        this.thread = Math.max(thread, 0);
    }

    public void openChrome() {
        profiles.clear();
        for (int i = 0; i < thread; i++) {
            profiles.add(new Profile());
        }
    }

    public void submit() {
        for (int i = 0; i < thread; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> profiles.get(finalI).submit());
            thread.setUncaughtExceptionHandler((t, e) -> {
            });
            thread.start();
        }
    }
}
