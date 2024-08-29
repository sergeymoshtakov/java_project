package com.company;

import com.company.models.User;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Базовая работа с List
        List<String> list1 = new ArrayList<>();
        String[] array = new String[]{"a", "b", "c", "d", "e", "f"};
        list1.addAll(Arrays.asList(array));
        List<String> list2 = new ArrayList<>(list1);
        List<String> list3 = Arrays.asList(array);
        list2.addAll(list2.size()/2, list3);
        list2.sort(Comparator.reverseOrder());
        ListIterator<String> iterator = list2.listIterator();
        boolean removeFlag = false;
        while (iterator.hasNext()) {
            iterator.next();
            if (removeFlag) {
                iterator.remove();
            }
            removeFlag = !removeFlag;
        }

        // Базовая работа с Set
        Set<String> set1 = new HashSet<>();
        set1.add("Hello");
        set1.add("World");
        set1.addAll(list1);
        set1.addAll(list2);
        System.out.println(set1);
        Set<String> set2 = new LinkedHashSet<>();
        set2.addAll(list2);
        set2.addAll(list3);
        System.out.println(set2);

        // Базовая работа с Map
        Map<Integer, String> map1 = new LinkedHashMap<>();
        map1.put(0, "January");
        map1.put(1, "February");
        map1.put(2, "March");
        map1.put(3, "April");
        map1.put(4, "May");
        map1.put(5, "June");
        map1.put(6, "July");
        map1.put(7, "August");
        map1.put(8, "September");
        map1.put(9, "October");
        map1.put(10, "November");
        map1.put(11, "December");
        System.out.println("First month: " + map1.get(0));
        System.out.println("Last month: " + map1.get(11));
        map1.put(5, "ОТПУСК");
        System.out.println(map1);
        Map<Integer, String> map2 = new HashMap<>();
        map2.putAll(map1);
        printMap(map1);
        printMap(map2);
        Map<String, Set<String>> map3 = new HashMap<>();
        Set<String> contactsJohn = new HashSet<>();
        contactsJohn.add("+380771234567");
        contactsJohn.add("sergey@gmail.com");
        contactsJohn.add("sergey_skype");
        map3.put("Sergey", contactsJohn);

        // Работа с своим классом
        Set<User> userSet = new HashSet<>();
        User user1 = new User("Sergey", 19, "+380771234567");
        User user2 = new User("Sergey", 19, "+380771234567");
        User user3 = new User("Sergey", 20, "+380771234567");
        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);
        System.out.println(userSet);
    }

    public static void printMap(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}