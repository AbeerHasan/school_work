/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_termproject;
////////////////////imports////////////////////////////////////
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
///////////////////////////////////////////////////////////////
/**
 *
 * @author DELL
 */
public class tree {
    ArrayList <Integer> node=new ArrayList <Integer>();
    ArrayList <Integer> track=new ArrayList <Integer>();
    private int m=11,rno=10,min=1;
    //-----------------------layla-------------------------
    private int Mn,Mnum=0,RecordsNumber=0;
   //-----------------------------------------------------------------------------------------------------//
    //__________________________________intialization ____________________________________\\
    public void CreateIndexFileFile (String filename, int numberOfRecords, int M) throws FileNotFoundException, IOException{
        m=M*2+1;
        min=(M/2)-1;
        RecordsNumber=rno=numberOfRecords;
        Mnum=m;
        Mn=M;
        
        RandomAccessFile file=new RandomAccessFile(filename+".bin", "rw");
        int f=0;
        System.out.println("*");
        ArrayList<Integer> n=new ArrayList<Integer>(); 
        while (f<rno){
            for (int i=1;i<m;i++){
            n.add(-1);
                if(n.size()==2){
                    n.add(f+1);
                }
            }
            writeopject(file,n,f);
            n.clear();
            f++;
        }
        n=readopject(file,m,0);
        n.set(0,m);
        writeopject(file,n,0);
    }
    public void RefrishTreeInfo(String filename) throws FileNotFoundException, IOException{
        RandomAccessFile file=new RandomAccessFile(filename+".bin", "rw");
        file.seek(0);
        m=file.readInt();
        System.out.print(" M : "+m);
        Mnum=m;
        Mn=(m-1)/2;
        min=(Mn/2)-1;
        RecordsNumber=rno=((int)file.length()/4)/m;
    }
    //______________________________read & write & diplay ________________________________\\
    public ArrayList <Integer> readopject(RandomAccessFile file ,int nodesize,int nodeindex) throws IOException{
        int i=0;
        ArrayList <Integer> Node=new ArrayList <Integer>();
        file.seek(0);
        if(nodeindex<0){
            nodeindex=nodeindex*-1;
        }
        file.seek(nodeindex*4*nodesize);
        while(i<nodesize){
            Node.add(file.readInt());
            i++;
        }
        file.seek(0);
        return Node;

    }
    public void writeopject(RandomAccessFile file ,ArrayList <Integer> Node,int nodeindex) throws IOException{
        int i=0;
        file.seek(nodeindex*4*m);
        while(i<Node.size()){
            file.writeInt(Node.get(i));
            i++;
        }  
    } 
    public void DisplayIndexFileContent (String filename) throws FileNotFoundException, IOException{
        RandomAccessFile file=new RandomAccessFile(filename+".bin", "rw");
        ArrayList<Integer> n= new ArrayList<Integer>();
        for (int i=0;i<rno;i++){
            n=readopject(file,m,i);
            System.out.print(n+" "); 
            System.out.println(" ");
        }
    }
    //____________________________________ search ________________________________________\\        
    public int SearchARecord (String filename, int RecordID)throws FileNotFoundException, IOException{
        
        //------------- Inetialization ------------------------------------------
        RandomAccessFile file=new RandomAccessFile(filename+".bin", "rw");
        node=readopject(file,m,1);
        track.clear();
        boolean found=false;
        int key=RecordID,ckey=0,nkey=0,reference=-1;
        // to help in tracking the node indexs
        int nodecounter=0,keycounter=1;
        //------------------------------------------------------------------------
        while(found==false){
            //------------- if the current node is a leaf node -------------------
           keycounter=1;
            if(node.get(0)==0){
                int x=1;
                while(x<m){ 
                    ckey=node.get(x);
                    if(key==ckey){
                        reference=node.get(x+1);
                        if(nodecounter==0){
                            track.add(1);
                        }
                        track.add(keycounter);
                        found=true;
                        break;
                    }else {
                        x+=2;
                    }
                    keycounter+=1;
                }
               nodecounter+=1; 
            }else{
            //----------- if not a leaf node ------------------------------------
                keycounter=1;
                int x=1;
                boolean b=false;
                while(x<m){ 
                    ckey=node.get(x);
                    if(key==ckey){
                        file.seek(0);
                        if(nodecounter==0){
                           track.add(1);
                        }
                        track.add(keycounter);
                        track.add(node.get(x+1));
                        node=readopject(file,m,node.get(x+1));
                        b=true;
                        break;
                    }else {
                        x+=2;
                    }
                    keycounter+=1;
                }
            //---------- if no key equals the given key search for bigger key------------  
            keycounter=1;
            if(b==false){
                int y=1;
                while(y<m){ 
                    ckey=node.get(y);
                    if(key<ckey){
                        file.seek(0);
                        if(nodecounter==0){
                            track.add(-1);
                        }
                        track.add(keycounter);
                        ArrayList<Integer> n=new ArrayList<Integer>();
                        n=readopject(file,m,node.get(y+1));
                        //_______________ckeck if the next node has key==wanted key _____\\
                        boolean p=false;
                        for(int i=1;i<m;i+=2){
                            ckey=n.get(i);
                            if(key==ckey){
                                p=true;
                                break;
                            }
                        }
                        if(p==true){
                            track.add(node.get(y+1));
                        }else {
                            track.add(-node.get(y+1));
                        }
                        node=n;
                        //_______________________________________________________________\\
                        break;
                    }else {
                        y+=2;
                    }
                    keycounter+=1;
                }   
            }
                nodecounter+=1;
            }
        }        return reference;
    }        
    public void getTrack(){
        for(int i=0;i<track.size();i+=2){
        System.out.print(" node :"+track.get(i));
        System.out.print(" key no :"+track.get(i+1));
        }
    }
    //_________________________________needed functions___________________________________\\
    public int GetNofKeys(ArrayList<Integer> Node){
        int counter=0;
        for(int i=1;i<m;i+=2){
            if(Node.get(i)!=-1){
                counter+=1;
            }
        }
        return counter;
    }
    public int GetMaxKey(ArrayList<Integer> Node){
        int max=-1;
        for(int i=1;i<m;i+=2){
            if(max<Node.get(i)){
                max=Node.get(i);
            }
        }
        return max;
    }
    public int CheckLeftsipling(RandomAccessFile file,ArrayList<Integer> Track,int M) throws IOException{
        int s=Track.size(),parent=0,position=0,nofkeys=0;       
        ArrayList <Integer> Node=new ArrayList <Integer>();
        //----------- get parent node if exsists--------------
        if(s>2){
            parent=Track.get(s-4);
            // size - keyP in it's node - it's node - keyP in parent node - parent node =parent node position 
            if(parent<0){
                parent=parent*-1;
            }
        }
        node=readopject(file,M,parent);
        //---------- get key pointer position in the parent node --------
        position=Track.get(s-3);
        if(position<0){
            position=position*-1;
        }
        //---------- get the left node--------------------------
        if(position!=1){
            Node=readopject(file,M,node.get((position*2)-2));
            return GetNofKeys(Node);
        }
        return -1;
    }
    public int Checkrightsipling(RandomAccessFile file,ArrayList<Integer> Track,int M) throws IOException{
        int s=Track.size(),parent=0,position=0,nofkeys=0;       
        ArrayList <Integer> Node=new ArrayList <Integer>();
        //----------- get parent node if exsists--------------
        if(s>2){
            parent=Track.get(s-4);
            // size - keyP in it's node - it's node - keyP in parent node - parent node =parent node position 
            if(parent<0){
                parent=parent*-1;
            }
        }
        
        System.out.println("************ Track :"+Track+" parent pos : "+parent);
        node.clear();
        node=readopject(file,M,parent);
        //---------- get key pointer position in the parent node --------
        position=Track.get(s-3);
        //---------- get the right node--------------------------
        if(position*2+1<M||position<GetNofKeys(node)){
            System.out.println("parent node  "+node+"key no in parent  : "+position);
            Node=readopject(file,M,node.get((position*2)+2));
            System.out.println("right sipling of te parent "+Node);
            return GetNofKeys(Node);
            
        }
        return -1;
    }
    public ArrayList<Integer> Marge(ArrayList<Integer> keys,ArrayList<Integer> Node,char s){
        ArrayList<Integer> n=new ArrayList<Integer>();
            
        if(s=='l'){
            int nk=GetNofKeys(Node);
            nk=(nk*2)+1;
            int i=0;
            while(i<keys.size()){
            Node.set(nk,keys.get(i));
            i+=1;
            nk+=1;
            }
        }else{
            int i=keys.size(),j=0;
            n.add(Node.get(0));
            while(j<i){
            n.add(keys.get(j));
            j+=1;
            }
            j=1;
            while(i<Node.size()-2){
                n.add(Node.get(j));
                i+=1;
                j+=1;
            }
        }
        if(n.size()>0){
        Node=n;
        }
        return Node;
    }
    
    public void Redistribute(RandomAccessFile file,ArrayList<Integer> Track,int key,int M) throws FileNotFoundException, IOException{
        int i=4,s=Track.size(),npos=0,kpos=0;
        ArrayList <Integer> Node=new ArrayList <Integer>();
        if((s-i)+2>0){
            while(i<=s){
            //------- get parent node -------------
                npos=Track.get(s-i);
                if(npos>0){
                    Node=readopject(file,m,npos);
                    kpos=Track.get((s-i)+1);
                    Node.set(kpos*2-1,key);
                    writeopject(file,Node,npos);
                    i+=2;
                }else {
                    break;
                }
            }
        }
    }
    public ArrayList <Integer> GetTreeLevels(){
        ArrayList <Integer> l=new ArrayList <Integer>();
        
        return l;
    }
    //_________________________________ Deletion  ________________________________________\\
    public ArrayList<Integer> Delete(ArrayList<Integer> Node,int pos){
        ArrayList<Integer> Node2=new ArrayList<Integer>();
        int i=0;
        while(i<=pos-2){
            Node2.add(Node.get(i));
            i+=1;
        }
        i=pos+1;
        while(i<m){
            Node2.add(Node.get(i));
            i+=1;
        }
        Node2.add(-1);
        Node2.add(-1);
        return Node2;
    }
    public void DeleteRecordFromIndex (String filename, int RecordID)throws FileNotFoundException, IOException {
        //----------------inetialization---------------------\\
        RefrishTreeInfo(filename);
        int keyp=0,pnp=0,np=0;
        ArrayList<Integer> Node=new ArrayList<Integer>();
        ArrayList<Integer> Node2=new ArrayList<Integer>();
        ArrayList <Integer> Node3=new ArrayList <Integer>();
        ArrayList<Integer> Node4=new ArrayList<Integer>();
        RandomAccessFile file=new RandomAccessFile(filename+".bin", "rw");
        System.out.println("reference :"+np);
        np=SearchARecord(filename,RecordID);
        System.out.println("reference :"+np);
        //----------------start delete condetions-----------------------\\
        if(np==-1){
            System.out.println("No such a record");
        }else {
            Node=readopject(file,m,track.get(track.size()-2));
             int s=track.size(),parent=0,position=0,nofkeys=0,ckey=0;       
                   //----------- get parent node if exsists--------------
                   if(s>2){
                        parent=track.get(s-4);
                        // size - keyP in it's node - it's node - keyP in parent node - parent node =parent node position 
                        if(parent<0){
                            parent=parent*-1;
                        }
                    }
                    Node4=readopject(file,m,parent);
                    System.out.println("Parent Node : "+Node4);
                  
            //______________condetion 1____________\\
            if(GetNofKeys(Node)-1>min&&GetMaxKey(Node)!=RecordID){
                System.out.println("condetion 1 ");
                int pos=track.get(track.size()-1)*2;
                if(pos<0){
                    pos=pos*-1;
                }
                Node2=Delete(Node,pos);
                Node.clear();
                pos=track.get(track.size()-2);
                if(pos<0){
                    pos=pos*-1;
                }
                writeopject(file,Node2,pos);
            //______________condetion 2____________\\
            }else if(GetNofKeys(Node)-1>min&&GetMaxKey(Node)==RecordID){
                System.out.println("condetion 2 ");
                int pos=track.get(track.size()-1)*2;
                if(pos<0){
                    pos=pos*-1;
                }
                Node2=Delete(Node,pos);
                Node.clear();
                pos=track.get(track.size()-2);
                if(pos<0){
                    pos=pos*-1;
                }
                
                writeopject(file,Node2,pos);
                if(track.size()>=4){ //----- if the the key (not in the root and the root is a leaf node)----\\ 
                int max=GetMaxKey(Node2);
                Redistribute(file,track,max,m);
                }
            //______________condetion 3____________\\    
            }else if(GetNofKeys(Node)-1==min){
                System.out.println("condetion 3* ");
               //______________condetion 3.1____________\\
                if(CheckLeftsipling(file,track,m)-1>min){
                   System.out.println("condetion 3.1 ");
                   //---------- get key pointer position in the parent node --------
                   position=track.get(s-3);
                   if(position<0){
                        position=position*-1;
                    }
                    System.out.println("key pointer position in the parent node : "+position);
                    //---------- get the left node--------------------------
                    Node3=readopject(file,m,Node4.get((position*2)-2));
                    System.out.println("th left node position : "+((position*2)-2)+"the left node "+Node3);
                    int r=0,k=0;
                    r=Node3.get(GetNofKeys(Node3)*2);
                    k=Node3.get((GetNofKeys(Node3)*2)-1);
                    System.out.println("k : "+k+" r : "+r);
                    //------- deletion and redestipute the left node ---------
                    ArrayList<Integer> Track=new ArrayList<Integer>();
                    getTrack();
                    for(int i=0;i<track.size();i++){
                    Track.add(track.get(i));
                    }
                    System.out.println("Track : "+Track);
                    int oldmax=GetMaxKey(Node3);
                    SearchARecord(filename,oldmax);
                    System.out.println("Track 2: "+Track);
                    System.out.println(" ");
                    getTrack();
                    Node3=Delete(Node3,GetNofKeys(Node3)*2);
                    
                    System.out.println("left node after deletion : "+Node3+" its posistion "+((position*2)-2));
                    writeopject(file,Node3,Node4.get((position*2)-2));
                    
                    int newmax=GetMaxKey(Node3);
                    Redistribute(file,track,newmax,m);
                    //----------------
                    int pos=Track.get(Track.size()-1)*2;
                    Node2=Delete(Node,pos);
                    ArrayList<Integer> keys=new ArrayList<Integer>();
                    keys.add(k);
                    keys.add(r);
                    System.out.println("the wanted node after deletion : "+Node2);
                    Node2=Marge(keys,Node2,'r');
                    System.out.println("the wanted node after marge : "+Node2);
                    writeopject(file,Node2,Track.get(Track.size()-2));
                    System.out.println("wanted node pos "+Track.get(Track.size()-2)+" ");
                    //DisplayIndexFileContent("Btree");
                    int max=GetMaxKey(Node2);
                    Redistribute(file,Track,max,m);
                    //----------------
                    
                }else if(Checkrightsipling(file,track,m)-1>min) {
                   System.out.println("condetion 3.2 ");
                   Node2.clear();
                   Node3.clear();
                   //Node4.clear();
                   //---------- get key pointer position in the parent node --------
                   position=track.get(s-3);
                   if(position<0){
                        position=position*-1;
                    }
                    System.out.println("key pointer position in the parent node : "+position+" parent node : "+Node4);
                    //---------- get the right node--------------------------
                    Node3=readopject(file,m,Node4.get((position*2)+2));
                    System.out.println("th right node position : "+((position*2)+2)+"the right node "+Node3);
                    int r=0,k=0;
                    r=Node3.get(2);
                    k=Node3.get(1);
                    System.out.println("k : "+k+" r : "+r);
                    Node2.clear();
                    //----------------
                    Node3=Delete(Node3,2);
                    System.out.println("right node after deletion : "+Node3+" its posistion "+((position*2)+2));
                    writeopject(file,Node3,Node4.get((position*2)+2));
                    //-------if the deleted key from the wanted node was the max---------
                    int pos=track.get(track.size()-1)*2;
                    Node2=Delete(Node,pos);
                    ArrayList<Integer> keys=new ArrayList<Integer>();
                    keys.add(k);
                    keys.add(r);
                    Node2=Marge(keys,Node2,'l');
                    System.out.println("the wanted node after marge : "+Node2);
                    writeopject(file,Node2,track.get(track.size()-2));
                    int max=GetMaxKey(Node2);
                    Redistribute(file,track,max,m);
                    //----------------
                    System.out.println("Node 3 : "+Node3);
                    max=GetMaxKey(Node3);
                    ArrayList<Integer> Track=new ArrayList<Integer>();
                    Track=track;
                    getTrack();
                    SearchARecord(filename,max);
                    Redistribute(file,track,max,m);
                    getTrack();
                    track.clear();
                    track=Track;
                    Track.clear();
                }
            
            //______________condetion 4____________\\
            else if(CheckLeftsipling(file,track,m)-1<=min
                    &&Checkrightsipling(file,track,m)-1<=min&&GetNofKeys(Node4)-1>min){
                System.out.println("condetion 4 "+GetNofKeys(Node4));
                //---------- get key pointer position in the parent node --------
                position=track.get(s-3);
                if(position<0){
                    position=position*-1;
                }
                System.out.println("key pointer position in the parent node "+position);
                Node=Delete(Node,track.get(s-1)*2);
                System.out.println("wanted node after deletion : "+Node);
                int i=1;
                //---------- get the remaining key/s to move it to the siplings------------\\
                while(Node.get(i)!=-1){  
                    Node2.add(Node.get(i));  
                    i+=1;
                }
                System.out.println("remaining keys in the wanted node : "+Node2);
                //---------- get the left node--------------------------
                if(position>1){
                    Node3=readopject(file,m,Node4.get((position*2)-2));
                    int oldmax=GetMaxKey(Node3);
                    System.out.println("th left node position : "+((position*2)-2)+"the left node "+Node3);
                    Node3=Marge(Node2,Node3,'l');
                    writeopject(file,Node3,Node4.get((position*2)-2));
                    //--------- delete the hole wanted node from the tree----------\\
                    Node.clear();
                    for(int j=0;j<m;j++){
                        Node.add(-1);
                    }
                    writeopject(file,Node,track.get(track.size()-2));
                    //-------- delete the key and the pointer which points to it in the parent node-----\\
                    Node4=Delete(Node4,position*2);
                    writeopject(file,Node4,parent);
                    int newmax=GetMaxKey(Node3);//------ redestipute the max of the left sipling
                    ArrayList<Integer> Track=new ArrayList<Integer>();
                    Track=track;
                    getTrack();
                    SearchARecord(filename,oldmax);
                    Redistribute(file,track,newmax,m);
                    getTrack();
                    track.clear();
                    track=Track;
                    Track.clear();
                }else {
                    //------------ get the right node-------------------\\
                    Node3=readopject(file,m,Node4.get((position*2+2)));
                    System.out.println("th right node position : "+((position*2+2))+"the right node "+Node3);
                    Node3=Marge(Node2,Node3,'r');
                    writeopject(file,Node3,Node4.get((position*2)+2));
                    //--------- delete the hole wanted node from the tree----------\\
                    Node.clear();
                    for(int j=0;j<m;j++){
                        Node.add(-1);
                    }
                    writeopject(file,Node,track.get(track.size()-2));
                     //-------- delete the key and the pointer which points to it in the parent node-----\\
                    Node4=Delete(Node4,position*2);
                    writeopject(file,Node4,track.get(track.size()-4));
                }
                //-------------------- condetion 5 ----------------------------\\
            }else if(CheckLeftsipling(file,track,m)-1<=min
                    &&Checkrightsipling(file,track,m)-1<=min&&GetNofKeys(Node4)-1<=min){
                //---------- get key pointer position in the parent node --------
                System.out.println("condetion 5 ");
                position=track.get(s-3);
                if(position<0){
                    position=position*-1;
                }
                Node=Delete(Node,track.get(s-1)*2);
                int i=1;
                //---------- get the remaining key/s to move it to the siplings------------\\
                while(Node.get(i)!=-1){  
                    Node2.add(Node.get(i));  
                    i+=1;
                }
                boolean rs=false,ls=false;
                //---------- get the left node--------------------------
                if(position>1){
                    ls=true;
                    System.out.println(" l- &&&&");
                    Node3=readopject(file,m,Node4.get((position*2)-2));
                    int oldmax=GetMaxKey(Node3);
                    Node3=Marge(Node2,Node3,'l');
                    writeopject(file,Node3,Node4.get((position*2)-2));
                    //--------- delete the hole wanted node from the tree----------\\
                    Node.clear();
                    for(int j=0;j<m;j++){
                        Node.add(-1);
                    }
                    writeopject(file,Node,track.get(track.size()-2));
                    //-------- delete the key and the pointer which points to it in the parent node-----\\
                    Node4=Delete(Node4,position*2);
                    writeopject(file,Node4,parent);
                    int newmax=GetMaxKey(Node3);//------ redestipute the max of the left sipling
                    ArrayList<Integer> Track=new ArrayList<Integer>();
                    Track=track;
                    getTrack();
                    SearchARecord(filename,oldmax);
                    Redistribute(file,track,newmax,m);
                    getTrack();
                    track.clear();
                    track=Track;
                    Track.clear();
                }else {
                    rs=true;
                    System.out.println(" r- &&&&");
                    //------------ get the right node-------------------\\
                    Node3=readopject(file,m,Node4.get((position*2+2)));
                    Node3=Marge(Node2,Node3,'r');
                    writeopject(file,Node3,Node4.get((position*2)+2));
                    //--------- delete the hole wanted node from the tree----------\\
                    Node.clear();
                    for(int j=0;j<m;j++){
                        Node.add(-1);
                    }
                    writeopject(file,Node,track.get(track.size()-2));
                     //-------- delete the key and the pointer which points to it in the parent node-----\\
                    Node4=Delete(Node4,position*2);
                    writeopject(file,Node4,parent);
                }
                 //_____________________________condetion 5.1__if the siplings has nofkeys-1>min _____________________________\\
                //----------------- read the left or the rigt sipling of the parent and check if thay dont has the min no of keys -------
                Node.clear();
                Node2.clear();//the sipling of the parent 
                Node3.clear();// parent of the parent 
                if(track.size()>4){ //-check if the parent has a perant----
                    int pofp_pos=track.get(track.size()-6);
                    int p_pos_inp=track.get(track.size()-5);
                    getTrack();
                    Node3=readopject(file,m,pofp_pos);
                    ArrayList<Integer> ParentNewTrack=new ArrayList<Integer>();
                    for(int j=0;j<track.size()-2;j++){
                    ParentNewTrack.add(track.get(j));
                    }
                    //DisplayIndexFileContent("Btree");
                   // System.out.println("---------------------------------------------------");
                    if(CheckLeftsipling(file,ParentNewTrack,m)-1>min){
                        System.out.println("condetion ** 5 ");
                        Node2=readopject(file,m,Node3.get(p_pos_inp*2-2));
                        ArrayList<Integer> keys=new ArrayList<Integer>();
                        keys.add(Node2.get((GetNofKeys(Node2)*2)-1));
                        keys.add(Node2.get((GetNofKeys(Node2)*2)));
                        
                       //---------------- the left sipling last key deletion and redetripution of the max no---\\
                        int oldmax=GetMaxKey(Node2);
                        Node2=Delete(Node2,(GetNofKeys(Node2)*2));
                        writeopject(file,Node2,Node3.get(p_pos_inp*2-2));
                        int newmax=GetMaxKey(Node2);
                        SearchARecord(filename,oldmax);
                        Redistribute(file,track,newmax,m);
                        //---------------- marge the taken key from the sipling to the wanted node-------------
                        Node4=Marge(keys,Node4,'r');
                        writeopject(file,Node4,parent);
                    /////////////////////////////////////////////////////////////////////////////////////////////    
                    }else if(Checkrightsipling(file,ParentNewTrack,m)-1>min){
                        System.out.println("condetion ***5 ");
                        Node2=readopject(file,m,Node3.get(p_pos_inp*2+2));
                        ArrayList<Integer> keys=new ArrayList<Integer>();
                        keys.add(Node2.get(1));
                        keys.add(Node2.get(2));
                       //---------------- the right sipling firs key deletion ---\\
                        Node2=Delete(Node2,2);
                        writeopject(file,Node2,Node3.get(p_pos_inp*2+2));
                       //---------------- marge the taken key from the sipling to the wanted node and redestripute -------------
                        int oldmax=GetMaxKey(Node4); 
                        SearchARecord(filename,oldmax);
                        ArrayList<Integer> NewTrack=new ArrayList<Integer>();
                        NewTrack=track;
                        NewTrack.remove(NewTrack.size()-1);
                        NewTrack.remove(NewTrack.size()-2);
                        Node4=Marge(keys,Node4,'l');
                        int newmax=GetMaxKey(Node4);
                        Redistribute(file,NewTrack,newmax,m);
                        writeopject(file,Node4,parent);
                    //////////////////////////////////////////////////////////////////////////////////////////////////    
                    }else if(Checkrightsipling(file,ParentNewTrack,m)-1<=min&&CheckLeftsipling(file,ParentNewTrack,m)-1<=min){
                        System.out.println("ana t3bt ");
                    }else{
                        System.out.println("condetion 5.2.1 ");
                        System.out.println("Node3 : p of p : "+Node3);
                        Node4=readopject(file,m,parent);
                        System.out.println("Node4 : parent : "+Node4);
                        boolean l=false,r=false;
                        int sipling_pos=0;
                        if(p_pos_inp>1){
                            l=true;
                            sipling_pos=p_pos_inp*2-2;
                            Node2=readopject(file,m,Node3.get(sipling_pos));
                        }else {
                            r=true;
                            sipling_pos=p_pos_inp*2+2;
                            Node2=readopject(file,m,Node3.get(sipling_pos));
                        }
                        if(m-(GetNofKeys(Node3)-1)>=GetNofKeys(Node2)||m-(GetNofKeys(Node3)-1)>=GetNofKeys(Node2)){
                        
                        System.out.println("condetion 5.2.2 ");
                            Node3=Delete(Node3,sipling_pos);
                            ArrayList<Integer> keys=new ArrayList<Integer>();
                            int x=1;
                            while(Node2.get(x)!=-1){
                                keys.add(Node2.get(x));
                                x+=1;
                            }
                            if(l==true){
                                Node3=Marge(keys,Node3,'r');
                            }else {
                                Node3=Marge(keys,Node3,'l');
                            }
                            writeopject(file,Node3,pofp_pos);
                            Node2.clear();
                            x=0;
                            while(x<m){
                                Node2.add(-1);
                                x+=1;
                            }
                            writeopject(file,Node2,Node3.get(sipling_pos));
                            ////////////////////////////////////////////////////////////////////////
                           // readopject(file,m,Node4.get((position*2)-2))
                           Node2.clear();
                           ArrayList<Integer> keys2=new ArrayList<Integer>();
                            
                           if(ls==true){ 
                                Node2=readopject(file,m,Node4.get((position*2)-2));
                                int y=0;
                                while(Node2.get(y)!=-1){
                                    keys2.add(Node2.get(y));
                                    y+=1;
                                }
                                Node2.clear();
                                y=0;
                                while(y<m){
                                    Node2.add(-1);
                                    y+=1;
                                }
                                writeopject(file,Node2,Node4.get((position*2)-2));
                                Node4=Delete(Node4,(position*2)-2);
                            }else if(rs==true){
                                Node2=readopject(file,m,Node4.get((position*2)+2));
                                int y=0;
                                while(Node2.get(y)!=-1){
                                    keys2.add(Node2.get(y));
                                    y+=1;
                                }
                                Node2.clear();
                                y=0;
                                while(y<m){
                                    Node2.add(-1);
                                    y+=1;
                                }
                                writeopject(file,Node2,Node4.get((position*2)+2));
                                Node4=Delete(Node4,(position*2)+2);
                            }
                            int k=0;
                            while(k<keys.size()){
                                Node4.set(k,keys.get(k));
                                k+=1; 
                            }
                            writeopject(file,Node4,parent);
                        }    
                       }
                    }
                }
            }
        }        
    }
            
  
}
