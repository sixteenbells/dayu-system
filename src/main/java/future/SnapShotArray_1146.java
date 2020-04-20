package future;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/5 10:07 PM
 * @description : https://leetcode.com/problems/snapshot-array/
 */
public class SnapShotArray_1146 {

    public static void main(String[] args) throws Exception {
        SnapshotArray snapshotArr = new SnapshotArray(1); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        snapshotArr.set(0,16);  // Set array[0] = 5
        snapshotArr.set(0,13);  // Set array[0] = 5
        int snpap_id1 = snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        int element = snapshotArr.get(0,0);
        int snpap_id2 = snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        System.out.println("finish");
    }


    static class SnapshotArray {

        private static final List<Integer[]> elementList = new LinkedList<>();

        public SnapshotArray(int length) {
            Integer[] array = new Integer[length];
            elementList.add(array);
        }

        public void set(int index, int val) {
            Integer[] array = elementList.get(elementList.size() - 1);
            array[index] = val;
        }

        public int snap() {
            Integer[] array = elementList.get(elementList.size() - 1);
            Integer[] copyArray = new Integer[array.length];
            System.arraycopy(array, 0, copyArray, 0, array.length);
            elementList.add(copyArray);
            return elementList.size() - 2;
        }

        public int get(int index, int snap_id) {
            return elementList.get(snap_id)[index];
        }
    }
}
