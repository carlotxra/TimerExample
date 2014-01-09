import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class SimpleUI implements Observer {
  private JButton cancelTimerButton;
  private JButton startTimerButton;
  private JTextArea outputTextArea;
  private JPanel mainPanel;
  private TimerSchedule timerSchedule;

  public SimpleUI() {
    startTimerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        outputTextArea.append("Start Button Pressed\n");
        timerSchedule.startTimer();
      }
    });

    cancelTimerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        outputTextArea.append("Cancel Button Pressed\n");
        timerSchedule.cancelTimer();
      }
    });
  }

  public void startUI() throws Exception {
    createTimerSchedule();
    createUI();
  }

  private void createUI() {
    JFrame frame = new JFrame("Test Frame");
    frame.add($$$getRootComponent$$$());
    $$$getRootComponent$$$().setVisible(true);
    frame.setLayout(new GridBagLayout());
    frame.setSize(640, 480);
    frame.validate();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void createTimerSchedule() {
    timerSchedule = new TimerSchedule();
    timerSchedule.addObserver(this);
  }

  public void update(Observable o, Object arg) {
    outputTextArea.append(arg.toString() + "\n");
  }

  public static void main(String[] args) throws Exception {
    new SimpleUI().startUI();
  }

  {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
    $$$setupUI$$$();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    startTimerButton = new JButton();
    startTimerButton.setText("Start");
    mainPanel.add(startTimerButton);
    cancelTimerButton = new JButton();
    cancelTimerButton.setText("Stop");
    mainPanel.add(cancelTimerButton);
    final JScrollPane scrollPane1 = new JScrollPane();
    scrollPane1.setHorizontalScrollBarPolicy(30);
    scrollPane1.setVerticalScrollBarPolicy(20);
    mainPanel.add(scrollPane1);
    outputTextArea = new JTextArea();
    outputTextArea.setBackground(new Color(-13395457));
    outputTextArea.setColumns(30);
    outputTextArea.setEditable(false);
    outputTextArea.setLineWrap(false);
    outputTextArea.setRows(15);
    outputTextArea.setText("");
    scrollPane1.setViewportView(outputTextArea);
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return mainPanel;
  }
}
