package com.popcivilar.code.map;

/**
 * 手写HashMap
 */
public class FakedHashMap<K,V> {

    private volatile Entry<K,V>[] table;

    private volatile int count;//个数

    private volatile int entrySize; // 数组长度

    private static volatile int initSize = 16;


    public FakedHashMap() {
        this.table = new Entry[initSize];
        this.entrySize = table.length;
    }

    static class Entry<K,V>{
        private Node<K,V> node;


    }


    static class Node<K,V>{

        private K key;

        private V val;

        private Node next;

        public Node(K k, V v) {
            this.key = k;
            this.val = v;
        }
    }


    public V put(K k,V v){
        V oldVal = null;
        if(k == null){
            return putEmptyKey(v);
        }
        int hash = k.hashCode();
        int index = hash % entrySize;
        if(table[index] != null){
            Entry<K,V> entry = table[index];
            Node<K, V> node = entry.node;
            if(node != null){
                Node<K,V> tail = null;
                for(Node<K,V> p = node;p != null ; p = p.next){
                    if(p.key.hashCode() == hash && p.key.equals(k)){
                       oldVal = p.val;
                       p.val = v;
                       count++;
                       return oldVal;
                    }
                    tail = p;
                }
                if(tail != null){
                    tail.next = new Node(k,v);
                }

            }else{
                putNode(entry,node,k,v);
                oldVal = v;
            }

        }else{
            //entry == null
            Entry<K, V> newEntry = new Entry<>();
            Node node = new Node(k, v);
            newEntry.node = node;
            table[index] = newEntry;
            oldVal = v;
        }
        //todo 考虑扩容
        count++;
        return oldVal;
    }

    private void putNode(Entry<K,V> entry,Node<K,V> node, K k, V v) {
        Node<K,V> newNode = new Node<>(k,v);
        if(node != null){
            node.next = newNode;
        }else{
            entry.node = newNode;
        }
    }


    int size(){
       return count;
    }

    public V get(K k){
        if(k == null){
            return getEmptyKey(k);
        }
        int hash = k.hashCode();
        int index = hash % entrySize;
        if(table[index] != null){
            Entry<K,V> entry = table[index];
            Node<K, V> node = entry.node;
            for(Node<K,V> p = node;p != null;p = p.next){
                if(k.hashCode() == hash && k.equals(p.key)){
                    return p.val;
                }
            }
        }
        return null;
    }


    /**
     * 获取空值
     * @param k
     * @return
     */
    private V getEmptyKey(K k) {

        return  null;
    }


    /**
     * 存放空值
     * @param v
     * @return
     */
    private V putEmptyKey(V v) {

        //todo  键 空 存储策略

        return null;

    }

}
