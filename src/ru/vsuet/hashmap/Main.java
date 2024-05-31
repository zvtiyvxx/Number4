package ru.vsuet.hashmap;


public class Main {
    public static void main(String[] args){
        HashMap<String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");

        map.printSize();
        System.out.println(map.printMap());

        System.out.println("седьмой элемент: " + map.get("seven"));

        map.remove("seven");
        System.out.println("седьмой элемент: " + map.get("seven"));
    }
}