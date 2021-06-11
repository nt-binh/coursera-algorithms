import java.util.concurrent.TimeUnit;

public class SocialNetwork {
    
    private int[] members;
    private int[] size;
    private int components;

    private int root(int i){
        int count = 0;
        while (i != members[i]){
            members[i] = members[members[i]];
            i = members[i];
            size[i] = ++count;
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ){
            return;
        }else{
            components++;
            //members[rootP] = rootQ;
            if (size[rootP] < size[rootQ]){
                members[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }else{
                members[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }
    }

    public boolean allConnected(){
        return components == members.length - 1;
    }

    public SocialNetwork(int n){
        members = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++){
            members[i] = i;
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        SocialNetwork network = new SocialNetwork(10);
        network.union(2,3);
        network.union(3,4);
        network.union(1,7);
        network.union(1,4);
        network.union(2,9);
        network.union(7,2);
        network.union(6,9);
        network.union(0,5);
        network.union(0,8);
        network.union(2,5);
        System.out.println(network.connected(7,3));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Execution time is " + duration + " s");
        System.out.println(network.allConnected());
    }
}
