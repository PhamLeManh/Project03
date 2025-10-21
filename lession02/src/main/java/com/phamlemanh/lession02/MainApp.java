package com.phamlemanh.lession02;

public class MainApp {
    public static void main(String[] args) {
        int[] numbers1 = {5, 1, 4, 2, 8};
        int[] numbers2 = {10, 7, 8, 9, 1, 5};

        // Tight Coupling (phụ thuộc cứng vào BubbleSort)
        TightCouplingService tightService = new TightCouplingService();
        tightService.sort(numbers1);

        System.out.println("--------------------------------");

        // Loose Coupling (linh hoạt)
        LooseCouplingService looseService1 = new LooseCouplingService(new BubbleSortAlgorithm());
        looseService1.sort(numbers1);

        LooseCouplingService looseService2 = new LooseCouplingService(new QuickSortAlgorithm());
        looseService2.sort(numbers2);
    }
}
