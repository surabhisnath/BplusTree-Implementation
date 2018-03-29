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


class Btree
{
	ArrayList<record> db;
	
	treenode root;
	String field;
	
	public findret find(String v)
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
		
		
		for(int l = 0; l<r.keys.size(); l++)
		{
			if(v.equals(r.keys.get(l)))
			{
				return new findret(r, l);
			}
		}
		
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
		
		if(field.equals("Instructor ID"))
		{
			db.sort(new OrderRecordsID());
			int i = binsearch(db,1,a,0,db.size()-1);
			findret obj = find(db.get(i).instructor_id);
			
		}
		
		else if(field.equals("Instructor Name"))
		{
			db.sort(new OrderRecordsName());
			int i = binsearch(db,1,a,0,db.size()-1);
			findret obj = find(db.get(i).instructor_name);
		}
		
		else if(field.equals("Department"))
		{
			db.sort(new OrderRecordsDept());
			int i = binsearch(db,1,a,0,db.size()-1);
			findret obj = find(db.get(i).department);
		}

		else if(field.equals("Salary"))
		{
			db.sort(new OrderRecordsSal());
			int i = binsearch(db,1,a,0,db.size()-1);
			findret obj = find(db.get(i).salary+"");
		}
		
		 
		
		
	}
	
	public void insert(record x) 
	{
		
		
		
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
					return 0;
				
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
	treenode nextleaf;
	public treenode()
	{
		keys = new ArrayList<String>();
		pointers_tn = new ArrayList<treenode>();
		pointers_r = new ArrayList<ArrayList<record>>();
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
	float salary;			//10
	
	public record(String a, String b, String c, String d, float e)
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
		//reading part, put into db
		
	}

	
	public static void main(String[] args) throws IOException
	{
		R.init(System.in);
		
		System.out.println("Enter field: ");
		
		
		String field = R.next();
		
		Btree a = new Btree();
		a.field = field;
	
		Assignment3 obj = new Assignment3();
		
		obj.database = new ArrayList<record>();
		obj.create_database();
		
		a.db = new ArrayList<record>(obj.database);
		
		
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


