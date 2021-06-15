/**
 * QuickFind
 */
class QuickFindUF {
    private int[] id;

    public QuickFindUF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++){
            id[i] = i;
        }
    }

    public boolean connected(int p, int q){
        return id[q] == id[p];
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == pid) id[i] = qid;
        }
    }
}

class QuickUnionUF{
    private int[] id;

    public QuickUnionUF(int n){
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
    }

    private int root(int i){
        while (i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int pRoot = root(p);
        int qRoot = root(q);
        id[pRoot] = qRoot;
    }
}

class WeightedQuickUnionUF{
    private int[] id;
    private int[] height;

    public WeightedQuickUnionUF(int n){
        id = new int[n];
        height = new int[n];
        for(int i = 0; i < n; i++){
            id[i] = i;
            height[i] = 0;
        }
    }

    private int root(int n){
        int count = 0;
        while (id[n] != n) {
            n = id[n];
            height[n] = ++count;
        }
        return n;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot) return;
        if (height[p] < height[q]){
            id[pRoot] = qRoot;
            height[qRoot]+=height[pRoot];
        } else{
            id[qRoot] = pRoot;
            height[pRoot]+=height[qRoot];
        }
    }
}
public class QuickFind {
    public static void main(String[] args) {
        QuickFindUF qf = new QuickFindUF(10);
        qf.union(7,4);
        qf.union(2,8);
        qf.union(2,9);
        qf.union(3,9);
        qf.union(4,5);
        qf.union(3,4);
        System.out.println(qf.connected(6, 9));

        QuickUnionUF qu = new QuickUnionUF(10);
        qf.union(2,3);
        qf.union(3,4);
        qf.union(1,7);
        qf.union(1,4);
        System.out.println(qf.connected(7,3));
    }    
}