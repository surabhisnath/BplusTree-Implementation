import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class R
{
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) 
    {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }
    
    /** get next word */
    static String next() throws IOException 
    {
        while ( ! tokenizer.hasMoreTokens() ) 
        {
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException 
    {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException 
    {
        return Double.parseDouble( next() );
    }
}



class findret
{
	treenode r;
	int index;
	
	public findret(treenode t, int i)
	{
		r = t;
		index = i;
	}
}


class Btree implements Serializable
{
	int num_nodes = 0;
	ArrayList<record> db;
	ArrayList<record> shallowdb;
	int N = 3;

	ArrayList<record> allrec;	
	treenode root;
	String field;
	
	
	public static void serialize(List<record> p) throws IOException 
	{
		ObjectOutputStream out = null;
		try 
		{
			out = new ObjectOutputStream (new FileOutputStream("Records.txt"));
			out.writeObject(p);
		}
		
		finally 
		{
			out.close();
		}
	}
	
	
	public static List<record> deserialize() throws IOException, ClassNotFoundException 
	{
		ObjectInputStream in = null;
		try 
		{
			in = new ObjectInputStream (new FileInputStream("Records.txt"));
			@SuppressWarnings("unchecked")
			List<record> p = (List<record>) in.readObject();
			return p;
		} 
		
		finally 
		{
			in.close();
		}
	}
	
	
	
	public void printAll(String v)
	{
		findret obj = find(v);
		
		if(obj == null)
		{
			System.out.println("No records with this value");
			return;
		}
		
		treenode node = obj.r;
		int i = obj.index;	
		
		for(int k=0; k<node.pointers_r.get(i).size(); k++)
		{
			System.out.println(k+"kis");
			record rec = node.pointers_r.get(i).get(k);
			System.out.println(rec);
		}
	}
	
	
	public void FindRange(String a , String b)
	{
		treenode tem = new treenode();
		tem = findhelp(a);
		
		if(tem==null)
		{
			System.out.println("There are no records in the given range");
			return;
		}
		
		System.out.println("aaaaa"+tem.keys.get(0));
		
		
		int l;
		boolean fl;
		while(true)
		{
			fl=false;
			System.out.println("sizeis"+tem.keys.size());
			for(l = 0; l<tem.keys.size(); l++)
			{
				System.out.println("how same l"+l);
				System.out.println(tem.keys.get(l)+"l"+l);
				if(compare(tem.keys.get(l),a)>=0)
				{
					System.out.println("Im reaking");
					fl=true;
					break;
				}
			}
			
			if(fl==false)
				tem=tem.right_leaf;
			else
				break;
		}
		
		System.out.println("l is:"+l);
		System.out.println(tem.keys.get(l));
		
		if(tem.right_leaf == null && l==tem.keys.size()-1 && fl==false)
		{
			System.out.println("There are no records in the given range");
		}
		
		else
		{
			while(true)
			{
				boolean flag=false;
				for(int m = l; m<tem.keys.size(); m++)
				{
					System.out.println(tem.keys.get(m));
					System.out.println();
					System.out.println(tem.pointers_r.size());
					System.out.println(tem.pointers_r.get(m).size());
					for(int k=0; k<tem.pointers_r.get(m).size(); k++)
					{
						System.out.println("printing recccccc");
						record rec = tem.pointers_r.get(m).get(k);
						System.out.println(rec);
					}
					
					System.out.println("ME HEREEEEEEEEEE");
					System.out.println(tem.keys.get(m));
					System.out.println(b);
					System.out.println(compare(tem.keys.get(m),b));
					if(compare(tem.keys.get(m),b)>=0)
					{
						flag=true;
						break;
					}
				}
				
				if(flag==false)
				{
					System.out.println("yuhoo"+tem.keys.get(0));
					tem=tem.right_leaf;
					if(tem==null)
						break;
					l=0;
				}
					
				else
					break;
			} 
		}
	}
	
	//---------------------------------------------------------------------
	
	
	public treenode findhelp(String v)
	{
		treenode r = root;
		
		System.out.println("1");
		while(r.leaf == false)
		{
			System.out.println("2");
			int i = -1;
			int counter=0;
			for(int j=0; j<r.keys.size(); j++)
			{
				if(compare(r.keys.get(j), v) >= 0)
				{
					System.out.println("3");
					i = j;
					counter=1;
					break;
				}					
			}
			
			if(counter==0)
			{
//				int index = -1;
//				
//				for(int k=0; k<r.pointers_tn.size(); k++)
//				{
//					if(r.pointers_tn.get(k)!=null)
//					{
//						index = k;
//					}
//				}
				
				System.out.println("4");
				r = r.pointers_tn.get(r.pointers_tn.size()-1);
			}
			
			
			else if(v == r.keys.get(i))
			{
				System.out.println("5");
				r = r.pointers_tn.get(i+1);
			}
			
			else
			{
				System.out.println("6");
				r = r.pointers_tn.get(i);
			}
		}
		
		System.out.println("7");
		System.out.println(r.keys.get(0));
		return r;
	}
	
	//--------------------------------------------------------------------------

	public findret find(String v)
	{
		System.out.println("jj"+v);
		treenode r = root;
		System.out.println("a");
		while(r.leaf == false)
		{
			System.out.println("b");
			int i = -1;
			int counter=0;
			for(int j=0; j<r.keys.size(); j++)
			{
				if(compare(r.keys.get(j), v) > 0)
				{
					
					i = j;
					counter=1;
					break;
				}					
			}
			
			if(counter==0)
			{
				System.out.println("c");
				int index = -1;
				
				for(int k=0; k<r.pointers_tn.size(); k++)
				{
					if(r.pointers_tn.get(k)!=null)
					{
						index = k;
					}
				}
				
				r = r.pointers_tn.get(index);
			}
			
			
			else if(v == r.keys.get(i))
			{
				System.out.println("d");
				r = r.pointers_tn.get(i+1);
			}
			
			else
			{
				System.out.println("e");
				r = r.pointers_tn.get(i);
			}
		}
		
		
		for(int l = 0; l<r.keys.size(); l++)
		{
			System.out.println(r.keys.get(l));
			if(v.equals(r.keys.get(l)))
			{
				System.out.println(v);
				System.out.println(r.keys.get(l));
				return new findret(r, l);
			}
		}
		
		System.out.println("f");
		return null;
		
	}
	
	
	
	//------------------------------------------------------------------------------
	
	public void insert(record x) throws IOException 
	{
		String value;
		System.out.println(field);
		if(field.equals("Instructor ID"))
		{		
			System.out.println("abc");
			value = x.instructor_id;		
		}
			
		else if(field.equals("Instructor Name"))
		{
			value = x.instructor_name;
		}
			
		else if(field.equals("Department"))
		{
			value = x.department;
		}
			
		else
		{
			value = x.salary+"";
		}
			
			
		if(num_nodes == 0)
		{
			System.out.println("A");
			root = new treenode();
			root.leaf = true;
			root.keys.add(0,value);
			root.pointers_r.add(0,new ArrayList<record>());
			root.pointers_r.get(0).add(x);
			num_nodes++;
			return;
		}
				
		System.out.println("B");			
		findret obj = find(value);
				
		if(obj!=null)
		{
			System.out.println("C");
			///obj.r.pointers_r.add(obj.index,new ArrayList<record>());
			obj.r.pointers_r.get(obj.index).add(x);
		}
				
		else
		{
			System.out.println("D");
			treenode r = findhelp(value);
			
			System.out.println(r.keys.get(0));
			
			if(r.keys.size()<(N-1))
			{
				System.out.println("E");
				//insert in leaf
//				if(compare(r.keys.get(0), value)>0)
//				{
//					System.out.println("F");
//					r.keys.add(0, value);
//					r.pointers_r.add(0,new ArrayList<record>());
//					r.pointers_r.get(0).add(x);
//				}
						
				//else
				{
					System.out.println("G");
					int y;
					for(y=0; y<r.keys.size(); y++)
					{
						if(compare(r.keys.get(y),value)>=0)
						break;
					}
							
					r.keys.add(y, value);
					r.pointers_r.add(y,new ArrayList<record>());
					r.pointers_r.get(y).add(x);
				}
			}
			   
			
			
			else	//split it
			{
				System.out.println("H");
				treenode newnode = new treenode();   
				newnode.leaf = true;
				ArrayList<String> keylist = new ArrayList<String>(r.keys);
				ArrayList<ArrayList<record>> pointerlist = new ArrayList<ArrayList<record>>(r.pointers_r);
				
				treenode t = new treenode();
				t.keys = keylist;
				t.pointers_r = pointerlist;  
				
//				if(compare(t.keys.get(0), value)>0)  
//				{
//					System.out.println("I");
//					t.keys.add(0, value);
//					t.pointers_r.add(0,new ArrayList<record>());
//					t.pointers_r.get(0).add(x);
//				}
						
//				else
				{
					System.out.println("J");
					int y;
					System.out.println("t.keys.size"+t.keys.size());
					
					for(y=0; y<t.keys.size(); y++)
					{
						if(compare(t.keys.get(y),value)>=0)
						break;
					}
					
					System.out.println("yis"+y);
					t.keys.add(y, value);
					t.pointers_r.add(y,new ArrayList<record>());
					t.pointers_r.get(y).add(x);
				}
				
				System.out.println("K");
				newnode.right_leaf = r.right_leaf;   
				r.right_leaf = newnode;
				
				r.keys = null;
				r.keys = new ArrayList<String>();
				r.pointers_r = null;
				r.pointers_r = new ArrayList<ArrayList<record>>();
				
				System.out.println("r.keyssize"+r.keys.size()+"pointsize"+r.pointers_r.size());
				int h;
				//System.out.println("h"+h+"r.keyssize"+r.keys.size()+"pointsize"+r.pointers_r.size());
				
				for( h=0; h<(int)Math.ceil((float)N/2)-1; h++)       
				{
					r.keys.add(h, t.keys.get(h));
					r.pointers_r.add(h,new ArrayList<record>());
					r.pointers_r.set(h, t.pointers_r.get(h));
				}
				
				for(int m=(int)Math.ceil((float)N/2)-1; m<N; m++)     
				{
					newnode.keys.add(m-((int)Math.ceil((float)N/2)-1),t.keys.get(m));
					System.out.println("abcdefghijlkm"+newnode.keys.size());
					newnode.pointers_r.add(m-((int)Math.ceil((float)N/2)-1),new ArrayList<record>());
					newnode.pointers_r.set(m-((int)Math.ceil((float)N/2)-1),t.pointers_r.get(m));
					System.out.println("nopqrstuvwxyz"+newnode.pointers_r.size());
				}
				
				if(value.equals("5.0"))
				{
					System.out.println("mmmm");
					System.out.println(r.keys.get(0));
					System.out.println(newnode.keys.get(0));
					System.out.println(newnode.keys.get(1));
				}
		
				
				System.out.println("L");
				String k = newnode.keys.get(0);	
				
				System.out.println("r.key0"+r.keys.get(0));
				System.out.println("new.key0"+newnode.keys.get(0));
				
				if(r.parent!=null && r.parent.keys.size()>1)
				{
					System.out.println(r.parent.keys.get(0));
					System.out.println(r.parent.keys.get(1));
				}
				
				insert_in_parent(r,k,newnode);		
			}
		}
		
		if(allrec == null)
			allrec = new ArrayList<record>();
		allrec.add(x);
		serialize(allrec);
	}
	
	
	public void insert_in_parent(treenode a, String b, treenode c)
	{
		System.out.println("b is "+b);
		
		if(a.parent == null)
		{
			System.out.println("M");
			treenode r = new treenode();
			r.keys.add(b);
			r.pointers_tn.add(0,a);
			r.pointers_tn.add(1,c);
			a.parent = r;
			c.parent = r;
			root = r;
			root.leaf = false;
			return;
		}
		
		
		
		treenode p = a.parent;
		System.out.println("p.key.get0"+p.keys.get(0));
		if(p.keys.size()>1)
			System.out.println("p.key.get0"+p.keys.get(1));
		
		
		if(p.pointers_tn.size()<N)
		{
			System.out.println("N");
			int index = 0;
			
			for(int i=0;i<p.pointers_tn.size();i++)
			{
				
				if(p.pointers_tn.get(i)==a)
				{
					index=i;
					break;
				}
				
			}
			
			System.out.println("O");
			p.pointers_tn.add(index+1,c);
			p.keys.add(index,b);
			c.parent = p;
		}
		
		else	//splitttt
		{
			System.out.println("P");
			treenode t = new treenode();
			
			ArrayList<String> keylist = new ArrayList<String>(p.keys);
			ArrayList<treenode> pointerlist = new ArrayList<treenode>(p.pointers_tn);
			
			t.keys = keylist;
			t.pointers_tn = pointerlist;
//			
//			for(int i=0;i<N-1;i++)
//			{
//				t.keys.add(p.keys.get(i));
//				t.pointers_tn.add(p.pointers_tn.get(i));
//			}
//			
//			t.pointers_tn.add(p.pointers_tn.get(N-1));

			
			int index = 0;
			System.out.println("uooo"+p.pointers_tn.size());
			for(int i=0;i<p.pointers_tn.size();i++)
			{
				if(p.pointers_tn.get(i)==a)
				{
					index=i;
					System.out.println("HELLOOO"+i);
					break;
				}
			}
			
			System.out.println("Q");
			t.pointers_tn.add(index+1,c);
			//c.parent = t;
			t.keys.add(index,b);
			
			
			p.keys = null;
			p.keys = new ArrayList<String>();
			p.pointers_tn = null;
			p.pointers_tn = new ArrayList<treenode>();
			
			treenode temp = new treenode();
		
			System.out.println("yoyo"+((int)Math.ceil((float)N/2)));
			
			for(int i=0;i<((int)Math.ceil((float)N/2) - 1);i++)
			{
				p.keys.add(t.keys.get(i));
				p.pointers_tn.add(t.pointers_tn.get(i));
	 			t.pointers_tn.get(i).parent = p;
			}
			
			p.pointers_tn.add(t.pointers_tn.get((int)Math.ceil((float)N/2)-1));
			t.pointers_tn.get((int)Math.ceil((float)N/2)-1).parent = p;
			
			String key = t.keys.get((int)Math.ceil((float)N/2) - 1);
			
			for(int i=((int)Math.ceil((float)N/2));i<N;i++)
			{
				temp.keys.add(t.keys.get(i));
				temp.pointers_tn.add(t.pointers_tn.get(i));
				t.pointers_tn.get(i).parent = temp;
			}
			
			temp.pointers_tn.add(t.pointers_tn.get(N));
			t.pointers_tn.get(N).parent = temp;
			
			System.out.println("R");
			insert_in_parent(p, key, temp);
		}
	}
	
	
	public void delete(record x)
	{
		String value;
		if(field.equals("Instructor ID"))
		{
			value = x.instructor_id;			
		}
			
		else if(field.equals("Instructor Name"))
		{
			value = x.instructor_name;
		}
			
		else if(field.equals("Department"))
		{
			value = x.department;
		}
			
		else
		{
			value = x.salary+"";
		}
		
		findret obj =find(value);
		delete_entry(obj.r,value);
	}
	
	public void delete_entry(treenode r, String value)
	{
		int index = r.keys.indexOf(value);
		r.keys.remove(index);
		
		if(r.leaf==true)
		{
			for(int i=0;i<r.pointers_r.get(index).size();i++)
			{
				record rec =r.pointers_r.get(index).get(i);
				rec.validity = "0000";
			}
			r.pointers_r.remove(index);
		}		
		else
		{
			r.pointers_tn.remove(index);
		}
		
		if(root==r && (r.pointers_tn.size()==1))
		{
			root = r.pointers_tn.get(0);
			root.parent = null ;
		}
		else if ((r.leaf==true && r.keys.size() < Math.ceil(((double)N-1)/2)) || (r.leaf!=true && r.pointers_tn.size() < Math.ceil((double)N/2)))	
		{
			treenode temp;
			treenode parent = r.parent ;
			String k;
			if(parent.pointers_tn.get(parent.pointers_tn.size()-1)==r)
			{
				temp = parent.pointers_tn.get(parent.pointers_tn.size()-2);
				k =  parent.keys.get(parent.pointers_tn.size()-2);
			}			
			else
			{
				temp = parent.pointers_tn.get(parent.pointers_tn.indexOf(r)+1);
				k = parent.keys.get(parent.pointers_tn.indexOf(r));
			}
			
			if((r.leaf == true && (r.pointers_r.size()+temp.pointers_r.size()<N)) || (r.leaf == false && (r.pointers_tn.size()+temp.pointers_tn.size()<N)))
			{
				if(parent.pointers_tn.indexOf(r)<parent.pointers_tn.indexOf(temp))
				{
					treenode node = r;
					r = temp ;
					temp = node;
				}
				
				if(r.leaf == false)
				{
					temp.keys.add(k);
					for(int i=0;i<r.keys.size();i++)
					{
						temp.keys.add(r.keys.get(i));
						
					}
					for(int i=0;i<r.pointers_tn.size();i++)
					{
						temp.pointers_tn.add(r.pointers_tn.get(i));						
					}					
					
				}
				
				else
				{
					for(int i=0;i<r.keys.size();i++)
					{
						temp.keys.add(r.keys.get(i));
						
					}
					for(int i=0;i<r.pointers_tn.size();i++)
					{
						temp.pointers_r.add(r.pointers_r.get(i));						
					}
					
					temp.right_leaf = r.right_leaf;
				}
				
				delete_entry(parent,k);
				r = null;				
			}
			
			else
			{
				if(parent.pointers_tn.indexOf(temp)<parent.pointers_tn.indexOf(r))
				{ 
					if(r.leaf == false)
					{
						int m = temp.pointers_tn.size()-1;
						treenode node = temp.pointers_tn.get(m);
						String key = temp.keys.get(m-1);
						temp.keys.remove(m-1);
						temp.pointers_tn.remove(m);
						r.pointers_tn.add(0,node);
						r.keys.add(0,k);
						
						int a = parent.keys.indexOf(k);
						parent.keys.set(a,key);
					}
					
					else
					{
						int m = temp.pointers_r.size()-1;
						ArrayList<record> rec = temp.pointers_r.get(m);
						String key = temp.keys.get(m);
						temp.pointers_r.remove(m);
						temp.keys.remove(m);
						
						r.pointers_r.add(0,rec);
						r.keys.add(0,key);
						
						int a = parent.keys.indexOf(k);
						parent.keys.set(a,key);
						
					}
				}
				
				else
				{
					if(temp.leaf == false)
					{
						int m = r.pointers_tn.size()-1;
						treenode node = r.pointers_tn.get(m);
						String key = r.keys.get(m-1);
						r.keys.remove(m-1);
						r.pointers_tn.remove(m);
						temp.pointers_tn.add(0,node);
						temp.keys.add(0,k);
						
						int a = parent.keys.indexOf(k);
						parent.keys.set(a,key);
					}
					
					else
					{
						int m = r.pointers_r.size()-1;
						ArrayList<record> rec = r.pointers_r.get(m);
						String key = r.keys.get(m);
						r.pointers_r.remove(m);
						r.keys.remove(m);
						
						temp.pointers_r.add(0,rec);
						temp.keys.add(0,key);
						
						int a = parent.keys.indexOf(k);
						parent.keys.set(a,key);
						
					}
				}
			}
		}
	}
	
	public int compare(String obj1, String obj2)
	{
		if(obj1.equals(obj2) == true)
		{
			return 0;
		}
		
		else
		{
			
			if (field.equals("Salary"))
			{
				double one = Double.parseDouble(obj1);
				double two = Double.parseDouble(obj2);
				
				if(one > two)
					return 1;
				else
					return -1;
			}
			
			else
			{
				return (obj1.compareTo(obj2));							
			}
		}
	}
}

class treenode implements Serializable
{	
	ArrayList<String> keys;
	boolean leaf;
	
	ArrayList<treenode> pointers_tn; 
	ArrayList<ArrayList<record>> pointers_r;
	treenode right_leaf;
	treenode parent;
	
	public treenode()
	{
		keys = new ArrayList<String>();
		pointers_tn = new ArrayList<treenode>();
		pointers_r = new ArrayList<ArrayList<record>>();
//		for(int i = 0; i<10; i++)
//		{
//			pointers_r.add(i, new ArrayList<record>());
//		}
		
//		System.out.println("size is: "+pointers_r.size());
			
//		leaf = true;
	}
}


class record implements Serializable
{
	String validity;		//4
	String instructor_id;	//4
	String instructor_name; //20
	String department;		//10
	double salary;			//10
	
	public record(String a, String b, String c, String d, double e)
	{
		validity = a;
		instructor_id = b;
		instructor_name = c;
		department = d;
		salary = e;
	}
	
	public String toString()
	{
		return ("Validity: "+validity+", Instructor ID: "+instructor_id+", Instructor Name: "+instructor_name+", Department: "+department+", Salary: "+salary);
	}
}



 
public class Assignment3
{
	ArrayList<record> database;
	Btree tree;
	ArrayList<record> recList;
	
	public void create_database()
	{
		BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader("data.csv"));
            
            recList = new ArrayList<record>();
        
            String line = "";

            while ((line = br.readLine()) != null) 
            {
                String[] recDetails = line.split(",");
                
                if(recDetails.length > 0 )
                {
                    record rec = new record(recDetails[0],recDetails[1],recDetails[2],recDetails[3],Double.parseDouble(recDetails[4]));
                    tree.insert(rec);
                    recList.add(rec);
                }
            }
        }
        
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        finally
        {
            try
            {
                br.close();
            }
            catch(IOException ie)
            {
                System.out.println("Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }
	}
	
	
	public static void serialize(Btree p) throws IOException 
	{
		ObjectOutputStream out = null;
		try 
		{
			out = new ObjectOutputStream (new FileOutputStream("Btree.txt"));
			out.writeObject(p);
		}
		
		finally 
		{
			out.close();
		}
	}
	
	
	public static Btree deserialize() throws IOException, ClassNotFoundException 
	{
		ObjectInputStream in = null;
		try 
		{
			in = new ObjectInputStream (new FileInputStream("Btree.txt"));
			Btree p = (Btree) in.readObject();
			return p;
		} 
		
		finally 
		{
			in.close();
		}
	}

	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		R.init(System.in);
		System.out.println("Enter field for B+tree: ");

		Assignment3 obj = new Assignment3();	
		
		String field = R.reader.readLine();
		obj.tree = new Btree();
		
		obj.tree.field = field;
	
		
		obj.database = new ArrayList<record>();
		obj.create_database();
	
		//serialize(obj.recList);
		//List<record> fullist = deserialize();
		
		obj.tree.allrec = obj.recList;
		obj.tree.db = new ArrayList<record>(obj.database);
		obj.tree.shallowdb = obj.database;
		serialize(obj.tree);
	
		int choose = -1;
		do
		{
			
			Btree tree = deserialize();
			
			System.out.println("Choose operation to perform:");
			System.out.println("1. Find");
			System.out.println("2. PrintAll");
			System.out.println("3. Find in Range");
			System.out.println("4. Insert");
			System.out.println("5. Delete");
			System.out.println("6. Exit");
			
			
			choose = R.nextInt();
			
			
			switch(choose)
			{
				case 1: System.out.println("Enter value of search key to find: ");
						String tofind = R.reader.readLine();
						findret o = tree.find(tofind);
						if(o == null)
							System.out.println("Value not found");
						else
						{
							System.out.println("Value was found");
							//record display
						}
						break;
						
				
				case 2: System.out.println("Enter value of search key to print records: ");
						String toprintall = R.reader.readLine();
						tree.printAll(toprintall);
						break;
				
				case 3: System.out.println("Enter lower limit value of Range: ");
						String low = R.reader.readLine();
						System.out.println("Enter higher limit value of Range: ");
						String high = R.reader.readLine();
						tree.FindRange(low, high);
						break;
				
				case 4: System.out.println("Enter Details of record to insert:");
						System.out.println("Enter validity: ");
						String a = R.reader.readLine();
						System.out.println("Enter Instructor ID: ");
						String b = R.reader.readLine();
						System.out.println("Enter Instructor Name: ");
						String c = R.reader.readLine();
						System.out.println("Enter Department: ");
						String d = R.reader.readLine();
						System.out.println("Enter Salary: ");
						Double e = R.nextDouble();
						
						record x = new record(a,b,c,d,e);
						tree.insert(x);
						System.out.println("Successfully inserted record");
						break;
				
				case 5: System.out.println("Enter details of record to delete: "); 
						System.out.println("Enter validity: ");
						String A = R.reader.readLine();
						System.out.println("Enter Instructor ID: ");
						String B = R.reader.readLine();
						System.out.println("Enter Instructor Name: ");
						String C = R.reader.readLine();
						System.out.println("Enter Department: ");
						String D = R.reader.readLine();
						System.out.println("Enter Salary: ");
						Double E = R.nextDouble();
				
						record y = new record(A,B,C,D,E);
						tree.delete(y);
						System.out.println("Successfully deleted record");
						break;
			}
			
			serialize(tree);
			
		}while(choose!=6);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		findret leaf1 = obj.tree.find("2.0");
//		System.out.println(leaf1==null);
//		System.out.println();
//		treenode leafone =leaf1.r;
//		System.out.println("jdhdkjchkwc"+obj.tree.root.keys.size());
//		System.out.println(obj.tree.root.keys.get(0));
//		
//		System.out.println(tree.root.keys.get(1));
//		System.out.println(obj.tree.root.pointers_tn.get(0).keys.get(0));
//		System.out.println(obj.tree.root.pointers_tn.get(1).keys.get(0));
//		System.out.println(obj.tree.root.pointers_tn.get(1).keys.get(1));
////		System.out.println(tree.root.pointers_tn.get(2).keys.get(0));
////		System.out.println(tree.root.pointers_tn.get(2).keys.get(1));
//		System.out.println(obj.tree.root.pointers_tn.get(0).pointers_tn.get(0).keys.get(0));
//		System.out.println(obj.tree.root.pointers_tn.get(0).pointers_tn.get(1).keys.get(0));
//		System.out.println(tree.root.pointers_tn.get(1).pointers_tn.get(0).keys.get(0));
//		System.out.println(tree.root.pointers_tn.get(1).pointers_tn.get(1).keys.get(0));
//		System.out.println(tree.root.pointers_tn.get(1).pointers_tn.get(1).keys.get(1));
//		System.out.println();
//		System.out.println(tree.root.pointers_tn.get(2).keys.get(1));
//		System.out.println(tree.root.pointers_tn.get(3).keys.get(0));
//		System.out.println(tree.root.pointers_tn.get(3).keys.get(1));
		
		//System.out.println(tree.root.keys.get(1));
//		while(leafone!=null)
//		{
//			for(int i=0;i<leafone.keys.size();i++)
//			{
//				System.out.println(leafone.keys.get(i));
//			}
//			
//			
//			leafone = leafone.right_leaf;
//		}
//		
//		System.out.println("Test printall");
//		//obj.tree.printAll("oneDept");
//		
//		System.out.println("Test findin range");
//		//obj.tree.FindRange("fiveDept", "oneDept");

 	}

}
