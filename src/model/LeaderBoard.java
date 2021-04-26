package model;

import java.io.Serializable;

public class LeaderBoard implements Serializable {
    private static final long serialVersionUID = 1;

    private Player root;

    private Player topRoot;

    private int topScoresNumb;

    public LeaderBoard() {
        root = null;
        topRoot = null;

        topScoresNumb = 0;
    }

    public void printLinearToplist(){
        printLinearToplist(topRoot);
    }

    private void printLinearToplist(Player player){
        if(player != null){
            printLinearToplist(player.getTopNext());
        }
    }

    public Player getNResult(Player node, int n, int i) {
        if(node == null ){
            return null;
        }     
        if(i == n){
           return node;
        }
        return getNResult(node.getTopNext(), n, i+1);
    }

    public void printInorden() {
        inordenHelper(root);
    }

    public void topList() {
        
        topRoot=null;
        topList(root);
        
    }

    private void topList(Player node) {
        
        if (node == null) {
            return;
        } 
        topList(node.getLeft());
        node.setTopNext(null);
        addNodeTopList(node);
        topList(node.getRight());
    }

    private void addNodeTopList(Player newNode) {

        if (topRoot == null) {
            topRoot = newNode;
            
        } else {

            nodeList(topRoot, newNode);
        }
    }

    private void nodeList(Player current, Player p) {

        if (current.getTopNext() == null) {
            current.setTopNext(p);
        } else { 
            nodeList(current.getTopNext(), p);
        }
    }

    private void inordenHelper(Player node) {
        if (node == null) {
            return;
        }
        inordenHelper(node.getLeft());
        System.out.println(node.getScore());
        inordenHelper(node.getRight());
    }

    public void insertNode(Player newPNode) {
        topScoresNumb++;
        
        if (root == null) {
            root = newPNode;
        } else {
            insertNode(root, newPNode);
        }
        
    }

    private void insertNode(Player parent, Player newPlayer) {
        if (newPlayer.getScore() >= parent.getScore()) {
            if (parent.getLeft() == null) {
                parent.setLeft(newPlayer);
            } else {
                insertNode(parent.getLeft(), newPlayer);
            }
        } else {
            if (parent.getRight() == null) {
                parent.setRight(newPlayer);
            } else {
                insertNode(parent.getRight(), newPlayer);
            }
        }
    }

    public Player getRoot() {
        return root;
    }

    public void setRoot(Player root) {
        this.root = root;
    }

    public Player getTopRoot() {
        return topRoot;
    }

    public void setTopRoot(Player topRoot) {
        this.topRoot = topRoot;
    }

    public int getTopScoresNumb() {
        return topScoresNumb;
    }

    public void setTopScoresNumb(int topScoresNumb) {
        this.topScoresNumb = topScoresNumb;
    }

}
