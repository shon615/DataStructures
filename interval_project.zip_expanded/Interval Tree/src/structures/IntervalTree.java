package structures;

import java.util.ArrayList;

/**
 * Encapsulates an interval tree.
 * 
 * @author runb-cs112
 */
public class IntervalTree {
	
	/**
	 * The root of the interval tree
	 */
	IntervalTreeNode root;
	
	/**
	 * Constructs entire interval tree from set of input intervals. Constructing the tree
	 * means building the interval tree structure and mapping the intervals to the nodes.
	 * 
	 * @param intervals Array list of intervals for which the tree is constructed
	 */
	public IntervalTree(ArrayList<Interval> intervals) {
		
		// make a copy of intervals to use for right sorting
		ArrayList<Interval> intervalsRight = new ArrayList<Interval>(intervals.size());
		for (Interval iv : intervals) {
			intervalsRight.add(iv);
		}
		
		// rename input intervals for left sorting
		ArrayList<Interval> intervalsLeft = intervals;
		
		// sort intervals on left and right end points
		sortIntervals(intervalsLeft, 'l');
		sortIntervals(intervalsRight,'r');
		
		// get sorted list of end points without duplicates
		ArrayList<Integer> sortedEndPoints = 
							getSortedEndPoints(intervalsLeft, intervalsRight);
		
		// build the tree nodes
		root = buildTreeNodes(sortedEndPoints);
		
		// map intervals to the tree nodes
		mapIntervalsToTree(intervalsLeft, intervalsRight);
	}
	
	/**
	 * Returns the root of this interval tree.
	 * 
	 * @return Root of interval tree.
	 */
	public IntervalTreeNode getRoot() {
		return root;
	}
	
	/**
	 * Sorts a set of intervals in place, according to left or right endpoints.  
	 * At the end of the method, the parameter array list is a sorted list. 
	 * 
	 * @param intervals Array list of intervals to be sorted.
	 * @param lr If 'l', then sort is on left endpoints; if 'r', sort is on right endpoints
	 */
	public static void sortIntervals(ArrayList<Interval> intervals, char lr) {
		// COMPLETE THIS METHOD
		if (lr == 'l') {
			int n = intervals.size();  
			Interval temp;  
			for(int i=0; i < n; i++){  
				for(int j=1; j < (n-i); j++){  
					if(intervals.get(j-1).leftEndPoint > intervals.get(j).leftEndPoint){  
						//swap elements  
						temp = intervals.get(j-1);  
						intervals.set(j-1, intervals.get(j));  
						intervals.set(j, temp); 
					}  

				}  
			}  
		}
		else {
			int n = intervals.size();  
			Interval temp;  
			for(int i=0; i < n; i++){  
				for(int j=1; j < (n-i); j++){  
					if(intervals.get(j-1).rightEndPoint > intervals.get(j).rightEndPoint){  
						//swap elements  
						temp = intervals.get(j-1);  
						intervals.set(j-1, intervals.get(j));  
						intervals.set(j, temp); 
					}  

				}  
			}  
		}

	}
	
	static private <T> void printArrayList (ArrayList<T> a) {
		System.out.print("{");
		for (T i: a) {
			System.out.print(i.toString() + ", ");
		}
		System.out.println("}");
	}
	

	/**
	 * Given a set of intervals (left sorted and right sorted), extracts the left and right end points,
	 * and returns a sorted list of the combined end points without duplicates.
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 * @return Sorted array list of all endpoints without duplicates
	 */
	public static ArrayList<Integer> getSortedEndPoints(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		ArrayList<Integer> endpoints = new ArrayList<Integer>();
		for (Interval i: leftSortedIntervals) {
			if (!endpoints.contains(i.leftEndPoint)){
				endpoints.add(i.leftEndPoint);
			}
			if (!endpoints.contains(i.rightEndPoint)){
				endpoints.add(i.rightEndPoint);
			}
		}
		int[] arr = new int[endpoints.size()];
		for (int i = 0; i < endpoints.size(); i++) {
			arr[i] = endpoints.get(i);
		}
		int n = arr.length;  
		int temp = 0;  
		for(int i=0; i < n; i++){  
			for(int j=1; j < (n-i); j++){  
				if(arr[j-1] > arr[j]){  
					//swap elements  
					temp = arr[j-1];  
					arr[j-1] = arr[j];  
					arr[j] = temp;  
				}  

			}  
		}
         for (int i=0; i < n; i++) {
        	 endpoints.set(i, arr[i]);
         }
		
		return endpoints;
	}
	
	/**
	 * Builds the interval tree structure given a sorted array list of end points
	 * without duplicates.
	 * 
	 * @param endPoints Sorted array list of end points
	 * @return Root of the tree structure
	 */
	public static IntervalTreeNode buildTreeNodes(ArrayList<Integer> endPoints) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		Queue<IntervalTreeNode> Q = new Queue<IntervalTreeNode>();
		for (Integer p : endPoints) {
			IntervalTreeNode T = new IntervalTreeNode(p,p,p);
			Q.enqueue(T);
		}
		
		
		while(true) {
			int s = Q.size;
			if (s==1){
				IntervalTreeNode T = Q.dequeue();
				return T;
			}
			else {
				int temps = s;
				while (temps > 1) {
					IntervalTreeNode T1 = Q.dequeue();
					IntervalTreeNode T2 = Q.dequeue();
					float v1 = T1.maxSplitValue;
					float v2 = T2.minSplitValue;
					float x = (v1+v2)/2;
					IntervalTreeNode N = new IntervalTreeNode(x, T1.minSplitValue, T2.maxSplitValue);
					N.leftChild = T1;
					N.rightChild = T2;
					Q.enqueue(N);
					temps = temps - 2;
				}
				if (temps == 1) {
					Q.enqueue(Q.dequeue());
				}
			}
		}
		
	}
	
	/**
	 * Maps a set of intervals to the nodes of this interval tree. 
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 */
	public void mapIntervalsToTree(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		// COMPLETE THIS METHOD
		IntervalTreeNode T = root;
		for (Interval I: leftSortedIntervals) {
			IntervalTreeNode tmp = root;
			while (tmp != null && !I.contains(tmp.splitValue)) {
				if (I.rightEndPoint < tmp.splitValue) {
					tmp = tmp.leftChild;
				}
				else {
					tmp = tmp.rightChild;
				}
			}
			if (tmp != null) {
				if (tmp.leftIntervals == null) {
					tmp.leftIntervals = new ArrayList<Interval>();
				}
				tmp.leftIntervals.add(I);
			}
		}
		
		for (Interval I: rightSortedIntervals) {
			IntervalTreeNode tmp = root;
			while (tmp != null && !I.contains(tmp.splitValue)) {
				if (I.rightEndPoint < tmp.splitValue) {
					tmp = tmp.leftChild;
				}
				else {
					tmp = tmp.leftChild;
				}
			}
			if (tmp != null) {
				if (tmp.rightIntervals == null) {
					tmp.rightIntervals = new ArrayList<Interval>();
				}
				tmp.rightIntervals.add(I);
			}
		}
	}
	
	/**
	 * Gets all intervals in this interval tree that intersect with a given interval.
	 * 
	 * @param q The query interval for which intersections are to be found
	 * @return Array list of all intersecting intervals; size is 0 if there are no intersections
	 */
	
	public ArrayList<Interval> findIntersectingIntervals(Interval q) {
		return findIntersectingIntervals(q, this.root);
	}
	private ArrayList<Interval> findIntersectingIntervals(Interval q, IntervalTreeNode r) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		ArrayList<Interval> ResultList = new ArrayList<Interval>();
		IntervalTreeNode R = r;
		float SplitVal = R.splitValue;
		ArrayList<Interval> LList = R.leftIntervals;
		ArrayList<Interval> RList = R.rightIntervals;
		IntervalTreeNode Lsub = R.leftChild;
		IntervalTreeNode Rsub = R.rightChild;
		
		if (Lsub == null && Rsub == null) {
			return ResultList;
		}
		
		if (q.contains(SplitVal)) {
			if  (LList!= null) {
				for (Interval i: LList) {
					if (!ResultList.contains(i)) {
						ResultList.add(i);
					}
				}
				for (Interval i: findIntersectingIntervals(q, Rsub)) {
					if (!ResultList.contains(i)) {
						ResultList.add(i);
					}
				}
				for (Interval i: findIntersectingIntervals(q, Lsub)) {
					if (!ResultList.contains(i)) {
						ResultList.add(i);
					}
				}
			}
		}
		else if (SplitVal < q.leftEndPoint) {
			if (RList != null) {
				int i = RList.size()-1;
				while (i >= 0 && RList.get(i).intersects(q)) {
					ResultList.add(RList.get(i));
					i-=1;
				}
				for (Interval I: findIntersectingIntervals(q, Rsub)) {
					if (!ResultList.contains(I)) {
						ResultList.add(I);
					}
				}
			}
		}
		else if (SplitVal > q.rightEndPoint) {
			int i = 0;
			if (LList!= null) {
				while (i < LList.size() && LList.get(i).intersects(q)) {
					ResultList.add(LList.get(i));
					i++;
				}
				for (Interval I: findIntersectingIntervals(q, Lsub)) {
					if (!ResultList.contains(I)) {
						ResultList.add(I);
					}
				}
			}
		}
		return ResultList;
	}

}

