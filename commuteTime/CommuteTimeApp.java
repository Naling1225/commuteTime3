package commuteTime;

import commuteTime.view.input.MainInputFrame;
import commuteTime.view.result.MainResultFrame;

import javax.swing.*;

public class CommuteTimeApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CommuteModel commuteModel = new CommuteModel();
            MainInputFrame mainInputFrame = new MainInputFrame();
            MainResultFrame mainResultFrame = new MainResultFrame(mainInputFrame);

            new CommuteController(commuteModel, mainInputFrame, mainResultFrame);
        });
    }
}
