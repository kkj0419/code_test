/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodeList = new ArrayList<>();
        if(root != null)    traverse(nodeList, root);
        return nodeList;
    }
    
    private void traverse(List<Integer> list, TreeNode node){
        if(node.left != null)   traverse(list, node.left);
        list.add(node.val);
        if(node.right != null) traverse(list, node.right);
    }
}
