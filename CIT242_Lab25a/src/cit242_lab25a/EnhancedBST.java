package cit242_lab25a;

import java.util.LinkedList;
import java.util.Queue;

/* Enhanced Binary Search Tree Class:
*      The original BST class is provided in the Liang textbook.
*/

public class EnhancedBST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public EnhancedBST() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public EnhancedBST(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    @Override
    /**
     * Returns true if the element is in the tree
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }
    
 
    public int getMaxDepth(TreeNode<E> n) {
        if(n == null)
            return 0;
        else if(n.left == null && n.right == null) {
            return 0;
        }
        int l = getMaxDepth(n.left);
        int r = getMaxDepth(n.right);
        
        // +1 for children
        return l > r ? l+1 : r+1;
        
    }
    
    

    /**
     * Returns height of node (or -1 if node not present)
     */
    public int height(E e) {
        
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                // At the node
                // Find Longest Path
                //return travelTree(current);
                return getMaxDepth(current);
                
            }
        }
        return -1;
        //return false;
        
        //        // (lab exercise)
        // contains(e);
        /*  
         int nodeHeight = -1;
         if (root == null) {
            return nodeHeight;
            } else {
             int leftHeight = height(root.left.element);
             int rightHeight = height(root.right.element);

    if (leftHeight > rightHeight) {
        nodeHeight = leftHeight + 1;
    } else {
        nodeHeight = rightHeight + 1;
    }
                   
        }
         
        return nodeHeight; */
        
    }
    
    
    public int getMaxWidth() {
  
      int maxWidth = 0;
      int nodeCount = 0;
   
     Queue<TreeNode<E>> tnQueue = new LinkedList<>(); 
     TreeNode<E> current = root;
     
        tnQueue.add(current);
     
        while(!tnQueue.isEmpty()) {
         if(current == null) {return 0;}
            // full size of queue
         nodeCount = tnQueue.size();
         // update maxWidth with the larger value
         maxWidth = Math.max(maxWidth, nodeCount);
         while(nodeCount > 0) {
          current = tnQueue.remove();
          if(current.left!=null) {tnQueue.add(current.left);}
          if(current.right!=null) {tnQueue.add(current.right);}
          nodeCount--;
         }
        }
       return maxWidth;
    }
        
    

        
    // (end height)

  
    @Override
    /**
     * Insert element e into the binary tree Return true if the element is
     * inserted successfully
     */
    
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        System.out.printf("root=%10d\n", System.identityHashCode(root));
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root);
        inorder(root.right);
    }

    @Override
    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        System.out.printf("root=%10d\n", System.identityHashCode(root));
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.println(root);
    }

    @Override
    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        System.out.printf("root=%10d\n", System.identityHashCode(root));
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.println(root);
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * This inner class is static, because it does not access any instance
     * members defined in its outer class
     */
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }

        @Override
        public String toString() {
            String leftText, rightText;
            if (this == null) {
                return "null";
            } else {
                if (this.left == null) {
                    leftText = "null";
                } else {
                    leftText = String.format("%d", System.identityHashCode(left));
                }
                if (this.right == null) {
                    rightText = "null";
                } else {
                    rightText = String.format("%d", System.identityHashCode(right));
                }
            }
            return String.format("node=%10d, element=%12s, left=%10s, right=%10s",
                    System.identityHashCode(this), element.toString(), leftText, rightText);
        } //(end toString)
    } // (end class TreeNode)

    @Override
    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }

    /**
     * Returns a path from the root leading to the specified element
     */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list
                = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list; // Return an array list of nodes
    }

    @Override
    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed at by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }
        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element deleted successfully
    }

    @Override
    /**
     * Obtain an iterator. Use inorder.
     */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list

        private java.util.ArrayList<E> list
                = new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        /**
         * More elements for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }

            return false;
        }

        @Override
        /**
         * Get the current element and move to the next
         */
        public E next() {
            return list.get(current++);
        }

        @Override // Remove the element returned by the last next()
        public void remove() {
            if (current == 0) // next() has not been called yet
            {
                throw new IllegalStateException();
            }
            delete(list.get(--current));
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    @Override
    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
} //(end class EnhancedBST)