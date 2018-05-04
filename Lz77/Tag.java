
public class Tag {
	  /* 
     * length()
     *charAt(index)
     *indexOf('6arf')//bydawar 3el7arf mn 2awel elkelma
     *lastIndexOf('7arf'))//bydawar 3el 7arf mn 2a5er el kelma 
     *contains('7arf' or "kelma")//momken lw da5alt goz2 bs mn elkelma hy2olle true
     *substring(start ind1,end ind2)bt3red el7etta elle ben el inds elle ana da5altohom 
     hya5od 2wel ind1 w msh hya5od ind2  
     */
 int pointer,length; 
 char nextchar;

//char
//String 
public Tag (){
	 pointer =length=0;
	 nextchar='0';
}
public Tag(int p,int l,char n){
	 pointer =p;
	 length =l;
	 nextchar=n;
}	
}
