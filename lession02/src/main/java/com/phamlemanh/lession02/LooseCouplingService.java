package com.phamlemanh.lession02;

public class LooseCouplingService {
    private SortAlgorithm sortAlgorithm;

    public LooseCouplingService(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    public void sort(int[] numbers) {
        sortAlgorithm.sort(numbers);
        System.out.println("Sorted with " + sortAlgorithm.getClass().getSimpleName() + " (Loose Coupling):");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
