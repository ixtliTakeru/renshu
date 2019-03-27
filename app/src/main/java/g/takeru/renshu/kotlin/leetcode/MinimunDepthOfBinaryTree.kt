package g.takeru.renshu.kotlin.leetcode

import g.takeru.renshu.kotlin.leetcode.`object`.BinaryTreeNode
import timber.log.Timber
import kotlin.math.min

class MinimumDepthOfBinaryTree {

    /**
     * Given a binary tree, find its minimum depth. The minimum depth is the number of nodes
     * along the shortest path from root node down to the nearest leaf node.
     *
     * Example:
     *        1
     *      /  \
     *     2   3
     *    / \
     *   4  5
     *   minimum height of above Binary Tree is 2.
     */

    fun testing() {
        var node1 = BinaryTreeNode(1)
        var node2 = BinaryTreeNode(2)
        var node3 = BinaryTreeNode(3)
        var node4 = BinaryTreeNode(4)
        var node5 = BinaryTreeNode(5)
        node1.left = node2
        node1.right = node3
        node2.left = node4
        node2.right = node5

        Timber.d("MinimumDepthOfBinaryTree is ${minDepth(node1)}")
    }

    fun minDepth(root: BinaryTreeNode?): Int {
        if (root == null) {
            Timber.d("minDepth node is null")
            return 0
        } else if (root.left == null && root.right == null) {
            Timber.d("minDepth node${root.value} left and right are null")
            return 1
        }
        else if (root.left == null) {
            Timber.d("minDepth node${root.value} left is null")
            return minDepth(root.right) + 1
        } else if (root.right == null) {
            Timber.d("minDepth node${root.value} right is null")
            return minDepth(root.left) + 1
        }

        Timber.d("minDepth node${root.value} min")
        return min(minDepth(root.left), minDepth(root.right)) + 1
     }
}