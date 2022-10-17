package ua.com.yaminsky.bank.controller;

public interface IUpdatableController<T, ID> extends IGeneralController<T, ID> {
    int update(ID id, T entity);
}
