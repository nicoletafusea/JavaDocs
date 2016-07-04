package exercise0;
import java.util.*;
/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Iterate over the keys of a Map using keySet method (this method returns a Set of all the map keys) and
 *          print all its elements.
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughMap(){

        // TODO Exercise #0 a) Create a Map (HashMap) and add elements to it (using put() method)
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Popescu", "Marius");
        hashMap.put("Ionescu", "Gigi");
        hashMap.put("Georgescu", "George");
        // TODO Exercise #0 a) Hint: Don't forget to specify the types of the key and value when creating the Map

        // TODO Exercise #0 b) Iterate over the Map using keySet() method and print all its elements
        // TODO Exercise #0 b) The elements could be printed like this: [key1=value1, key2=value2, ...]
        System.out.print("[");
        int i = 0;
        for (String s : hashMap.keySet()) {
            if (i != hashMap.keySet().size() - 1) {
                System.out.print(s + "=" + hashMap.get(s));
                System.out.print(",");
            } else {
                System.out.print(s + "=" + hashMap.get(s));
            }
            i++;
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughMap();
    }
}
