package commuteTime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import commuteTime.view.input.MainInputFrame;
import commuteTime.view.result.MainResultFrame;

public class CommuteController {
    private CommuteModel model;
    private MainInputFrame inputView;
    private MainResultFrame resultView;

    public CommuteController(CommuteModel model, MainInputFrame inputView, MainResultFrame resultView) {
        this.model = model;
        this.inputView = inputView;
        this.resultView = resultView;

        // 소요시간 정보를 얻어오기 위한 리스너를 등록한다.
        inputView.addRequiredTimeButtonListener(new DurationButtonListener());

        // View에서 발생한 이벤트 처리를 위한 리스너를 등록한다.
        inputView.addSubmitButtonListener(new SubmitButtonListener());
    }

    class DurationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("소요시간을 가져오기 위한 이벤트 리스너가 트리거됨");
            String departureLocation = inputView.getDepartureLocation();
            String destinationLocation = inputView.getDestinationLocation();
            model.setDepartureLocation(departureLocation);
            model.setDestinationLocation(destinationLocation);
            try {
                model.calculateCommuteTime();
                inputView.setDuration(model.duration);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    int n = 0;

    class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WaitingThread waitThread = new WaitingThread(inputView);
            waitThread.start();
            waitThread.setName("SubmitButtonListener" + (n++));
            // 사용자 입력 값을 Model에 전달한다.
            double hourlyWage = inputView.getWage();
            String wageType = inputView.getWageType();
            String day = inputView.getComputeDay();
            String time = inputView.getWorkingTime();

            time = time.replaceAll("시간", "");
            day = day.replaceAll("일", "");
            int workingTime = Integer.parseInt(time);
            int commuteDay = Integer.parseInt(day);
            String departureLocation = inputView.getDepartureLocation();
            String destinationLocation = inputView.getDestinationLocation();

            model.setHourlyWage(hourlyWage);
            model.setWageType(wageType);
            model.setDepartureLocation(departureLocation);
            model.setDestinationLocation(destinationLocation);
            model.setWorkingTime(workingTime);
            model.setCommuteDay(commuteDay);

            // Model에서 계산된 결과를 View에 전달한다.
            try {
                String commuteTime = model.calculateCommuteTime();
                int cost = model.calculateCommuteCost();
                String departure = model.getDepartureName();
                String destination = model.getDestinationName();
                int fare = model.getFare();

                resultView.setDeparture(departure);
                resultView.setDestination(destination);
                //resultView.setCost(cost);
                resultView.setCommuteCost(cost);
                resultView.setFare(fare);
                resultView.setCommuteDay(commuteDay);
                resultView.createResultFrame();
                inputView.setVisible(false);
                resultView.setVisible(true);
                waitThread.setEnd();
            } catch (IOException | InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}

class WaitingThread extends Thread {
    JDialog dlg = null;
    JLabel lbMsg = null;
    MainInputFrame inputView;

    public WaitingThread(MainInputFrame inputView) {
        this.inputView = inputView;
        dlg = new JDialog();
        dlg.setTitle("처리 중입니다.");
        lbMsg = new JLabel("처리 중입니다.", SwingConstants.CENTER);
        lbMsg.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        dlg.add(lbMsg, BorderLayout.CENTER);
    }

    @Override
    public void run() {
        Point point = inputView.getLocation();
        int parentX = (int) point.getX();
        int parentY = (int) point.getY();

        dlg.getContentPane().setBackground(Color.WHITE);
        dlg.setSize(350, 150);
        dlg.setLocation(parentX + 50, parentY + 250);
        dlg.setResizable(false);
        dlg.setVisible(true);

        lbMsg.repaint();
        dlg.repaint();
    }

    public void setEnd() {
        dlg.dispose();
    }
}