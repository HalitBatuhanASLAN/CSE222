import java.lang.Math;
public class GTUHashMap<K, V>
{
    private final int HASH_BASE = 31; 
    private final int INITIAL_CAPACITY = 10;
    private Entry<K, V>[] table;
    private int size;
    
    public GTUHashMap()
    {
        table = new Entry[INITIAL_CAPACITY];
        size = INITIAL_CAPACITY;
    }
    
    public void put(K key, V value) 
    {
        if(size == table.length)
            reallocate();
        int indexValue = hashCalculater(key) % size;
        put(key, value, indexValue, indexValue,0);
    } //use linear probing here. Quadratic for the bonus.
    
    private void put(K key,V value,int startIndex,int indexValue,int probeNum)
    {
        if(table[indexValue].key == null)
        {
            table[indexValue].key = key;
            table[indexValue].value = value;
            size++;
        }
        else if(table[indexValue] == key)
        {
            table[indexValue].key = key;
            table[indexValue].value = value;
        }
        else
        {
            probeNum++;
            indexValue = (startIndex + probeNum*probeNum) % size;
            put(key,value,startIndex,indexValue,probeNum);
        }
    }

    private void reallocate()
    {
        Entry<K,V>[] previousTable = table;
        int oldSize = size;
        size *= 2;
        table = (Entry<K,V>[]) new Entry[size];
        for(int i = 0;i<oldSize;i++)
        {
            Entry<K,V> element = previousTable[i];
            put(element.key,element.value);
        }
    }

    public V get(K key)
    {
        if(key == null)
            return null;
        else if(containsKey(key))
        {
            int indexValue = hashCalculater(key) % size;
            return get(key,indexValue,indexValue,0);
        }
        else
            return null;
    }
    
    private V get(K key,int startIndex,int indexValue,int probeNum)
    {
        if(table[indexValue].key == null)
            return null;
        else if(table[indexValue] == key)
            return table[indexValue].value;
        else
        {
            probeNum++;
            indexValue = (startIndex + probeNum*probeNum) % size;
            return get(key,startIndex,indexValue,probeNum);
        }
    }

    public void remove(K key)
    {
        if(containsKey(key))
        {
            int indexValue = hashCalculater(key) % size;
            remove(key, indexValue, indexValue, 0);
        }
        else
            System.out.println("We could not find element in table");
    } // mark as deleted (tombstone)
    
    private void remove(K key,int startIndex,int indexValue,int probeNum)
    {
        if(table[indexValue] == key)
            table[indexValue].isDeleted = true;
        else
        {
            probeNum++;
            indexValue = (startIndex + probeNum*probeNum) % size;
            remove(key,startIndex,indexValue,probeNum);
        }
    }

    public boolean containsKey(K key)
    {
        int hashValue = hashCalculater(key);
        int indexValue = hashValue % size;
        return containsKey(key,indexValue,indexValue,0);
    }

    private boolean containsKey(K key,int startIndex,int indexValue,int probeNum)
    {
        if(table[indexValue].key == null)
            return false;
        else if(table[indexValue].key == key)
            return true;
        else
        {
            probeNum++;
            indexValue = (startIndex + probeNum*probeNum) % size;
            return containsKey(key,startIndex,indexValue,probeNum);
        }
    }

    //check for key is null
    private int hashCalculater(K key)
    {
        int hashValue = 0;
        int counter = 0;
        String keyString = key.toString();
        for(char letter : keyString.toCharArray())
        {
            counter++;
            hashValue += letter * Math.pow(HASH_BASE,keyString.length()-counter);
        }
        return hashValue;
    }
    
    public int size()
        { return size; }
}