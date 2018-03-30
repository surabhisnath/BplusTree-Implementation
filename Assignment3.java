import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;

class R	//Reader class
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




class OrderRecordsID implements Comparator<record>
{
	//int mode;
	@Override
	public int compare(record one, record two)
	{
		if(one.instructor_id.compareTo(two.instructor_id) > 0)
				return 1;
		else
				return -1;
	}
}


class OrderRecordsName implements Comparator<record>
{
	@Override
	public int compare(record one, record two)
	{
		if(one.instructor_name.compareTo(two.instructor_name) > 0)
			return 1;
		else
			return -1;
	}
}

class OrderRecordsDept implements Comparator<record>
{
	@Override
	public int compare(record one, record two)
	{
		if(one.department.compareTo(two.department) > 0)
			return 1;
		else
			return -1;
	}
}


class OrderRecordsSal implements Comparator<record>
{
	@Override
	public int compare(record one, record two)
	{
		if(one.salary > two.salary)
			return 1;
		else
			return -1;
	}
}






class Btree
{
	int num_nodes = 0;
	ArrayList<record> db;
	ArrayList<record> shallowdb;
	int N = 2;
	
	
	treenode root;
	String field;
	
	
	public treenode findhelp(String v)
	{
		treenode r = root;
		
		while(r.isleaf() == false)
		{
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
				r = r.pointers_tn.get(i+1);
			}
			
			else
			{
				r = r.pointers_tn.get(i);
			}
		}
		
		
		
		return r;
	}
	
	public findret find(String v)
	{
		System.out.println("jj"+v);
		treenode r = root;
		System.out.println("a");
		while(r.isleaf() == false)
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
	
	public void printAll(String v)
	{
		
		findret obj = find(v);
		treenode node = obj.r;
		int i = obj.index;
		
		if(obj == null)
			return;
			 
		for(int k=0;k<node.pointers_r.get(i).size();k++)
		{
						
			System.out.println(node.pointers_r.get(i).get(k));
		}
	}
	
	public int binsearch(ArrayList<record> arr, int index, String a,int start,int end)
	{
		 
		int mid = (start+end)/2 ;
		
		if(index==1)
		{
			if(compare(a,arr.get(start).instructor_id)<=0)
			{
				 return start ;
			}
			
			else if(compare(a,arr.get(end).instructor_id)>0)
			{
				return -1;				
			}
			
			else
			{
				  if((compare(a,arr.get(mid).instructor_id)==0))
				  {
					  return mid;
				  }
				  
				  else if((compare(arr.get(mid).instructor_id,a)<0))
				  {
					  if(mid+1 <= end && (compare(a,arr.get(mid+1).instructor_id)<=0))
					  {
						  return mid+1;
					  }
					  
					  else
					  {
						 return  binsearch(arr,index,a,mid+1,end);
					  }
				  }
				  
				  else
				  {
					  if(mid - 1 >= start && (compare(a,arr.get(mid-1).instructor_id)>0))
					  {
						  return mid;
					  }
					  
					  else
					  {
						  return   binsearch(arr,index,a,start, mid-1);
					  }
					  
				  }
			}
		}
		
		if(index==2)
		{
			if(compare(a,arr.get(start).instructor_name)<=0)
			{
				 return start ;
			}
			
			else if(compare(a,arr.get(end).instructor_name)>0)
			{
				return -1;				
			}
			
			else
			{
				  if((compare(a,arr.get(mid).instructor_name)==0))
				  {
					  return mid;
				  }
				  
				  else if((compare(arr.get(mid).instructor_name,a)<0))
				  {
					  if(mid+1 <= end && (compare(a,arr.get(mid+1).instructor_name)<=0))
					  {
						  return mid+1;
					  }
					  
					  else
					  {
						 return  binsearch(arr,index,a,mid+1,end);
					  }
				  }
				  
				  else
				  {
					  if(mid - 1 >= start && (compare(a,arr.get(mid-1).instructor_name)>0))
					  {
						  return mid;
					  }
					  
					  else
					  {
						  return   binsearch(arr,index,a,start, mid-1);
					  }
					  
				  }
			}
		}
		
		
		else if(index==3)
		{
			if(compare(a,arr.get(start).department)<=0)
			{
				 return start ;
			}
			
			else if(compare(a,arr.get(end).department)>0)
			{
				return -1;				
			}
			
			else
			{
				  if((compare(a,arr.get(mid).department)==0))
				  {
					  return mid;
				  }
				  
				  else if((compare(arr.get(mid).department,a)<0))
				  {
					  if(mid+1 <= end && (compare(a,arr.get(mid+1).department)<=0))
					  {
						  return mid+1;
					  }
					  
					  else
					  {
						 return  binsearch(arr,index,a,mid+1,end);
					  }
				  }
				  
				  else
				  {
					  if(mid - 1 >= start && (compare(a,arr.get(mid-1).department)>0))
					  {
						  return mid;
					  }
					  
					  else
					  {
						  return   binsearch(arr,index,a,start, mid-1);
					  }
					  
				  }
			}
		}
		
		
		else 
		{
			if(compare(a,arr.get(start).salary+"")<=0)
			{
				 return start ;
			}
			
			else if(compare(a,arr.get(end).salary+"")>0)
			{
				return -1;				
			}
			
			else
			{
				  if((compare(a,arr.get(mid).salary+"")==0))
				  {
					  return mid;
				  }
				  
				  else if((compare(arr.get(mid).salary+"",a)<0))
				  {
					  if(mid+1 <= end && (compare(a,arr.get(mid+1).salary+"")<=0))
					  {
						  return mid+1;
					  }
					  
					  else
					  {
						 return  binsearch(arr,index,a,mid+1,end);
					  }
				  }
				  
				  else
				  {
					  if(mid - 1 >= start && (compare(a,arr.get(mid-1).salary+"")>0))
					  {
						  return mid;
					  }
					  
					  else
					  {
						  return   binsearch(arr,index,a,start, mid-1);
					  }
					  
				  }
			}
		}
	}
	
	
	public void FindRange(String a , String b)
	{
		findret obj = null;
		if(field.equals("Instructor ID"))
		{
			db.sort(new OrderRecordsID());
			int i = binsearch(db,1,a,0,db.size()-1);
			obj = find(db.get(i).instructor_id);
		}
		
		else if(field.equals("Instructor Name"))
		{
			db.sort(new OrderRecordsName());
			int i = binsearch(db,1,a,0,db.size()-1);
			obj = find(db.get(i).instructor_name);
		}
		
		else if(field.equals("Department"))
		{
			db.sort(new OrderRecordsDept());
			int i = binsearch(db,1,a,0,db.size()-1);
			obj = find(db.get(i).department);
		}

		else if(field.equals("Salary"))
		{
			db.sort(new OrderRecordsSal());
			int i = binsearch(db,1,a,0,db.size()-1);
			obj = find(db.get(i).salary+"");
		}
		
		
		if(obj != null)
		{
			boolean flag = true;
			while(flag == true)
			{

				if(obj.index < obj.r.keys.size())
				{
					for(int j=0; j<obj.r.pointers_r.get(obj.index).size(); j++)
						System.out.println(obj.r.pointers_r.get(j));
					
					if(compare(obj.r.keys.get(obj.index+1),b)<=0)
					{
						obj.index++;
					}
					
					else
						flag = false;
				}
			
				else
				{
					if(obj.r.right_leaf!=null)
					{
						obj.r = obj.r.right_leaf;
						obj.index = 0;
					}
					
					else
						flag = false;
				}
			}
		}
		
		else
			System.out.println("No record in the given range.");
		
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
			
			if(r.keys.size()<(N-1))
			{
				System.out.println("E");
				//insert in leaf
				if(compare(r.keys.get(0), value)>0)
				{
					System.out.println("F");
					r.keys.add(0, value);
					r.pointers_r.add(0,new ArrayList<record>());
					r.pointers_r.get(0).add(x);
				}
						
				else
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
				
				if(compare(t.keys.get(0), value)>0)  
				{
					System.out.println("I");
					t.keys.add(0, value);
					t.pointers_r.add(0,new ArrayList<record>());
					t.pointers_r.get(0).add(x);
				}
						
				else
				{
					System.out.println("J");
					int y;
					for(y=0; y<t.keys.size(); y++)
					{
						if(compare(t.keys.get(y),value)>=0)
						break;
					}
							
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
				
				
				for(int h=0; h<(int)Math.ceil(N/2); h++)       
				{
					r.keys.add(h, t.keys.get(h));
					r.pointers_r.add(h,new ArrayList<record>());
					r.pointers_r.add(h, t.pointers_r.get(h));
				}
				
				for(int m=(int)Math.ceil(N/2); m<N; m++)     
				{
					newnode.keys.add(t.keys.get(m));
					newnode.pointers_r.add(new ArrayList<record>());
					newnode.pointers_r.add(t.pointers_r.get(m));
				}
				
				System.out.println("L");
				String k = newnode.keys.get(0);
				insert_in_parent(r,k,newnode);
			}
		}
	}
	
	
	public void insert_in_parent(treenode a, String b, treenode c)
	{
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
			return;
		}
		
		treenode p = a.parent;
		
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
			
		}
		
		else
		{
			System.out.println("P");
			treenode t = new treenode();
			
			for(int i=0;i<N-1;i++)
			{
				t.keys.add(p.keys.get(i));
				t.pointers_tn.add(p.pointers_tn.get(i));
			}
			
			t.pointers_tn.add(p.pointers_tn.get(N-1));
			
			
			int index = 0;
			for(int i=0;i<p.pointers_tn.size();i++)
			{
				if(p.pointers_tn.get(i)==a)
				{
					index=i;
				
					break;
				}
			}
			
			System.out.println("Q");
			t.pointers_tn.add(index+1,c);
			t.keys.add(index,b);
			
			p.keys = null;
			p.keys = new ArrayList<String>();
			p.pointers_tn = null;
			p.pointers_tn = new ArrayList<treenode>();
			
			treenode temp = new treenode();
			
			for(int i=0;i<((int)Math.ceil(N/2) - 1);i++)
			{
				p.keys.add(t.keys.get(i));
				p.pointers_tn.add(t.pointers_tn.get(i));
			}
			
			p.pointers_tn.add(t.pointers_tn.get((int)Math.ceil(N/2)));
			
			String key = t.keys.get((int)Math.ceil(N/2));
			
			for(int i=((int)Math.ceil(N/2) + 1);i<N;i++)
			{
				temp.keys.add(t.keys.get(i));
				temp.pointers_tn.add(t.pointers_tn.get(i));
			}
			
			System.out.println("R");
			insert_in_parent(p, key, temp);
		}
	}
	
	public void delete(record x)
	{
		
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
			
		leaf = isleaf();
	}
	
	public boolean isleaf()
	{
		
		for(int i=0; i<pointers_tn.size(); i++)
		{
			if(pointers_tn.get(i)!=null)
				return false;
		}
		
		return true;
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
	
	public String toString(record r)
	{
		return "Validity: "+r.validity+", Instructor ID: "+r.instructor_id+", Instructor Name: "+r.instructor_name+", Department: "+r.department+", Salary: "+r.salary;
	}
}



 
public class Assignment3 
{
	ArrayList<record> database;
	
	
	public void create_database()
	{
		//reading part, put into database
	}

	
	public static void main(String[] args) throws IOException
	{
		R.init(System.in);
		System.out.println("Enter field: ");
		
		
		String field = R.reader.readLine();
		 
		
		Btree tree = new Btree();
		tree.field = field;
	
		Assignment3 obj = new Assignment3();
		
		obj.database = new ArrayList<record>();
		obj.create_database();
		
		tree.db = new ArrayList<record>(obj.database);
		tree.shallowdb = obj.database;
//		
//		treenode parent = new treenode();
//		treenode child = new treenode();
//		parent.pointers_tn.add(child);
//		child.parent = parent;
//		
//		System.out.println(child.parent.pointers_tn.get(0)==child);
		
		record one = new record("1","oneID","oneName","oneDept",1.0);
		record two = new record("1","twoID","twoName","twoDept",2.0);
		record three = new record("1","threeID","threeName","threeDept",3.0);
		record four = new record("1","fourID","fourName","fourDept",4.0);
		record five = new record("1","fiveID","fiveName","fiveDept",5.0);
		
		tree.insert(one);
		tree.insert(two);
		tree.insert(three);
		tree.insert(four);
		tree.insert(five);
		
		findret leaf1 = tree.find("1.0");
		System.out.println(leaf1==null);
		System.out.println();
		treenode leafone =leaf1.r;
		
		while(leafone!=null)
		{
			for(int i=0;i<leafone.keys.size();i++)
			{
				System.out.println(leafone.keys.get(i));
			}
			
			leafone = leafone.right_leaf;
			
		}
		
		
 	}

}
