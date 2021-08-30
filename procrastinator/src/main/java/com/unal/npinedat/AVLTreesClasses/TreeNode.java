package com.unal.npinedat.AVLTreesClasses;

import java.io.Serializable;

import com.unal.npinedat.appClasses.*;

public class TreeNode implements Serializable{
    public int key, height;
    public TreeNode leftSubtree, rightSubtree;
    public Objetivo objetivo;
    
    TreeNode(int key, Objetivo objetivo) {
        this.key = key;
        height = 1;
        this.objetivo = objetivo;
    }
}