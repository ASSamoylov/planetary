import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class GUI extends JFrame {

    final String[] nameTabs = {
            "Текст",
            "Превью",
            "Техническая информация",
            "Текущая строка",
            "Работа с презентацией",
            "Console (Browser)"
    }; // Добавлять новые вкладки с конца!!

    final String [] pageNames = {"Java",
    "C++",
    "Python",
    "C#",
    "Delphi"};
    final JButton buttonStop = new JButton("Стоп");
    final JButton buttonPause = new JButton("||");
    final JButton buttonNext = new JButton(">");
    final JButton buttonPrev = new JButton("<");
    JComboBox<String> pageBox = new JComboBox<>(pageNames);

    JLabel name = new JLabel();
    JLabel timeGo = new JLabel();
    JLabel timeEnd = new JLabel();
    JLabel pauseStatus = new JLabel();
    boolean onPause = false;
    Border solidBorder = BorderFactory.createLineBorder(Color.GRAY, 1, true);

    Font fontReadText = new Font("Dialog", Font.PLAIN, 16);
    Font fontTechInfo = new Font(null, Font.PLAIN, 20);

    //TODO: После прочтения - сжечь!
    String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dignissim urna in nisi tempus, nec laoreet justo lobortis. Curabitur vestibulum nunc magna, ut blandit erat posuere eget. Sed ac rutrum magna. Phasellus hendrerit interdum lectus, lacinia bibendum lectus rhoncus nec. Etiam feugiat blandit semper. Nunc aliquet odio a imperdiet porta. Sed bibendum tellus et eros sodales, ut facilisis nulla vulputate. Mauris quis enim sem. Morbi commodo sit amet lectus bibendum vehicula. Fusce non fermentum ligula, consectetur sagittis lectus. Nam dignissim lorem eget libero aliquam semper eu et urna. Quisque ac velit ligula. Praesent maximus, mi in congue imperdiet, lectus diam fringilla orci, ac pharetra sapien ipsum vel leo. Donec mattis, dolor sit amet cursus aliquam, dolor massa rhoncus est, non ornare eros neque vitae augue. Suspendisse sollicitudin enim ex, et congue dolor aliquet sit amet. Duis fringilla, dui at laoreet pellentesque, tellus ligula placerat mi, ut elementum dui sem eget ex.\n" +
            "\n" +
            "Etiam quis sapien sit amet felis tempor vestibulum. Proin at tortor blandit, laoreet tortor id, finibus eros. Nullam in lacus in mi mollis vehicula in quis metus. Suspendisse ac turpis venenatis, semper quam tempus, interdum lacus. Cras risus nunc, bibendum sed augue in, semper cursus quam. Sed et lacus odio. Sed fringilla ligula sit amet augue tincidunt, id ultrices ex viverra. Suspendisse nisi ex, imperdiet sit amet dapibus ac, aliquet sed lectus. Quisque accumsan felis sit amet elit congue lacinia. Curabitur iaculis orci felis, at varius ipsum gravida nec. Sed ac tincidunt dui, ut auctor dolor.\n" +
            "\n" +
            "Maecenas a volutpat arcu. Aliquam maximus leo quis pulvinar ornare. Vestibulum id justo vel neque aliquam auctor sit amet at mi. Curabitur enim odio, vestibulum eu diam a, tempus accumsan eros. Aliquam quis neque et nisi dapibus ullamcorper. Nullam bibendum mattis libero, eget sodales felis tristique eget. Sed eleifend laoreet nisi vel tempus. In vitae posuere lectus, at molestie diam. \n" +
            "Etiam quis sapien sit amet felis tempor vestibulum. Proin at tortor blandit, laoreet tortor id, finibus eros. Nullam in lacus in mi mollis vehicula in quis metus. Suspendisse ac turpis venenatis, semper quam tempus, interdum lacus. Cras risus nunc, bibendum sed augue in, semper cursus quam. Sed et lacus odio. Sed fringilla ligula sit amet augue tincidunt, id ultrices ex viverra. Suspendisse nisi ex, imperdiet sit amet dapibus ac, aliquet sed lectus. Quisque accumsan felis sit amet elit congue lacinia. Curabitur iaculis orci felis, at varius ipsum gravida nec. Sed ac tincidunt dui, ut auctor dolor.\n" +
            "\n" +
            "Maecenas a volutpat arcu. Aliquam maximus leo quis pulvinar ornare. Vestibulum id justo vel neque aliquam auctor sit amet at mi. Curabitur enim odio, vestibulum eu diam a, tempus accumsan eros. Aliquam quis neque et nisi dapibus ullamcorper. Nullam bibendum mattis libero, eget sodales felis tristique eget. Sed eleifend laoreet nisi vel tempus. In vitae posuere lectus, at molestie diam. \n" +
            "Etiam quis sapien sit amet felis tempor vestibulum. Proin at tortor blandit, laoreet tortor id, finibus eros. Nullam in lacus in mi mollis vehicula in quis metus. Suspendisse ac turpis venenatis, semper quam tempus, interdum lacus. Cras risus nunc, bibendum sed augue in, semper cursus quam. Sed et lacus odio. Sed fringilla ligula sit amet augue tincidunt, id ultrices ex viverra. Suspendisse nisi ex, imperdiet sit amet dapibus ac, aliquet sed lectus. Quisque accumsan felis sit amet elit congue lacinia. Curabitur iaculis orci felis, at varius ipsum gravida nec. Sed ac tincidunt dui, ut auctor dolor.\n" +
            "\n" +
            "Maecenas a volutpat arcu. Aliquam maximus leo quis pulvinar ornare. Vestibulum id justo vel neque aliquam auctor sit amet at mi. Curabitur enim odio, vestibulum eu diam a, tempus accumsan eros. Aliquam quis neque et nisi dapibus ullamcorper. Nullam bibendum mattis libero, eget sodales felis tristique eget. Sed eleifend laoreet nisi vel tempus. In vitae posuere lectus, at molestie diam.";

    public GUI(String title) {
        super(title);
        this.setBounds(100, 100, 900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //TODO: Вместо panel.add(new JButton...) сделать нормальный интерфейс
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        for (int i = 0; i < nameTabs.length; i++) {
            JPanel panel = new JPanel();
            switch (i) {
                // Вкладка 1: Текст слайда
                case (0):
                    JTextArea presetText = new JTextArea(21, 65);
                    JScrollPane scrollPane = new JScrollPane(presetText);

                    presetText.setFont(fontReadText);
                    presetText.setText(lorem);

                    presetText.setLineWrap(true);
                    presetText.setWrapStyleWord(true);

                    JButton pushEdit = new JButton("Сохранить");

                    pushEdit.addActionListener(e -> {
                        //TODO: Добавить действие

                    });

                    panel.add(scrollPane);
                    panel.add(pushEdit);

                    tabbedPane.addTab(nameTabs[i], panel);
                    break;

                // Вкладка 2: Превью презентации
                case (1):
                    BufferedImage bufferedImage;
                    try {
                        File fileImage = new File("image.gif"); //картинку сюда
                        bufferedImage = ImageIO.read(fileImage);
                        Image image = bufferedImage.getScaledInstance(450, 450, Image.SCALE_AREA_AVERAGING);
                        ImageIcon icon = new ImageIcon(image);
                        JLabel label = new JLabel();
                        label.setIcon(icon);
                        panel.add(label);
                    } catch (IOException e) {
                        e.printStackTrace();
                        panel.add(new JLabel("Изображение не найдено"));
                    }
                    tabbedPane.addTab(nameTabs[i], panel);
                    break;

                // Вкладка 3: Техническая информация:
                // текущее видео (название), время, оставшееся время, индикация паузы
                case (2):
                    JPanel rightPanel = new JPanel();
                    rightPanel.setLayout(new GridLayout(4, 1));

                    name.setText("name");
                    name.setFont(fontTechInfo);
                    name.setBorder(solidBorder);
                    timeGo.setText("timeGo");
                    timeGo.setFont(fontTechInfo);
                    timeGo.setBorder(solidBorder);
                    timeEnd.setText("timeEnd");
                    timeEnd.setFont(fontTechInfo);
                    timeEnd.setBorder(solidBorder);
                    pauseStatus.setText(onPause ? "||" : ">");
                    pauseStatus.setFont(fontTechInfo);
                    pauseStatus.setBorder(solidBorder);

                    rightPanel.add(name);
                    rightPanel.add(timeGo);
                    rightPanel.add(timeEnd);
                    rightPanel.add(pauseStatus);

                    JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    panel1.add(createLeftPanel());
                    panel1.add(rightPanel);

                    tabbedPane.addTab(nameTabs[i], panel1);
                    break;
                // Вкладка 4: Текущая строка презентации (с будущей возможностью редактирования)
                case (3):
                    JLabel label = new JLabel("Редактор кода");
                    JTextArea presentCode = new JTextArea(20, 65);
                    JScrollPane scrollCodePane = new JScrollPane(presentCode);

                    presentCode.setFont(fontReadText);
                    presentCode.setText(lorem);

                    presentCode.setLineWrap(true);
                    presentCode.setWrapStyleWord(true);

                    JButton pushCodeEdit = new JButton("Сохранить");

                    pushCodeEdit.addActionListener(e -> {
                        //TODO: Добавить действие

                    });

                    panel.add(label);
                    panel.add(scrollCodePane);
                    panel.add(pushCodeEdit);

                    tabbedPane.addTab(nameTabs[i], panel);

                    break;

                // Вкладка 5: Работа с презентацией
                /*
                добавить папку
                стереть пустую папку
                добавить презентация
                стереть из списка презентаций (не с диска)
                выбор презентация
                быстрый доступ (5-10 презентаций) к часто используемым.
                 */
                //TODO: сделать интерфейс + функционал
                case (4):
                    JButton buttonOpenFile = new JButton("Выбрать файл");
                    JLabel chosenFile =  new JLabel("пока ничего");
                    JFileChooser fileChooser = new JFileChooser();

                    buttonOpenFile.addActionListener(e -> {
                        int ret = fileChooser.showDialog(null, "Открыть файл");
                        if (ret == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            chosenFile.setText(file.getName());
                            /*
                             * Какие-то действия.
                             */
                        }
                    });

                    panel.add(chosenFile);
                    panel.add(buttonOpenFile);

                    tabbedPane.addTab(nameTabs[i], panel);
                    break;

                // Вкладка 6: Сообщение от браузера
                case (5):
                    JTextArea messageArea = new JTextArea(20, 65);
                    JScrollPane scrollMessagePane = new JScrollPane(messageArea);
                    JTextField sendMessageField = new JTextField("", 55);
                    sendMessageField.setFont(fontReadText);

                    messageArea.setFont(fontReadText);
                    messageArea.setText(lorem);

                    messageArea.setLineWrap(true);
                    messageArea.setWrapStyleWord(true);

                    JButton sendMessage = new JButton("Отправить");

                    sendMessage.addActionListener(e -> {
                        //TODO: Добавить действие

                    });

                    panel.add(scrollMessagePane);
                    panel.add(sendMessageField);
                    panel.add(sendMessage);

                    tabbedPane.addTab(nameTabs[i], panel);
                    break;

                default:
                    panel.add(new JLabel("Необходимо добавить \"case(n)\" с необходимыми элементами"));
                    tabbedPane.addTab(nameTabs[i], panel);

            }
        }


        getContentPane().add(tabbedPane);
        getContentPane().add(createBottomPanel(), BorderLayout.SOUTH);


        setVisible(true);
    }

    JPanel createBottomPanel () {
        JPanel bottomPanel = new JPanel(new GridLayout());
        buttonNext.addActionListener(e -> {
            System.out.println("сработала кнопка вперед");
        });
        buttonPause.addActionListener(e -> {
            System.out.println("сработала кнопка пауза");
        });
        buttonPrev.addActionListener(e -> {
            System.out.println("сработала кнопка назад");
        });
        buttonStop.addActionListener(e -> {
            System.out.println("сработала кнопка стоп");
        });
        pageBox.addActionListener(a -> {
            String x = String.valueOf(pageBox.getSelectedItem());
            System.out.println("способ рабочий " + x);

        });

        bottomPanel.add(buttonPrev);
        bottomPanel.add(buttonNext);
        bottomPanel.add(buttonStop);
        bottomPanel.add(buttonPause);
        pageBox.setEditable(true);
        bottomPanel.add(pageBox);

        bottomPanel.setBackground(Color.BLUE);

        return bottomPanel;
    }

    JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        JLabel name = new JLabel("Текущее видео: ");
        JLabel timeGo = new JLabel("Время: ");
        timeGo.setFont(fontTechInfo);
        JLabel timeEnd = new JLabel("Оставшееся время: ");
        timeEnd.setFont(fontTechInfo);
        JLabel pauseStatus = new JLabel("Пауза: ");
        pauseStatus.setFont(fontTechInfo);

        name.setVerticalAlignment(JLabel.CENTER);
        name.setHorizontalAlignment(JLabel.RIGHT);
        name.setFont(fontTechInfo);
        name.setBorder(solidBorder);

        timeGo.setVerticalAlignment(JLabel.CENTER);
        timeGo.setHorizontalAlignment(JLabel.RIGHT);
        timeGo.setFont(fontTechInfo);
        timeGo.setBorder(solidBorder);

        timeEnd.setVerticalAlignment(JLabel.CENTER);
        timeEnd.setHorizontalAlignment(JLabel.RIGHT);
        timeEnd.setFont(fontTechInfo);
        timeEnd.setBorder(solidBorder);

        pauseStatus.setVerticalAlignment(JLabel.CENTER);
        pauseStatus.setHorizontalAlignment(JLabel.RIGHT);
        pauseStatus.setFont(fontTechInfo);
        pauseStatus.setBorder(solidBorder);

        panel.add(name);
        panel.add(timeGo);
        panel.add(timeEnd);
        panel.add(pauseStatus);


        return panel;
    }


}
