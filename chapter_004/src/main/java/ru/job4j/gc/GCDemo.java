package ru.job4j.gc;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;
import java.util.Scanner;

public class GCDemo {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        //info();
        Scanner sc = new Scanner(System.in);
        long start = System.currentTimeMillis();
        long end;
        List<MemoryPoolMXBean> list = null;
        System.out.println("Go?");
        if (sc.nextLine().toLowerCase().equals("y")) {
        end = System.currentTimeMillis();
        System.out.print("[" + (float) (end - start) / 1000 + "s] - start!: ");
        list = ManagementFactory.getMemoryPoolMXBeans();
        list.forEach(p -> System.out.print(p.getName() +  " = " + p.getUsage().getUsed() + " | "));
        System.out.println();
            for (int i = 0; i <= 6000; i++) {
                //if (i % 1000 == 0) {
                //System.out.println("It was " + i + " objects added. Continue? (y/n)");
                //if (sc.nextLine().toLowerCase().equals("y")) {
                if (i % 100 == 0) {
                    end = System.currentTimeMillis();
                    System.out.print("[" + (float) (end - start) / 1000 + "s] - " + i + ": ");
                    list = ManagementFactory.getMemoryPoolMXBeans();
                    list.forEach(p -> System.out.print(p.getName() +  " = " + p.getUsage().getUsed() + " | "));
                    System.out.println();
                }
                new User("user" + i, "login" + i, i, 'm');
                //    }
            }
        }
        System.out.println("Go?");
        sc.nextLine().toLowerCase().equals("y");
        //System.gc();
        //info();
    }
}