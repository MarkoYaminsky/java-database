package ua.com.yaminsky.bank.dao;

public interface IUpdatableDao<T, ID> extends IGeneralDao<T, ID> {
    int update(ID id, T entity);
}
