import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String title = "Приветсвтвенный экран";
        GUI gui = new GUI(title);
        gui.setVisible(true);

        Person arina = new Person(16, "Arisha");
        List<Person> personList = new ArrayList<>();

        personList.add(arina);
        personList.add(new Person(20,"Kostyan"));

        for (int i = 0; i < 2; i++)
        System.out.println(personList.get(i));
    }
}
