package org.tpfinal.Interfaces;

// TODO: REFACTOR SEARCH TO USE UUID. ONLY IF NECESSARY LATER ON.

public interface IntRepository<T> {
    void add(T add);
    T search(String searchField);
    void update(T toUpdate, T updated);
    void remove(T remove);
}
