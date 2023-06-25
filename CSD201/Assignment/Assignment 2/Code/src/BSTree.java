
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Stack;

/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    String f0() {//input your RollNumber
        String RollNum = "HE171600";
        return RollNum;
    }

    void insert(String xType, int xRate, int xWing) {
        if (xType.charAt(0) == 'B' || xRate > 10) {
            return; // Do nothing
        }

        Bird newBird = new Bird(xType, xRate, xWing);
        Node newNode = new Node(newBird);

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;

            while (true) {
                parent = current;

                if (newBird.type.compareToIgnoreCase(current.info.type) < 0) {
                    current = current.left;

                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;

                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1(int line) throws Exception {

        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        Queue queue = new Queue();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            Bird bird = current.info;

            if (bird.wing >= 4 && bird.wing <= 10) {
                f.writeBytes(bird.toString() + " ");
            }

            if (current.left != null) {
                queue.enqueue(current.left);
            }

            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f3(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        int[] counter = {1}; // Use an array to hold the counter value and make it effectively final

        preOrderOdd(root, f, counter);

//------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    void preOrderOdd(Node p, RandomAccessFile f, int[] counter) throws Exception {
        if (p != null) {
            if (counter[0] % 2 == 1) {
                f.writeBytes(p.info.toString() + " ");
            }
            counter[0]++;
            preOrderOdd(p.left, f, counter);
            preOrderOdd(p.right, f, counter);
        }
    }

    void f4(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        inOrderFilter(root, f);

//------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    void inOrderFilter(Node node, RandomAccessFile f) throws Exception {
        if (node != null) {
            inOrderFilter(node.left, f);
            if (node.info.wing < 4 && node.info.rate > 6) {
                f.writeBytes("(" + node.info.type + "," + node.info.rate + "," + node.info.wing + ") ");
            }
            inOrderFilter(node.right, f);
        }
    }

    void f5(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        postOrderFilter(root, f);

//------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    void postOrderFilter(Node node, RandomAccessFile f) throws Exception {
        if (node != null) {
            postOrderFilter(node.left, f);
            postOrderFilter(node.right, f);
            if (node.info.type.charAt(0) == 'A' || node.info.type.charAt(0) == 'C') {
                f.writeBytes("(" + node.info.type + "," + node.info.rate + "," + node.info.wing + ") ");
            }
        }
    }

    void f6(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        inOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        Node fifthNode = findFifthNode(root); // Find the fifth node after in-order traversal
        deleteByCopy(fifthNode);

        //------------------------------------------------------------------------------------
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    Node findFifthNode(Node root) {
        int count = 1;
        Node fifthNode = null;
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            count++;

            if (count == 5) {
                fifthNode = current;
                break;
            }

            current = current.right;
        }

        return fifthNode;
    }

    void deleteByCopy(Node p) {
        if (p == null) {
            return;
        }
        Node f = findParent(root, p);
        // 1. p has no child
        if (p.left == null && p.right == null) {
            if (f == null) {
                root = null;
            } else if (f.left == p) {
                f.left = null;
            } else {
                f.right = null;
            }
        } // 2. p has left child only
        else if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
            } else if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        } // 3. p has right child only
        else if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
            } else if (f.left == p) {
                f.left = p.right;
            } else {
                f.right = p.right;
            }
        } // 4. p has both children
        else if (p.left != null && p.right != null) {
            // Find q as the rightmost node in the left subtree of p
            Node q = p.left;
            Node qParent = p;
            while (q.right != null) {
                qParent = q;
                q = q.right;
            }
            // Replace p's info with q's info
            p.info = q.info;
            // Delete q by copying
            if (qParent.left == q) {
                qParent.left = q.left;
            } else {
                qParent.right = q.left;
            }
        }
    }

// Function to find the parent of a given node in the tree
    Node findParent(Node root, Node node) {
        if (root == null || root == node) {
            return null;
        }
        if (root.left == node || root.right == node) {
            return root;
        }
        Node leftResult = findParent(root.left, node);
        if (leftResult != null) {
            return leftResult;
        }
        return findParent(root.right, node);
    }

    void f7(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        Node p = findNodeByIndex(root, 6); // Find the 6th node (p) in post-order traversal
        deleteByMerging(p);

        //------------------------------------------------------------------------------------
        postOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    Node findNodeByIndex(Node node, int index) {
        // Find the node with the specified index in post-order traversal
        if (node == null) {
            return null;
        }

        // Get the sizes of the left and right subtrees
        int leftSize = getSize(node.left);
        int rightSize = getSize(node.right);

        // Check if the node itself is the desired node
        if (leftSize + rightSize + 1 == index) {
            return node;
        }

        // Check if the desired node is in the left subtree
        if (leftSize >= index) {
            return findNodeByIndex(node.left, index);
        }

        // Adjust the index to account for the nodes in the left subtree
        index -= leftSize;

        // Check if the desired node is in the right subtree
        return findNodeByIndex(node.right, index);
    }

    int getSize(Node node) {
        // Get the size of the subtree rooted at the specified node
        if (node == null) {
            return 0;
        }
        return getSize(node.left) + getSize(node.right) + 1;
    }

    void deleteByMerging(Node p) {
        Node f = findParent(root, p); // Find the parent node (f) of p

        // 1. p has no child
        if (p.left == null && p.right == null) {
            if (f == null) {
                root = null;
            } else if (f.left == p) {
                f.left = null;
            } else {
                f.right = null;
            }
        } // 2. p has left child only
        else if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
            } else if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        } // 3. p has right child only
        else if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
            } else if (f.left == p) {
                f.left = p.right;
            } else {
                f.right = p.right;
            }
        } // 4. p has both child
        else if (p.left != null && p.right != null) {
            // Find q as the rightmost node in the left subtree of p
            Node q = p.left;
            Node qParent = p;
            while (q.right != null) {
                qParent = q;
                q = q.right;
            }

            // Move the right subtree of p to the rightmost node (q) in the left subtree
            q.right = p.right;

            // Update the parent of p to point to the left subtree of p
            if (f == null) {
                root = p.left;
            } else if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }

            // Update the parent of q if q is not the left child of p
            if (qParent != p) {
                qParent.right = null;
            }
        }
    }

    //=============================================================
    void f8(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void f9(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void f10(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void f11(int line) throws Exception {
        clear();
        loadData(line);
        String fname = "fout.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

}
