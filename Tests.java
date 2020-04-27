public class Tests{
    public static void main(String[] args){
        testResidue();
        testKK();
    }

    private static void testResidue(){
        long[] arr1 = new long[] {1,2,3,4,5};
        long[] arr2 = new long[] {10000000000000L,20000000000000L,10000000000000L,9,8,12};
        long[] arr3 = new long[] {70, 20, 19, 9, 6, 6, 5, 4, 1};
        long[] arr4 = new long[] {1, 20, 6, 6, 9, 19, 70, 4, 5};
        long[] arr5 = new long[] {80, 70, 20, 19, 9, 6, 6, 5, 4, 1};
        long[] arr6 = new long[] {80, 70, 22, 20, 19, 8, 6, 6, 5, 4, 3, 1};
        long[] arr7 = new long[] {80, 70, 7, 13, 39};
        long[] arr8 = new long[] {3,7,5}; // equivalent to arr1 with pre = {1,1,2,2,3}
        long[] arr9 = new long[] {2,3,10}; // equivalent to arr1 with pre = {3,1,2,3,3}
        long[] arr10 = new long[] {80, 92, 40, 8, 6, 6, 5, 7}; // equivalent to arr6 with pre = {1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 8, 3}

        //------- Testing Residue Standard
        Solution sol1_a = new Standard(new int[] {1, 0, 0, 1, 0});
        Solution sol1_b = new Standard(new int[] {0, 0, 0, 1, 1});
        Solution sol2_a = new Standard(new int[] {0, 1, 0, 1, 0, 0});
        Solution sol2_b = new Standard(new int[] {1, 1, 1, 1, 1, 1});
        Solution sol2_c = new Standard(new int[] {0, 0, 0, 0, 0, 0});

        long res1_a = 5;
        long res1_b = 3;
        long res2_a = 11;
        long res2_b = 40000000000029L;

        // System.out.println(sol1_a.residue(arr1));

        assert(sol1_a.residue(arr1) == res1_a);
        assert(sol1_b.residue(arr1) == res1_b);
        assert(sol2_a.residue(arr2) == res2_a);
        assert(sol2_b.residue(arr2) == res2_b);
        assert(sol2_c.residue(arr2) == res2_b);

        //------- Testing Residue Prepartition
        Solution pre1_a = new Prepartition(new int[] {1,2,2,1,2});
        Solution pre1_b = new Prepartition(new int[] {3,3,3,1,1});
        Solution pre2_a = new Prepartition(new int[] {4,2,4,2,4,4});
        Solution pre2_b = new Prepartition(new int[] {0,0,0,0,0,0});
        Solution pre2_c = new Prepartition(new int[] {2,2,2,2,2,2});

        assert(pre1_a.residue(arr1) == res1_a);  // using only two numbers such for a +1 group and a -1 group
        assert(pre1_b.residue(arr1) == res1_b);  // equivalent to NPSolver.residue with 0 and 1
        assert(pre2_a.residue(arr2) == res2_a);
        assert(pre2_b.residue(arr2) == res2_b);
        assert(pre2_c.residue(arr2) == res2_b);

        Solution pre1_c = new Prepartition(new int[] {0,0,1,1,2});
        Solution pre1_d = new Prepartition(new int[] {2,0,1,2,2});
        Solution pre6_a = new Prepartition(new int[] {0, 1, 1, 2, 2, 3, 4, 5, 6, 7, 7, 2});
        assert(pre1_c.residue(arr1) == NPSolver.KK(arr8));
        assert(pre1_d.residue(arr1) == NPSolver.KK(arr9));
        assert(pre6_a.residue(arr6) == NPSolver.KK(arr10));
    }

    private static void testKK(){
        long[] arr1 = new long[] {1,2,3,4,5};
        long[] arr2 = new long[] {10000000000000L,20000000000000L,10000000000000L,9,8,12};
        long[] arr3 = new long[] {70, 20, 19, 9, 6, 6, 5, 4, 1};
        long[] arr4 = new long[] {1, 20, 6, 6, 9, 19, 70, 4, 5};
        long[] arr5 = new long[] {80, 70, 20, 19, 9, 6, 6, 5, 4, 1};
        long[] arr6 = new long[] {80, 70, 22, 20, 19, 8, 6, 6, 5, 4, 3, 1};
        long[] arr7 = new long[] {80, 70, 7, 13, 39};

        long KK1 = 1;
        long KK2 = 5;
        long KK3 = 0;
        long KK7 = 9;

        assert(NPSolver.KK(arr1) == KK1);
        assert(NPSolver.KK(arr2) == KK2);
        assert(NPSolver.KK(arr3) == KK3);
        assert(NPSolver.KK(arr4) == KK3);
        assert(NPSolver.KK(arr5) == KK3);
        assert(NPSolver.KK(arr6) == KK3);
        assert(NPSolver.KK(arr7) == KK7);
    }
    // generate 100 64-bit integers.
}
