package org.luncert.algorithm.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestLinkedList {

    LinkedList<Integer> list = new LinkedList<>();

    @Test
    public void test() {
        for (int i = 0; i < 10; i++)
            list.add(i);
        list.add(0, 13);
        list.add(5, 14);
        System.out.println(list.size());
        System.out.println(list);

        list.remove(new Integer(9));
        System.out.println(list);

        System.out.println(list.get(3));
        System.out.println(list.set(3, new Integer(100)));
        System.out.println(list.get(3));

        System.out.println(list);
        System.out.println(list.ringDetection());
    }

    @Test
    public void test1() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100; i++)
            list.add(i);
        maxK(list, 4);
    }

    /**
     * 最大子数组问题
     */
    private List<Integer> maxK(List<Integer> list, int k) {
        if (k < list.size()) {
            int sum = 0;
            for (int i = 0; i < k; i++)
                sum += list.get(i);
            for (int i = k, leftSum = 0, rightSum = 0, ci = 0, cj = 0, limit = list.size(); i < limit; i++) {
                leftSum += list.get(ci + cj);
                rightSum += list.get(i);
                if (i - ci <= 2 * k) {
                    if (rightSum > leftSum) {
                        sum += (rightSum - leftSum);
                        ci += cj + 1;
                        cj = rightSum = leftSum = 0;
                    }
                    else if (++cj == k)
                        cj = rightSum = leftSum = 0;
                }
                else if (cj + 1 == k) {
                    if (rightSum > leftSum)
                        ci = i - k;
                    cj = rightSum = leftSum = 0;
                }
            }
            System.out.println(sum);
        }
        return null;
    }

}