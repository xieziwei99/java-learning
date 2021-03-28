package 数据结构;

import java.util.Scanner;

/**
 * @author xzw
 * 2021-02-25
 */
public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree() {
    }

    public BinaryTree(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public BinaryTree<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public BinaryTree<T> setLeft(BinaryTree<T> left) {
        this.left = left;
        return this;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public BinaryTree<T> setRight(BinaryTree<T> right) {
        this.right = right;
        return this;
    }

    /**
     * 按前序遍历输入序列（数字）生成一棵树
     * 示例输入序列：12　23　$　$　36　78　$　$　89　$　$
     *         		89
     *          36
     *              78
     *      12
     *          23
     *
     * 如果输入时只输入 $ ，则会返回一个 null，但这并不能代表空树，
     * 如果调用他的相关方法，则会抛出空指针异常
     */
    public static BinaryTree<Integer> createBinaryTree(Scanner in) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        String input = in.next();
        if ("$".equals(input)) {
            tree = null;
        } else {
            tree.setValue(Integer.parseInt(input));
            tree.setLeft(createBinaryTree(in));
            tree.setRight(createBinaryTree(in));
        }
        return tree;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    /**
     * RDL(Right, Data, Left)递归打印树
     *
     * @param tree 树
     */
    private static <T> void printTree(BinaryTree<T> tree, int level) {
        if (tree != null) {
            printTree(tree.getRight(), level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println(tree.getValue());
            printTree(tree.getLeft(), level + 1);
        }
    }

    /**
     * RDL(Right, Data, Left)递归打印树
     *
     * @param tree 树
     */
    public static <T> void printTree(BinaryTree<T> tree) {
        printTree(tree, 0);
    }

    /**
     * 前序遍历-递归
     */
    public void preorderTraverse(ExecutorWithNoResult executor) {
        if (this.getValue() != null) {
            executor.execute(this.value);
            if (this.getLeft() != null) {
                this.getLeft().preorderTraverse(executor);
            }
            if (this.getRight() != null) {
                this.getRight().preorderTraverse(executor);
            }
        }
    }

    /**
     * 后序遍历-递归
     */
    public void postorderTraverse(ExecutorWithNoResult executor) {
        if (this.getValue() != null) {
            if (this.getLeft() != null) {
                this.getLeft().postorderTraverse(executor);
            }
            if (this.getRight() != null) {
                this.getRight().postorderTraverse(executor);
            }
            executor.execute(this.getValue());
        }
    }

    /**
     * 中序遍历-递归
     */
    public void inorderTraverse(ExecutorWithNoResult executor) {
        if (this.getValue() != null) {
            if (this.getLeft() != null) {
                this.getLeft().inorderTraverse(executor);
            }
            executor.execute(this.getValue());
            if (this.getRight() != null) {
                this.getRight().inorderTraverse(executor);
            }
        }
    }

    /**
     * 前序遍历-栈
     */
    @SuppressWarnings("rawtypes")
    public void preorderTraverseWithStack(ExecutorWithNoResult executor) {
        Stack<BinaryTree> stack = new Stack<>(BinaryTree.class);
        BinaryTree p = this;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                executor.execute(p.getValue());
                stack.push(p);
                p = p.left;
            } else {
                p = stack.peek();
                stack.pop();
                p = p.right;
            }
        }
    }

    /**
     * 中序遍历-栈
     */
    @SuppressWarnings("rawtypes")
    public void inorderTraverseWithStack(ExecutorWithNoResult executor) {
        Stack<BinaryTree> stack = new Stack<>(BinaryTree.class);
        BinaryTree p = this;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.getLeft();
            } else {
                p = stack.peek();
                stack.pop();
                executor.execute(p.getValue());
                p = p.getRight();
            }
        }
    }

    /**
     * 后序遍历-栈
     */
    @SuppressWarnings("rawtypes")
    public void postorderTraverseWithStack(ExecutorWithNoResult executor) {
        Stack<BinaryTree> stack = new Stack<>(BinaryTree.class);
        BinaryTree cur = this;
        BinaryTree pre = null;
        stack.push(cur);

        boolean canVisit;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            canVisit = (pre != null && (pre == cur.getLeft() || pre == cur.getRight()));

            if ((cur.getLeft() == null && cur.getRight() == null) || canVisit) {
                executor.execute(cur.getValue());
                pre = cur;
                stack.pop();
            } else {
                if (cur.getRight() != null) {
                    stack.push(cur.getRight());
                }
                if (cur.getLeft() != null) {
                    stack.push(cur.getLeft());
                }
            }
        }
    }

    /**
     * 带有返回值的前序遍历-递归
     */
    @SuppressWarnings("unchecked")
    public void preorderTraverse(ExecutorWithResult executor) {
        if (this.getValue() != null) {
            this.setValue((T) executor.execute(this.value));
            if (this.getLeft() != null) {
                this.getLeft().preorderTraverse(executor);
            }
            if (this.getRight() != null) {
                this.getRight().preorderTraverse(executor);
            }
        }
    }

    /**
     * 递归-得到叶子节点的个数
     * @return 叶子节点的个数
     */
    public int getLeaveNum() {
        if (this.getLeft() == null && this.getRight() == null) {
            return 1;
        }
        int leftNum = 0, rightNum = 0;
        if (this.getLeft() != null) {
            leftNum = this.getLeft().getLeaveNum();
        }
        if (this.getRight() != null) {
            rightNum = this.getRight().getLeaveNum();
        }
        return leftNum + rightNum;
    }

    /**
     * 递归-交换左右子树
     */
    public void swapLeftAndRight() {
        BinaryTree<T> temp = this.getLeft();
        this.setLeft(this.getRight());
        this.setRight(temp);
        if (this.getLeft() != null) {
            this.getLeft().swapLeftAndRight();
        }
        if (this.getRight() != null) {
            this.getRight().swapLeftAndRight();
        }
    }

    /**
     * 递归-计算节点个数
     * @return 节点个数
     */
    public int getNodeNum() {
        int leftNum = 0, rightNum = 0;
        if (this.getLeft() != null) {
            leftNum = this.getLeft().getNodeNum();
        }
        if (this.getRight() != null) {
            rightNum = this.getRight().getNodeNum();
        }
        return leftNum + rightNum + 1;
    }

    public int getHeight() {
        int leftHeight = 0, rightHeight = 0;
        if (this.getLeft() != null) {
            leftHeight = this.getLeft().getHeight();
        }
        if (this.getRight() != null) {
            rightHeight = this.getRight().getHeight();
        }
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    private void checkSum(int sum, int res, boolean[] exists) {
        if (this.getValue() != null) {
            res += (Integer) this.getValue();
            if (this.getLeft() != null) {
                this.getLeft().checkSum(sum, res, exists);
            }
            if (this.getRight() != null) {
                this.getRight().checkSum(sum, res, exists);
            }
            if (this.getLeft() == null && this.getRight() == null) {
                if (sum == res) {
                    exists[0] = true;
                }
            }
        }
    }

    /**
     * 检查从根节点到所有叶子节点，是否存在一条路径，使得它的权值和等于给定的 sum 值
     * @param sum 给定的权值和
     * @return 如果存在，则返回 true
     */
    public boolean checkSum(int sum) {
        boolean[] exists = {false};
        checkSum(sum, 0, exists);
        return exists[0];
    }

    private void acquirePathWithSpecifySum(int sum, int res, List<Integer> tempList, List<Integer> resList) {
        if (this.getValue() != null) {
            res += (Integer) this.getValue();
            tempList.add((Integer) this.getValue());
            if (this.getLeft() != null) {
                this.getLeft().acquirePathWithSpecifySum(sum, res, new ArrayList<>((ArrayList<Integer>) tempList), resList);
            }
            if (this.getRight() != null) {
                this.getRight().acquirePathWithSpecifySum(sum, res, new ArrayList<>((ArrayList<Integer>) tempList), resList);
            }
            if (this.getLeft() == null && this.getRight() == null) {
                if (sum == res) {
                    for (int i = 0; i < tempList.length(); i++) {
                        resList.add(tempList.get(i));
                    }
                }
            }
        }
    }

    /**
     * 检查从根节点到所有叶子节点，是否存在一条路径，使得它的权值和等于给定的 sum 值
     * @param sum 给定的权值和
     * @return 如果存在，则返回找到的那条路径（后找到的路径）
     */
    public List<Integer> acquirePathWithSpecifySum(int sum) {
        List<Integer> ret = new ArrayList<>();
        acquirePathWithSpecifySum(sum, 0, new ArrayList<>(), ret);
        return ret;
    }

    public static void main(String[] args) {
        // 常规的树
        BinaryTree<Integer> tree1 = new BinaryTree<>(12);
        tree1.setLeft(new BinaryTree<>(23));
        tree1.setRight(new BinaryTree<>(36).setLeft(new BinaryTree<Integer>().setValue(78)).setRight(new BinaryTree<>(89)));

        // 输入时测试，一般的树，一般更大点的树
        /*
        12　23　$　$　36　78　$　$　89　$　$
        		89
            36
                78
        12
            23
         */
        /*
        12 23 45 57 $ $ 67 $ 89 $ $ $ 36 78 56 $ $ $ 89 $ $
        		89
            36
                78
                    56
        12
            23
                         89
                    67
                45
                    57
         */
        Scanner in = new Scanner(System.in);
        tree1 = BinaryTree.createBinaryTree(in);
        in.close();

        BinaryTree.printTree(tree1);
        tree1.preorderTraverse((ExecutorWithNoResult) System.out::println);
        tree1.preorderTraverse(o -> (int) o + 1);
        BinaryTree.printTree(tree1);

        System.out.println("后序遍历输出");
        tree1.postorderTraverse(System.out::println);

        System.out.println("中序遍历输出");
        tree1.inorderTraverse(System.out::println);

        System.out.println("前序遍历输出");
        tree1.preorderTraverseWithStack(System.out::println);

        System.out.println("中序遍历输出");
        tree1.inorderTraverseWithStack(System.out::println);

        System.out.println("后序遍历输出");
        tree1.postorderTraverseWithStack(System.out::println);

        System.out.println("叶子节点个数");
        System.out.println(tree1.getLeaveNum());

        tree1.swapLeftAndRight();
        System.out.println("交换左右子树后");
        BinaryTree.printTree(tree1);

        System.out.println("节点个数");
        System.out.println(tree1.getNodeNum());

        System.out.println("树的深度");
        System.out.println(tree1.getHeight());
    }
}

@FunctionalInterface
interface ExecutorWithNoResult {
    void execute(Object o);
}

@FunctionalInterface
interface ExecutorWithResult {
    Object execute(Object o);
}