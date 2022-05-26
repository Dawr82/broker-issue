package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static int[] argsort(final int[] a, final boolean ascending) {
        Integer[] indexes = new Integer[a.length];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, (i1, i2) -> (ascending ? 1 : -1) * Integer.compare(a[i1], a[i2]));
        return asArray(indexes);
    }

    public static <T extends Number> int[] asArray(final T... a) {
        int[] b = new int[a.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[i].intValue();
        }
        return b;
    }

    public static void main( String[] args ) {
        int[][] costs = {{8, 14, 17}, {12, 9, 19}};
        List<Integer> supply = new ArrayList<>(Arrays.asList(20, 30));
        List<Integer> demand = new ArrayList<>(Arrays.asList(10, 28, 27));
        int[][] profit = null;

        // Check if it's balanced. If it's not, adjust.
        int supplyTotal = supply.stream().mapToInt(Integer::intValue).sum();
        int demandTotal = demand.stream().mapToInt(Integer::intValue).sum();

        if(supplyTotal != demandTotal){
            supply.add(demandTotal);
            demand.add(supplyTotal);
            profit = new int[supply.size()][demand.size()];
        }

        int[] orderCosts = {10, 12};
        int[] sellCosts = {30, 25, 30};

        int[][] transportation = new int[supply.size()][demand.size()];

        if(profit == null){
            profit = new int[costs.length][costs[0].length];
        }

        for(int i = 0; i < costs.length; ++i){
            for(int j = 0; j < costs[0].length; ++j){
                profit[i][j] = sellCosts[j] - orderCosts[i] - costs[i][j];
            }
        }

        // Czy liczby ujemne powinny byc po 0?
        int[][] profitSorted = new int[profit.length][];
        for(int i = 0; i < profitSorted.length; ++i){
            profitSorted[i] = argsort(profit[i], true);
        }

        System.out.println(Arrays.deepToString((profit)));
        System.out.println(Arrays.deepToString(profitSorted));

        int s = 0;
        int d = 0;
    }
}