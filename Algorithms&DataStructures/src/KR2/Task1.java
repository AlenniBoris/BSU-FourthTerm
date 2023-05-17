package KR2;



import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task1 implements Runnable{

    static ArrayList<Long> values = new ArrayList<>();

    private static class Node{
        public Node left;
        public Node right;
        long key;
        int hleft;
        int hright;
        int height;
        public Node(long key) {
            this.key = key;
            this.height = -1;
        }
    }

    static class BST{
        Node root;
        public BST() {
            this.root = null;
        }

        public void insert(long key){
            this.root = insertRec(root, key);
        }

        private Node insertRec(Node root, long key){
            if (root == null){
                return new Node(key);
            }
            if (key > root.key){
                root.right = insertRec(root.right, key);
            }
            if (key < root.key){
                root.left = insertRec(root.left, key);
            }
            return root;
        }

        public void traversal(Node node, PrintStream out){
            if(node != null){
                out.println(node.key);
                traversal(node.left, out);
                traversal(node.right, out);
            }
        }


        public void delete(long val){
            this.root =  deleteRec(this.root, val);
        }

        private Node deleteRec(Node node, long val){
            if (node == null){
                return node;
            }
            if (val < node.key){
                node.left = deleteRec(node.left, val);
            }
            else if(val > node.key){
                node.right = deleteRec(node.right, val);
            }
            else{
                if (node.left == null && node.right == null){
                    return null;
                }
                if (node.right == null){
                    return node.left;
                }
                if (node.left == null){
                    return node.right;
                }
                else{
                    node.key = getSmallestValue(node.right);
                    node.right = deleteRec(node.right, node.key);
                }
            }
            return node;
        }

        private Long getSmallestValue(Node root){
            return root.left == null ? root.key : getSmallestValue(root.left);
        }

        public void setApproved(Node node){
            if (node != null){
                setApproved(node.left);
                setApproved(node.right);
                if (node.right == null && node.left == null){
                    node.hright = -1;
                    node.hleft = -1;
                    node.height = 0;
                }
                else if (node.right == null){
                    node.hright = -1;
                    node.height = node.left.height + 1;

                    if (node.left.hright == -1){
                        node.hleft = node.left.hleft + 1;
                    }else if (node.left.hleft == -1){
                        node.hleft = node.left.hright + 1;
                    }else{
                        node.hleft = node.left.hright + node.left.hleft + 2;
                    }
                }
                else if (node.left == null){
                    node.hleft = -1;
                    node.height = node.right.height + 1;

                    if (node.right.hright == -1){
                        node.hright = node.right.hleft + 1;
                    }else if (node.right.hleft == -1){
                        node.hright = node.right.hright + 1;
                    }else{
                        node.hright = node.right.hright + node.right.hleft + 2;
                    }
                }
                else{
                    node.hright = node.right.hright + 1;
                    node.hleft = node.left.hleft + 1;

                    node.height = 1 + Math.max(node.right.height, node.left.height);
                }

            }
        }

        public void setValues(Node node){
            if (node != null){
                setValues(node.left);
                if (node.left == null && node.right == null){
                    return;
                }
                else if (node.left == null){
                    if (node.right.height != -1){
                        values.add(node.key);
                    }
                }
                else if (node.right == null){
                    if (node.left.height != -1){
                        values.add(node.key);
                    }
                }
                else{
                    if (node.left.height != node.right.height){
                        values.add(node.key);
                    }
                }
                setValues(node.right);
            }
        }
    }

    static void deleteMid(BST tree){
        if (values.size() % 2 != 0){
            tree.delete(values.get(values.size()/2));
        }
    }

    static void fillFromFile(Scanner reader, BST tree) throws IOException{
        while(reader.hasNextLine()){
            tree.insert(Long.parseLong(reader.nextLine()));
        }
    }

    public static void main(String[] args){
        new Thread(null, new Task1(), "", 64*1024*1024).start();
    }

    @Override
    public void run() {
        try{
            Scanner sc = new Scanner(new InputStreamReader(new FileInputStream("in.txt")));
            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("out.txt")));

            BST tree = new BST();

            fillFromFile(sc, tree);
            sc.close();

            tree.setApproved(tree.root);
            tree.setValues(tree.root);

            deleteMid(tree);

            tree.traversal(tree.root, out);
            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}