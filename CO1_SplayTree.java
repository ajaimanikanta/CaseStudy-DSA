public class CO1_SplayTree {

    static class SplayNode {
        int key;
        SplayNode left, right, parent;

        SplayNode(int k) {
            key = k;
        }
    }

    SplayNode root;

    void rotateLeft(SplayNode x) {
        SplayNode y = x.right;
        x.right = y.left;

        if (y.left != null)
            y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    void rotateRight(SplayNode x) {
        SplayNode y = x.left;
        x.left = y.right;

        if (y.right != null)
            y.right.parent = x;

        y.parent = x.parent;

        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.right = x;
        x.parent = y;
    }

    void splay(SplayNode x) {
        while (x.parent != null) {
            SplayNode p = x.parent;
            SplayNode g = p.parent;

            if (g == null) {
                if (x == p.left)
                    rotateRight(p);
                else
                    rotateLeft(p);
            }
            else if ((x == p.left) == (p == g.left)) {
                if (x == p.left) {
                    rotateRight(g);
                    rotateRight(p);
                } else {
                    rotateLeft(g);
                    rotateLeft(p);
                }
            }
            else {
                if (x == p.left) {
                    rotateRight(p);
                    rotateLeft(g);
                } else {
                    rotateLeft(p);
                    rotateRight(g);
                }
            }
        }
        root = x;
    }

    public static void main(String[] args) {
        System.out.println("CO1 - Splay Tree Implementation");
    }
}