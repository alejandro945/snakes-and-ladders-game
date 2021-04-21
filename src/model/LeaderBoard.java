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

}
