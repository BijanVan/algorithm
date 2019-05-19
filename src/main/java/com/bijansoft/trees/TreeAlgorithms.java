package com.bijansoft.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TreeAlgorithms {

  private static class TreeNode<T> {

    private final T data;
    private final TreeNode<T> left, right;

    TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  private static <T extends Comparable<T>> List<List<T>> generateFromTree(TreeNode<T> root) {
    Objects.requireNonNull(root, "root must be not null");

    List<List<T>> list = new ArrayList<>();
    List<T> subList = new ArrayList<>();
    subList.add(root.data);
    list.add(subList);
    generateFromTree(root, list);
    return list;
  }

  private static <T extends Comparable<T>> void generateFromTree(TreeNode<T> root,
      List<List<T>> list) {
    if (root.left != null && root.right != null) {
      List<List<T>> addedList = new ArrayList<>();
      for (List<T> subList : list) {
        List<T> subListCopy = new ArrayList<>(subList);
        subList.add(root.left.data);
        subList.add(root.right.data);
        subListCopy.add(root.right.data);
        subListCopy.add(root.left.data);
        addedList.add(subListCopy);
      }
      list.addAll(addedList);
    } else if (root.left != null) {
      for (List<T> subList : list) {
        subList.add(root.left.data);
      }
    } else if (root.right != null) {
      for (List<T> subList : list) {
        subList.add(root.right.data);
      }
    }

    if (root.left != null) {
      generateFromTree(root.left, list);
    }
    if (root.right != null) {
      generateFromTree(root.right, list);
    }
  }

  private static <T extends Comparable<T>> boolean checkSubTree(TreeNode<T> tree1,
      TreeNode<T> tree2) {
    Objects.requireNonNull(tree1, "tree1 must be not null");
    Objects.requireNonNull(tree2, "tree2 must be not null");

    Optional<TreeNode<T>> node = findNode(tree1, tree2.data);
    if (node.isEmpty()) {
      return false;
    }

    return areTreesEqual(node.get(), tree2);
  }

  private static <T extends Comparable<T>> Optional<TreeNode<T>> findNode(TreeNode<T> root, T key) {
    if (root.data.equals(key)) {
      return Optional.of(root);
    }

    Optional<TreeNode<T>> node = Optional.empty();
    if (root.left != null) {
      node = findNode(root.left, key);
    }
    if (node.isEmpty() && root.right != null) {
      node = findNode(root.right, key);
    }

    return node;
  }

  private static <T extends Comparable<T>> boolean areTreesEqual(TreeNode<T> tree1,
      TreeNode<T> tree2) {
    if (!tree1.data.equals(tree2.data)) {
      return false;
    }

    boolean areEqual = true;
    if (tree1.left != null && tree2.left != null) {
      areEqual = areTreesEqual(tree1.left, tree2.left);
    }
    if (areEqual && tree1.right != null && tree2.right != null) {
      areEqual = areTreesEqual(tree1.right, tree2.right);
    }

    return areEqual;
  }

  public static void main(String[] args) {
    // Tree #1
    TreeNode<Integer> node9 = new TreeNode<>(9, null, null);
    TreeNode<Integer> node8 = new TreeNode<>(8, null, null);
    TreeNode<Integer> node7 = new TreeNode<>(7, node8, node9);
    TreeNode<Integer> node5 = new TreeNode<>(5, null, null);
    TreeNode<Integer> node6 = new TreeNode<>(6, node5, node7);
    TreeNode<Integer> node3 = new TreeNode<>(3, null, null);
    TreeNode<Integer> node1 = new TreeNode<>(1, null, null);
    TreeNode<Integer> node2 = new TreeNode<>(2, node1, node3);
    TreeNode<Integer> node4 = new TreeNode<>(4, node2, node6);

    // Tree #2
    TreeNode<Integer> node91 = new TreeNode<>(9, null, null);
    TreeNode<Integer> node81 = new TreeNode<>(8, null, null);
    TreeNode<Integer> node71 = new TreeNode<>(7, node81, node91);
    TreeNode<Integer> node51 = new TreeNode<>(5, null, null);
    TreeNode<Integer> node61 = new TreeNode<>(6, node51, node71);

//    generateFromTreeTest(node4);

    boolean isSubTree = checkSubTree(node4, node61);
    System.out.println(isSubTree);
//    System.out.println(findNode(node4, 6).map(r -> r.data));
  }

  private static void generateFromTreeTest(TreeNode<Integer> root) {
    List<List<Integer>> list = generateFromTree(root);
    System.out.println(list.size());
    for (List<Integer> subList : list) {
      System.out.println(subList);
    }
  }
}
