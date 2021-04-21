package model;

public class LeaderBoard {
    private Player root;

    public LeaderBoard() {
        root = null;
    }

    public void printInorden() {
        inordenHelper(root);
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
        if (root == null) {
            root = newPNode;
        } else {
            root.insertNode(newPNode);
        }
    }

}
