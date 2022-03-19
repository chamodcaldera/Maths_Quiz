package com.example.cw

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.ArithmeticException

import java.util.*
import java.lang.NumberFormatException

class GameWindow : AppCompatActivity()  {

    var correctans=0
    var wrongans=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_window)


        progtimer()
        genrate()




    }

//    fun markButtonDisable(button: Button) {
//        button?.isEnabled = false
//
//    }



        @SuppressLint("SetTextI18n")
       fun genrate(){




    val expre1 = findViewById<TextView>(R.id.Expression1)
    val expre2 = findViewById<TextView>(R.id.expression2)
    val random = Random()
   val ranlevel1 = 1 + random.nextInt(4)
    val ranlevel2 = 1 + random.nextInt(4)
    val newtree1 = Node.buildTree(ranlevel1.toDouble())
    val newtree2 = Node.buildTree(ranlevel2.toDouble())
            val validation1 = Node.evalTree(newtree1)
            val validation2 = Node.evalTree(newtree2)

    try {


        while (true) {
            if (0 > validation1 && validation2 > 100) {
                genrate()

            } else {
                break
            }
        }


        if (ranlevel1 == 2 || ranlevel2 == 2) {
            expre1.setText(newtree1.toString1())
            expre2.setText(newtree2.toString1())
        } else {
            expre1.setText(newtree1.toString())
            expre2.setText(newtree2.toString())
        }

        val gbt = findViewById<Button>(R.id.Gbtn)
        val eqbt = findViewById<Button>(R.id.Eqbtn)
        val lbtn = findViewById<Button>(R.id.Lbtn)

        val eqtxt = findViewById<TextView>(R.id.EvaluvateTxt2)



        lbtn.setOnClickListener {
            if (validation1 > validation2) {
                eqtxt.setTextColor(Color.GREEN)
                eqtxt.setText("Correct!")

                correctans = correctans + 1
                genrate()
            } else {
                eqtxt.setTextColor(Color.RED)
                eqtxt.setText("Wrong")

                wrongans = wrongans + 1
                genrate()
            }
        }

        eqbt.setOnClickListener {
            if (validation1 > validation2) {
                eqtxt.setTextColor(Color.GREEN)
                eqtxt.setText("Correct!")

                correctans = correctans + 1
                genrate()
            } else {
                eqtxt.setTextColor(Color.RED)
                eqtxt.setText("Wrong")


                genrate()
            }
        }


        gbt.setOnClickListener {

            if (validation1 > validation2) {
                eqtxt.setTextColor(Color.GREEN)
                eqtxt.setText("Correct!")

                correctans = correctans + 1
                genrate()
            } else {
                eqtxt.setTextColor(Color.RED)
                eqtxt.setText("Wrong")

                wrongans = wrongans + 1
                genrate()
            }

        }
    }catch (ex :Exception){
        print("")
    }
}

    private fun progtimer() {
        val progbar= findViewById<ProgressBar>(R.id.progressBar)
        val corrtxt=findViewById<TextView>(R.id.Correcttxt)
        val wrongtxt=findViewById<TextView>(R.id.wrongAmt)

        object : CountDownTimer(50000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val increment:Int
                increment = ((progbar.getMax() * 1000) / (1000 * 50))
                progbar.setProgress(progbar.getProgress() + increment)

            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {

                corrtxt.setText("Correct Ansers :"+ correctans)
                wrongtxt.setText("Wrong Answers :"+wrongans)

                val gretbtn=findViewById<Button>(R.id.Gbtn)
                val equlbtn=findViewById<Button>(R.id.Eqbtn)
                val lessbtn=findViewById<Button>(R.id.Lbtn)

                gretbtn.isEnabled=false
                equlbtn.isEnabled=false
                lessbtn.isEnabled=false




            }
        }.start()
    }


    object Node  {


        var operators = arrayOf("/", "*", "-", "+")
        fun buildTree(numOfNodes: Double): TreeNode {
            val randNum = Random()
            if (numOfNodes == 1.0) {
//                val value = 1 + randNum.nextInt(20)
                  val value=(1..20).random()

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
                return root.operator.toInt()
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
            return if (root.operator == "*") leftEval * rightEval else try {
                leftEval / rightEval
            } catch (e :ArithmeticException){
                return 0
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
                } else "($left$operator$right)"
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







}

