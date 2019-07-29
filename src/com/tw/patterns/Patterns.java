package com.tw.patterns;


public class Patterns {

	public static void main(String[] args) {
//		diamondOfNumbers(4);
//		squareWithSmallestInCenter(7);
		squareWithSmallestInCenter2(4);
	}
	
//input 3
//  1
// 234
//56789
//101112
// 13
	public static void diamondOfNumbers(int mid){
//		int mid = 3;
		int count = 1;
		for(int i=0; i<2*mid-1; i++){
//			if(i<mid){
			    for(int j=0;j<Math.abs((mid-1-i));j++){
			        System.out.print(" ");
			    }
			    for(int j=0;j<2*(mid-Math.abs(mid-1-i))-1;j++){
			        System.out.print(count++);
			    }
//			} else {
//				
//			}
		    System.out.println();
		}
	}
	
	public static void squareWithSmallestInCenter(int width){
		int[][] a = new int[width][width];
		int start = (width/2)+2;
		width++;
		int innerStart = -1;
		for(int i=0; i<width; i++){
			start--;
			width--;
			innerStart++;
			for(int j = innerStart; j<width; j++){
				a[i][j]=start;
			}
			for(int j = innerStart; j<width; j++){
				a[j][width-1]=start;
			}
			for(int j = width-1; j>innerStart; j--){
				a[width-1][j]=start;
			}
			for(int j = width-1; j>innerStart; j--){
				a[j][i]=start;
			}
		}
		
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void squareWithSmallestInCenter2(int largest){
		for(int i=0;i<2*largest-1;i++){
	        for(int j=0;j<2*largest-1;j++)
//	        	System.out.print(Math.abs(largest-i-1)+"<>"+Math.abs(largest-j-1)+" "  );
	            System.out.print(1 + Math.max( Math.abs(largest-i-1),Math.abs(largest-j-1) ) );
	        System.out.println();
	    }
	}
	
//	public static void squareWithSmallestInCenter(int width){
//		int largest = (width/2)+1;
//		for(int i=0; i<width; i++){
//			int count = largest;
			
			
			
			
//			for(int j=0;j<width;j++){
				
//				System.out.print((largest-i) - Math.abs(largest-j));
//				if(j==count)
//					count=count+1;
//				if(i<largest ){
//					if(j>=largest && Math.abs(j-largest) < i){
//						System.out.print(++count);
//					}else if(Math.abs(j-largest) < i){
//						System.out.print(count--);
//					} else{
//						System.out.print(count);						
//					}
//				}
//				else if(j>largest && i<j){
//					System.out.print(++count);
//				} else{
//					System.out.print(count);
//				}
				
				
				
				
//				System.out.print(Math.abs(largest-(i-j)));
				
//			if(Math.abs(i-j) > i){
//				if(j>=largest)
//					count = Math.abs(largest-j)+2;
//				else{
//					count = Math.abs(largest-j)+2;
//				}
//			} else {
//				
//			}
				
				
//				if(j==largest && j==i){
//					count++;
//				}
//				if(j<largest && j<i)
//					System.out.print(Math.abs(count - j));
//				else if (j>largest && )
//					System.out.print(Math.abs(count + j));
//				else
//					System.out.print(count);
//				System.out.print(count);
				
//				if(Math.abs(largest-j)<i && j<largest)
//					System.out.print(Math.abs(count--));
//				else if(Math.abs(largest-j)>i && j>largest){
//					System.out.print(count++);
//				} else{
//					System.out.print(count);
//				}
				
				
//				if(Math.abs(i-j)<largest)
//					System.out.print(Math.abs(count-j));
//				else if(Math.abs(largest-i)<i)
//					System.out.print(++count);
//				else{
//					System.out.print(count);
//				}
//			}
//			System.out.println();
//		}
//	}

}
