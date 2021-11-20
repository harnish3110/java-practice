package interview.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Second {
/*
    Given a list of numbers, return a shuffled list containing every original element as well as the doubled values of every original element.
            F.ex. [1,2,4] --> [1,2,4,2,4,8] --> [4,4,2,1,2,8] (or any other permutation)

 */


    public List<Long> doublingValues(List<Long> given) {
        if (!given.isEmpty()) {
            Collections.sort(given);
            List<Long> list = new ArrayList<>(given);
            int length = given.size();
            for (int el = 0; el <= length - 1; el++) {
                Long element = given.get(el) * 2; //element at index
                list.add(element);
            }
            System.out.println("After Doubling: " + list);
            randomise(list);
            return list;
        }
        return Collections.emptyList();
    }

    public static <T> void randomise(List<T> list) {
        Random random = new Random();
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            T obj = list.get(i);
            list.set(i, list.get(j));
            list.set(j, obj);
        }

    }

/*  2. Given a list of numbers, find a possible input to the previous function that could have yielded this output, if any exists.
    That is, write an inverse function. for.ex.
    [2,4,1,8] --> [1,4]
    [1,4] --> ?
    */


    List<Long> reverse(List<Long> input) {
        if (!input.isEmpty() && input.size() % 2 == 0) {
            Collections.sort(input); //1,2,2,4,4,8
            List<Long> copy = new ArrayList<>(input); // copy of input
            List<Long> result = new ArrayList<>();
            while (!copy.isEmpty()) {
                Long element = copy.get(0);
                if (copy.contains(element * 2)) {
                    copy.remove(element);
                    copy.remove(element * 2);
                    result.add(element);
                } else {
                    return Collections.emptyList();
                }
            }
            return result;
        }
        return Collections.emptyList();
    }

    public static void main(String[] arg) {
        Second s = new Second();
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        System.out.println("Given List: " + list);
        System.out.print("Doubling: ");
        list = new ArrayList<>(s.doublingValues(list));
        System.out.println(list);
        list = new ArrayList<>(s.reverse(list));
        System.out.print("Reversing: ");
        System.out.println(list);
    }

}
