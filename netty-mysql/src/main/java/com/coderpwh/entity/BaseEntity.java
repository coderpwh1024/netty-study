package com.coderpwh.entity;

public interface BaseEntity<D> extends ToData<D> {


    Long getId();

    void setId(Long id);

}
