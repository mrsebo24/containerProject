package org.example.run;

import org.example.mechanics.Container;

public class Main {
    public static void main(String[] args) {
        Container[] containers = new Container[8];
        containers[0] = new Container("sebastian", 50);
        containers[1] = new Container("michal", 50);
        containers[2] = new Container("kamil", 50);
        containers[3] = new Container("andrzej", 50);
        containers[4] = new Container("waldek", 50);
        containers[5] = new Container("grzegorz", 50);
        containers[6] = new Container("slawomir", 50);
        containers[7] = new Container("eustachy", 50);
        //6
        containers[0].add(5);
        containers[0].add(5);
        containers[0].add(5);
        containers[0].add(5);
        containers[0].add(5);
        containers[0].add(5);
        containers[0].add(5);
        containers[0].add(5);
        containers[0].add(5);containers[0].add(5);
        containers[0].add(5);
        containers[0].add(5);


        //4
        containers[1].add(5);
        containers[1].add(5);
        containers[1].add(5);
        containers[1].add(5);
        //5
        containers[4].add(5);
        containers[4].add(4);
        containers[4].add(4);
        containers[4].add(4);
        containers[4].add(5);
        //7
        containers[6].add(5);
        containers[6].add(5);
        containers[6].add(5);
        containers[6].add(5);
        containers[6].add(5);
        containers[6].add(5);
        containers[6].add(5);
        containers[6].add(5);
        containers[6].add(5);
        containers[6].add(5);containers[6].add(5);containers[6].add(5);
        containers[6].add(5);
        containers[6].add(5);

        containers[1].getMostFalse(containers);
    }
}