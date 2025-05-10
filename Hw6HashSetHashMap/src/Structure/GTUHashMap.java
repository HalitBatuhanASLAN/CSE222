package Structure;
import java.lang.Math;
public class GTUHashMap<K, V>
{
    //private final int HASH_BASE = 31; 
    
    private final int INITIAL_CAPACITY = 20089;
    private final double LOAD_FACTOR = 0.75;
    private int capacity;
    private Entry<K, V>[] table;
    private int size;
    
    @SuppressWarnings("unchecked")
    public GTUHashMap()
    {
        table = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
        size = 0;
    }
    
    public void put(K key, V value) 
    {
        if((double)size/capacity >= LOAD_FACTOR)
            reallocate();
        int indexValue = hashCalculater(key);
        put(key, value, indexValue, indexValue,0);
        System.err.println("**********" + size + "***************");
    }
    
    private void put(K key,V value,int startIndex,int indexValue,int probeNum)
    {
        while(true)
        {
            if(table[indexValue] == null || table[indexValue].isDeleted)
            {
                table[indexValue] = new Entry<>(key, value);
                size++;
                return;
            }
            else if(table[indexValue].key.equals(key))
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
        capacity = nextPrime(capacity);
        table = new Entry[capacity];
        size = 0;
        for(int i = 0;i<oldCapacity;i++)
        {
            Entry<K,V> element = previousTable[i];
            if (element != null && !element.isDeleted)
                put(element.key,element.value);
        }
    }

    public V get(K key)
    {
        if(key == null)
            return null;
        else if(containsKey(key))
        {
            int indexValue = hashCalculater(key);
            return get(key,indexValue,indexValue,0);
        }
        else
            return null;
    }
    
    private V get(K key,int startIndex,int indexValue,int probeNum)
    {
        while(true)
        {
            if(table[indexValue] == null)
                return null;
            else if(table[indexValue].key.equals(key))
                return table[indexValue].value;
            else
            {
                probeNum++;
                indexValue = (startIndex + probeNum*probeNum) % capacity;
            }
        }
    }
 
    public void remove(K key)
    {
        if(containsKey(key))
        {
            int indexValue = hashCalculater(key);
            remove(key, indexValue, indexValue, 0);
        }
        else
            System.out.println("We could not find element in table");
    }

    private void remove(K key,int startIndex,int indexValue,int probeNum)
    {
        while(true)
        {
            if(table[indexValue].key.equals(key))
                table[indexValue].isDeleted = true;
            else
            {
                probeNum++;
                indexValue = (startIndex + probeNum*probeNum) % capacity;
            }
        }
    }

    public boolean containsKey(K key)
    {
        int hashValue = hashCalculater(key);
        int indexValue = hashValue;
        return containsKey(key,indexValue,indexValue,0);
    }

    private boolean containsKey(K key,int startIndex,int indexValue,int probeNum)
    {
        while(true)
        {
            if(table[indexValue] == null)
                return false;
            else if(table[indexValue].key.equals(key))
                return true;
            else
            {
                probeNum++;
                indexValue = (startIndex + probeNum*probeNum) % capacity;
            }
        }
    }


    private int hashCalculater(K key)
    {
        if(key == null)
            return 0;
        int hashValue = 0;
    
        /*
        int counter = 0;
        String keyString = key.toString();
        for(char letter : keyString.toCharArray())
        {
            counter++;
            hashValue += letter * Math.pow(HASH_BASE,keyString.length()-counter);
        }
        hashValue = Math.abs(hashValue % capacity);
        hashValue = hashValue % capacity;
        if(hashValue < 0)
            hashValue += capacity;
        */    
        

        hashValue = Math.abs(key.hashCode() % capacity);
        
        return hashValue;
    }
    
    public int size()
        { return size; }

    private int nextPrime(int n)
    {
        while (!isPrime(n))
            n++;
        return n;
    }

    private boolean isPrime(int n)
    {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6)
        {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }

    public GTUArrayList<K> keySet()
    {
        GTUArrayList<K> keys = new GTUArrayList<>();
        for (Entry<K, V> entry : table)
            if (entry != null && !entry.isDeleted)
                keys.add(entry.key);
        return keys;
    }
}