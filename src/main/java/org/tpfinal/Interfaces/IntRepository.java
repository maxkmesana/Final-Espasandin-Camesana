package org.tpfinal.Interfaces;

public interface IntRepository<T> {
    void add(T add);
    T search(String username);
    void update(T toUpdate, T updated);
    void remove(T remove);
}
