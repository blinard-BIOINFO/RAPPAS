/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author ben
 */
public class PhyloNode extends DefaultMutableTreeNode implements Serializable {

    private static final long serialVersionUID = 2010L;

    //node  metadata
    /////////////////////////////////////////
    
    //nodeId internal to our program (used by algorithms)
    private int id=-1; 
    //label internal to our program, used when outputing trees (used by algorithms)
    private String label=null;
    
    //actual branch length in the current tree
    private float branchLengthToAncestor=-1.0f;
    //below are branch length related to the original tree (before addition of fake nodes)
    //note that these values should be set to something else than 0 only 
    //when the current PhyloNode represents a fake node (X0,X1,X2,X3).
    //let's consider this:
    //                        /-----X2
    //                       /
    //                   /--X1------X3
    //                  /
    //  OriginalNode1--X0-----OriginalNode2
    //
    //  for X2: branchLengthToOriginalAncestor= bl(X2;X1) + bl(X1;X0) + bl(X0;OriginalNode1)
    //          branchLengthToOriginalSon= bl(X2;X1) + bl(X1;X0) + bl(X0;OriginalNode2)
    
    private float branchLengthToOriginalAncestor=-1.0f;
    private float branchLengthToOriginalSon=-1.0f;

    NumberFormat nf=NumberFormat.getNumberInstance();
    
    /**
     * empty constructor for situation where node can be filled with metadata only later
     */
    public PhyloNode() {
        nf.setMinimumFractionDigits(3);
        nf.setMaximumFractionDigits(6);
    }
    
    /**
     * empty constructor for situation where node can be filled with metadata only later
     * @param id 
     */
    public PhyloNode(int id) {
        nf.setMinimumFractionDigits(3);
        nf.setMaximumFractionDigits(6);
        this.id=id;
    }
    
    public PhyloNode(int id, String label, float branchLengthToAncestor) {
        nf.setMinimumFractionDigits(3);
        nf.setMaximumFractionDigits(6);
        this.branchLengthToAncestor=branchLengthToAncestor;
        this.id=id;
        this.label=label;
    }
    
    /**
     * this constructor should be used only for the copy() method
     * @param id
     * @param externalId
     * @param label
     * @param branchLengthToAncestor
     * @param branchLengthToOriginalAncestor
     * @param branchLengthToOriginalSon 
     */
    private PhyloNode(  int id,
                        String label,
                        float branchLengthToAncestor,
                        float branchLengthToOriginalAncestor,
                        float branchLengthToOriginalSon,
                        List<PhyloNode> children) {
        nf.setMinimumFractionDigits(3);
        nf.setMaximumFractionDigits(6);
        this.branchLengthToAncestor=branchLengthToAncestor;
        this.id=id;
        this.branchLengthToOriginalAncestor=branchLengthToOriginalAncestor;
        this.branchLengthToOriginalSon=branchLengthToOriginalSon;
        this.label=label;
        children.forEach(c->{this.add(c);});
    }
    
    /**
     * produces a deep copy of this node and its subtree; no references are kept,
     * all nodes and associated atomic values are new instances.
     * @return 
     */
    public PhyloNode copy() {
        Enumeration childrenEnum = this.children();
        ArrayList<PhyloNode> listSons=new ArrayList();
        while (childrenEnum.hasMoreElements()) {
            PhyloNode n = (PhyloNode)childrenEnum.nextElement();
            PhyloNode newNode=n.copy();
            listSons.add(newNode);

        }
        return new PhyloNode(   id,
                                label,
                                branchLengthToAncestor,
                                branchLengthToOriginalAncestor,
                                branchLengthToOriginalSon,
                                listSons
                            );
    }

    public int getId() {
        return id;
    }
    
    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getBranchLengthToAncestor() {
        return branchLengthToAncestor;
    }

    public void setBranchLengthToAncestor(float branchLengthToAncestor) {
        this.branchLengthToAncestor = branchLengthToAncestor;
    }

    public void setBranchLengthToOriginalAncestor(float branchLengthToOriginalAncestor) {
        this.branchLengthToOriginalAncestor = branchLengthToOriginalAncestor;
    }

    public float getBranchLengthToOriginalAncestor() {
        return branchLengthToOriginalAncestor;
    }

    public void setBranchLengthToOriginalSon(float branchLengthToOriginalSon) {
        this.branchLengthToOriginalSon = branchLengthToOriginalSon;
    }

    public float getBranchLengthToOriginalSon() {
        return branchLengthToOriginalSon;
    }
    

    @Override
    public String toString() {
        //return this.hashCode()+" id:"+id+" label:"+label+" bl:"+nf.format(branchLengthToAncestor);
        //System.out.println("id:"+id+" extId:"+externalId+" label:"+label+" bl:"+nf.format(branchLengthToAncestor));
        StringBuilder sb=new StringBuilder();

        sb.append("["+id);
        sb.append("]");
        sb.append(label+":"+nf.format(branchLengthToAncestor));
        if (branchLengthToOriginalAncestor>-1.0f)
            sb.append(":bla:"+nf.format(branchLengthToOriginalAncestor));
        if (branchLengthToOriginalSon>-1.0f)
            sb.append(":bls:"+nf.format(branchLengthToOriginalSon));                    
        return sb.toString();
    }




    @Override
    public PhyloNode getChildAt(int index) {
        return (PhyloNode)super.getChildAt(index); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
