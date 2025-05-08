package Structure;
import java.util.Iterator;
public class GTUHashSet<E> implements Iterable<E>
{
    private static final Object WORD = new Object();
    private GTUHashMap<E, Object> map;
    public GTUHashSet()
        { map = new GTUHashMap<>(); }
    public void add(E element)
        { map.put(element, WORD); }
    public void remove(E element)
        { map.remove(element); }
    public boolean contains(E element)
        { return map.containsKey(element); }
    public int size()
        { return map.size(); }



    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (E key : map.keySet())
            sb.append(key).append(", ");
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }



}