package com.wil.practice.algo.cp600.dc;

public class SearchInRotatedArray {

    private static int binarySearch(int[] arr, int l, int r, int target) {
        if(l==r) {
            return arr[l]==target?l:-1;
        }
        int mid = l + (r - l) / 2;
        if(arr[mid] == target) return mid;
        else if (arr[mid]<target) {
            l = mid+1;
        } else{
            r = mid-1;
        }
        return binarySearch(arr, l, r, target);
    }


    private static int searchRotated(int[] arr, int target) {
        if(arr.length<=2) {
            if(arr[0]==target) return 0;
            else if (arr[arr.length-1]==target) return arr.length-1;
            else return -1;
        }
        int k=1;
        while (k<arr.length && arr[k]>arr[0]) {
            k*=2;
        }
        int pivot = 0;
        if(k>arr.length) k /=2;
        int l1 = k/2;
        int r1 = k;
        while (l1 !=r1) {
            int mid = l1+(r1-l1)/2;
            if(arr[mid]>arr[0]) {
                l1 = mid+1;
            } else{
                r1 = mid-1;
            }
        }
        pivot = l1;
        if(target == arr[pivot]) return pivot;

        if(arr[pivot] > arr[0] && arr[pivot]<= arr[arr.length-1]){
            if(target<arr[pivot]) return binarySearch(arr, 0, pivot-1, target);
            else return binarySearch(arr, pivot+1, arr.length-1, target);
        } else if(target<arr[0] && target>arr[pivot]) {
            return binarySearch(arr, pivot+1, arr.length-1, target);
        } else if(target>arr[pivot] && target>arr[0]) {
            return binarySearch(arr, 0, pivot-1, target);
        }
        return -1;
    }



    private static int searchInRotated(int[] arr, int target) {
        int l = 0, r = arr.length-1;
        if (l==r) {
            if(arr[l]==target) return l;
            else return -1;
        }
        while (l<=r) {
            int mid = l+(r-l)/2;
            if(arr[mid]==target) return mid;

            if(arr[l]<arr[mid]) {
                if(arr[l]<= target && target<arr[mid]) r = mid -1;
                else l = mid+1;
            } else {
                if(arr[mid]<target && target<=arr[r]) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }



    private static int searchInRotatedII(int[] arr, int target) {
        int l = 0, r = arr.length-1;
        if (l==r) {
            if(arr[l]==target) return l;
            else return -1;
        }
        while (l<=r) {
            int mid = l+(r-l)/2;
            if(arr[mid]==target) return mid;

            if((arr[l] == arr[mid]) && (arr[r] == arr[mid]))
            {
                l++;
                r--;
            } else if(arr[l]<=arr[mid]){
                if(arr[l]<= target && target<arr[mid])
                    r = mid -1;
                else
                    l = mid+1;
            } else {
                if(arr[mid]<target && target<=arr[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5};
        System.out.println(searchInRotated(arr, 0));
        int[] arr2 = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
        System.out.println(searchInRotatedII(arr2, 0));
    }

}
