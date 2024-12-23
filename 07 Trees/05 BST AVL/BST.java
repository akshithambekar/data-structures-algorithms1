// Name: 
// Date: 

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

public class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return countNodes(root);
   }

   public int countNodes(TreeNode t) 
   {
      if (t == null) {
         return 0;
      }
      return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());

   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      root = add(root, s);
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if (t == null) {
         return new TreeNode(s, null, null);
      } else if (s.compareTo((String)t.getValue()) <= 0) {
         t.setLeft(add(t.getLeft(), s));
      } else {
         t.setRight(add(t.getRight(), s));
      }
      return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
   {
      String toRet = "";
      if (t == null)
         return "";
      toRet += display(t.getRight(), level + 1); 
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); 
      return toRet;
   }
   
   public boolean contains(String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if (t == null) {
         return false;
      }
      int c = x.compareTo((String) t.getValue());
      if (c == 0) {
         return true;
      } else if (c < 0) {
         return contains(t.getLeft(), x);
      } else {
         return contains(t.getRight(), x);
      }
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if (t == null) {
         return null;
      }
      while (t.getLeft() != null) {
         t = t.getLeft();
      }
      return t.getValue().toString();
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if (t == null) {
         return null;
      }
      if (t.getRight() == null) {
         return t.getValue().toString();
      }
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String returnString = "";
      if (t != null) {
         returnString += toString(t.getLeft());
         returnString += t.getValue() + " ";
         returnString += toString(t.getRight());
      }
      return returnString;
   }
   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      if (current == null)
         return null;
      
      if (target.equals(current.getValue())) {
         if (current.getLeft() == null && current.getRight() == null)
            return null;
         else if (current.getLeft() == null)
            return current.getRight();
         else if (current.getRight() == null)
            return current.getLeft();
         else {
            String maxLeft = max(current.getLeft())
            current.setValue(maxLeft);
            current.setLeft(remove(current.getLeft(), maxLeft));
         }
      }

      else if (target.compareTo((String)current.getValue()) < 0)
         current.setLeft(remove(current.getLeft(), target));
      else
         current.setRight(remove(current.getRight(), target));
      return current;
   }

   /*  start the addBalanced methods */

   private int calcBalance(TreeNode t) //height to right minus 
   {                                    //height to left
      return height(t.getRight()) - height(t.getLeft());
   }

   private int height(TreeNode t)   //from TreeLab
   {
      if (t == null) {
         return -1;
      }
      int left = 1 + height(t.getLeft());
      int right = 1 + height(t.getRight());
      
      if (left > right) {
         return left;
      } else {
         return right;
      }
   }

   public void addBalanced(String value)  
   {
      root = add(value);
      root = balanceTree(root);   // for an AVL tree. Put in the arguments you want.
   }
   private void balanceTree(TreeNode t)  //recursive.  Whatever makes sense.
   {
      if (calcBalance(t) > 0) { // tree is right heavy
         
         if (calcBalance(t.getRight()) < 0) { // tree's right subtree is left heavy
            doubleLeft(t.getRight());
         } else {
            singleLeft(t.getRight());
         }
       
      } else if (calcBalance(t) < 0) { // tree is left heavy
         
         if (calcBalance(t.getLeft()) > 0) { // tree's left subtree is right heavy
            doubleRight(t.getLeft());
         } else {
            singleRight(t.getLeft());
         }
      }
   }
   // 4 rotation methods
   
   public TreeNode singleLeft(TreeNode a) {
      a.getRight().setRight(a);
      return a;
   }

   public TreeNode singleRight(TreeNode c) {
      c.getLeft().setRight(c);
      return c;
   }

   public TreeNode doubleLeft(TreeNode t) {

   }

   public TreeNode doubleRight(TreeNode t) {
      
   }
   
}