/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author nithishkp
 */
class TrieNode
{
    private HashMap <Character,TrieNode> node;
    private boolean last;
    
    private static TrieNode root = null;
    public static TrieNode getRoot()
    {
        if(root == null)
        {
            root = new TrieNode();
            root.node = new HashMap<Character,TrieNode>();
            root.last = true;
        }
        return root ;
    }
    public TrieNode getNode()
    {
        TrieNode t = new TrieNode();
        t.node = new HashMap<Character,TrieNode>();
                
        return t;
    }
    public boolean isCharPresent(Character ch)
    {
        return this.node.containsKey((Character)ch);
    }
    public boolean isLastNode()
    {
        return this.last;
    }
    public TrieNode addWord(String word)
    {
        TrieNode cur = null;
        
        cur = getRoot();
        
        int i = 0;
        while(i<word.length() )
        {
            if(this.isCharPresent(word.charAt(i)))
            {
                cur = cur.node.get(word.charAt(i));                
                if(cur.last)
                    cur.last = false;
            }
            else
            {                
                 cur.node.put(word.charAt(i), getNode());
                 cur.last = false;
                 cur = cur.node.get(word.charAt(i));
            }          
            
            i++;
        }
        cur.last = true;
        return root;
    }
    
    private void pushTrieChild(Stack st,TrieNode tn)
    {
        Set s = tn.node.keySet();
        Iterator it = s.iterator();
        while(it.hasNext())
        {
            st.push(tn.node.get(it.next()));
        }
    }
    
    public boolean searchTrie(TrieNode root,String searchString)
    {
        boolean stringFound = true;
        if(root == null || searchString == null ) return false;
        
        int i = 0;
        TrieNode cur = root;
        
        while(i<searchString.length())
        {
            if(cur.isCharPresent(searchString.charAt(i)))
                cur = cur.node.get(searchString.charAt(i));
            else
            {
                stringFound = false;
                break;
            }
            i++;
        }
        
        return stringFound;
    }
    public void printTrie(TrieNode rootNode)
    {
        Stack <TrieNode> st = new Stack<TrieNode>();
        if(rootNode == null)
        {
            System.out.print("Empty Trie");
            return;
        }
        
        pushTrieChild(st,rootNode);
        
        while(!st.empty())
        {
            TrieNode temp = st.pop();
            System.out.print("");
        }
        
    }
    
    
}



public class Trie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TrieNode rootNode = TrieNode.getRoot();
        String words[] = {"an","a","and","ant","anne","arthur"};
        for(String s : words)
        {
            rootNode.addWord(s);
        }
        
        
        if(rootNode.searchTrie(rootNode, "arthur"))
            System.out.print("String Found");
        else
            System.out.print("String Not Found");
    }
    
}
