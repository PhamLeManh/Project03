package com.phamlemanh.lession02;

public class TightCouplingService {
    private BubbleSortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();

    public void sort(int[] numbers) {
        bubbleSortAlgorithm.sort(numbers);
        System.out.println("Sorted with BubbleSortAlgorithm (Tight Coupling):");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
