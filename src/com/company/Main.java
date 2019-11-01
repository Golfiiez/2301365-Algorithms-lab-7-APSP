package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int N = inp.nextInt();
        int M = inp.nextInt();
        int C = inp.nextInt();

        int[] workerNeeds = new int[M];

        for (int i = 0; i < N; i++) {
            workerNeeds[i] = inp.nextInt();
        }

        int[][] adjMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                adjMatrix[i][j] = (i == j) ? 0 : -1;
            }
        }

        for (int i = 0; i < M; i++) {
            int start = inp.nextInt();
            int stop = inp.nextInt();
            int weight = inp.nextInt();
            adjMatrix[start - 1][stop - 1] = weight;
        }
//        for (int[] row : adjMatrix) {
//            System.out.println(Arrays.toString(row));
//        }
        int workers = inp.nextInt();


        //end of input gathering
        // find all pairs SP
        int spLength = 1;
        int[][] spCurrentMatrix = adjMatrix;
        while (spLength < N - 1) {
            spCurrentMatrix = ExtendMat(spCurrentMatrix);
            spLength = spLength * 2;
            System.out.println(spLength);
        }
        //greedy techniques
        SPNode[] Nodes = new SPNode[N];
        for (int i = 0; i < N; i++) {
            Nodes[i] = new SPNode(workerNeeds[i], spCurrentMatrix[0][i]);
        }
        System.out.println(Arrays.toString(Nodes));
        Arrays.sort(Nodes);
        System.out.println(Arrays.toString(Nodes));

        for(int i=0;i<N;i++)
        {

               for(int j=0;j<Nodes[i].workerNeeds;j++)
               {
                   int salary=C+Nodes[i].range;
                   if(workers>0 && Nodes[i].range>=0)
                   {
                       System.out.println(salary);
                       workers--;
                   }

               }


        }
        while(workers>0)
        {
            System.out.println("-1");
            workers--;
        }
    }

    public static int[][] ExtendMat(int[][] currentMat) {
        int n = currentMat.length;
        int[][] Extended = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Extended[i][j] = -1;
                Extended[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {


                    Extended[i][j] = Math.min(Extended[i][j], ((currentMat[k][j] != -1) && (currentMat[i][k] != -1)) ? currentMat[k][j] + currentMat[i][k] : Integer.MAX_VALUE);

                }
                if (Extended[i][j] == Integer.MAX_VALUE) {
                    Extended[i][j] = -1;
                }


            }
        }
        return Extended;
    }
}

class SPNode implements Comparable<SPNode> {
    int workerNeeds;
    int range;

    public SPNode(int workerNeeds,int range)
    {
        this.workerNeeds=workerNeeds;
        this.range=range;
    }
    public int compareTo(SPNode node)
    {
        if(this.range==-1)
        {
            if(node.range==-1)
            {
                return 0;
            }
            else
            {
                return Integer.MAX_VALUE-node.range;
            }
        }
        else
        {
            if(node.range==-1)
            {
                return this.range - Integer.MAX_VALUE;
            }
            else
            {
                return this.range - node.range;
            }
        }

    }

    @Override
    public String toString() {
        return (this.workerNeeds+" "+this.range);
    }
}

