package model;

public class LeaderBoard {

    private Player root;

    public LeaderBoard() {
        root = null;
    }

    public void printInorden() {
        inordenHelper(root);
    }

    public Player getNResult(Player node, int n, int i, int control) {
        if (node == null) {
            return null;
        } else  {  // if (i < n)
            getNResult(node.getLeft(), n, i+1, control);
            
            
            //System.out.println( i + " - "+ n);
            //if ((n) == i) {
                System.out.println("return 1 "+node.getNickName() + " puntaje "+ node.getScore()+ "Valor numero " + control);
                //return node;
            //}

            getNResult(node.getRight(), n, i+1, control);
            System.out.println("return 2 "+node.getNickName());
            return node;
        }
    }

    public Player getwinner(Player node, int n, int i) {
        if (node == null) {
            return null;
        }
        getwinner(node.getLeft(), n, i-1);
        System.out.println(node.getScore());
        return node;
        //getwinner(node.getRight());
    }

    private void inordenHelper(Player node) {
        if (node == null) {
            return;
        }
        inordenHelper(node.getLeft());
        System.out.println(node.getScore());
        inordenHelper(node.getRight());
    }

    public int insertNode(Player newPNode, int count) {
        count++;
        if (root == null) {
            root = newPNode;
        } else {
            insertNode(root, newPNode);
        }
        return count;
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
}
