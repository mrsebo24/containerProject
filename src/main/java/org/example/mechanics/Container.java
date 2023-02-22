package org.example.mechanics;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Container extends Save{
    private String name;
    private double capacity;
    private double amountWater;
    private final List<Save> list = new ArrayList<>();

    public Container(String name, double capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void add(double amount){
        if((amountWater + amount) <= capacity) {
            setAmountWater(getAmountWater() + amount);
            list.add(new Save(getDate(), "add", getName(), amount, true));
        } else if ((amountWater + amount) > capacity) {
            list.add(new Save(getDate(), "add", getName(), amount, false));
        }
    }

    public void removee(double amount){
        if(amountWater - amount >= 0){
            amountWater = amountWater - amount;
            list.add(new Save(getDate(), "removee", getName(), amount, true));
        }
        else if(amountWater - amount < 0){
            setAmountWater(0);
            list.add(new Save(getDate(), "removee", getName(), amount, false));
        }
    }

    public void tradeWater(Container container, double amount){
        if((this.getAmountWater() + amount) <= this.getCapacity() && (container.getAmountWater() - amount) >= 0){
            this.add(amount);
            container.removee(amount);
        }
        if((this.getAmountWater() + amount) > this.getCapacity() && (this.getAmountWater() - amount) < 0){
            this.list.add(new Save(getDate(), "add", getName(), amount, false));
            container.list.add(new Save(getDate(), "removee", getName(), amount, false));
        }
    }
    public List<Save> getList() {
        return list;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getCapacity() {
        return capacity;
    }
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
    public Double getAmountWater() {
        return amountWater;
    }
    public void setAmountWater(double amountWater) {
        this.amountWater = amountWater;
    }


    public static void nullContainer(Container [] containers){
        for (Container container : containers) {
            if (container.getAmountWater() == 0) {
                System.out.println(container);
            }
        }
    }

    public static void mostAmountWater(Container [] containers){
        Map<String, Double> map = getStringIntigerMap(containers);
        Optional<Double> value = getaDouble(map);
        for (Map.Entry<String, Double> stringDoubleEntry : map.entrySet()) {
            if(Objects.equals(stringDoubleEntry.getValue(), value.get()))
                System.out.println(stringDoubleEntry);
        }

    }

    private static Map<String, Double> getStringIntigerMap(Container[] containers) {
        Map<String, Double> map = new HashMap<>();
        for (Container container : containers) {
            map.put(container.getName(), container.getAmountWater());
        }
        return map;
    }

    public static void mostFullContainer(Container [] containers) {
        Map<String, Double> map = getPercentFillingMap(containers);
        Optional<Double> value = getaDouble(map);
        for (Map.Entry<String, Double> stringDoubleEntry : map.entrySet()) {
            if(Objects.equals(stringDoubleEntry.getValue(), value.get()))
                System.out.println(stringDoubleEntry);
        }
    }

    @NotNull
    private static Optional<Double> getaDouble(Map<String, Double> map) {
        Optional<Double> value = getCollect(map).values().stream().max(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o1 > o2)
                    return 1;
                else if (o1 < o2)
                    return -1;
                else return 0;
            }
        });
        return value;
    }

    @NotNull
    private static Map<String, Double> getPercentFillingMap(Container[] containers) {
        Map<String, Double> map = new HashMap<>();
        for (Container container : containers) {
            map.put(container.getName(), ((container.getAmountWater() * 100) / container.getCapacity()));
        }
        return map;
    }

    @NotNull
    private static LinkedHashMap<String, Double> getCollect(Map<String, Double> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
