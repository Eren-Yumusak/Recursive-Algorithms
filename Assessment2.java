import java.util.Arrays;

/**
 * Assessment2 class
 *
 * @author (your name)
 */
public class Assessment2
{
    /*
     * merge method for Task 1.1
     */
    public static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < a.length && j < b.length){
            if(a[i] <= b[j]){
                c[k] = a[i];
                i++;
            }else{
                c[k] = b[j];
                j++;
            }
            k++;
        }
        while(i < a.length){
            c[k] = a[i];
            i++;
            k++;
        }
        while( j < b.length){
            c[k] = b[j];
            j++;
            k++;
        }
        return c;
    }

    /*
     * mergesort method for Task 1.2
     */
    public static int[] mergesort(int[] arr) {
        int n = arr.length;
        if(n < 2){
            return arr;
        }
        int m = n/2;

        int[] leftHalf = new int[m];
        int[] rightHalf = new int[n - m];

        for(int i =0; i < m; i++){
            leftHalf[i] = arr[i];
        }
        for (int i = m; i < n; i++){
            rightHalf[i - m] = arr[i];
        }
        leftHalf = mergesort(leftHalf);
        rightHalf = mergesort(rightHalf);

        int[] sortedArr = merge(leftHalf,rightHalf);
        return sortedArr;
    }

    /*
     * merge3 method for Task 1.3
     */
    public static int[] merge3(int[] a, int[] b, int[] c) {
        int[] array1 = merge(a,b);
        int[] array3 = merge(array1,c);
        return array3;
    }

    /*
     * mergesort3 method for Task 1.4
     */
    public static int[] mergesort3(int[] arr) {
        int n = arr.length;
        if(n < 3){
            return arr;
        }
        int firstThird = n/3;
        int secondThird = firstThird + firstThird;

        int[] leftThird = new int[firstThird];
        int[] middleThird = new int[secondThird];
        int[] rightThird = new int[n - firstThird];

        for(int i =0; i < firstThird; i++){
            leftThird[i] = arr[i];
        }
        //System.out.println("left Third = " + Arrays.toString(leftThird));
        for (int i = firstThird; i < secondThird; i++){
            middleThird[i - firstThird] = arr[i];
        }
        //System.out.println("middle Third = " + Arrays.toString(middleThird));
        for(int i = secondThird; i < n; i++){
            rightThird[i - secondThird] = arr[i];
        }
        //System.out.println("right Third = " + Arrays.toString(rightThird));
        leftThird = mergesort3(leftThird);
        middleThird = mergesort3(middleThird);
        rightThird = mergesort3(rightThird);

        int[] sortedArr = merge3(leftThird,middleThird,rightThird);

        int noZeroes = 0;

        for(int i=0; i < sortedArr.length; i++){
            if(sortedArr[i] != 0){
                noZeroes++;
            }
        }

        int[] tempArr = new int[noZeroes];
        int tempIndex = 0;

        for(int j = 0; j < sortedArr.length; j++){
            if(sortedArr[j] != 0){
                tempArr[tempIndex] = sortedArr[j];
                tempIndex++;
            }
        }
        sortedArr = tempArr;
        return sortedArr;
    }

    /*
     * mergeAll method for Task 1.5
     */
    public static int[] mergeAll(int[][] arrays) {
        int n = 0;
        int temp = 0;
        for(int[] a : arrays){
            n += a.length;
        }
        int[] result = new int[n];
        int[] index = new int[arrays.length];
        for(int i =0; i < n; i++){
            int minJ = -1;
            int minValue = 0;
            for(int j=0; j < arrays.length; j++){
                if(index[j] < arrays[j].length){
                    int value = arrays[j][index[j]];
                    if(minJ == -1 || value < minValue){
                        minJ = j;
                        minValue = value;
                    }
                }
            }
            result[i] = minValue;
            index[minJ]++;
        }
        return result;
    }

    /*
     * mergesortK method for Task 1.6
     */
    public static int[] mergesortK(int[] arr, int k) {
        int n = arr.length;
        if(n < k){
            return arr;
        }
        int m = n/2;
        int[] leftHalf = new int[m];
        int[] rightHalf = new int[n - m];

        for(int i =0; i < m; i++){
            leftHalf[i] = arr[i];
        }
        for (int i = m; i < n; i++){
            rightHalf[i - m] = arr[i];
        }
        for (int i = 0; i <leftHalf.length; i++) {     
          for (int j = i+1; j <leftHalf.length; j++) {     
              if(leftHalf[i] >leftHalf[j]) {      //swap elements if not in order
                 int temp = leftHalf[i];    
                 leftHalf[i] = leftHalf[j];    
                 leftHalf[j] = temp;    
               }     
            }     
        }  
        for (int i = 0; i <rightHalf.length; i++) {     
          for (int j = i+1; j <rightHalf.length; j++) {     
              if(rightHalf[i] >rightHalf[j]) {      //swap elements if not in order
                 int temp = rightHalf[i];    
                 rightHalf[i] = rightHalf[j];    
                 rightHalf[j] = temp;    
               }     
            }     
        }  
        leftHalf= mergesortK(leftHalf,k);
        rightHalf = mergesortK(rightHalf,k);
        int[][] result = {leftHalf,rightHalf};
        int[] sortedArr = mergeAll(result);
        return sortedArr;
    }

    /*
     * maxResources method for Task 2.1
     */
    public static int maxResources(int[][] mine) {
        int i = mine[0].length;
        int result = -1;
        for(int columns = 0;columns < i; columns++){
            result = Math.max(result, maxResources(mine,0,columns));
        }
        return result;
    }
    
    public static int maxResources(int[][] mine, int rows, int columns) {
        int j = mine.length;
        int i = mine[0].length;
        if(columns <0 || columns>=i){
            return -1;
        }
        int result = mine[rows][columns];
        if(rows + 1 < j){
            result = result + Math.max(maxResources(mine, rows + 1,columns-1), maxResources(mine, rows + 1, columns + 1));
        }
        return result;
    }

    /*
     * maxResources method for Task 2.2
     */
    public static int maxResourcesM(int[][] mine) {
        int result = -1;
        int j = mine.length;
        int i = mine[0].length;
        int[][] memo = new int[j][i];
        for(int rows=0; rows < j ;rows++){
            for(int columns = 0; columns < i; columns++){
                memo[rows][columns]= -1;
            }
        }
        for(int columns=0; columns < i; columns++){
            result = Math.max(result,maxResourcesM(mine, memo, 0, columns));
        }
        return result;
    }
    
    public static int maxResourcesM(int[][] mine, int[][]memo, int rows, int columns) {
        int j = mine.length;
        int i = mine[0].length;
        if(columns < 0 ||columns>= j){
            return -1;
        }
        if(memo[rows][columns] != -1){
            return memo[rows][columns];
        }
        int result = mine[rows][columns];
        if(rows + 1 < j){
            result = result + Math.max(maxResources(mine, rows + 1,columns-1),maxResources(mine,rows + 1,columns +1));
        }
        return memo[rows][columns] = result;
    }

    /*
     * Main method for testing
     * Includes some basic tests for each of the methods required by tasks
     * You can use this method to check your methods before submitting
     */
    public static void main(String[] args) {
        System.out.println("Beginning testing");

        System.out.println("   Testing merge method:");

        try {
            int[] a = new int[] {1,3,5};
            int[] b = new int[] {2,4,6};
            int[] expected = new int[] {1,2,3,4,5,6};
            int[] result = Assessment2.merge(a, b);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Inputs were [1,3,5] and [2,4,6]");
                System.out.println("     Expected output [1,2,3,4,5,6]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing mergesort method:");

        try {
            int[] arr = new int[] {3,6,4,1,5,2};
            int[] expected = new int[] {1,2,3,4,5,6};
            int[] result = Assessment2.mergesort(arr);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [3,6,4,1,5,2]");
                System.out.println("     Expected output [1,2,3,4,5,6]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing merge3 method:");

        try {
            int[] a = new int[] {1,4,7};
            int[] b = new int[] {2,5,8};
            int[] c = new int[] {3,6,9};
            int[] expected = new int[] {1,2,3,4,5,6,7,8,9};
            int[] result = Assessment2.merge3(a, b, c);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Inputs were [1,4,7], [2,5,8] and [3,6,9]");
                System.out.println("     Expected output [1,2,3,4,5,6,7,8,9]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing mergesort3 method:");

        try {
            int[] arr = new int[] {8,3,6,2,9,5,1,7,4};
            int[] expected = new int[] {1,2,3,4,5,6,7,8,9};
            int[] result = Assessment2.mergesort3(arr);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [8,3,6,2,9,5,1,7,4]");
                System.out.println("     Expected output [1,2,3,4,5,6,7,8,9]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing mergeAll method:");

        try {
            int[] a = new int[] {1,6,11,16,21};
            int[] b = new int[] {2,7,12,17,22};
            int[] c = new int[] {3,8,13,18,23};
            int[] d = new int[] {4,9,14,19,24};
            int[] e = new int[] {5,10,15,20,25};
            int[][] arrs = new int[][] {a,b,c,d,e};
            int[] expected = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
            int[] result = Assessment2.mergeAll(arrs);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Inputs were [1,6,11,16,21], [2,7,12,17,22], [3,8,13,18,23], [4,9,14,19,24] and [5,10,15,20,25]");
                System.out.println("     Expected output [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing mergesortK method:");

        try {
            int[] arr = new int[] {10,4,11,15,2,14,6,8,1,12,3,16,7,9,13,5};
            int[] expected = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
            int[] result = Assessment2.mergesortK(arr,4);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [10,4,11,15,2,14,6,8,1,12,3,16,7,9,13,5] with K=4");
                System.out.println("     Expected output [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing maxResources method:");

        try {
            int[][] mine = new int[][] {new int[] {91,82,55,25,10},
                    new int[] {99,75,49,37,21},
                    new int[] {80,63,32,48,51},
                    new int[] {40,36,47,52,64},
                    new int[] {12,27,33,71,82}
                };
            int expected = 362;
            int result = Assessment2.maxResources(mine);
            boolean testPassed = expected == result;
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [[91,82,55,25,10]");
                System.out.println("                [99,75,49,37,21]");
                System.out.println("                [80,63,32,48,51]");
                System.out.println("                [40,36,47,52,64]");
                System.out.println("                [12,27,33,71,82]]");
                System.out.println("     Expected output 362");
                System.out.println("     But got " + result);
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing maxResourcesM method:");

        try {
            int[][] mine = new int[][] {new int[] {91,82,55,25,10},
                    new int[] {99,75,49,37,21},
                    new int[] {80,63,32,48,51},
                    new int[] {40,36,47,52,64},
                    new int[] {12,27,33,71,82}
                };
            int expected = 362;
            int result = Assessment2.maxResourcesM(mine);
            boolean testPassed = expected == result;
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [[91,82,55,25,10]");
                System.out.println("                [99,75,49,37,21]");
                System.out.println("                [80,63,32,48,51]");
                System.out.println("                [40,36,47,52,64]");
                System.out.println("                [12,27,33,71,82]]");
                System.out.println("     Expected output 362");
                System.out.println("     But got " + result);
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("Finished testing");
    }

}