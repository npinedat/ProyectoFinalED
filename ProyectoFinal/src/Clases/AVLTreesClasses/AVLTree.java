package Clases.AVLTreesClasses;

import java.io.Serializable;
import java.util.ArrayList;

import Clases.appClasses.Objetivo;

public class AVLTree implements Serializable{
    public TreeNode root = null;
    public ArrayList <Objetivo> objetivos = new ArrayList <Objetivo>();

    public boolean empty() {
        if(root == null) {
            return true;
        }else {
            return false;
        }
    }

    public TreeNode find(int nodeKey){
        TreeNode temporal = root;
        if (nodeKey == temporal.key) {
            return temporal;
        }else {
            while(temporal != null) {
                if(temporal.key == nodeKey) {
                    return temporal;
                }
                if(nodeKey < temporal.key) {
                    temporal = temporal.leftSubtree;
                }else {
                    temporal = temporal.rightSubtree;
                }
            }
            return null;
        }
    }
    
    int getHeight(TreeNode node) {
        if(node == null) {
            return 0;
        }
        
        return node.height;
    }
    
    int getBalance(TreeNode node) {
        if(node == null) {
            return 0;
        }
        
        return getHeight(node.leftSubtree)- getHeight(node.rightSubtree);
    }

    TreeNode balance(TreeNode node, int key) {
        int balance = getBalance(node);
        
        if(balance > 1 && key < node.rightSubtree.key) {
            return rotateRight(node);
        }
        if(balance > 1 && key > node.rightSubtree.key) {
            node.leftSubtree = rotateRight(node.leftSubtree);
            return rotateRight(node);
        }
        if(balance < -1 && key > node.rightSubtree.key) {
            return rotateLeft(node);
        }
        if(balance < -1 && key < node.rightSubtree.key) {
            node.rightSubtree = rotateLeft(node.rightSubtree);
            return rotateLeft(node);
        }

        return node;
    }

    TreeNode next(TreeNode root) {
        TreeNode temporal = root;
        while(temporal.leftSubtree != null) {
            temporal = temporal.leftSubtree;
        }
        return temporal;
    }
    
    TreeNode rotateLeft(TreeNode node) {
        TreeNode rightSubtree = node.rightSubtree;
        
        node.rightSubtree = rightSubtree.leftSubtree;
        rightSubtree.leftSubtree = node;
        
        node.height = Math.max(getHeight(node.leftSubtree), getHeight(node.rightSubtree));
        rightSubtree.height = Math.max(getHeight(rightSubtree.leftSubtree), getHeight(rightSubtree.rightSubtree));
        
        return rightSubtree;
    }
    
    TreeNode rotateRight(TreeNode node) {
        TreeNode leftSubtree = node.leftSubtree;
        
        node.leftSubtree = leftSubtree.rightSubtree;
        leftSubtree.rightSubtree = node;
        
        node.height = Math.max(getHeight(node.leftSubtree), getHeight(node.rightSubtree));
        leftSubtree.height = Math.max(getHeight(leftSubtree.leftSubtree), getHeight(leftSubtree.rightSubtree));
        
        return leftSubtree;
    }
    
    public TreeNode insert(TreeNode node, int key, Objetivo objetivo) {
        if(node == null){
            node = new TreeNode(key, objetivo);
            return node;
        }
        if(key < node.key) {
            node.leftSubtree = insert(node.leftSubtree, key, objetivo);
        }else if(key > node.key){
            node.rightSubtree = insert(node.rightSubtree, key, objetivo);
        }else {
            System.out.println("Ya existe un elemento con este nombre");
            return node;
        }
        
        node.height = Math.max(getHeight(node.leftSubtree), getHeight(node.rightSubtree));

        return balance(node, key);
    }
    
    public TreeNode delete(TreeNode node, int key) {
        if(node == null) {
            return node;
        }
        if(key < node.key) {
            node.leftSubtree = delete(node.leftSubtree, key);
        }else if(key > node.key) {
            node.rightSubtree = delete(node.rightSubtree, key);
        }else {
            if(node.leftSubtree == null || node.rightSubtree == null) {
                TreeNode temporal = null;
                if(temporal == node.leftSubtree) {
                    temporal = node.rightSubtree;
                }else {
                    temporal = node.leftSubtree;
                }

                if(temporal == null) {
                    temporal = node;
                    node = null;
                }else {
                    node = temporal;
                }
            }else {
                TreeNode temporal = next(node.rightSubtree);
                node.key = temporal.key;
                node.rightSubtree = delete(node.rightSubtree, temporal.key);
            }
        }
        if(node == null){
            return node;
        }
        node.height = Math.max(getHeight(node.leftSubtree), getHeight(node.rightSubtree));
        return balance(node, key);
    }

    public ArrayList <Objetivo> toArray(TreeNode node) {
        if(node == null) {
            return objetivos;
        }
        objetivos.add(node.objetivo);
        objetivos = toArray(node.leftSubtree);
        objetivos = toArray(node.rightSubtree);
        return objetivos;
    }
}