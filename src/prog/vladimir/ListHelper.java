package prog.vladimir;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListHelper {
    public static List<String> removeFromTo(List<String> input, int from, int to) {
        List<String> output = new LinkedList<String>(input);
        ListIterator listIterator = output.listIterator(from);
        for (int i = from; i <= to; i++) {
            listIterator.next();
            listIterator.remove();
        }
        return output;
    }

    public static List<String> copyFromTo(List<String> input, int from, int to) {
        ListIterator listIterator = input.listIterator(from);
        List<String> output = new LinkedList<String>();
        for (int i = from; i <= to; i++) {
            output.add(listIterator.next().toString());
        }
        return output;
    }
}
