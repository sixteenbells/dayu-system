package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/12/5 7:00 PM
 * @description :
 */
public class ContainerWater_11 {
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,8,6,2,5,4,8,3,7};
        int maxArea = maxArea(array);
        System.out.println("max area:" + maxArea);
    }
}
