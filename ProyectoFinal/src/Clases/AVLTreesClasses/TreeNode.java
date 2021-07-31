package Clases.AVLTreesClasses;

import java.io.Serializable;

import Clases.appClasses.Objetivo;
public class TreeNode implements Serializable{
    public int key, height;
    TreeNode leftSubtree, rightSubtree;
    public Objetivo objetivo;
    
    TreeNode(int key, Objetivo objetivo) {
        this.key = key;
        height = 1;
        this.objetivo = objetivo;
    }
}