package Clases.AVLTreesClasses;

import java.io.Serializable;
public class TreeNode implements Serializable{
    public int key, height;
    TreeNode leftSubtree, rightSubtree;
    
    TreeNode(int key) {
        this.key = key;
        height = 1;
    }
}