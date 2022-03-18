package com.example.cw

import java.lang.NumberFormatException
import java.util.*
import kotlin.jvm.JvmStatic

object Node {
    var operators = arrayOf("/", "*", "-", "+")
    fun buildTree(numOfNodes: Double): TreeNode {
        val randNum = Random()
        if (numOfNodes == 1.0) {
            val value = 1 + randNum.nextInt(20)
            return TreeNode(null, null, Integer.toString(value))
        }
        val numLeft = Math.ceil(numOfNodes / 2)
        val leftSubTree = buildTree(numLeft)
        val numRight = Math.floor(numOfNodes / 2)
        val rightSubTree = buildTree(numRight)
        val m = randNum.nextInt(operators.size)
        val operator = operators[m]
        return TreeNode(leftSubTree, rightSubTree, operator)
    }

    fun evalTree(root: TreeNode?): Int {

        // Empty tree
        if (root == null) return 0

        // Leaf node i.e, an integer
        if (root.left == null && root.right == null) try {
            return root.operator.toInt() // output = 25
        } catch (ex: NumberFormatException) {
            ex.printStackTrace()
        }


        // Evaluate left subtree
        val leftEval = evalTree(root.left)

        // Evaluate right subtree
        val rightEval = evalTree(root.right)

        // Check which operator to apply
        if (root.operator == "+") return leftEval + rightEval
        if (root.operator == "-") return leftEval - rightEval
        return if (root.operator == "*") leftEval * rightEval else leftEval / rightEval
    }

    @JvmStatic
    fun main(args: Array<String>) {
//        Scanner sc= new Scanner(System.in);
//        System.out.print("Enter the level no: ");
//        double input = sc.nextDouble();
//        System.out.println(buildTree(3).toString());
        val random = Random()
        val ranlevel1 = 1 + random.nextInt(4)
        val ranlevel2 = 1 + random.nextInt(4)
        val newtree1 = buildTree(ranlevel1.toDouble())
        val newtree2 = buildTree(ranlevel2.toDouble())
        val validation1 = evalTree(newtree1)
        val validation2 = evalTree(newtree2)
        if (ranlevel1 == 2 || ranlevel2 == 2) {
            println(newtree1.toString1())
            println(newtree2.toString1())
        } else {
            println(newtree1.toString())
            println(newtree2.toString())
        }
    }

    class TreeNode(var left: TreeNode?, var right: TreeNode?, var operator: String) {
        override fun toString(): String {
            return if (left == null && right == null) {
                operator
            } else if (left == null) {
                "$left $operator "
            } else if (right == null) {
                " $operator $right"
            } else String.format("(%1\$s), %2\$s ,%3\$s", left, operator, right)
        }

        fun toString1(): String {
            return if (left == null && right == null) {
                operator
            } else if (left == null) {
                "$left $operator "
            } else if (right == null) {
                " $operator $right"
            } else "" + left + "" + operator + "" + right + ""
        }
    }
}