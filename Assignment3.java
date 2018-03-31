import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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


class Btree
{
	int num_nodes = 0;
	ArrayList<record> db;
	ArrayList<record> shallowdb;
	int N = 3;
	
	
	treenode root;
	String field;
	
	
	
	
//	public static void serialize( p) throws IOException 
//	{
//		ObjectOutputStream out = null;
//		try 
//		{
//			out = new ObjectOutputStream (new FileOutputStream("./src/" + p.getplaylistname() + ".txt"));
//			out.writeObject(p);
//		}
//		
//		finally 
//		{
//			out.close();
//		}
//	}
//	
//	
//	public static Playlist deserialize(String name) throws IOException, ClassNotFoundException 
//	{
//		ObjectInputStream in = null;
//		try 
//		{
//			in = new ObjectInputStream (new FileInputStream("./src/" + name + ".txt"));
//			Playlist p = (Playlist) in.readObject();
//			return p;
//		} 
//		
//		finally 
//		{
//			in.close();
//		}
//	}
	
	
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
	
	public void insert(record x) 
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
			
			
		//          twoDept
		//   threedept	  twodept
		//onedept  threedept      
			
			
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

class treenode
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


class record
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
	
	public void create_database()
	{
		//reading part, put into database
		BufferedReader br = null;
        try
        {
            //Reading the csv file
            br = new BufferedReader(new FileReader("data.csv"));
            
            String line = "";

            //Reading from the second line
            while ((line = br.readLine()) != null) 
            {
                String[] recDetails = line.split(",");
                
                if(recDetails.length > 0 )
                {
                    record rec = new record(recDetails[0],recDetails[1],recDetails[2],recDetails[3],Double.parseDouble(recDetails[4]));
                    tree.insert(rec);
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

	
	public static void main(String[] args) throws IOException
	{
		R.init(System.in);
		System.out.println("Enter field: ");

		Assignment3 obj = new Assignment3();	
		
		String field = R.reader.readLine();
		obj.tree = new Btree();
		
		obj.tree.field = field;
	
		
		obj.database = new ArrayList<record>();
		obj.create_database();
		
		obj.tree.db = new ArrayList<record>(obj.database);
		obj.tree.shallowdb = obj.database;	
//		treenode parent = new treenode();
//		treenode child = new treenode();
//		parent.pointers_tn.add(child);
//		child.parent = parent;
//		
//		System.out.println(child.parent.pointers_tn.get(0)==child);
//		
//		record one = new record("1","oneID","oneName","oneDept",1.0);
//		record two = new record("1","twoID","twoName","twoDept",2.0);
//		record three = new record("1","threeID","threeName","threeDept",3.0);
//		record four = new record("1","fourID","fourName","fourDept",4.0);
//		record five = new record("1","fiveID","fiveName","fiveDept",5.0);
//		record six = new record("1","sixID","sixName","sixDept",6.0);
//		record seven = new record("1","fiveID","sevenName","sevenDept",7.0);
//		record eight = new record("1","fiveID","eightName","eightDept",8.0);
//		record nine = new record("1","fiveID","nineName","nineDept",9.0);
//		record ten = new record("1","fiveID","tenName","tenDept",10.0);
//		
//		
//		obj.tree.insert(one);
//		obj.tree.insert(two);
//		obj.tree.insert(three);
//		obj.tree.insert(four);
//		obj.tree.insert(five);
//		obj.tree.insert(six);
//		tree.insert(seven);
//		tree.insert(eight);
//		tree.insert(nine);
//		tree.insert(ten);
//		
		
		
//		record first = new record("1111","A","A","A",1234);
//		record second = new record("1111","B","B","B",1234);
//		record third = new record("1111","C","A","A",1234);
//		record fourth = new record("1111","D","B","B",1234);
//		record fifth= new record("1111","E","A","A",1234);
//        record six = new record("1111","F","B","B",1234);
//		record seven = new record("1111","Gb","A","A",1234);
//		record eight = new record("1111","Ga","B","B",1234);
//		record nine = new record("1111","G","B","B",1234);
//		record eleven = new record("1111","H","B","B",1234);
//		record ten = new record("1111","Freeq","B","B",1234);
//		record twel = new record("1111","I","B","B",1234);
//		record thirt = new record("1111","J","B","B",1234);
//		record fourt = new record("1111","Da","B","B",1234);
//		record fift =  new record("1111","Db","B","B",1234);
//		record sixt = new record("1111","Ca","B","B",1234);
//		record sevent =  new record("1111","Cb","B","B",1234);
//		record eightt = new record("1111","Ia","B","B",1234);
//		record ninet =  new record("1111","Ib","B","B",1234);
//		record thirty = new record("1111","Ayne","B","B",1234);
//		record thirto = new record("1111","Ona","B","B",1234);
//		
//		record sev = new record("1111","You","B","B",1234);
//		record a = new record("1111","Jone","B","B",1234);
//		record b = new record("1111","Oye","B","B",1234);
//		record c = new record("1111","TOna","B","B",1234);
//		record d = new record("1111","Tni","B","B",1234);
//		record e = new record("1111","Yiu","B","B",1234);
//		record f = new record("1111","Zoi","B","B",1234);
//		record g = new record("1111","Oyi","B","B",1234);
//		record h = new record("1111","Onab","B","B",1234);
//		record p = new record("1111","Tani","B","B",1234);
//		record y = new record("1111","Bob","B","B",1234);
//		record z = new record("1111","Cat","B","B",1234);
//		record k = new record("1111","Dark","B","B",1234);
//		record l = new record("1111","Abiee","B","B",1234);
//		record q = new record("1111","Cani","B","B",1234);
//		record r = new record("1111","Dob","B","B",1234);
//		record s = new record("1111","Dat","B","B",1234);
//		record t = new record("1111","Cark","B","B",1234);
//		record u = new record("1111","Dbiee","B","B",1234);
//		record A = new record("1111","Aone","B","B",1234);
//		record B = new record("1111","Aye","B","B",1234);
//		record C = new record("1111","AOna","B","B",1234);
//		record D = new record("1111","Ani","B","B",1234);
//		record E = new record("1111","Aiu","B","B",1234);
//		record F = new record("1111","Eoi","B","B",1234);
//		record G = new record("1111","Eyi","B","B",1234);
//		record H = new record("1111","Enab","B","B",1234);
//		record P = new record("1111","Eani","B","B",1234);
//		record Y = new record("1111","Eob","B","B",1234);
//		record Z = new record("1111","Yat","B","B",1234);
//		record K = new record("1111","Yark","B","B",1234);
//		record L = new record("1111","YAbiee","B","B",1234);
//		record Q = new record("1111","Yani","B","B",1234);
//		record R = new record("1111","Ydob","B","B",1234);
//		record S = new record("1111","Zat","B","B",1234);
//		record T = new record("1111","Zark","B","B",1234);
//		record U = new record("1111","Zbiee","B","B",1234);
//		record W = new record("1111","Zani","B","B",1234);
//		record N = new record("1111","Zdob","B","B",1234);
//		record twe = new record("1111","TZani","B","B",1234);
//		record tweo = new record("1111","Tdob","B","B",1234);
//		record first1 = new record("1111","Tanya","A","A",1234);
//		record second1 = new record("1111","Aarushi","B","B",1234);
//		record third1 = new record("1111","Aishwarya","A","A",1234);
//		record fourth1 = new record("1111","4","B","B",1234);
//		record fifth1= new record("1111","5","A","A",1234);
//		record six1 = new record("1111","6","6","B",1234);
//		record seven1 = new record("1111","7","A","A",1234);
//		record eight1 = new record("1111","8","B","B",1234);
//		record nine1 = new record("1111","9","B","B",1234);
//		record eleven1 = new record("1111","10","B","B",1234);
//		record ten1 = new record("1111","11","B","B",1234);
//		record twel1 = new record("1111","I3","B","B",1234);
//		record thirt1 = new record("1111","J5","B","B",1234);
//		record fourt1 = new record("1111","K5","B","B",1234);
//		record fift1 = new record("1111","Ay4ne","B","B",1234);
//		record sixt1 = new record("1111","Onka","B","B",1234);
//		record sev1 = new record("1111","Yoklu","B","B",1234);
//		record a1 = new record("1111","Jonme","B","B",1234);
//		record b1 = new record("1111","Oymke","B","B",1234);
//		record c1 = new record("1111","TgkOna","B","B",1234);
//		record d1 = new record("1111","Tnji","B","B",1234);
//		record e1 = new record("1111","Yijgu","B","B",1234);
//		record f1 = new record("1111","Zjhoi","B","B",1234);
//		record g1 = new record("1111","jfOyi","B","B",1234);
//		record h1 = new record("1111","Onhfab","B","B",1234);
//		record p1 = new record("1111","Thkani","B","B",1234);
//		record y1 = new record("1111","Bklhob","B","B",1234);
//		record z1 = new record("1111","Cklat","B","B",1234);
//		record k1 = new record("1111","Dkrgark","B","B",1234);
//		record l1 = new record("1111","Akfhlbiee","B","B",1234);
//		record q1 = new record("1111","Ckfhani","B","B",1234);
//		record r1 = new record("1111","Dhkfob","B","B",1234);
//		record s1 = new record("1111","Dkgat","B","B",1234);
//		record t1 = new record("1111","Cklfark","B","B",1234);
//		record u1 = new record("1111","Dlfbiee","B","B",1234);
//		record A1 = new record("1111","Alfone","B","B",1234);
//		record B1 = new record("1111","Alfye","B","B",1234);
//		record C1 = new record("1111","AlOna","B","B",1234);
//		record D1 = new record("1111","Alfni","B","B",1234);
//		record E1 = new record("1111","Ahkiu","B","B",1234);
//		record F1 = new record("1111","gEoi","B","B",1234);
//		record G1 = new record("1111","Eygi","B","B",1234);
//		record H1 = new record("1111","Enfjab","B","B",1234);
//		record P1 = new record("1111","Ekggfani","B","B",1234);
//		record Y1 = new record("1111","Egfob","B","B",1234);
//		record Z1 = new record("1111","Ygkat","B","B",1234);
//		record K1 = new record("1111","Ylfark","B","B",1234);
//		record L1 = new record("1111","YgfAbiee","B","B",1234);
//		record Q1 = new record("1111","Ykgani","B","B",1234);
//		record R1 = new record("1111","Yjgfdob","B","B",1234);
//		record S1 = new record("1111","Zklffgat","B","B",1234);
//		record T1 = new record("1111","Zagkfflkrk","B","B",1234);
//		record U1 = new record("1111","Zkfkgfbiee","B","B",1234);
//		record W1 = new record("1111","Zgkani","B","B",1234);
//		record N1 = new record("1111","Zgfjdob","B","B",1234);
//		record twe1 = new record("1111","Tgfani","B","B",1234);
//		record tweo1 = new record("1111","Tgfdob","B","B",1234);
//		
//		
//		
//		tree.insert(first);
//		tree.insert(second);
//		tree.insert(third);
//		tree.insert(fourth);
//		tree.insert(fifth);
//		tree.insert(six);
//		tree.insert(nine);
//		tree.insert(eleven);
//		tree.insert(twel);
//		tree.insert(thirt);
//		tree.insert(fourt);
//		tree.insert(fift);
//		tree.insert(sixt);
//		tree.insert(sev);
//		tree.insert(a);
//		tree.insert(b);
//		tree.insert(c);
//		tree.insert(d);
//		tree.insert(e);
//		tree.insert(f);
//		tree.insert(g);
//		tree.insert(h);
//		tree.insert(p);
//		tree.insert(y);
//		tree.insert(z);
//		tree.insert(k);
//		tree.insert(l);
//		tree.insert(q);
//		tree.insert(fift);
//		tree.insert(sixt);
//		tree.insert(sevent);
//		tree.insert(seven);
//		tree.insert(eight);
//		tree.insert(ninet);
//		tree.insert(eightt);
//		tree.insert(r);
//		tree.insert(s);
//		tree.insert(t);
//		tree.insert(u);
//		tree.insert(a);
//		tree.insert(B);
//		tree.insert(C);
//		tree.insert(D);
//		tree.insert(E);
//		tree.insert(F);
//		tree.insert(G);
//		tree.insert(H);
//		tree.insert(P);
//		tree.insert(Y);
//		tree.insert(Z);
//		tree.insert(K);
//		tree.insert(L);
//		tree.insert(Q);
//		tree.insert(R);
//		tree.insert(S);
//		tree.insert(T);
//		tree.insert(U);
//		tree.insert(W);
//		tree.insert(N);
//		tree.insert(twe);
//		tree.insert(tweo);
//		tree.insert(first);
//		tree.insert(second);
//		tree.insert(third);
//		tree.insert(fourth);
//		tree.insert(fifth);
//		tree.insert(six);
//		tree.insert(seven);
//		tree.insert(eight);
//		tree.insert(nine);
//		tree.insert(eleven);
//		tree.insert(twel);
//		tree.insert(thirt);
//		tree.insert(fourt);
//		tree.insert(fift);
//		tree.insert(sixt);
//		tree.insert(sev);
//		tree.insert(a);
//		tree.insert(b);
//		tree.insert(c);
//		tree.insert(d);
//		tree.insert(e);
//		tree.insert(f);
//		tree.insert(g);
//		tree.insert(h);
//		tree.insert(p);
//		tree.insert(y);
//		tree.insert(z);
//		tree.insert(k);
//		tree.insert(l);
//		tree.insert(q);
//		tree.insert(r);
//		tree.insert(s);
//		tree.insert(t);
//		tree.insert(u);
//		tree.insert(a);
//		tree.insert(B);
//		tree.insert(C);
//		tree.insert(D);
//		tree.insert(E);
//		tree.insert(F);
//		tree.insert(G);
//		tree.insert(H);
//		tree.insert(P);
//		tree.insert(Y);
//		tree.insert(Z);
//		tree.insert(K);
//		tree.insert(L);
//		tree.insert(Q);
//		tree.insert(R);
//		tree.insert(S);
//		tree.insert(T);
//		tree.insert(U);
//		tree.insert(W);
//		tree.insert(N);
//		tree.insert(twe);
//		tree.insert(tweo);
//		tree.insert(first1);
//		tree.insert(second1);
//		tree.insert(third1);
//		tree.insert(fourth1);
//		tree.insert(fifth1);
//		tree.insert(six1);
//		tree.insert(seven1);
//		tree.insert(eight1);
//		tree.insert(ten1);
//		tree.insert(nine1);
//		tree.insert(eleven1);
//		tree.insert(twel1);
//		tree.insert(thirt1);
//		tree.insert(fourt1);
//		tree.insert(fift1);
//		tree.insert(sixt1);
//		tree.insert(sev1);
//		tree.insert(a1);
//		tree.insert(b1);
//		tree.insert(c1);
//		tree.insert(d1);
//		tree.insert(e1);
//		tree.insert(f1);
//		tree.insert(g1);
//		tree.insert(h1);
//		tree.insert(p1);
//		tree.insert(y1);
//		tree.insert(z1);
//		tree.insert(k1);
//		tree.insert(l1);
//		tree.insert(q1);
//		tree.insert(r1);
//		tree.insert(s1);
//		tree.insert(t1);
//		tree.insert(u1);
//		tree.insert(a1);
//		tree.insert(B1);
//		tree.insert(C1);
//		tree.insert(D1);
//		tree.insert(E1);
//		tree.insert(F1);
//		tree.insert(G1);
//		tree.insert(H1);
//		tree.insert(P1);
//		tree.insert(Y1);
//		tree.insert(Z1);
//		tree.insert(K1);
//		tree.insert(L1);
//		tree.insert(Q1);
//		tree.insert(R1);
//		tree.insert(S1);
//		tree.insert(T1);
//		tree.insert(U1);
//		tree.insert(W1);
//		tree.insert(N1);
//		tree.insert(twe1);
//		tree.insert(tweo1);
//		tree.insert(ten);
//		tree.insert(thirty);
//		tree.insert(thirto);

	
		
		
		findret leaf1 = obj.tree.find("twoDept");
		System.out.println(leaf1==null);
		System.out.println();
		treenode leafone =leaf1.r;
		System.out.println("jdhdkjchkwc"+obj.tree.root.keys.size());
		System.out.println(obj.tree.root.keys.get(0));
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
		while(leafone!=null)
		{
			for(int i=0;i<leafone.keys.size();i++)
			{
				System.out.println(leafone.keys.get(i));
			}
			
			
			leafone = leafone.right_leaf;
			
		}
		
		System.out.println("Test printall");
		obj.tree.printAll("oneDept");
		
		System.out.println("Test findin range");
		obj.tree.FindRange("fiveDept", "oneDept");
		
 	}

}
