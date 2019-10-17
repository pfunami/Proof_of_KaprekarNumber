import java.util.Arrays;
import java.util.Collections;

public class Proof_4digitsKaprekarNum {
    public static int[] dArray = new int[10000];

    public static void main(String args[]) {
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
                        if (d > c || a == d) {
                            continue;
                        }
                        int Num = 1000 * a + 100 * b + 10 * c + d;
                        diff = sortdiff(Num);

                        dArray[Num] = diff;
                        System.out.println(Num+ "：" + diff);
                    }
                }
            }
        }
        for (int i = 0; i <= 9999; i++) {
            if (dArray[i] != 0) {
                if (!search(i)) {
                    System.out.println("Out!    i=" + i + ", num:" + dArray[i]);
                }
            }
            if (i == 9999) {
                System.out.println("Comp!");
            }
        }


    }

    public static boolean search(int num) {
        if (dArray[num] == 6174) {
            return true;
        } else if (dArray[num] == 0) {
            System.out.println(num);
            return false;
        } else {
            int diff = sortdiff(dArray[num]);
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

