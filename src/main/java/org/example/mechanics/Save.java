package org.example.mechanics;

import java.util.*;
import java.util.stream.Collectors;

public class Save {
    private Date date = new Date();
    private String methodName;
    private String containerName;
    private Double amountWater;
    private Boolean succes;
    private int size;

    protected Save() {
    }

    protected Save(Date date, String methodName, String containerName, Double amountWater, Boolean succes) {
        this.date = date;
        this.methodName = methodName;
        this.containerName = containerName;
        this.amountWater = amountWater;
        this.succes = succes;
    }

    public void getMostUsedMethod(Container [] containers, String method){
        Map<String, Integer> map = mapMethodFilter(mapCreator(containers), method);
        Map.Entry<String, Integer> lastElement = null;
        for (Map.Entry<String, Integer> stringIntegerEntry : extracted(map).entrySet()) {
            lastElement = stringIntegerEntry;
        }
        System.out.println(lastElement);
    }

    private LinkedHashMap<String, Integer> extracted(Map<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public void getMostFalse(Container [] containers){
        Map<String, Integer> map = mapFalseFilter(mapCreator(containers));
        Map.Entry<String, Integer> lastElement = null;
        for (Map.Entry<String, Integer> stringIntegerEntry : extracted(map).entrySet()) {
            lastElement = stringIntegerEntry;
        }
        System.out.println(lastElement);
    }

    private Map<String, Integer> mapMethodFilter(Map<String, List<Save>> map, String methodName){
        Map<String, Integer> integerMap = new HashMap<>();
        for (Map.Entry<String, List<Save>> stringListEntry : map.entrySet()) {
            for (int i = 0; i < stringListEntry.getValue().size(); i++) {
                if (stringListEntry.getValue().get(i).getMethodName().equals(methodName)) {
                    integerMap.put(stringListEntry.getKey(), i+1);
                }
            }
        }
        return integerMap;
    }

    private Map<String, Integer> mapFalseFilter(Map<String, List<Save>> map){
        Map<String, Integer> integerMap = new HashMap<>();
        int falseNumber = 0;
        for (Map.Entry<String, List<Save>> stringListEntry : map.entrySet()) {
            for (int i = 0; i < stringListEntry.getValue().size(); i++) {
                if(!stringListEntry.getValue().get(i).getSucces()) {
                    falseNumber++;
                    integerMap.put(stringListEntry.getKey(), falseNumber);
                }
            }
        }
        return integerMap;
    }

    private Map<String, List<Save>> mapCreator(Container [] containers){
        Map<String, List<Save>> map = new HashMap<>();
        for (Container container : containers) {
            if (container.getList().size() > 0)
                map.put(container.getName(), container.getList());
        }
        return map;
    }

    public Date getDate() {
        return date;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getContainerName() {
        return containerName;
    }

    public Double getAmountWater() {
        return amountWater;
    }

    public Boolean getSucces() {
        return succes;
    }

    @Override
    public String toString() {
        return "Save{" +
                "date=" + date +
                ", methodName='" + methodName + '\'' +
                ", containerName='" + containerName + '\'' +
                ", amountWater=" + amountWater +
                ", succes=" + succes +
                '}';
    }
}
