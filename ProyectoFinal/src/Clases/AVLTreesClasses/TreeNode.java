package Clases.AVLTreesClasses;

public class TreeNode {
    public int key, height;
    TreeNode leftSubtree, rightSubtree;
    
    TreeNode(int key) {
        this.key = key;
        height = 1;
    }
}