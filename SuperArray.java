//class SuperArray, now implemented with Comparable Powers!

/* Originally From:
 Team BatArray - Shaumik Ashraf, Sadia Azmine
 APCS1 - pd9
 HW#42 - Array of Titanium
 2015-12-06
*/

//Witness the BatArray! Batman's newest device! (BatmanArray)

/*****************************
 * class SuperArray --  A wrapper class for an array.
 * 
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 *  report number of meaningful items
 * 
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *  add item (at end)
 *  insert item
 *  remove item (while maintaining "left-justification")
 * 
 * Implements functionality:
 *  comparable
 *****************************/

public class SuperArray implements comparable {
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private int[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor initializes 10-item array
    public SuperArray() 
    { 
	_data = new int[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() 
    { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() 
    { 
	int[] temp = new int[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public int get( int index ) {
        return _data[index]; 
        
    }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public int set( int index, int newVal ) 
    { 
 	int temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( int newVal ) { 
        if((_size % 10) == 0) {
            expand();
        }
        _data[_lastPos + 1] = newVal;
        _lastPos += 1;
        _size += 1;
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, int newVal ) {
    	if (_size>index&&index>-1){
    		int[] b=new int[_data.length+1];
    		for(int x=0;x<index;x++){
    			b[x]=_data[x];
    		}
    		b[index]=newVal;
    		for(int x=index+1;x<_data.length;x++){
    			b[x]=_data[x-1];
    		}
    		_data=b;
    		_lastPos+=1;
    		_size+=1;
    	}
    	else {
    	    System.out.println("There is no space in SuperArray.");
    	}
    }


    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) { 
        if (index > -1) {
        for (int x = index; x < _data.length - 1; x++) {
            _data[x] = _data[x + 1];
        }
        _size -= 1;
        _lastPos -= 1;
        }
    }

    //return number of meaningful items in _data
    public int size() { 
        return _size;
    }

    //~~~~~~~~~~~~~~~~~~Comparable implementations(...)~~~~~~~~~~~~~~~

    public int linSearch(Comparable o) {
        
        try {
            for(int i=0; i<_size; i++) {
            	if( o.compareTo( this._data )==0 ) {
            		return(i);
            	}
            }
        } catch(Exception e) {
            System.err.println(e);
        }
        
    }
    
    public boolean isSorted() {
        
        boolean rb = true; //return boolean value
        
        for(int i=0; i<_size; i++) {
        	for(int j=i; i<_size; j++) {
        		rb = (rb && (_data[i].compareTo(_data[j])>0));
        	}
        }
        
        return(rb);
        
    }

    //main method for testing
    public static void main( String[] args ) 
    {
	SuperArray curtis = new SuperArray();
	System.out.println("Printing empty SuperArray curtis...");
	System.out.println(curtis);

	for( int i = 0; i < curtis._data.length; i++ ) {
	    curtis.set(i,i*2);
	    curtis._size++; //necessary bc no add() method yet
	}

	System.out.println("Printing populated SuperArray curtis...");
	System.out.println(curtis);

	System.out.println("testing get()...");
	for( int i = 0; i < curtis._size; i++ ) {
	    System.out.print( "item at index" + i + ":\t" );
	    System.out.println( curtis.get(i) );
	}

	System.out.println("Expanded SuperArray curtis:");
	curtis.expand();
	System.out.println(curtis);


	SuperArray mayfield = new SuperArray();
	System.out.println("Printing empty SuperArray mayfield...");
	System.out.println(mayfield);

	  mayfield.add(5);
	  mayfield.add(4);
	  mayfield.add(3);
	  mayfield.add(2);
	  mayfield.add(1);

	  System.out.println("Printing populated SuperArray mayfield...");
	  System.out.println(mayfield);

	  mayfield.remove(3);
	  System.out.println("Printing SuperArray mayfield post-remove...");
	  System.out.println(mayfield);
	  mayfield.remove(3);
	  System.out.println("Printing SuperArray mayfield post-remove...");
	  System.out.println(mayfield);

	  mayfield.add(3,99);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	  mayfield.add(2,88);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	  mayfield.add(1,77);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	  
	  ListInt BatmanArray = new SuperArray();
	  System.out.println(BatmanArray);
	  BatmanArray.add(5);
	  BatmanArray.add(6);
	  BatmanArray.add(7);
	  BatmanArray.add(8);
	  System.out.println(BatmanArray);
	  BatmanArray.remove(2);
	  BatmanArray.remove(2);
	  System.out.println(BatmanArray);
	  BatmanArray.add(1,9);
	  System.out.println(BatmanArray);
	  BatmanArray.add(0,10);
	  System.out.println(BatmanArray);
	  BatmanArray.add(3,11);
	  System.out.println(BatmanArray);
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	  Comparable Comps = new SuperArray();
	  
	  
	//*****INSERT ANY ADDITIONAL TEST CALLS HERE*****

    }//end main
		
}//end class
