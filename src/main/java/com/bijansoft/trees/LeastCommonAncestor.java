//package com.bijansoft.trees;
//
//import java.util.Objects;
//
//public class LeastCommonAncestor {
//
//  private static class TreeNode<T> {
//
//    private final T data;
//    private final TreeNode<T> left, right;
//
//    TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
//      this.data = data;
//      this.left = left;
//      this.right = right;
//    }
//  }
//
//  private static class FoundState<T> {
//
//    boolean isNode1Found;
//    boolean isNode2Found;
//    boolean bothFound;
//    TreeNode<T> ancestor;
//  }
//
//  // NOT WORKING! no extra data structure to store nodes
//  private static <T> TreeNode<T> findLeastCommonAncestor(TreeNode<T> root, TreeNode<T> node1,
//      TreeNode<T> node2) {
//    Objects.requireNonNull(root, "root must be not null");
//    Objects.requireNonNull(node1, "node1 must be not null");
//    Objects.requireNonNull(node2, "node2 must be not null");
//
//    FoundState<T> state = new FoundState<>();
//    findLeastCommonAncestor(root, node1, node2, state);
//
//    return state.ancestor;
//  }
//
//  private static <T> void findLeastCommonAncestor(TreeNode<T> root, TreeNode<T> node1,
//      TreeNode<T> node2, FoundState<T> state) {
//    if (root == node1) {
//      state.isNode1Found = true;
//    } else if (root == node2) {
//      state.isNode2Found = true;
//    }
//    if (state.isNode1Found && state.isNode2Found) {
//      return;
//    }
//
//    if (root.left != null) {
//      findLeastCommonAncestor(root.left, node1, node2, state);
//    }
//    if ((state.isNode1Found || state.isNode2Found)) {
//      state.ancestor = root;
//      System.out.println(root.data);
//    }
//    if (root.right != null) {
//      findLeastCommonAncestor(root.right, node1, node2, state);
//    }
//    if (!state.bothFound && state.isNode1Found && state.isNode2Found) {
//      state.bothFound = true;
//      state.ancestor = root;
//      System.out.println(root.data);
//    }
//  }
//
//  public static void main(String[] args) {
//    TreeNode<Integer> node9 = new TreeNode<>(9, null, null);
//    TreeNode<Integer> node8 = new TreeNode<>(8, null, node9);
//
//    TreeNode<Integer> node4 = new TreeNode<>(4, null, null);
//    TreeNode<Integer> node5 = new TreeNode<>(5, null, null);
//
//    TreeNode<Integer> node61 = new TreeNode<>(61, null, null);
//    TreeNode<Integer> node6 = new TreeNode<>(6, null, node61);
//
//    TreeNode<Integer> node7 = new TreeNode<>(7, null, node8);
//
//    TreeNode<Integer> node2 = new TreeNode<>(2, node4, node5);
//    TreeNode<Integer> node3 = new TreeNode<>(3, node6, node7);
//
//    TreeNode<Integer> root = new TreeNode<>(1, node2, node3);
//
//    TreeNode<Integer> ancestor = findLeastCommonAncestor(root, node6, node9);
//
//    System.out.println("ancestor: " + ancestor.data);
//  }
//}
