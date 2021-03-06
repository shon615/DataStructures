
public class BinarySearch {
	
	private static <T extends Comparable<T>> int recursiveBinarySearch(T[] arr, T target, int lo, int hi) {
		if (lo > hi) return -1;
		int mid = (lo+hi)/2;
		int c = target.compareTo(arr[mid]);
		if (c == 0)	return mid;
		if (c < 0) 	return recursiveBinarySearch(arr, target, lo, mid-1);
		else		return recursiveBinarySearch(arr, target, mid+1, hi);
	}
	private static <T extends Comparable<T>> int BinarySearch(T[] arr, T target) {
		int lo = 0, hi = arr.length - 1;
		while (lo <= hi) {
			int mid = (lo+hi)/2;
			if (arr[mid].compareTo(target) == 0)	return mid;
			if (arr[mid].compareTo(target) > 0) 	hi = mid - 1;
			else									lo = mid + 1;
		}
		return -1;
	}
}
