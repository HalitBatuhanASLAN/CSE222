package Structure;
import java.lang.Math;
public class GTUHashMap<K, V>
{
    private final int HASH_BASE = 31; 
    private final int INITIAL_CAPACITY = 10;
    private int capacity = INITIAL_CAPACITY;
    private Entry<K, V>[] table;
    private int size;
    
    @SuppressWarnings("unchecked")
    public GTUHashMap()
    {
        table = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
        for (int i = 0; i < INITIAL_CAPACITY; i++)
        {
            table[i] = new Entry<>(null, null);
        }
    }
    
    public void put(K key, V value) 
    {
        if(size == capacity)
            reallocate();
        int indexValue = hashCalculater(key) % capacity;
        put(key, value, indexValue, indexValue,0);
        System.out.println("index for " + key + " is " + indexValue);
    } //use linear probing here. Quadratic for the bonus.
    
    /*private void put(K key,V value,int startIndex,int indexValue,int probeNum)
    {
        if(table[indexValue] == null)
        {
            table[indexValue] = new Entry<>(key, value);
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
            indexValue = (startIndex + probeNum*probeNum) % capacity;
            put(key,value,startIndex,indexValue,probeNum);
        }
    }*/

    private void put(K key,V value,int startIndex,int indexValue,int probeNum)
    {
        while(true)
        {
            if(table[indexValue].key == null)
            {
                table[indexValue] = new Entry<>(key, value);
                size++;
                return;
            }
            else if(table[indexValue].key == key)
            {
                table[indexValue].key = key;
                table[indexValue].value = value;
                return;
            }
            else
            {
                probeNum++;
                indexValue = (startIndex + probeNum*probeNum) % capacity;
            }        
        }
    }

    @SuppressWarnings("unchecked")
    private void reallocate()
    {
        Entry<K,V>[] previousTable = table;
        int oldCapacity = capacity;
        capacity *= 2;
        table = (Entry<K,V>[]) new Entry[capacity];
        for (int i = 0; i < capacity; i++)
        {
            table[i] = new Entry<>(null, null);
        }
        for(int i = 0;i<oldCapacity;i++)
        {
            Entry<K,V> element = previousTable[i];
            if (element.key != null && !element.isDeleted)
            {
                int indexValue = hashCalculater(element.key) % capacity;
                put(element.key, element.value, indexValue, indexValue,0);
            }
        }
    }

    public V get(K key)
    {
        if(key == null)
            return null;
        else if(containsKey(key))
        {
            int indexValue = hashCalculater(key) % capacity;
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
            indexValue = (startIndex + probeNum*probeNum) % capacity;
            return get(key,startIndex,indexValue,probeNum);
        }
    }

    public void remove(K key)
    {
        if(containsKey(key))
        {
            int indexValue = hashCalculater(key) % capacity;
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
            indexValue = (startIndex + probeNum*probeNum) % capacity;
            remove(key,startIndex,indexValue,probeNum);
        }
    }

    public boolean containsKey(K key)
    {
        int hashValue = hashCalculater(key);
        int indexValue = hashValue % capacity;
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
            indexValue = (startIndex + probeNum*probeNum) % capacity;
            return containsKey(key,startIndex,indexValue,probeNum);
        }
    }

    //check for key is null
    private int hashCalculater(K key)
    {
        if(key == null)
            return 0;
        int hashValue = 0;
        /*int counter = 0;
        String keyString = key.toString();
        for(char letter : keyString.toCharArray())
        {
            counter++;
            hashValue += letter * Math.pow(HASH_BASE,keyString.length()-counter);
        }*/
        hashValue = Math.abs(key.hashCode());
        return hashValue;
    }
    
    public int size()
        { return size; }
}