import java.util.*;
import java.util.Collections;

public class Proof_4digitsKaprekarNum {
    public static int[] dArray = new int[10000];
    public static HashSet<Integer> candKaprekar = new HashSet<Integer>();

    public static void main(String args[]) {
        System.out.println("最大の数：M, 最小の数との差：D");
        System.out.println("\tM\t：\tD");

        /*４桁の自然数からできる最大の数と、最大の数と最小の数との差の対応*/
        int a, b, c, d;
        int diff;
        for (int i = 0; i <= 9999; i++) {
            dArray[i] = 0;
        }
        for (a = 1; a <= 9; a++) {
            for (b = 0; b <= 9; b++) {
                if (b > a) {
                    continue;
                }
                for (c = 0; c <= 9; c++) {
                    if (c > b) {
                        continue;
                    }
                    for (d = 0; d <= 9; d++) {
                        int Num = 1000 * a + 100 * b + 10 * c + d;
                        if (d > c || a == d || Num % 9 != 0) {
                            continue;
                        }
                        diff = sortdiff(Num);
                        dArray[Num] = diff;
                        System.out.println(Num + "\t：\t" + diff);
                    }
                }
            }
        }

        /*カプレカ数が一意に定まり、かつそれが6174であることの証明*/
        System.out.println("\nStart proof......");
        for (int i = 0; i <= 9999; i++) {
            if (dArray[i] != 0) {
                System.out.print(dArray[i]);
                if (!search(i)) {
                    System.out.println("Out!    i=" + i + ", num:" + dArray[i]);
                }
            }
            if (i == 9999) {
                System.out.println();
                if (candKaprekar.size() == 1) {
                    for (int Kap : candKaprekar) {
                        System.out.println("Kaprekar Number is only " + Kap);
                    }
                } else {
                    for (int Kap : candKaprekar) {
                        System.out.println("Kaprekar Number is " + Kap);
                    }
                }
                System.out.println("Complete!");
            }
        }

    }

    public static boolean search(int num) {
        if (dArray[num] == sortdiff(dArray[num])) {
            System.out.println(" → " + dArray[num] + " ○");
            candKaprekar.add(dArray[num]);
            return true;
        } else if (dArray[num] == 0) {
            System.out.print(" → ×");
            System.out.println(num);
            return false;
        } else {
            int diff = sortdiff(dArray[num]);
            System.out.print(" → " + diff);
            return search(sort(diff));
        }
    }

    public static int sortdiff(int o) {
        Integer[] Num = common(o);
        return 9 * (111 * Num[0] + 10 * Num[1] - 10 * Num[2] - 111 * Num[3]);
    }

    public static int sort(int o) {
        Integer[] Num = common(o);
        return 1000 * Num[0] + 100 * Num[1] + 10 * Num[2] + Num[3];
    }

    public static Integer[] common(int o) {
        int a, b, c, d;
        a = o / 1000;
        b = (o - a * 1000) / 100;
        c = (o - a * 1000 - b * 100) / 10;
        d = o % 10;
        Integer[] Num = {a, b, c, d};
        Arrays.sort(Num, Collections.reverseOrder());
        return Num;
    }
}