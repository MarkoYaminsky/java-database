package ua.com.yaminsky.bank.service;

public interface IUpdatableService<T, ID> extends IGeneralService<T, ID> {
    int update(ID id, T entity);
}
