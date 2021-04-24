package model;

public class LeaderBoard {

    private Player root;

    private Player topRoot;

    public LeaderBoard() {
        root = null;
        topRoot = null;
    }

    public void printLinearToplist(){
        printLinearToplist(topRoot);
    }

    private void printLinearToplist(Player player){
        if(player != null){
            System.out.println(player.getScore());
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
        topList(root);
        
    }

    private void topList(Player node) {
        
        if (node == null) {
            //System.out.println("entra al null");
            return;
        } 
        topList(node.getLeft());
        //System.out.println(node.getScore());
        addNodeTOpList(node);
        topList(node.getRight());
    }

    private void addNodeTOpList(Player newNode) {

        if (topRoot == null) {
            //System.out.println(newNode.getScore());
            topRoot = newNode;
        } else {
            //System.out.println(newNode.getScore());
            nodeList(topRoot, newNode, 1);
        }
    }

    private void nodeList(Player current, Player p, int i) {

        if (current.getTopNext() == null) {
            //System.out.println("Entra null");
            current.setTopNext(p);
            //System.out.println(p.getNickName() + " " + p.getScore() + " pos " + i);
        } else {
            //System.out.println("Entra al a consulta el siguiente");
            current = current.getTopNext();
            nodeList(current, p, i+1);
        }
    }

    private void inordenHelper(Player node) {
        if (node == null) {
            return;
        }
        inordenHelper(node.getLeft());
        System.out.println(node.getScore());
        //nodeList(node, topRoot);
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

    public Player getTopRoot() {
        return topRoot;
    }

    public void setTopRoot(Player topRoot) {
        this.topRoot = topRoot;
    }

}
